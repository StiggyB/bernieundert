package a08;

import java.io.File;
import java.io.FileNotFoundException;

public class MatrixGraph implements IGraph {

	private Node[][] adjacencyMatrix = new Node[3][3];
	

	@Override
	public void readXML(File xml) throws FileNotFoundException {
		// TODO Auto-generated method stub

	}

	@Override
	public Node[] getAdjacencys(Node node) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int[] getWeights(Node node) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getOrder() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getHeight() {
		// TODO Auto-generated method stub
		return 0;
	}

}
