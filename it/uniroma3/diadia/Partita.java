package it.uniroma3.diadia;

import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.giocatore.Giocatore;

/**
 * Questa classe modella una partita del gioco

 * @see Stanza
 * @see Giocatore
 * @see Labirinto
 * @version 2.0
 */

public class Partita {
	private Stanza stanzaCorrente;
	private boolean finita;
	private Giocatore giocatore;	

	public Partita(Labirinto labirinto){
		this.giocatore = new Giocatore();
		this.stanzaCorrente=labirinto.getStanzaIngresso();
		this.finita = false;
		System.out.println(labirinto.getMessaggioBenvenuto());
		System.out.println("\nTi trovi in:\n" + this.stanzaCorrente.getDescrizione() + "\nHai " + this.giocatore.getCfu() + " CFU");
	}

	/**
	 * Imposta la stanza corrente
	 * @param string stanza corrente
	 */
	public void setStanzaCorrente(Stanza stanzaCorrente) {
		this.stanzaCorrente = stanzaCorrente;
	}
	
	/**
	 * Restituisce la stanza corrente
	 * @return string stanza corrente
	 */
	public Stanza getStanzaCorrente() {
		return this.stanzaCorrente;
	}
	
	/**
	 * Restituisce il giocatore della partita
	 * @return il giocatore della partita
	 */
	public Giocatore getGiocatore() {
		return this.giocatore;
	}

	/**
	 * Restituisce vero se e solo se la partita e' stata vinta
	 * @return vero se partita vinta
	 */
	public boolean vinta(Labirinto labirinto) {
		return this.getStanzaCorrente()==labirinto.getStanzaVincente();
	}

	/**
	 * Restituisce vero se e solo se la partita e' finita
	 * @return vero se partita finita
	 */
	public boolean isFinita(Labirinto labirinto) {
		return finita || vinta(labirinto) || !this.giocatoreIsVivo();
	}
	
	/**
	 * Restituisce vero se e solo se il giocatore non ha esaurito i CFU
	 * @return false se il giocatore ha esaurito i CFU
	 */
	public boolean giocatoreIsVivo() {
		return !(this.giocatore.getCfu()==0);	
	}

	/**
	 * Imposta la partita come finita
	 */
	public void setFinita() {
		this.finita = true;
	}


}
