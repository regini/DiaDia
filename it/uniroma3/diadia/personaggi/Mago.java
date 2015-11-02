package it.uniroma3.diadia.personaggi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class Mago extends AbstractPersonaggio {
	
	private static final String PRESENTAZIONE = "\nI miei poteri sono sconfinati.\n" + 
			"La mia magia e' in grado di creare e modificare gli oggetti che ci circondano.\n" +
			"Sii gentile ed esaudiro' i tuoi desideri";
	private static final String MESSAGGIO_DONO = "Sei un vero simpaticone, " +
			"con una mia magica azione, troverai un nuovo oggetto per il tuo bel borsone!";
	private static final String MESSAGGIO_SCUSE = "Mi spiace, ma non ho piu' nulla...";
	private Attrezzo attrezzo;
	
	public Mago(String nome, String presentazione, Attrezzo attrezzo) {
		super(nome, presentazione);
		this.attrezzo = attrezzo;
	}
	
	public Mago() {
		super();
		super.setPresentazione(PRESENTAZIONE);
		this.attrezzo = new Attrezzo("bacchetta", 2);
	}
	
	@Override
	public String agisci(Partita partita) {
		String msg;
		if (attrezzo!=null) {
			partita.getStanzaCorrente().addAttrezzo(attrezzo);
			this.attrezzo = null;
			msg = MESSAGGIO_DONO;
		}
		else {
			msg = MESSAGGIO_SCUSE;
		}
		return msg;
	}

	@Override
	public String riceviRegalo(Attrezzo attrezzo, Partita partita) {
		Attrezzo attrezzoConPesoDimezzato = new Attrezzo(attrezzo.getNome(), (attrezzo.getPeso()/2));
		partita.getGiocatore().getBorsa().removeAttrezzo(attrezzo.getNome());
		partita.getStanzaCorrente().addAttrezzo(attrezzoConPesoDimezzato);
		return "Grazie per il regalo! In cambio dimezzero' il peso dell' attrezzo: " + attrezzo.getNome();
	}
	
	public Attrezzo getAttrezzo() {
		return this.attrezzo;
	}
	
}