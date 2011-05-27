package a08;

import java.io.File;
import java.io.FileNotFoundException;

public interface IGraph {

	void readXML(File xml) throws FileNotFoundException;
	
	Node[] getAdjacencys(Node node);
	
	int[] getWeights(Node node);
	
	int getOrder();
	
	int getHeight();
	
}
