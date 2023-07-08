package Esercizio01;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
import java.util.function.Supplier;

import org.apache.commons.io.FileUtils;

import com.github.javafaker.Faker;

public class GestioneClasse {

	public static void main(String[] args) {
		System.out.println("Esercizio 1: Gestione degli studenti");


		Scanner input = new Scanner(System.in);
		Map<Integer, Studente> listaStudenti = new HashMap<Integer, Studente>();
		listaStudenti = creaLista();
		try {
			int scelta;
			do {
				System.out.println("Gestione Classe 2A dell'Istituto A. Volta");
				System.out.println("Menù di navigazione:");
				System.out.println("1. Stampa Lista Studenti");
				System.out.println("2. Fai una ricerca per Nome o Cognome Studente");
				System.out.println("3. Stampa Lista Studenti con voto maggiore di...");
				System.out.println("0. Esci");
				scelta = Integer.parseInt(input.nextLine());
				switch (scelta) {
				case 1:
					stampaLista(listaStudenti);
					break;
				case 2:
				case 3:
				case 0:
				default:
					System.out.println("Valore non valido");
					break;
				}

			} while (scelta != 0);

		} catch (Exception e) {
			System.out.println("Inserire valore valido");
		}



		


	


		input.close();
	}

	public static Map<Integer, Studente> creaLista() {
		Faker f = new Faker(Locale.ITALY);
		Map<Integer, Studente> lista = new HashMap<Integer, Studente>();
		Supplier<Double> randomSupMedia = () -> {
			Random rnd = new Random();
			double randomValue = 18.00 + (rnd.nextDouble() * (33.00 - 18.00));
			return Math.round(randomValue * 100.0) / 100.0;
		};

		Supplier<Integer> randomSupId = () -> {
			return f.random().nextInt(100, 999);
		};
		Supplier<Studente> studenteSup = () -> {
			return new Studente(f.name().firstName(), f.name().lastName(), randomSupMedia.get());
		};
		for (int i = 0; i < 25; i++) {
			lista.put(randomSupId.get(), studenteSup.get());
		}
		return lista;
	}

	public static void stampaLista(Map<Integer, Studente> map) {

		System.out.println("ID\tNOME\t\tCOGNOME\t\t\tMEDIA");
		for (Map.Entry<Integer, Studente> entry : map.entrySet()) {
			System.out.println(entry);
		}
		File file = new File("GestioneClasse.txt");
		try {
			FileUtils.writeStringToFile(file, "ID NOME COGNOME MEDIA"+System.lineSeparator(), "UTF-8");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (Map.Entry<Integer, Studente> entry : map.entrySet()) {
			Integer idStudente =  entry.getKey();
			String nomeStudente = entry.getValue().getNome();
			String cognomeStudente = entry.getValue().getCognome();
			double mediaStudente = entry.getValue().getmedia();
			
			String testo = idStudente + "\t" + nomeStudente + "\t" + cognomeStudente + "\t" + mediaStudente;

			try {
				FileUtils.writeStringToFile(file, testo + System.lineSeparator(), "UTF-8", true);
		} catch (IOException e) {

			e.printStackTrace();
		}
	}

	}

}