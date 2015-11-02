package it.uniroma3.diadia.personaggi;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.ComparatoreNumeroAttrezzi;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class Strega extends AbstractPersonaggio {
	private static final String PRESENTAZIONE = "\nBirichina son io sai,\n" + 
			"ma se ben ti comporterai,\n" + "un aiuto tu avrai!";
	private static final String MESSAGGIO_BUONO = "Vedo che sei educato,\n"+
			"un dono ti sara' dato,\n" + "nella stanza con piu' attrezzi verrai mandato.\n";
	private static final String MESSAGGIO_CATTIVO = "Maleducato piu' che mai,\n"+
			"intruso per me tu sarai,\n" + "nella stanza con pochi attrezzi marcerai.\n";
	private static final String MESSAGGIO_REGALO = "IHIHIHIHIH! Grazie del regalo!";
	
	public Strega(String nome, String presentazione) {
		super(nome, presentazione);
	}
	
	public Strega() {
		super();
		super.setPresentazione(PRESENTAZIONE);
	}

	@Override
	public String agisci(Partita partita) {
		Stanza stanzaCorrente = partita.getStanzaCorrente();
		List<Stanza> stanzeAdiacenti = new ArrayList<Stanza>();
		Set<String> direzioni = stanzaCorrente.getStanzeAdiacenti().keySet();
		for (String direzione : direzioni) {
			Stanza uscita = stanzaCorrente.getStanzaAdiacente(direzione);
			stanzeAdiacenti.add(uscita);
		}
		if (this.haSalutato()) {
			partita.setStanzaCorrente(Collections.max(stanzeAdiacenti,new ComparatoreNumeroAttrezzi()));
			return MESSAGGIO_BUONO + "Sei stato trasferito in:" + partita.getStanzaCorrente().getDescrizione();
		}
		else {
			partita.setStanzaCorrente(Collections.min(stanzeAdiacenti,new ComparatoreNumeroAttrezzi()));
			return MESSAGGIO_CATTIVO + "Sei stato trasferito in:" + partita.getStanzaCorrente().getDescrizione();
		}
	}

	@Override
	public String riceviRegalo(Attrezzo attrezzo, Partita partita) {
		partita.getGiocatore().getBorsa().removeAttrezzo(attrezzo.getNome());
		return MESSAGGIO_REGALO;
	}

}
