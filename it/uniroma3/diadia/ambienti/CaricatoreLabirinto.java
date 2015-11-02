package it.uniroma3.diadia.ambienti;

import it.uniroma3.diadia.FormatoFileNonValidoException;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.personaggi.AbstractPersonaggio;

import java.io.*;
import java.util.*;

public class CaricatoreLabirinto {

	/* prefisso di una singola riga di testo contenente tutti i nomi delle stanze */
	private static final String STANZE_MARKER = "Stanze:";       
	
//	/* prefisso di una singola riga di testo contenente tutti i nomi delle stanze magiche */
//	private static final String STANZE_MAGICHE_MARKER = "Stanze magiche:"; 
//	
//	/* prefisso di una singola riga di testo contenente tutti i nomi delle stanze buie */
//	private static final String STANZE_BUIE_MARKER = "Stanze buie:";
//	
//	/* prefisso di una singola riga di testo contenente tutti i nomi delle stanze bloccate */
//	private static final String STANZE_BLOCCATE_MARKER = "Stanze bloccate:"; 

	/* prefisso di una singola riga contenente il nome della stanza iniziale */
	private static final String STANZA_INIZIALE_MARKER = "Inizio:";    

	/* prefisso della riga contenente il nome stanza vincente */
	private static final String STANZA_VINCENTE_MARKER = "Vincente:";  

	/* prefisso della riga contenente le specifiche degli attrezzi da collocare nel formato <nomeAttrezzo> <peso> <nomeStanza> */
	private static final String ATTREZZI_MARKER = "Attrezzi:";

	/* prefisso della riga contenente le specifiche dei collegamenti tra stanza nel formato <nomeStanzaDa> <direzione> <nomeStanza> */
	private static final String USCITE_MARKER = "Uscite:";
	
	/* prefisso della riga contenente le specifiche dei personaggi da collocare nelle stanze nel formato <nomePersonaggio> <nomeStanza> */
	private static final String PERSONAGGI_MARKER = "Personaggi:"; 

	/*
	 *  Esempio di un possibile file di specifica di un labirinto (vedi POO-26-eccezioni-file.pdf)

		Stanze: biblioteca, N10, N11
		Inizio: N10
		Vincente: N11
		Attrezzi: martello 10 biblioteca, pinza 2 N10
		Uscite: biblioteca nord N10, biblioteca sud N11

	 */
	private LineNumberReader reader;

	private Map<String, Stanza> nome2stanza;
	private Stanza stanzaIniziale;
	private Stanza stanzaVincente;


	public CaricatoreLabirinto(String nomeFile) throws FileNotFoundException {
		this.nome2stanza = new HashMap<String,Stanza>();
		this.reader = new LineNumberReader(new FileReader(nomeFile));
	}
	
	public CaricatoreLabirinto(StringReader reader) {
		this.nome2stanza = new HashMap<String,Stanza>();
		this.reader = new LineNumberReader(reader);
	}

	public void carica() throws FormatoFileNonValidoException {
		try {
			this.leggiECreaStanze();
			this.leggiInizialeEVincente();
			this.leggiECollocaAttrezzi();
			this.leggiEImpostaUscite();
			this.leggiECollocaPersonaggi();
		} finally {
			try {
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}
		}

	}

	private String leggiRigaCheCominciaPer(String marker) throws FormatoFileNonValidoException {
		try {
			String riga = this.reader.readLine();
			check(riga.startsWith(marker),"era attesa una riga che cominciasse per "+marker);
			return riga.substring(marker.length());
		} catch (IOException e) {
			throw new FormatoFileNonValidoException(e.getMessage());
		}
	}

	private void leggiECreaStanze() throws FormatoFileNonValidoException  {
		String specificheStanze = this.leggiRigaCheCominciaPer(STANZE_MARKER);
		for(String specificaStanza : separaStringheAlleVirgole(specificheStanze)) {
			String nomeStanza = null;
			String tipoStanza = null;
			Stanza stanza = null;
			try (Scanner scannerLinea = new Scanner(specificaStanza)) {
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il nome della stanza."));
				nomeStanza = scannerLinea.next();
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il tipo della stanza " + nomeStanza + "."));
				tipoStanza = scannerLinea.next();

				String nomeClasse = "it.uniroma3.diadia.ambienti." + tipoStanza;
				stanza = (Stanza)Class.forName(nomeClasse).newInstance();
				stanza.setNome(nomeStanza);
				this.nome2stanza.put(nomeStanza, stanza);
			} catch (Exception e) {
				throw new FormatoFileNonValidoException(e.getMessage());
			}
		}
	}
	
//	private void leggiECreaStanzeMagiche() throws FormatoFileNonValidoException  {
//		String nomiStanze = this.leggiRigaCheCominciaPer(STANZE_MAGICHE_MARKER);
//		for(String nomeStanza : separaStringheAlleVirgole(nomiStanze)) {
//			Stanza stanza = new StanzaMagica(nomeStanza);
//			this.nome2stanza.put(nomeStanza, stanza);
//		}
//	}
//
//	private void leggiECreaStanzeBloccate() throws FormatoFileNonValidoException  {
//		String nomiStanze = this.leggiRigaCheCominciaPer(STANZE_BLOCCATE_MARKER);
//		for(String nomeStanza : separaStringheAlleVirgole(nomiStanze)) {
//			Stanza stanza = new StanzaBloccata(nomeStanza);
//			this.nome2stanza.put(nomeStanza, stanza);
//		}
//	}
//	
//	private void leggiECreaStanzeBuie() throws FormatoFileNonValidoException  {
//		String nomiStanze = this.leggiRigaCheCominciaPer(STANZE_BUIE_MARKER);
//		for(String nomeStanza : separaStringheAlleVirgole(nomiStanze)) {
//			Stanza stanza = new StanzaBuia(nomeStanza);
//			this.nome2stanza.put(nomeStanza, stanza);
//		}
//	}
	
	private List<String> separaStringheAlleVirgole(String string) {
		List<String> result = new ArrayList<>();
		Scanner scanner = new Scanner(string);
		scanner.useDelimiter(",");
		try (Scanner scannerDiParole = scanner) {
			while(scannerDiParole.hasNext())
				result.add(scannerDiParole.next().trim());
		}
		return result;
	}


	private void leggiInizialeEVincente() throws FormatoFileNonValidoException {
		String nomeStanzaIniziale = null;
		nomeStanzaIniziale = this.leggiRigaCheCominciaPer(STANZA_INIZIALE_MARKER);
		check(this.isStanzaValida(nomeStanzaIniziale), nomeStanzaIniziale +" non definita");
		String nomeStanzaVincente = this.leggiRigaCheCominciaPer(STANZA_VINCENTE_MARKER);
		check(this.isStanzaValida(nomeStanzaVincente), nomeStanzaVincente + " non definita");
		this.stanzaIniziale = this.nome2stanza.get(nomeStanzaIniziale);
		this.stanzaVincente = this.nome2stanza.get(nomeStanzaVincente);
	}

	private void leggiECollocaAttrezzi() throws FormatoFileNonValidoException {
		String specificheAttrezzi = this.leggiRigaCheCominciaPer(ATTREZZI_MARKER);

		for(String specificaAttrezzo : separaStringheAlleVirgole(specificheAttrezzi)) {
			String nomeAttrezzo = null;
			String pesoAttrezzo = null;
			String nomeStanza = null; 
			try (Scanner scannerLinea = new Scanner(specificaAttrezzo)) {
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il nome di un attrezzo."));
				nomeAttrezzo = scannerLinea.next();
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il peso dell'attrezzo "+nomeAttrezzo+"."));
				pesoAttrezzo = scannerLinea.next();
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il nome della stanza in cui collocare l'attrezzo "+nomeAttrezzo+"."));
				nomeStanza = scannerLinea.next();
			}				
			posaAttrezzo(nomeAttrezzo, pesoAttrezzo, nomeStanza);
		}
	}

	private void posaAttrezzo(String nomeAttrezzo, String pesoAttrezzo, String nomeStanza) throws FormatoFileNonValidoException {
		int peso;
		try {
			peso = Integer.parseInt(pesoAttrezzo);
			Attrezzo attrezzo = new Attrezzo(nomeAttrezzo, peso);
			check(isStanzaValida(nomeStanza),"Attrezzo "+ nomeAttrezzo+" non collocabile: stanza " +nomeStanza+" inesistente");
			this.nome2stanza.get(nomeStanza).addAttrezzo(attrezzo);
		}
		catch (NumberFormatException e) {
			check(false, "Peso attrezzo "+nomeAttrezzo+" non valido");
		}
	}


	private boolean isStanzaValida(String nomeStanza) {
		return this.nome2stanza.containsKey(nomeStanza);
	}

	private void leggiEImpostaUscite() throws FormatoFileNonValidoException {
		String specificheUscite = this.leggiRigaCheCominciaPer(USCITE_MARKER);
		
		for(String specificaUscita : separaStringheAlleVirgole(specificheUscite)) {
			try (Scanner scannerDiLinea = new Scanner(specificaUscita)) {			

				while (scannerDiLinea.hasNext()) {
					check(scannerDiLinea.hasNext(),msgTerminazionePrecoce("le uscite di una stanza."));
					String stanzaPartenza = scannerDiLinea.next();
					check(scannerDiLinea.hasNext(),msgTerminazionePrecoce("la direzione di una uscita della stanza "+stanzaPartenza));
					String dir = scannerDiLinea.next();
					check(scannerDiLinea.hasNext(),msgTerminazionePrecoce("la destinazione di una uscita della stanza "+stanzaPartenza+" nella direzione "+dir));
					String stanzaDestinazione = scannerDiLinea.next();

					impostaUscita(stanzaPartenza, dir, stanzaDestinazione);
				}
			} 
		}
	}
	
	private String msgTerminazionePrecoce(String msg) {
		return "Terminazione precoce del file prima di leggere "+msg;
	}

	private void impostaUscita(String stanzaDa, String dir, String nomeA) throws FormatoFileNonValidoException {
		check(isStanzaValida(stanzaDa),"Stanza di partenza sconosciuta "+dir);
		check(isStanzaValida(nomeA),"Stanza di destinazione sconosciuta "+ dir);
		Stanza partenzaDa = this.nome2stanza.get(stanzaDa);
		Stanza arrivoA = this.nome2stanza.get(nomeA);
		partenzaDa.impostaStanzaAdiacente(dir, arrivoA);
	}
	
	private void leggiECollocaPersonaggi() throws FormatoFileNonValidoException {
		String specifichePersonaggi = this.leggiRigaCheCominciaPer(PERSONAGGI_MARKER);
		for(String specificaPersonaggio : separaStringheAlleVirgole(specifichePersonaggi)) {
			String nomePersonaggio = null;
			String nomeStanza = null;
			AbstractPersonaggio personaggio = null;
			try (Scanner scannerLinea = new Scanner(specificaPersonaggio)) {
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il nome di un personaggio."));
				nomePersonaggio = scannerLinea.next();
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("la stanza dove collocare il personaggio " + nomePersonaggio + "."));
				nomeStanza = scannerLinea.next();

				String nomeClasse = "it.uniroma3.diadia.personaggi.";
				nomeClasse += Character.toUpperCase(nomePersonaggio.charAt(0));
				nomeClasse += nomePersonaggio.substring(1);
				personaggio = (AbstractPersonaggio)Class.forName(nomeClasse).newInstance();
				personaggio.setNome(nomePersonaggio);
				this.nome2stanza.get(nomeStanza).setPersonaggio(personaggio);
			} catch (Exception e) {
				throw new FormatoFileNonValidoException(e.getMessage());
			}
		}
			
	}

	final private void check(boolean condizioneCheDeveEsseraVera, String messaggioErrore) throws FormatoFileNonValidoException {
		if (!condizioneCheDeveEsseraVera)
			throw new FormatoFileNonValidoException("Formato file non valido [" + this.reader.getLineNumber() + "] "+messaggioErrore);		
	}

	public Stanza getStanzaIniziale() {
		return this.stanzaIniziale;
	}

	public Stanza getStanzaVincente() {
		return this.stanzaVincente;
	}
	
	public Map<String, Stanza> getNome2Stanza() {
		return this.nome2stanza;
	}
}
