package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.giocatore.Borsa;

public class ComandoRegala implements Comando {
	private String nomeAttrezzoDaRegalare;

	@Override
	public String esegui(Partita partita) {
		
		if(partita.getStanzaCorrente().getPersonaggio()==null){
			return "Nella stanza non ci sono personaggi";
		}
		if(this.nomeAttrezzoDaRegalare == null) {
			return "Quale attrezzo vuoi regalare?";
		}
		Borsa borsa = partita.getGiocatore().getBorsa();
		if(borsa.hasAttrezzo(this.nomeAttrezzoDaRegalare)) {
			Attrezzo attrezzoDaRegalare = borsa.getAttrezzo(this.nomeAttrezzoDaRegalare);
			return partita.getStanzaCorrente().getPersonaggio().riceviRegalo(attrezzoDaRegalare, partita) + "\n" + borsa.toString();
		}
		else
			return this.nomeAttrezzoDaRegalare + " non e' presente in borsa";
	}

	@Override
	public void setParametro(String parametro) {
		this.nomeAttrezzoDaRegalare = parametro;
	}

	@Override
	public String getNome() {
		return "regala";
	}

	@Override
	public String getParametro() {
		return this.nomeAttrezzoDaRegalare;
	}

}
