package a08;

import java.io.File;
import java.io.FileNotFoundException;

/**
 * Interface fuer die Implementierung eines Graphen, der 
 * als XML-Datei vorliegt.
 * 
 * @author Tugend und Laster
 */
public interface IGraph {

	/**
	 * Liest einen Graphen aus einer XML-Datei in die 
	 * Datenstruktur ein (Adjazenzliste oder Matrix)
	 * 
	 * @param xml XML-Datei des Graphen
	 * @throws FileNotFoundException
	 */
	void readXML(File xml) throws FileNotFoundException;

	/**
	 * Liefert alle Nachbarn eines Knotens 
	 * @param nodeIdx zu pruefender Knoten
	 * @return alle Nachbarn des Knotens
	 */
	int[] getAdjacencies(int nodeIdx);
	
	/**
	 * Liefert alle Kosten von allen Kanten eiens Knotens
	 * 
	 * @param nodeIdx zu pruefender Knoten
	 * @return alle Kosten
	 */
	int[] getWeights(int nodeIdx);
	
	/**
	 * Liefert die niedrigstens Kosten von allen Kanten des 
	 * uebergebenen Knotens
	 * 
	 * @param nodeIdx zu pruefender Knoten
	 * @return niedrigste Kosten
	 */
	int getLowestNodeWeight(int nodeIdx);
	
	/**
	 * Liefert die Anzahl der Knoten des Graphen
	 * 
	 * @return Anzahl der Knoten
	 */
	int getOrder();
	
	/**
	 * Liefert die Anzahl der Kanten des Graphen
	 * 
	 * @return Anzahl der Kanten
	 */
	int getHeight();
	
}
