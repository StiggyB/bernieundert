package a08;

import java.io.File;
import java.io.FileNotFoundException;

/**
 * Interface für die Implementierung einer Queue nach dem TI3-AD_Script.
 * Das Interface wurde um die Methode size() erweitert.
 * 
 * @author Tugend und Laster
 */
public interface IGraph {

	void readXML(File xml) throws FileNotFoundException;

	int[] getAdjacencies(int nodeIdx);
	
	int[] getWeights(int nodeIdx);
	
	int getLowestNodeWeight(int nodeIdx);
	
	int getOrder();
	
	int getHeight();
	
}
