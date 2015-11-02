package it.uniroma3.diadia;

import java.util.Scanner;

public class InterfacciaUtenteConsole implements InterfacciaUtente {

	@Override
	public void mostraMessaggio(String messaggio) {
		System.out.println(messaggio);
	}

	@Override
	public String prendiIstruzione() {
		String istruzione = null;
        Scanner scannerDiLinee = null; 
        try {
        	scannerDiLinee = new Scanner(System.in);
        	istruzione = scannerDiLinee.nextLine();
        }
        catch(Exception e) {
        	scannerDiLinee.close();
        }
        return istruzione;
	}

}
