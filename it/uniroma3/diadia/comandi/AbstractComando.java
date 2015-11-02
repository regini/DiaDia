package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;

public abstract class AbstractComando implements Comando {
	private String nomeComando;
	
	public AbstractComando(String nomeComando) {
		this.nomeComando = nomeComando;
	}
	
	@Override
	abstract public String esegui(Partita partita);
	
	public String getNome() {
		return this.nomeComando;
	}
	
	@Override
	public void setParametro(String parametro) {}
	
	@Override
	public String getParametro() {
		return null;
	}

}
