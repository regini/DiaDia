package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.personaggi.AbstractPersonaggio;

public class ComandoSaluta extends AbstractComando {

	public ComandoSaluta() {
		super("saluta");
	}

	@Override
	public String esegui(Partita partita) {
		if(partita.getStanzaCorrente().getPersonaggio()==null)
			return "Qui non c'e' nessuno da salutare";
		AbstractPersonaggio personaggioDaSalutare = partita.getStanzaCorrente().getPersonaggio();
		return personaggioDaSalutare.saluta();
	}

}
