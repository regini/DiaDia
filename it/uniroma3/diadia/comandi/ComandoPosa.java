
package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.giocatore.Borsa;
import it.uniroma3.diadia.giocatore.Giocatore;

/**
 *  Gli attrezzi posati dal giocatore vengono rimossi dalla borsa
 *  e aggiunti alla stanza.
 *  Il comando viene eseguito se nella borsa e' presente l'alttrezzo 
 *  e la stanza non e' piena.
 *  @param string nome dell'attrezzo
 */
public class ComandoPosa implements Comando {

	private String nomeAttrezzoDaPosare;

	@Override
	public String esegui(Partita partita) {
		Giocatore giocatore=partita.getGiocatore();
		Borsa borsa=giocatore.getBorsa();
		Stanza stanzaCorrente=partita.getStanzaCorrente();
		
		if(nomeAttrezzoDaPosare==null)
			return "Quale attrezzo vuoi posare?";
		else if(!borsa.hasAttrezzo(nomeAttrezzoDaPosare))
			return nomeAttrezzoDaPosare + " non e' presente nella borsa";
		else {
			Attrezzo attrezzoPosato=borsa.getAttrezzo(nomeAttrezzoDaPosare);
			stanzaCorrente.addAttrezzo(attrezzoPosato);
			borsa.removeAttrezzo(nomeAttrezzoDaPosare);
			return attrezzoPosato.getNome() + (" posato\n") + borsa.toString();	
		}
	}

	@Override
	public void setParametro(String parametro) { 
		this.nomeAttrezzoDaPosare = parametro; 
	}

	@Override
	public String getNome() {
		return "posa";
	}

	@Override
	public String getParametro() {
		return this.nomeAttrezzoDaPosare;
	}
}