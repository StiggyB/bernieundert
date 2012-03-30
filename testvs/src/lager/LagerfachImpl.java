package lager;

/* Verteilte Systeme Praktikum
 * Aufgabe 1
 * Praktikumsgruppe 2
 * 
 * ------Teammitglieder-----
 * - Falk Böschen  1940016 -
 * - Laura Knetter 1938302 -
 * -------------------------
 */

import lagern.*;
import lagern.LagerfachPackage.*;


public class LagerfachImpl extends LagerfachPOA{

	private String name;
	private String besitzer;
	private int anzahl;
	
	public String getName() 
	{
	// return Storage name
		return name;
	}
	
	public LagerfachImpl(String name, String besitzer) 
	{
	// Create new Storage, set name and holder, init number of parts
		super();
		this.name = name ;
		besitzer(besitzer);
		this.anzahl = 0;
	}

	@Override
	public int anzahl() 
	{
	// return number of parts stored 
		return this.anzahl;
	}

	@Override
	public synchronized void auslagern(String user, int anzahl) throws invalidCount,
			notEnoughPieces 
	{
	// Test if "anzahl" is suitable
		if(anzahl <= 0)
			throw new invalidCount("Error");
		if(anzahl() < anzahl)
			throw new notEnoughPieces("Error");
	// If "anzahl" is ok then remove parts an signal monitors
		this.anzahl -= anzahl;
		LagerImpl.signalMonitor(user," hat " + anzahl+ " Teile aus Lagerfach " + getName() + " entfernt. Neuer Lagerstand : " + anzahl());
	}

	@Override
	public String besitzer() 
	{
	// return Storage holder
		return this.besitzer;
	}

	@Override
	public void besitzer(String newBesitzer) 
	{
	// set Storage holder
		this.besitzer = newBesitzer;
	}

	@Override
	public synchronized void einlagern(String user, int anzahl) throws invalidCount 
	{
	// Test if "anzahl" is suitable
		if(anzahl <= 0)
			throw new invalidCount("Error");
	// If "anzahl" is ok insert parts and signal onitors
		this.anzahl += anzahl;
		LagerImpl.signalMonitor(user," hat " + anzahl+ " Teile in Lagerfach " + getName() + " eingelagert. Neuer Lagerstand : " + anzahl());
	}
	@Override
	public String toString()
	{
	// returns a String that represents all informations about the Storage
		return "Name : " + getName() + " Besitzer : " + besitzer()+ " Teileanzahl : " + anzahl();
		
	}
}
