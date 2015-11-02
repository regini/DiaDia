package it.uniroma3.diadia.giocatore;

/**
 *  Classe che modella il giocatore che ha una borsa, dei cfu iniziali
 *  ed � in grado di spostarsi nel labirinto
 *

 * @version 2.0
 */

public class Giocatore {
	private int cfu;
	private Borsa borsa;
	private static int CFU_INIZIALI = 20;

	public Giocatore() {
		this.borsa = new Borsa();
		this.cfu = CFU_INIZIALI;
	}
	
	/**
	 * Restituisce i cfu del giocatore
	 * @return il numero di cfu
	 */
	public int getCfu() {
		return this.cfu;
	}
	
	/**
	 * Imposta i cfu del giocatore
	 * @param int il numero di cfu da impostare
	 */
	public void setCfu(int cfu) {
		this.cfu = cfu;		
	}

	/**
	 * Restituisce la borsa del giocatore
	 * @return la borsa del giocatore
	 */
	public Borsa getBorsa() {
		return this.borsa;
	}
}
