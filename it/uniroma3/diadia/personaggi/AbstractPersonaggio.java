package it.uniroma3.diadia.personaggi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public abstract class AbstractPersonaggio {

	private String nome;
	private String presentazione;
	private boolean haSalutato;
	
	public AbstractPersonaggio(String nome, String presentazione) {
		this.nome = nome;
		this.presentazione = presentazione;
		this.haSalutato = false;
	}

	public AbstractPersonaggio() {
		this.haSalutato = false;
	}
	
	public String getNome() {
		return this.nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getPresentazione() {
		return this.presentazione;
	}
	
	public void setPresentazione(String presentazione) {
		this.presentazione = presentazione;
	}
	
	public boolean haSalutato() {
		return this.haSalutato;
	}

	public String saluta() {
		String risposta = "Ciao, io sono "+this.getNome()+".";
		if (!haSalutato) {
			risposta += this.getPresentazione();
			this.haSalutato = true; 
		}
		else
			risposta += "Ci siamo gia' presentati.";
		return risposta;
	}

	abstract public String agisci(Partita partita);
	
	abstract public String riceviRegalo(Attrezzo attrezzo, Partita partita);

	public String toString() {
		return this.getNome();
	}
	
	@Override
	public boolean equals(Object o) {
		AbstractPersonaggio that= (AbstractPersonaggio)o;
		return this.getNome().equals(that.getNome());
	}
	
	@Override
	public int hashCode() {
		return this.getNome().hashCode();
	}
}
