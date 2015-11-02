package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.giocatore.Borsa;
import it.uniroma3.diadia.giocatore.Giocatore;

public class ComandoPrendi implements Comando {

	private String nomeAttrezzoDaPrendere;

	/**
	 * Gli attrezzi presi dal giocatore vengono rimossi dalla stanza
	 * e aggiunti alla borsa. 
	 * Il comando viene eseguito se l'oggetto e' presente nella stanza 
	 * e la borsa non e' piena.
	 * @param string nome dell'attrezzo da prendere
	 * @return 
	 */
	@Override
	public String esegui(Partita partita) {
		Stanza stanzaCorrente= partita.getStanzaCorrente();
		Giocatore giocatore=partita.getGiocatore();
		Borsa borsa=giocatore.getBorsa();
		
		if(nomeAttrezzoDaPrendere==null)
			return "Quale attrezzo vuoi prendere?";
		else if(!stanzaCorrente.hasAttrezzo(nomeAttrezzoDaPrendere))
			return nomeAttrezzoDaPrendere + " non e' presente nella stanza";
		else {
			Attrezzo attrezzoPreso= stanzaCorrente.getAttrezzo(nomeAttrezzoDaPrendere);
			if(borsa.limitePesoMax(attrezzoPreso) && stanzaCorrente.removeAttrezzo(attrezzoPreso) & borsa.addAttrezzo(attrezzoPreso))
				return attrezzoPreso.getNome() + " preso\n" + borsa.toString();
			else
				return nomeAttrezzoDaPrendere +" supera il peso massimo della borsa che e' " + borsa.getPesoMax() + "(kg)";

		}	
	}


	@Override
	public void setParametro(String parametro) { 
		this.nomeAttrezzoDaPrendere = parametro; 
	}


	@Override
	public String getNome() {
		return "prendi";
	}


	@Override
	public String getParametro() {
		return this.nomeAttrezzoDaPrendere;
	}
}
