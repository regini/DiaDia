package it.uniroma3.diadia.giocatore;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.attrezzi.ComparatoreAttrezziPerPeso;


/**
 * Questa classe modella un comando.
 * Un comando consiste al piu' di due parole:
 * il nome del comando ed un parametro
 * su cui si applica il comando.
 * (Ad es. alla riga digitata dall'utente "vai nord"
 *  corrisponde un comando di nome "vai" e parametro "nord").
 *

 * @see Attrezzo
 * @version 2.0
 */

public class Borsa {
	public final static int DEFAULT_PESO_MAX_BORSA = 10;
	private List<Attrezzo> attrezzi;
	private int pesoMax;

	public Borsa() {
		this(DEFAULT_PESO_MAX_BORSA);
	}

	public Borsa(int pesoMax) {
		this.pesoMax = pesoMax;
		this.attrezzi = new ArrayList<Attrezzo>();
	}

	/**
	 * Aggiunge un attrezzo nella borsa
	 * @param attrezzo l'oggetto attrezzo da aggiungere nella borsa
	 * @return true se l'operazione e' andata a buon fine
	 */
	public boolean addAttrezzo(Attrezzo attrezzo) {
		if (this.getPeso() + attrezzo.getPeso() > this.getPesoMax())
			return false;
		return this.attrezzi.add(attrezzo);
		
	}
	/**
	 * Ritorna il peso massimo della borsa
	 * @return il peso della borsa
	 */
	public int getPesoMax() {
		return pesoMax;
	}

	/**
	 * Restituisce l'attrezzo cercato nella borsa in base al nome
	 * @param string  nome dell'attrezzo
	 * @return oggetto istanza della classe attrezzo se trovato altrimenti null
	 */
	public Attrezzo getAttrezzo(String nomeAttrezzo) {
		Attrezzo attrezzoCercato= new Attrezzo(nomeAttrezzo , -1);
		int index = this.attrezzi.indexOf(attrezzoCercato);
		return (index == -1 ? null : this.attrezzi.get(index));
	}

	/**
	 * Restituisce il peso della borsa
	 * @return il peso della borsa
	 */
	public int getPeso() {
		int peso = 0;
		for(Attrezzo attrezzo : this.attrezzi)
			peso += attrezzo.getPeso();
		return peso;
	}

	/**
	 * Verifica se la borsa e' vuota
	 * @return true se la borsa e' vuota
	 */
	public boolean isEmpty() {
		return this.attrezzi.isEmpty();
	}

	/**
	 * Verifica se esiste nella borsa un attrezzo in base al nome
	 * @return true se l'attrezzo e' presente nella borsa
	 */
	public boolean hasAttrezzo(String nomeAttrezzoCercato) {
		return this.attrezzi.contains(this.getAttrezzo(nomeAttrezzoCercato));
	}

	/**
	 * Rimuove l'attrezzo dalla borsa in base al nome
	 * @param string nome dell'attrezzo da eliminare
	 * @return l'attrezzo eliminato
	 */
	public Attrezzo removeAttrezzo(String nomeAttrezzoCercato) {
		Attrezzo attrezzoCercato = this.getAttrezzo(nomeAttrezzoCercato);
		this.attrezzi.remove(attrezzoCercato);
		return attrezzoCercato;
	}

	/**
	 * Stampa il contenuto della borsa e il peso della borsa
	 */
	public String toString() {
		StringBuilder s = new StringBuilder();
		
		this.getContenutoOrdinatoPerNome();
		
		if (!this.isEmpty()) {
			s.append("Contenuto borsa ("+this.getPeso()+"kg/"+this.getPesoMax()+"kg): ");
			for (Attrezzo attrezzo : this.attrezzi)
				s.append(attrezzo+" ");
		}
		else
			s.append("Borsa vuota");

		return s.toString();
	}

	/**
	 * Verifica che l'attrezzo che devo aggiungere alla borsa non sia troppo pesante
	 * @param attrezzo l'oggetto attrezzo che devo aggiungere
	 * @return vero se l'operazione � andata a buon fine
	 */
	public boolean limitePesoMax(Attrezzo attrezzoDaAggiungere) {
		int pesoPrevistoBorsa=0;
		for(int i=0; (i<this.attrezzi.size()); i++){
			pesoPrevistoBorsa += attrezzi.get(i).getPeso();
		}
		pesoPrevistoBorsa= pesoPrevistoBorsa + attrezzoDaAggiungere.getPeso();
		if(pesoPrevistoBorsa<=this.pesoMax)
			return true;
		return false;		
	}
	
	public List<Attrezzo> getContenutoOrdinatoPerNome() {
		Collections.sort(this.attrezzi);
		return this.attrezzi;
	}
	
	public List<Attrezzo> getContenutoOrdinatoPerPeso() {
		ComparatoreAttrezziPerPeso comparatore = new ComparatoreAttrezziPerPeso();
		Collections.sort(this.attrezzi, comparatore);
		return this.attrezzi;
	}
	
	public Map<Integer, Set<Attrezzo>> getContenutoRaggruppatoPerPeso() {
		Set<Attrezzo> tmp;
		Map<Integer, Set<Attrezzo>> mappa = new TreeMap<Integer, Set<Attrezzo>>();
		
		for(Attrezzo attrezzo : this.attrezzi) {
			if(mappa.containsKey(attrezzo.getPeso())) {
				tmp = mappa.get(attrezzo.getPeso());
				tmp.add(attrezzo);
			}
			else {
				tmp=new TreeSet<Attrezzo>();
				tmp.add(attrezzo);
				mappa.put(attrezzo.getPeso(), tmp);
			}
		}
		
		return mappa;
	}
}
