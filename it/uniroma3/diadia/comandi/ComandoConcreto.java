package it.uniroma3.diadia.comandi;

import java.util.Scanner;

/**
 * Questa classe modella un comando.
 * Un comando consiste al piu' di due parole:
 * il nome del comando ed un parametro
 * su cui si applica il comando.
 * (Ad es. alla riga digitata dall'utente "vai nord"
 *  corrisponde un comando di nome "vai" e parametro "nord").
 *

 * @version 1.0
 */

public class ComandoConcreto {
	private String nome;
	private String parametro;

	public ComandoConcreto(String istruzione) {
		Scanner scannerDiParole = new Scanner(istruzione);

		// prima parola: nome del comando
		if (scannerDiParole.hasNext())
			this.nome = scannerDiParole.next(); 

		// seconda parola: eventuale parametro
		if (scannerDiParole.hasNext())
			this.parametro = scannerDiParole.next();

		scannerDiParole.close();
	}

	/**
	 * Restituisce il nome del comando.
	 * @return il nome del comando
	 */
	public String getNome() {
		return this.nome;
	}

	/**
	 * Restituisce il parametro del comando.
	 * @return il parametro del comando
	 */
	public String getParametro() {
		return this.parametro;
	}

	/**
	 * Verifia se il comando � sconosciuto.
	 * @return true se il nome del comando non e' nullo altrimenti false
	 */
	public boolean sconosciuto() {
		return (this.nome == null);
	}
}