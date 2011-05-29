package a08;

import java.io.File;
import java.io.FileNotFoundException;

public interface IGraph {

	void readXML(File xml) throws FileNotFoundException;

	//TODO Which kind of design?
//	Node[] getAdjacencys(int nodeIdx);
	
	int[] getWeights(int nodeIdx);
	
	int getOrder();
	
	int getHeight();
	
}
