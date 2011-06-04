package a08;

/**
 * Diese Klasse repraesentiert einen Knoten mit Zusatzinformationen fuer den
 * Dijkstra-Algorithmus
 * 
 * @author Tugend und Laster
 */
public class CostNode {

	int data;
	CostNode pred;
	int cost;
	boolean marked;

	/**
	 * Dieser Konstruktor initialsisiert einen Costnode mit den entsprechend
	 * uebergeben Werten
	 * 
	 * @param data Name des Knoten
	 * @param pred Vorgaengerknoten
	 * @param cost Gewichtung
	 * @param marked <b>true</b>, wenn Knoten bereits abgearbeitet, 
	 * <b>false</b> falls nicht
	 */
	public CostNode(int data, CostNode pred, int cost, boolean marked) {
		super();
		this.data = data;
		this.pred = pred;
		this.cost = cost;
		this.marked = marked;
	}

	/**
	 * Liefert eine uebersichtliche String-Praesentation eines Costnodes
	 * (Dijkstra)
	 * 
	 * @return String-Repraesentation eines Costnode
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("node: " + data + "\t\t");
		if (pred == null) {
			sb.append("vorgaenger: null" + "\t");
		} else if (!(pred.equals(this))) {
			sb.append("vorgaenger: " + pred.data + "\t\t");
		} else {
			sb.append("vorgaenger: " + this.data + "\t\t");
		}
		sb.append("kosten: " + cost + "\t");
		sb.append("fertig: " + marked);
		return sb.toString();
	}
}
