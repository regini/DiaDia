package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;

/**
 * Stampa informazioni di aiuto.
 */
public class ComandoAiuto extends AbstractComando {
	public static final String[] elencoComandi = {"vai", "prendi", "posa", "interagisci", "saluta", "regala", "aiuto", "fine"};
	
	public ComandoAiuto() {
		super("aiuto");
	}
	
	@Override
	public String esegui(Partita partita) {
		String msg = elencoComandi[0] + " ";
		for(int i = 1; i< elencoComandi.length; i++) 
			msg += (elencoComandi[i]+" ");
		msg += "\n";
		return msg;
	}

}
