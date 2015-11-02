package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;

public class ComandoNonValido extends AbstractComando {

	public ComandoNonValido() {
		super("Comando sconosciuto");
	}

	@Override
	public String esegui(Partita partita) {
		return "Comando sconosciuto";
	}
}
