package a08;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class ListGraph extends Benchmark implements IGraph {
	
	List<Node> adjancencyList = new ArrayList<Node>();

	@Override
	public void readXML(File xml) throws FileNotFoundException {
		// TODO Auto-generated method stub

	}

//	@Override
	public Node[] getAdjacencys(int nodeIdx) {
		Node node = adjancencyList.get(nodeIdx);
		if(!(node.equals(null))) {
//			throw NullPointerException;
		}
		Node[] adjancencyArr = new Node[node.adjacencys.size()];
		for (int i = 0; i < adjancencyArr.length; i++) {
			adjancencyArr[i++] = node.adjacencys.get(i).node;
		}
		return adjancencyArr;
	}

	@Override
	public int[] getWeights(int nodeIdx) {
		Node node = adjancencyList.get(nodeIdx);
		if(!(node.equals(null))) {
//			throw NullPointerException;
		}
		int[] weightArr = new int[node.adjacencys.size()];
		for (int i = 0; i < weightArr.length; i++) {
			weightArr[i++] = node.adjacencys.get(i).weight;
		}
		return weightArr;
	}

	@Override
	public int getOrder() {
		return adjancencyList.size();
	}

	@Override
	public int getHeight() {
		int height = 0;
		for (Node node : adjancencyList) {
			for (Edge edge : node.adjacencys) {
				height += edge.weight;
			}
		}
		return height;
	}

}
