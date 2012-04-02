package server;

import lagern.FachPOA;
import lagern.FachPackage.exInvalidCount;
import lagern.FachPackage.exNotEnoughPieces;

public class LagerfachImpl extends FachPOA {

	private String fachname;
	private String user;
	private int anzahl;
	private final static int MAX_LAGERTEILE = Integer.MAX_VALUE;
	private LagerImpl lager;

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
	
	public String getUser() {
		return user;
	}

	public void setLager(LagerImpl lager) {
		this.lager = lager;
	}

	@Override
	public synchronized void einlagern(String user, int anzahl)
			throws exInvalidCount {
		if (anzahl <= 0 || (this.anzahl + anzahl) > MAX_LAGERTEILE) {
			throw new exInvalidCount("einlagern(): Ungueltige Anzahl!");
		}

		this.anzahl += anzahl;
		
		lager.benachrichtigeMonitore(user, "einlagern(): '" + anzahl + "' Teile in Fach '" + this.fachname + "' eingelagert!");
	}

	@Override
	public synchronized void auslagern(String user, int anzahl) throws exInvalidCount, exNotEnoughPieces {
		if (anzahl <= 0) {
			throw new exInvalidCount("auslagern(): Ungueltige Anzahl!");
		} else if (anzahl > this.anzahl) {
			throw new exNotEnoughPieces("auslagern(): Nicht genug Teile im Fach zum Auslagern!");
		}
		
		this.anzahl -= anzahl;
		
		lager.benachrichtigeMonitore(user, "auslagern(): '" + anzahl + "' Teile aus Fach '" + this.fachname + "' ausgelagert!");
	}


}
