package it.uniroma3.diadia;

import java.io.FileNotFoundException;

import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.comandi.Comando;
import it.uniroma3.diadia.comandi.ComandoFine;
import it.uniroma3.diadia.comandi.FabbricaDiComandi;
import it.uniroma3.diadia.comandi.FabbricaDiComandiRiflessiva;


/**
 *  Classe principale di diadia, un semplice gioco di ruolo ambientato al dia.
 *  Per giocare crea un'istanza di questa classe e invoca il letodo gioca
 *
 *  Questa e' la classe principale crea e istanzia tutte le altre
 *
 */

public class DiaDia {
	private Partita partita;
	private Labirinto labirinto;
	private InterfacciaUtente interfacciaUtente;

	public DiaDia() throws FileNotFoundException, FormatoFileNonValidoException {
		this.labirinto = new Labirinto("campusdiadia.txt");
		this.partita = new Partita(labirinto);
		this.interfacciaUtente = new InterfacciaUtenteConsole();
	}

	public void gioca() {	
		String istruzione;
		do		
			istruzione = interfacciaUtente.prendiIstruzione();
		while (!processaIstruzione(istruzione));	
	}   


	/**
	 * Processa una istruzione 
	 * @param string nome dell'istruzione
	 * @return true se l'istruzione e' eseguita e il gioco continua, false altrimenti
	 */
	private boolean processaIstruzione(String istruzione) {

		Comando comandoDaEseguire; 
		FabbricaDiComandi factory = new FabbricaDiComandiRiflessiva();

		comandoDaEseguire = factory.costruisciComando(istruzione); 
		this.interfacciaUtente.mostraMessaggio(comandoDaEseguire.esegui(this.partita)); //Chiamata Polimorfa
		
		if (this.partita.vinta(this.labirinto)) 
			System.out.println("Hai vinto!"); 
		if (!this.partita.giocatoreIsVivo()) { 
			System.out.println("GAME OVER!!! \nHai esaurito i CFU..."); 
			comandoDaEseguire=new ComandoFine();
			comandoDaEseguire.esegui(this.partita); 
		}
		return this.partita.isFinita(this.labirinto); 
	}

	/**
	 * Restituisce il labirinto della partita
	 * @return il labirinto della partita
	 */
	public Labirinto getLabirinto(){
		return this.labirinto;
	}

	public static void main(String[] argc) throws FileNotFoundException, FormatoFileNonValidoException {
		DiaDia gioco = new DiaDia();
		gioco.gioca();
	}
}