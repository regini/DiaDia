package it.uniroma3.diadia.personaggi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.giocatore.Borsa;

public class Cane extends AbstractPersonaggio {
	private static final String PRESENTAZIONE = "\nHo fame! Non avresti del cibo?\nIn cambio ti daro' qualcosa di utile.";
	private Attrezzo ciboPreferito;
	private Attrezzo attrezzoPersonaggio;

	public Cane(String nome, String presentazione, Attrezzo attrezzoPersonaggio, Attrezzo ciboPreferito) {
		super(nome, presentazione);
		this.attrezzoPersonaggio = attrezzoPersonaggio;
		this.ciboPreferito = ciboPreferito;
	}
	
	public Cane() {
		super();
		super.setPresentazione(PRESENTAZIONE);
		this.attrezzoPersonaggio = new Attrezzo("chiave", 1);
		this.ciboPreferito = new Attrezzo("osso", 1);
	}
	
	@Override
	public String agisci(Partita partita) {
		partita.getGiocatore().setCfu(partita.getGiocatore().getCfu()-1);
		return "Il cane ti ha morso! Hai perso un CFU!\n" + "Ora hai " + partita.getGiocatore().getCfu() +" CFU";
	}

	@Override
	public String riceviRegalo(Attrezzo attrezzo, Partita partita) {
		Borsa borsa = partita.getGiocatore().getBorsa();
		if(attrezzo.equals(this.ciboPreferito)) {
			borsa.removeAttrezzo(attrezzo.getNome());
			partita.getStanzaCorrente().addAttrezzo(this.attrezzoPersonaggio);
			return "Grazie! Questo e' il mio cibo preferito! Ti lascio: " + this.attrezzoPersonaggio.getNome();
		}
		else {
			borsa.removeAttrezzo(attrezzo.getNome());
			partita.getStanzaCorrente().addAttrezzo(attrezzo);
			return "Questo attrezzo non mi piace!";
		}
	}
	
	public Attrezzo getAttrezzoPersonaggio() {
		return this.attrezzoPersonaggio;
	}
	
	public Attrezzo getCiboPreferito() {
		return this.ciboPreferito;
	}

}
