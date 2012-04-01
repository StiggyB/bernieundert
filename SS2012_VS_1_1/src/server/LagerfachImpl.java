package server;

import lagern.FachPOA;
import lagern.FachPackage.exInvalidCount;
import lagern.FachPackage.exNotEnoughPieces;

public class LagerfachImpl extends FachPOA {

	private String fachname;
	private String user;
	private int anzahl;
	private final static int MAX_LAGERTEILE = Integer.MAX_VALUE;

	public LagerfachImpl(String user, String name) {

		this.fachname = name;
		this.user = user;

	}

	@Override
	public int anzahl() {
		return anzahl;
	}

	@Override
	public String name() {
		return fachname;
	}
	
	//eigentlich nicht benoetigt...
	public String getUser() {
		return user;
	}

	// TODO: liste an teilen<String> needed oder nur teile durch erhoehung der
	// anzahl einlagern?
	@Override
	public synchronized void einlagern(String user, int anzahl)
			throws exInvalidCount {
		if (anzahl <= 0 || (this.anzahl + anzahl) > MAX_LAGERTEILE) {
			throw new exInvalidCount("einlagern(): Ungueltige Anzahl!");
		}

		this.anzahl += anzahl;
		
		LagerImpl.benachrichtigeMonitore(user, "einlagern(): '" + anzahl + "' Teile in Fach '" + this.fachname + "' eingelagert!");
	}

	@Override
	public synchronized void auslagern(String user, int anzahl) throws exInvalidCount, exNotEnoughPieces {
		if(anzahl <= 0){
			throw new exInvalidCount("auslagern(): Ungueltige Anzahl!");
		} else if(anzahl > this.anzahl) {
			throw new exNotEnoughPieces("auslagern(): Nicht genug Teile im Fach zum Auslagern!");
		}
		
		this.anzahl -= anzahl;
		
		LagerImpl.benachrichtigeMonitore(user, "auslagern(): '" + anzahl + "' Teile aus Fach '" + this.fachname + "' ausgelagert!");
	}

}
