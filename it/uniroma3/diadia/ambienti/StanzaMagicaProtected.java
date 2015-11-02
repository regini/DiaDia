package it.uniroma3.diadia.ambienti;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class StanzaMagicaProtected extends Stanza {
	final static private int SOGLIA_DEFAULT = 3; 
	private int contatoreAttrezziPosati; 
	private int sogliaMagica; 

	public StanzaMagicaProtected(String nome) { 
		this(nome, SOGLIA_DEFAULT); 
	} 

	public StanzaMagicaProtected(String nome, int soglia) { 
		super(nome); 
		this.contatoreAttrezziPosati = 0; 
		this.sogliaMagica = soglia; 
	}

	public StanzaMagicaProtected() {
		super();
		this.contatoreAttrezziPosati = 0; 
		this.sogliaMagica = SOGLIA_DEFAULT; 
	}
	private Attrezzo modificaAttrezzo(Attrezzo attrezzo) { 
		StringBuilder nomeInvertito; 
		int pesoDoppio = attrezzo.getPeso()*2; 
		nomeInvertito = new StringBuilder(attrezzo.getNome()); 
		nomeInvertito = nomeInvertito.reverse(); 
		attrezzo = new Attrezzo(nomeInvertito.toString(), pesoDoppio); 
		return attrezzo; 
	}

	public boolean addAttrezzo(Attrezzo attrezzo) { 
		this.contatoreAttrezziPosati++; 
		if (this.contatoreAttrezziPosati > this.sogliaMagica) 
			attrezzo = this.modificaAttrezzo(attrezzo); 
		return this.nome2attrezzo.put(attrezzo.getNome(), attrezzo) == null;
	}
}
