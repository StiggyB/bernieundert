package a08;

/**
 * Diese Klasse repraesentiert eine Kante/Verbindung zwischen
 * zwei Knoten des Graphen
 * 
 * @author Tugend und Laster
 */
public class Edge {

	Node node;
	int weight;
	
	/**
	 * Initialisiert einen Kante fuer einen Knoten mit 
	 * uebergebenen Kosten
	 * 
	 * @param node Knoten fuer den die Kante erzeugt wird
	 * @param weight Kosten der Kante
	 */
	public Edge(Node node, int weight) {
		super();
		this.node = node;
		this.weight = weight;
	}
	
	/**
	 * Hilfsmethode: Gibt die Daten einer Kante als String zurueck
	 * 
	 * @return Kanteninformationen als String
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("nachbar: " + node.toString() + " ");
		sb.append("kosten: " + weight);
		
		return sb.toString();
	}
}
