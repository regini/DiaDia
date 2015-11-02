package it.uniroma3.diadia.ambienti;

import java.io.FileNotFoundException;

import it.uniroma3.diadia.FormatoFileNonValidoException;
import it.uniroma3.diadia.attrezzi.Attrezzo;

/**
 * Questa classe modella un labirinto del gioco

 * @see Attrezzo
 * @version 1.0
 */

public class Labirinto {

	private static final String MESSAGGIO_BENVENUTO = 
			"Ti trovi nell'Universita', ma oggi e' diversa dal solito...\n" +
					"Meglio andare al piu' presto in biblioteca a studiare. Ma dov'e'?\n"+
					"I locali sono popolati da strani personaggi, " +
					"alcuni amici, altri... chissa!\n"+
					"Ci sono attrezzi che potrebbero servirti nell'impresa:\n"+
					"puoi raccoglierli, usarli, posarli quando ti sembrano inutili\n" +
					"o regalarli se pensi che possano ingraziarti qualcuno.\n\n"+
					"Per conoscere le istruzioni usa il comando 'aiuto'.";

	
	private CaricatoreLabirinto caricatoreLabirinto;
	private Stanza stanzaIngresso;
	private Stanza stanzaVincente;


	public Labirinto(String nomeFile) throws FormatoFileNonValidoException, FileNotFoundException {
		this.caricatoreLabirinto = new CaricatoreLabirinto(nomeFile); 
		this.caricatoreLabirinto.carica(); 
		this.stanzaIngresso = this.caricatoreLabirinto.getStanzaIniziale(); 
		this.stanzaVincente = this.caricatoreLabirinto.getStanzaVincente();
	}


	/**
	 * Crea tutte le stanze, le porte di collegamento, aggiunge gli attrezzi
	 * imposta la stanza di ingresso e quella vincente
	 */
//	private void creaStanze() {
//
//		/* crea gli attrezzi */
//		Attrezzo lanterna = new Attrezzo("lanterna",3);
//		Attrezzo osso = new Attrezzo("osso",1);
//		Attrezzo chiave=new Attrezzo("chiave",2);
//
//		/* crea stanze del labirinto */
//		Stanza atrio = new StanzaBloccata("Atrio");
//		Stanza biblioteca = new Stanza("Biblioteca");
//		Stanza aulaN11 = new StanzaMagica("Aula N11");
//		Stanza aulaN10 = new StanzaBuia("Aula N10");
//		Stanza laboratorio = new StanzaMagicaProtected("Laboratorio Campus");
//		Stanza ds3a=new Stanza("DS3A");
//
//
//		/* collega le stanze */
//		atrio.impostaStanzaAdiacente("nord", biblioteca);
//		atrio.impostaStanzaAdiacente("est", aulaN11);
//		atrio.impostaStanzaAdiacente("sud", aulaN10);
//		atrio.impostaStanzaAdiacente("ovest", laboratorio);
//		aulaN11.impostaStanzaAdiacente("est", laboratorio);
//		aulaN11.impostaStanzaAdiacente("ovest", atrio);
//		aulaN10.impostaStanzaAdiacente("nord", atrio);
//		aulaN10.impostaStanzaAdiacente("est", aulaN11);
//		aulaN10.impostaStanzaAdiacente("ovest", laboratorio);
//		laboratorio.impostaStanzaAdiacente("est", atrio);
//		laboratorio.impostaStanzaAdiacente("ovest", aulaN11);
//		laboratorio.impostaStanzaAdiacente("nord", ds3a);
//		biblioteca.impostaStanzaAdiacente("sud", atrio);
//		ds3a.impostaStanzaAdiacente("sud", laboratorio);
//
//		/* pone gli attrezzi nelle stanze */
//		aulaN10.addAttrezzo(lanterna);
//		atrio.addAttrezzo(osso);
//		ds3a.addAttrezzo(chiave);
//
//		/* imposto la stanza di ingresso e la stanza vincente */
//		this.stanzaIngresso=atrio;
//		this.stanzaVincente=biblioteca;
//	}

	/**
	 * Imposta la stanza vincente
	 * @param stanza la stanza vincente
	 */
	public void setStanzaVincente(Stanza stanzaVincente) {
		this.stanzaVincente=stanzaVincente;
	}

	/**
	 * Imposta la stanza d'ingresso
	 * @param stanza la stanza d'ingresso
	 */
	public void setStanzaIngresso(Stanza stanzaIngresso) {
		this.stanzaIngresso=stanzaIngresso;
	}

	/**
	 * Restituisce la stanza vincente
	 * @return la stanza vincente
	 */
	public Stanza getStanzaVincente() {
		return this.stanzaVincente;
	}

	/**
	 * Restituisce la stanza d'ingresso
	 * @return la stanza d'ingresso
	 */
	public Stanza getStanzaIngresso() {
		return this.stanzaIngresso;
	}

	/**
	 * Restituisce il messagio di benventuto
	 * @return il messaggio di benvenuto
	 */
	public String getMessaggioBenvenuto(){
		return MESSAGGIO_BENVENUTO;
	}
}