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
		this.currentUser = userName;
		long nonce = this.generateNonce();

		// TGS-Ticket holen (zunaechst TicketResponse)
		TicketResponse tgsResponse = this.myKDC.requestTGSTicket(userName, this.myKDC.getName(), nonce);

		// TGS-Response entschluesseln
		if (tgsResponse.decrypt(this.generateSimpleKeyForPassword(password)) && tgsResponse.getNonce() == nonce) {
			// Wenn der Schluessel der richtige und tgsResponse noch nicht
			// entschluesselt
			// Auswerten und speichern
			// TGS-Ticeket
			this.tgsTicket = tgsResponse.getResponseTicket();
			// TGS-Session Key
			this.tgsSessionKey = tgsResponse.getSessionKey();

			this.tgsTicket.print();

			returnCode = true;
		}

		// Passwort im Hauptspeicher loeschen
		Arrays.fill(password, ' ');

		return returnCode;
	}

	public boolean showFile(String serverName, String filePath) {
		boolean returnCode = false;
		long nonce = this.generateNonce();

		// Login pruefen: TGS-Ticket vorhanden?
		if (this.tgsTicket != null) {
			// Serverticket vorhanden? Wenn nicht, neues Serverticket anfordern
			// (Schritt 3: requestServerTicket) und Antwort auswerten
			if (this.serverTicket == null) {
				// Authentification erzeugen
				Auth tgsAuth = new Auth(this.currentUser, System.currentTimeMillis());
				// Verschluesseln mit K C-TGS
				tgsAuth.encrypt(this.tgsSessionKey);
				tgsAuth.print();

				TicketResponse ticketRes = myKDC.requestServerTicket(this.tgsTicket, tgsAuth, serverName, nonce);

				// 4 Response auswerten
				// Entschluesseln mit K C-TGS
				if (ticketRes.decrypt(this.tgsSessionKey) && ticketRes.getNonce() == nonce) {
					this.serverTicket = ticketRes.getResponseTicket();

					this.serverSessionKey = ticketRes.getSessionKey();

					this.serverTicket.print();
				}
			}

			// Service beim Server anfordern (Schritt 5: requestService)
			// Authentification erzeugen
			Auth serverAuth = new Auth(this.currentUser, System.currentTimeMillis());
			// Verschluesseln mit K C-S
			serverAuth.encrypt(this.serverSessionKey);
			serverAuth.print();

			returnCode = this.myFileserver.requestService(this.serverTicket, serverAuth, "showFile", filePath);
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
