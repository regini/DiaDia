package it.uniroma3.diadia.ambienti;

public class StanzaBloccata extends Stanza {

	final static private String ATTREZZO_SBLOCCANTE_STANZA_BLOCCATA = "chiave";
	final static private String NOME_DIREZIONE_BLOCCATA = "nord";

	private String attrezzoSbloccante;
	private String direzioneBloccata;

	public StanzaBloccata(String nome){
		this(nome, ATTREZZO_SBLOCCANTE_STANZA_BLOCCATA, NOME_DIREZIONE_BLOCCATA);
	}

	public StanzaBloccata(String nome, String attrezzoSbloccante, String direzioneBloccata) {
		super(nome);
		this.attrezzoSbloccante=attrezzoSbloccante;
		this.direzioneBloccata=direzioneBloccata;
	}
	public StanzaBloccata() {
		super();
		this.attrezzoSbloccante = ATTREZZO_SBLOCCANTE_STANZA_BLOCCATA;
		this.direzioneBloccata = NOME_DIREZIONE_BLOCCATA;
	}

	@Override
	public Stanza getStanzaAdiacente(String direzione) {
		if(!this.hasAttrezzo(this.attrezzoSbloccante) && direzione.equals(this.direzioneBloccata))
			return this;
		else
			return super.getStanzaAdiacente(direzione);
	}

	@Override
	public String getDescrizione() {
		if(!this.hasAttrezzo(this.attrezzoSbloccante))
			return super.getDescrizione()+"\nL'uscita a "+this.direzioneBloccata+" e' bloccata ti serve l'attrezzo sbloccante: " + this.attrezzoSbloccante;
		else
			return super.getDescrizione();
	}
}

