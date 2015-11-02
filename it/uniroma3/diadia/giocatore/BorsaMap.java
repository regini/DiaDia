package it.uniroma3.diadia.giocatore;

import java.util.Collections;
import java.util.LinkedList;
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

public class BorsaMap {
	public final static int DEFAULT_PESO_MAX_BORSA = 10;
	private Map<String, Attrezzo> nome2attrezzo;
	private int pesoMax;

	public BorsaMap() {
		this(DEFAULT_PESO_MAX_BORSA);
	}

	public BorsaMap(int pesoMax) {
		this.pesoMax = pesoMax;
		this.nome2attrezzo = new TreeMap<String, Attrezzo>();
	}

	/**
	 * Aggiunge un attrezzo nella borsa
	 * @param attrezzo l'oggetto attrezzo da aggiungere nella borsa
	 * @return true se l'operazione e' andata a buon fine
	 */
	public boolean addAttrezzo(Attrezzo attrezzo) {
		if (this.getPeso() + attrezzo.getPeso() > this.getPesoMax())
			return false;
		this.nome2attrezzo.put(attrezzo.getNome(), attrezzo);
		return true;
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
		return this.nome2attrezzo.get(nomeAttrezzo);
	}

	/**
	 * Restituisce il peso della borsa
	 * @return il peso della borsa
	 */
	public int getPeso() {
		int peso = 0;
		for(Attrezzo attrezzo : this.nome2attrezzo.values())
			peso += attrezzo.getPeso();
		return peso;
	}

	/**
	 * Verifica se la borsa e' vuota
	 * @return true se la borsa e' vuota
	 */
	public boolean isEmpty() {
		return this.nome2attrezzo.isEmpty();
	}

	/**
	 * Verifica se esiste nella borsa un attrezzo in base al nome
	 * @return true se l'attrezzo e' presente nella borsa
	 */
	public boolean hasAttrezzo(String nomeAttrezzoCercato) {
		return this.nome2attrezzo.get(nomeAttrezzoCercato)!=null;
	}

	/**
	 * Rimuove l'attrezzo dalla borsa in base al nome
	 * @param string nome dell'attrezzo da eliminare
	 * @return l'attrezzo eliminato
	 */
	public Attrezzo removeAttrezzo(String nomeAttrezzoCercato) {
		Attrezzo attrezzoCercato = this.getAttrezzo(nomeAttrezzoCercato);
		this.nome2attrezzo.remove(nomeAttrezzoCercato);
		return attrezzoCercato;
	}

	/**
	 * Stampa il contenuto della borsa e il peso della borsa
	 */
	public String toString() {
		StringBuilder s = new StringBuilder();

		if (!this.isEmpty()) {
			s.append("Contenuto borsa ("+this.getPeso()+"kg/"+this.getPesoMax()+"kg): ");
			for (Attrezzo attrezzo : this.nome2attrezzo.values())
				s.append(attrezzo + " ");
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
		for(Attrezzo attrezzo : this.nome2attrezzo.values()){
			pesoPrevistoBorsa += attrezzo.getPeso();
		}
		pesoPrevistoBorsa= pesoPrevistoBorsa + attrezzoDaAggiungere.getPeso();
		if(pesoPrevistoBorsa<=this.pesoMax)
			return true;
		return false;		
	}

	public List<Attrezzo> getContenutoOrdinatoPerNome() {
		List<Attrezzo> attrezziOrdinatiPerNome = new LinkedList<Attrezzo>();
		attrezziOrdinatiPerNome.addAll(this.nome2attrezzo.values());
		return attrezziOrdinatiPerNome;
	}

	public List<Attrezzo> getContenutoOrdinatoPerPeso() {
		ComparatoreAttrezziPerPeso comparatore = new ComparatoreAttrezziPerPeso();
		
		List<Attrezzo> attrezziOrdinatiPerPeso = new LinkedList<Attrezzo>();
		attrezziOrdinatiPerPeso.addAll(this.nome2attrezzo.values());
		
		Collections.sort(attrezziOrdinatiPerPeso, comparatore);

		return attrezziOrdinatiPerPeso;
	}

	public Map<Integer, Set<Attrezzo>> getContenutoRaggruppatoPerPeso() {
		Map<Integer, Set<Attrezzo>> peso2attrezzo = new TreeMap<Integer, Set<Attrezzo>>();
		Set<Attrezzo> tmp;

		for(Attrezzo attrezzo : this.nome2attrezzo.values()) {
			if(peso2attrezzo.containsKey(attrezzo.getPeso())) {
				//Peso gi� incontrato
				tmp = peso2attrezzo.get(attrezzo.getPeso());
				tmp.add(attrezzo);
			}
			else {
				//Peso mai visto prima
				tmp= new TreeSet<Attrezzo>();
				tmp.add(attrezzo);
				peso2attrezzo.put(attrezzo.getPeso(), tmp);
			}
		}
		
		return peso2attrezzo;
	}
}