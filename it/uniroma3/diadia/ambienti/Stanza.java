  package it.uniroma3.diadia.ambienti;

import java.util.Map;
import java.util.TreeMap;

import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.personaggi.AbstractPersonaggio;

/**
 * Classe Stanza - una stanza in un gioco di ruolo.
 * Una stanza e' un luogo fisico nel gioco.
 * E' collegata ad altre stanze attraverso delle uscite.
 * Ogni uscita e' associata ad una direzione.
 * 

 * @see Attrezzo
 * @version 2.0
 */

public class Stanza {
	private String nome;
	protected Map<String, Attrezzo> nome2attrezzo;
	private Map<String, Stanza> uscite;
	private AbstractPersonaggio personaggio;

	/**
	 * Crea una stanza. Non ci sono stanze adiacenti, non ci sono attrezzi.
	 * @param string nome della stanza
	 */
	public Stanza(String nome) {
		this.nome = nome;
		this.nome2attrezzo = new TreeMap<String, Attrezzo>();
		this.uscite = new TreeMap<String, Stanza>();
	}

	public Stanza() {
		this.nome2attrezzo = new TreeMap<String, Attrezzo>();
		this.uscite = new TreeMap<String, Stanza>();
	}
	/**
	 * Imposta una stanza adiacente.
	 * @param string direzione in cui sara' posta la stanza adiacente
	 * @param stanzaAdiacente stanza adiacente nella direzione indicata dal primo parametro
	 */
	public void impostaStanzaAdiacente(String direzione, Stanza stanzaAdiacente) {
		this.uscite.put(direzione, stanzaAdiacente);
	}

	/**
	 * Restituisce la stanza adiacente nella direzione specificata.
	 * @param string direzione
	 */
	public Stanza getStanzaAdiacente(String direzione) {
		return this.uscite.get(direzione);
	}

	/**
	 * Restituisce la nome della stanza.
	 * @return il nome della stanza
	 */
	public String getNome() {
		return this.nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public Map<String, Stanza> getStanzeAdiacenti() {
		return this.uscite;
	}
	
	/**
	 * Restituisce la descrizione della stanza.
	 * @return la descrizione della stanza
	 */
	public String getDescrizione() {
		return this.toString();
	}

	/**
	 * Restituisce il numero degli attrezzi nella stanza.
	 * @return il numero degli attrezzi nella stanza
	 */
	public int getNumeroAttrezzi(){
		return this.nome2attrezzo.size();
	}

	/**
	 * Restituisce la collezione di attrezzi presenti nella stanza.
	 * @return la collezione di attrezzi nella stanza
	 */
	public Attrezzo[] getAttrezzi() {
		Attrezzo[] risultato= new Attrezzo[this.nome2attrezzo.size()];
		risultato=this.nome2attrezzo.values().toArray(risultato);
		return risultato;
//		return this.nome.toArray(new Attrezzo[0]); //Il parametro serve per far capire al compilatore che l'array restituito � di tipo Attrezzo
	}

	/**
	 * Mette un attrezzo nella stanza.
	 * @param attrezzo l'attrezzo da mettere nella stanza
	 * @return true se riesce ad aggiungere l'attrezzo, false atrimenti
	 */
	public boolean addAttrezzo(Attrezzo attrezzo) {
		if(!this.nome2attrezzo.containsKey(attrezzo.getNome()))
			return this.nome2attrezzo.put(attrezzo.getNome(), attrezzo) == null;
		return false;
	}


	/**
	 * Restituisce una rappresentazione stringa di questa stanza,
	 * stampadone la descrizione, le uscite e gli eventuali attrezzi contenuti.
	 * @return la rappresentazione stringa
	 */
	public String toString() {
		StringBuilder s = new StringBuilder();
		s.append(this.nome);
		s.append("\nUscite: ");
		for (String direzione : this.uscite.keySet())
			s.append(" " + direzione);
		s.append("\nAttrezzi nella stanza: ");
		for (Attrezzo attrezzo : this.nome2attrezzo.values())
			s.append(attrezzo +" ");
		s.append("\nPersonaggi nella stanza: ");
		if(this.personaggio!=null)
			s.append(this.personaggio.getNome());
		return s.toString();
	}

	/**
	 * Controlla se un attrezzo esiste nella stanza (uguaglianza sul nome).
	 * @return true se l'attrezzo esiste nella stanza, false altrimenti
	 */
	public boolean hasAttrezzo(String nomeAttrezzoCercato) {
		return (this.nome2attrezzo.containsKey(nomeAttrezzoCercato));
	}

	/**
	 * Restituisce l'attrezzo nomeAttrezzo se presente nella stanza.
	 * @param string nomeAttrezzo
	 * @return l'attrezzo presente nella stanza.
	 * 		   null se l'attrezzo non e' presente.
	 */
	public Attrezzo getAttrezzo(String nomeAttrezzoCercato) {
		return this.nome2attrezzo.get(nomeAttrezzoCercato);
	}

	/**
	 * Rimuove un attrezzo dalla stanza (ricerca in base al nome).
	 * @param string nomeAttrezzo
	 * @return true se l'attrezzo e' stato rimosso, false altrimenti
	 */
	public boolean removeAttrezzo(Attrezzo attrezzo) {
		return this.nome2attrezzo.remove(attrezzo.getNome()) != null;
	}
	
	/**
	 * Restituisce un nuovo array con le direzioni percorribli dalla stanza.
	 * @return array con le direzioni percorribli
	 */
	public String[] getDirezioni() {
		return this.uscite.keySet().toArray(new String[0]);
	}
	
	public AbstractPersonaggio getPersonaggio() {
		return this.personaggio;
	}
	
	public void setPersonaggio(AbstractPersonaggio personaggio) {
		this.personaggio = personaggio;
	}
	
	@Override
	public boolean equals(Object o){
		Stanza stanza=(Stanza)o;
		return (this.getNome().equals(stanza.getNome()));
	}

	@Override
	public int hashCode() {
		return this.getNome().hashCode();
	}

}