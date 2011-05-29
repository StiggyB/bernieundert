package a08;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class MatrixGraph implements IGraph {

//	Node[][] adjacencyMatrix = new Node[3][3];
	int[][] adjancencyMatrix = new int[3][3];
	

	@Override
	public void readXML(File xml) throws FileNotFoundException {
		// TODO Auto-generated method stub

	}

//	@Override
	public int[] getAdjacencys(int nodeIdx) {
		if(nodeIdx < 1 || nodeIdx > adjancencyMatrix.length) {
			//Do something!
		}
		int[] adjacencyIndexArr = new int[adjancencyMatrix.length];
		
		Node[] adjecencyArr = new Node[adjancencyMatrix.length];
		List<Edge> list = new ArrayList<Edge>();
		list.add(new Edge(null, 3));
		Node node = new Node(1, list);
		adjecencyArr[0] = node;
		
		for (int i = 0; i < adjancencyMatrix.length; i++) {
			if(adjancencyMatrix[nodeIdx-1][i] != 0) {
				adjacencyIndexArr[i] = i + 1;
			}
		}
		return adjacencyIndexArr;
	}

	@Override
	public int[] getWeights(int nodeIdx) {
		if(nodeIdx < 1 || nodeIdx > adjancencyMatrix.length) {
			//Do something!
		}
		int[] weightArr = new int[adjancencyMatrix.length];
		for (int i = 0; i < adjancencyMatrix.length; i++) {
			weightArr[i] = adjancencyMatrix[nodeIdx-1][i];
		}
		return weightArr;
	}

	@Override
	public int getOrder() {
		return adjancencyMatrix.length;
	}

	@Override
	public int getHeight() {
		int height = 0;
		for (int i = 0; i < adjancencyMatrix.length; i++) {
			for (int j = 0; j < adjancencyMatrix[0].length; j++) {
				if(adjancencyMatrix[i][j] != 0) {
					height++;
				}
			}
		}
		return height;
	}

}
