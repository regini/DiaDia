package it.uniroma3.diadia.ambienti;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class StanzaMagica extends Stanza {
	final static private int SOGLIA_DEFAULT = 3; 
	private int contatoreAttrezziPosati; 
	private int sogliaMagica; 

	public StanzaMagica(String nome) { 
		this(nome, SOGLIA_DEFAULT); 
	} 

	public StanzaMagica(String nome, int soglia) { 
		super(nome); 
		this.contatoreAttrezziPosati = 0; 
		this.sogliaMagica = soglia; 
	}
	
	public StanzaMagica() {
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

	@Override
	public boolean addAttrezzo(Attrezzo attrezzo) { 
		this.contatoreAttrezziPosati++; 
		if (this.contatoreAttrezziPosati > this.sogliaMagica) 
			attrezzo = this.modificaAttrezzo(attrezzo); 
		return super.addAttrezzo(attrezzo); 
	}
}
