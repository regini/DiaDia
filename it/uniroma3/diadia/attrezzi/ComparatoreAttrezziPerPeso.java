package it.uniroma3.diadia.attrezzi;

import java.util.Comparator;

public class ComparatoreAttrezziPerPeso implements Comparator<Attrezzo> {

	@Override
	public int compare(Attrezzo arg0, Attrezzo arg1) {
		if(arg0.getPeso()!=arg1.getPeso())
			return arg0.getPeso() - arg1.getPeso();
		return arg0.getNome().compareTo(arg1.getNome());
	}

}
