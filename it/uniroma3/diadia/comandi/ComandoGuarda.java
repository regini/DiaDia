package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;

public class ComandoGuarda implements Comando {

	private String direzione;

	@Override
	public String esegui(Partita partita) { 

		Stanza stanzaCorrente = partita.getStanzaCorrente(); 
		Stanza prossimaStanza = null; 
		if (this.direzione==null)
			return partita.getStanzaCorrente().getDescrizione(); 
		prossimaStanza = stanzaCorrente.getStanzaAdiacente(this.direzione); 
		if (prossimaStanza==null)
			return "Direzione inesistente";
		if(stanzaCorrente.equals(prossimaStanza))
			return "La stanza a " + this.direzione + " e' bloccata";
		else
			return "A " + this.direzione.toString() + " c'e':\n" + prossimaStanza.toString(); 
	} 

	@Override
	public void setParametro(String parametro) {
		this.direzione=parametro;

	}

	@Override
	public String getNome() {
		return "guarda";
	}

	@Override
	public String getParametro() {
		return this.direzione;
	}

}
