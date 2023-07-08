package Esercizio01;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
import java.util.function.Supplier;
import java.util.stream.Collectors;

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
				try {
					scelta = Integer.parseInt(input.nextLine());
				} catch (NumberFormatException e) {
					scelta = 10;
				}
				switch (scelta) {
				case 1:
					stampaLista(listaStudenti);
					System.out.println("**********");
					break;
				case 2:
					System.out.println("Inserire Nome o Cognome dello studente");
					String studente = input.nextLine();
					ricerca(listaStudenti, studente);
					System.out.println("**********");
					break;
				case 3:

					System.out.println("Inserire voto");
					int voto = Integer.parseInt(input.nextLine());
					ricercaVoto(listaStudenti, voto);
					System.out.println("**********");

					break;
				case 0:
					System.out.println("Arrivederci");
					System.out.println("**********");

					break;
				default:
					System.out.println("Inserire un numero valido");
					System.out.println("**********");

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

	public static void ricerca(Map<Integer, Studente> lista, String inputNomeOCognome) {
		// Map<Integer, Studente> studentiTrovati = new HashMap<Integer, Studente>();
		// Studente studenteNomeOCognomeTrovato;
		String nome;
		String cognome;
		double media;
		int id;
		boolean trovato = false;
		for (Map.Entry<Integer, Studente> entry : lista.entrySet()) {
			if (entry.getValue().getNome().equalsIgnoreCase(inputNomeOCognome)
					|| entry.getValue().getCognome().equalsIgnoreCase(inputNomeOCognome)) {
				nome = entry.getValue().getNome();
				cognome = entry.getValue().getCognome();
				media = entry.getValue().getmedia();
				id = entry.getKey();
				System.out.println("**********");
				System.out.println("Risultati per '" + inputNomeOCognome + "'");
				System.out.println(nome + " " + cognome + " MEDIA=" + media + " ID=" + id);
				trovato = true;
			}
		}
		if (!trovato) {
			System.out.println("La ricerca per '" + inputNomeOCognome + "' non ha dato nessun risultato");
			System.out.println("**********");
		}
	}

	public static void ricercaVoto(Map<Integer, Studente> map, double inputVoto) {
		Map<Integer, Studente> mapStudentiVoto = new HashMap<Integer, Studente>();
		mapStudentiVoto = map.entrySet().stream().filter(entry -> entry.getValue().getmedia() > inputVoto)
				.collect(Collectors.toMap(entry -> entry.getKey(), entry -> entry.getValue()));

		mapStudentiVoto.entrySet().stream()
				.forEach(entry -> System.out.println("ID=" + entry.getKey() + " " + entry.getValue()));
	}

}
