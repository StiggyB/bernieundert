package a08;

/**
 * Benchmarkklasse fuer die Implementierung mit Adjazenz-Matrix
 * 
 * @author Tugend und Laster
 */
public class MatrixBenchmark extends MatrixGraph{
	
	int ops = 0;
	
	/**
	 * Methode der Oberklasse um Zaehler erweitert, Ops werden hier fuer 
	 * Komplexitaet inkrementiert und anschliessend die Methode der Oberklasse
	 * aufgerufen.
	 */
	@Override
	protected int getAdjecenciesFor(int nodeIdx, int[] adjacencyIndexArr,
			int adjacencyIdx, int i) {
		ops++;
		return super.getAdjecenciesFor(nodeIdx, adjacencyIndexArr,
				adjacencyIdx, i);
	}

	/**
	 * Methode der Oberklasse um Zaehler erweitert, Ops werden hier fuer 
	 * Komplexitaet inkrementiert und anschliessend die Methode der Oberklasse
	 * aufgerufen.
	 */
	@Override
	protected int getWeightsFor(int nodeIdx, int[] weightArr, int weightIdx,
			int i) {
		ops++;
		return super.getWeightsFor(nodeIdx, weightArr, weightIdx, i);
	}

	/**
	 * Methode der Oberklasse um Zaehler erweitert, Ops werden hier fuer 
	 * Komplexitaet inkrementiert und anschliessend die Methode der Oberklasse
	 * aufgerufen.
	 */
	@Override
	protected int getCountOfAdjacenciesForNodeFor(int[] arr, int count, int i) {
		ops++;
		return super.getCountOfAdjacenciesForNodeFor(arr, count, i);
	}

	/**
	 * Methode der Oberklasse um Zaehler erweitert, Ops werden hier fuer 
	 * Komplexitaet inkrementiert und anschliessend die Methode der Oberklasse
	 * aufgerufen.
	 */
	@Override
	protected int getHeightFor(int height, int i, int j) {
		ops++;
		return super.getHeightFor(height, i, j);
	}

	/**
	 * setter zum Resetten des Op-Zaehlers
	 */
	public void resetOps() {
		this.ops = 0;
	}
}
