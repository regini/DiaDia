package it.uniroma3.diadia.ambienti;

public class StanzaBuia extends Stanza {

	final static private String ATTREZZO_PARTICOLARE_STANZA_BUIA = "lanterna";
	private String attrezzoParticolare;

	public StanzaBuia(String nome){
		this(nome, ATTREZZO_PARTICOLARE_STANZA_BUIA);
	}

	public StanzaBuia(String nome, String attrezzoParticolare){
		super(nome);
		this.attrezzoParticolare=attrezzoParticolare;
	}
	
	public StanzaBuia() {
		super();
		this.attrezzoParticolare = ATTREZZO_PARTICOLARE_STANZA_BUIA;
	}

	@Override
	public String getDescrizione() {
		if(!this.hasAttrezzo(this.attrezzoParticolare))
			return this.getNome() +"\nQui c'è un buio pesto!";
		else
			return super.getDescrizione();
	}

}
