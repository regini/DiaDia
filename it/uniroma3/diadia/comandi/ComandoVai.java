package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;


/**
 * Cerca di andare in una direzione. Se c'e' una stanza ci entra 
 * e ne stampa il nome, altrimenti stampa un messaggio di errore
 * @param Partita
 */
public class ComandoVai implements Comando {

	private String direzione;

	@Override
	public String esegui(Partita partita) { 

		Stanza stanzaCorrente = partita.getStanzaCorrente(); 
		Stanza prossimaStanza = null; 
		if (this.direzione==null) 
			return "Dove vuoi andare? Devi specificare una direzione";

		prossimaStanza = stanzaCorrente.getStanzaAdiacente(this.direzione); 
		if (prossimaStanza==null)
			return "Direzione inesistente";

		if(!stanzaCorrente.equals(prossimaStanza) && partita.giocatoreIsVivo()) {
			partita.getGiocatore().setCfu(partita.getGiocatore().getCfu()-1); 
			partita.setStanzaCorrente(prossimaStanza); 
		}
		return partita.getStanzaCorrente().getDescrizione() + "\nHai " + partita.getGiocatore().getCfu() +" CFU";
	}

	@Override
	public void setParametro(String parametro) { 
		this.direzione = parametro;
	}

	@Override
	public String getNome() {
		return "vai";
	}

	@Override
	public String getParametro() {
		return this.direzione;
	}
}