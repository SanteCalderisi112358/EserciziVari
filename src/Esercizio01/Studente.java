package Esercizio01;

public class Studente {
	private String nome;
	private String cognome;

	private double media;

	public Studente(String nome, String cognome, double media) {

		this.nome = nome;
		this.cognome = cognome;
		this.media = media;

	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}



	public double getmedia() {
		return media;
	}

	public void setmedia(double media) {
		this.media = media;
	}

	@Override
	public String toString() {
		String nomeSquadrato = String.format("%-20s", nome);
		String cognomeSquadrato = String.format("%-20s", cognome);
		String mediaSquadrata = String.format("%.2f", media);
		return "[" + nomeSquadrato + " " + cognomeSquadrato + " " + mediaSquadrata + "]";
	}


}
