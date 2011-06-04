package a08;

/**
 * Benchmarkklasse fuer die Implementierung mit Adjazenz-Liste
 * 
 * @author Tugend und Laster
 */
public class ListBenchmark extends ListGraph{

	int ops;

	/**
	 * Methode der Oberklasse um Zaehler erweitert, Ops werden hier fuer 
	 * Komplexitaet inkrementiert und anschliessend die Methode der Oberklasse
	 * aufgerufen.
	 */
	@Override
	protected void getAdjacenciesFor(Node node, int[] adjancencyArr, int i) {
		ops++;
		super.getAdjacenciesFor(node, adjancencyArr, i);
	}

	/**
	 * Methode der Oberklasse um Zaehler erweitert, Ops werden hier fuer 
	 * Komplexitaet inkrementiert und anschliessend die Methode der Oberklasse
	 * aufgerufen.
	 */
	@Override
	protected void getWeightsFor(Node node, int[] weightArr, int i) {
		ops++;
		super.getWeightsFor(node, weightArr, i);
	}

	/**
	 * Methode der Oberklasse um Zaehler erweitert, Ops werden hier fuer 
	 * Komplexitaet inkrementiert und anschliessend die Methode der Oberklasse
	 * aufgerufen.
	 */
	@Override
	protected int getHeightFor(int height, Edge edge) {
		ops++;
		return super.getHeightFor(height, edge);
	}
	
	/**
	 * setter zum Resetten des Op-Zaehlers
	 */
	public void resetOps() {
		this.ops = 0;
	}
}
