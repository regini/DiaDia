package it.uniroma3.diadia.attrezzi;

import it.uniroma3.diadia.ambienti.Stanza;

/**
 * Una semplice classe che modella un attrezzo.
 * Gli attrezzi possono trovarsi all'interno delle stanze
 * del labirinto.
 * Ogni attrezzo ha un nome ed un peso.
 *

 * @see Stanza
 * @version 1.0
 */

public class Attrezzo implements Comparable<Attrezzo> {
	private String nome;
	private int peso;

	/**
	 * Crea un attrezzo
	 * @param string nome il nome che identifica l'attrezzo
	 * @param int peso il peso dell'attrezzo
	 */
	public Attrezzo(String nome, int peso) {
		this.peso = peso;
		this.nome = nome;
	}

	/**
	 * Restituisce il nome identificatore dell'attrezzo
	 * @return il nome identificatore dell'attrezzo
	 */
	public String getNome() {
		return this.nome;
	}

	/**
	 * Restituisce il peso dell'attrezzo
	 * @return il peso dell'attrezzo
	 */
	public int getPeso() {
		return this.peso;
	}

	/**
	 * Restituisce una rappresentazione stringa di questo attrezzo
	 * @return la rappresentazione stringa
	 */
	@Override
	public String toString() {
		return this.getNome()+" ("+this.getPeso()+"kg)";
	}
	
	@Override
	public boolean equals(Object o) {
		Attrezzo attrezzo=(Attrezzo)o;
		return(this.getNome().equals(attrezzo.getNome()));
	}
	
	@Override
	public int hashCode() {
		return this.getNome().hashCode();
	}
	
	@Override
	public int compareTo(Attrezzo that) {
		return this.getNome().compareTo(that.getNome());
	}

}