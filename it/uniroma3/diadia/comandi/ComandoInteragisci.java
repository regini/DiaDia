package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.personaggi.AbstractPersonaggio;

public class ComandoInteragisci extends AbstractComando {
	private String messaggio;
	
	public ComandoInteragisci() {
		super("interagisci");
	}

	@Override
	public String esegui(Partita partita) {
		if(partita.getStanzaCorrente().getPersonaggio()==null)
			return "Qui non c'e' nessuno con cui interagire";
		AbstractPersonaggio personaggio;
		personaggio = partita.getStanzaCorrente().getPersonaggio();
		this.messaggio = personaggio.agisci(partita);
		return this.messaggio;
	}
	
	public String getMessaggio() {
		return this.messaggio;
	}
}
