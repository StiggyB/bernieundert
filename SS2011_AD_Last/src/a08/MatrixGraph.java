package a08;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class MatrixGraph implements IGraph {

	int[][] adjacencyMatrix = new int[3][3];
	

	@Override
	public void readXML(File xml) throws FileNotFoundException {
		// TODO Auto-generated method stub

	}

	@Override
	public int[] getAdjacencys(int nodeIdx) {
		if(nodeIdx < 1 || nodeIdx > adjacencyMatrix.length) {
			//Do something!
		}
		int[] adjacencyIndexArr = new int[adjacencyMatrix.length];
		for (int i = 0; i < adjacencyMatrix.length; i++) {
			if(adjacencyMatrix[nodeIdx-1][i] != 0) {
				adjacencyIndexArr[i] = i + 1;
			}
		}
		return adjacencyIndexArr;
	}

	@Override
	public int[] getWeights(int nodeIdx) {
		if(nodeIdx < 1 || nodeIdx > adjacencyMatrix.length) {
			//Do something!
		}
		int[] weightArr = new int[adjacencyMatrix.length];
		for (int i = 0; i < adjacencyMatrix.length; i++) {
			weightArr[i] = adjacencyMatrix[nodeIdx-1][i];
		}
		return weightArr;
	}
	
	@Override
	public int getLowestNodeWeight(int nodeIdx) {
		int[] adjacencyArr = getWeights(nodeIdx);
		int lowestWeight = adjacencyArr[0];
		int adjacencyIdx = 0;
		for (int i = 1; i < adjacencyArr.length; i++) {
			if(adjacencyArr[i] < lowestWeight) {
				//..
				adjacencyIdx = i;
			}
		}
		return adjacencyIdx;
	}
	
	@Override
	public int getOrder() {
		return adjacencyMatrix.length;
	}

	@Override
	public int getHeight() {
		int height = 0;
		for (int i = 0; i < adjacencyMatrix.length; i++) {
			for (int j = 0; j < adjacencyMatrix[0].length; j++) {
				if(adjacencyMatrix[i][j] != 0) {
					height++;
				}
			}
		}
		return height;
	}


}
