package kerberos;

/* Simulation einer Kerberos-Session mit Zugriff auf einen Fileserver
 /* Client-Klasse
 */

import java.util.*;

public class Client extends Object {

	private KDC myKDC;

	private Server myFileserver;

	private String currentUser;

	private Ticket tgsTicket = null;

	private long tgsSessionKey; // K(C,TGS)

	private Ticket serverTicket = null;

	private long serverSessionKey; // K(C,S)

	// Konstruktor
	public Client(KDC kdc, Server server) {
		myKDC = kdc;
		myFileserver = server;
	}

	public boolean login(String userName, char[] password) {
		boolean returnCode = false;
		currentUser = userName;
		long nonce = generateNonce();

		// TGS-Ticket holen (zunaechst TicketResponse)
		TicketResponse tgsResponse = myKDC.requestTGSTicket(userName, myKDC.getName(), nonce);

		// TGS-Response entschlüsseln und auswerten
		if (tgsResponse == null) {
			System.out.println("Kein Response Ticket erhalten");
			return false;
		} else {
			// vorher
			System.out.println("vorher...");
			tgsResponse.print();
		}
		if (!tgsResponse.decrypt(generateSimpleKeyForPassword(password))) {
			System.out.println("Response konnte nicht entschlüsselt werden");
			return false;
		}
		if (tgsResponse.getNonce() != nonce) {
			System.out.println("Nonce-Wert überprüfung fehlgeschlagen");
			return false;
		} else {

			// nachher
			// TicketResponse ausgeben
			System.out.println("nachher...");
			tgsResponse.print();

			// Ticket extrahieren ({Ticket}Ktgs
			tgsTicket = tgsResponse.getResponseTicket();

			// TGS-Session-Key (Kc-tgs)
			tgsSessionKey = tgsResponse.getSessionKey();

			tgsTicket.print();
			returnCode = true;

			// Passwort im Hauptspeicher loeschen
			Arrays.fill(password, ' ');
		}

		return returnCode;
	}

	public boolean showFile(String serverName, String filePath) {
		boolean returnCode = false;
		long nonce = generateNonce();

		// Login pruefen: TGS-Ticket vorhanden?
		if (tgsTicket != null) {
			// Serverticket vorhanden? Wenn nicht, neues Serverticket anfordern
			// und Antwort auswerten
			if (serverTicket == null) {
				// Authentification erzeugen
				Auth tgsAuth = new Auth(currentUser, System.currentTimeMillis());
				// Verschluesseln mit tgs-Session-Key (Kc-tgs)
				tgsAuth.encrypt(tgsSessionKey);
				tgsAuth.print();

				TicketResponse tgsResponse = myKDC.requestServerTicket(tgsTicket, tgsAuth, serverName, nonce);

				// Response auswerten
				// Entschluesseln mit tgs-Session-Key (Kc-tgs)
				if (tgsResponse.decrypt(tgsSessionKey) && tgsResponse.getNonce() == nonce) {
					// bingo!
					serverTicket = tgsResponse.getResponseTicket();
					serverSessionKey = tgsResponse.getSessionKey();
					serverTicket.print();
				}
			}
			// Serverticket vorhanden
			// Service beim Server anfordern
			// Authentification erzeugen
			Auth serverAuth = new Auth(currentUser, System.currentTimeMillis());
			// Verschluesseln mit serverSessionKey (Kc-s)
			serverAuth.encrypt(serverSessionKey);
			serverAuth.print();

			returnCode = myFileserver.requestService(serverTicket, serverAuth, "showFile", filePath);
		}

		return returnCode;
	}

	/* *********** Hilfsmethoden **************************** */

	private long generateSimpleKeyForPassword(char[] pw) {
		// Liefert einen Schlüssel für ein Passwort zurück, hier simuliert als
		// long-Wert
		long pwKey = 0;
		for (int i = 0; i < pw.length; i++) {
			pwKey = pwKey + pw[i];
		}
		return pwKey;
	}

	private long generateNonce() {
		// Liefert einen neuen Zufallswert
		long rand = (long) (100000000 * Math.random());
		return rand;
	}
}
