package a08;

import java.util.List;

/**
 * Knotenklasse, die Knoten in einem Graph repraesentieren
 * 
 * @author Tugend und Laster
 */
public class Node {

	int data;
	List<Edge> adjacencies;
	
	/**
	 * Konstruktor fuer das Anlegen eines Knotens fuer den
	 * Graphen
	 * 
	 * @param data Name des Knoten
	 * @param adjacencies Liste von Nachbarn des Knoten
	 */
	public Node(int data, List<Edge> adjacencies) {
		super();
		this.data = data;
		this.adjacencies = adjacencies;
	}
	
	/**
	 * Hilfsmethode: Gibt den Namen (Data) des Knotens zurueck
	 * 
	 * @return Name (Data) des Knotens
	 */
	@Override
	public String toString() {
		return data+"";
	}
}
