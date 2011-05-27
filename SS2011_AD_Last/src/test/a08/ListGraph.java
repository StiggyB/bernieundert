package test.a08;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ListGraph<T> extends Benchmark<T> implements IGraph<T>  {

	List<Node<T>>adjanceneyList = new ArrayList<Node<T>>();
	
	@Override
	public void readXML(File xml) {
		// TODO Auto-generated method stub
	}

	@Override
	public Node<T>[] getAdjaceneys(Node<T> node) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Node<T>[] getWeights(Node<T> node) {
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
