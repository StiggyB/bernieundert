package test.a08;

import java.io.File;

public interface IGraph<T> {

	void readXML(File xml);
	
	Node<T>[] getAdjaceneys(Node<T> node);
	
	Node<T>[] getWeights(Node<T> node);
	
	int getOrder();
	
	int getHeight();
	
}
