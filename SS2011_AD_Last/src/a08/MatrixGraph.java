package a08;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * Implementierung des Interface IGraph mit Adjazenzmatrix.
 * 
 * @author Tugend und Laster
 */
public class MatrixGraph implements IGraph {

	int[][] adjacencyMatrix = new int[3][3];

	/**
	 * Liest einen Graphen aus einer XML-Datei in die 
	 * Datenstruktur ein (Adjazenzliste oder Matrix)
	 * 
	 * @param xml XML-Datei des Graphen
	 * @throws FileNotFoundException
	 */
	@Override
	public void readXML(File xml) throws FileNotFoundException {
		try {
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(xml);
			doc.getDocumentElement().normalize();
			Element docEle = doc.getDocumentElement();
			readNodes(docEle);
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Hilfsmethode: Diese Methode liest die einzelnen Knoten aus der 
	 * XML-Datei ein
	 * 
	 * @param docEle Liste von Elementen, in denen die Knoten enthalten sind
	 */
	private void readNodes(Element docEle) {
		NodeList nl = docEle.getElementsByTagName("node");
		adjacencyMatrix = new int[nl.getLength()][nl.getLength()];
		if (nl != null) {
			for (int i = 0; i < nl.getLength(); i++) {
				Element el = (Element) nl.item(i);
				String idString = el.getAttribute("id");
				int id = Integer.parseInt(idString);
				readAdjacencies(el, id);
			}
		}
	}

	/**
	 * Hilfsmethode: Diese Methode liest aus einem Knoten seine Nachbarn aus
	 * und traegt diese in die Adjazenzmatrix ein.
	 * 
	 * @param el Knoten als Element
	 * @param id Knotenname
	 */
	private void readAdjacencies(Element el, int id) {
		NodeList childNodes = el.getChildNodes();
		for (int j = 0; j < childNodes.getLength(); j++) {
			if (childNodes.item(j).getNodeType() == org.w3c.dom.Node.ELEMENT_NODE) {
				Element childNode = (Element) childNodes.item(j);
				int edgeNodeId = Integer.parseInt(childNode
						.getAttribute("id"));
				int cost = Integer.parseInt(childNode
						.getAttribute("cost"));
				adjacencyMatrix[id][edgeNodeId] = cost;
			}
		}
	}

	/**
	 * Liefert alle Nachbarn eines Knotens 
	 * @param nodeIdx zu pruefender Knoten
	 * @return alle Nachbarn des Knotens
	 */
	@Override
	public int[] getAdjacencies(int nodeIdx) {
		if (nodeIdx < 0 || nodeIdx > adjacencyMatrix.length) {
			 throw new ArrayIndexOutOfBoundsException();
		}
		int[] adjacencyIndexArr = new int[getCountOfAdjacenciesForNode(adjacencyMatrix[nodeIdx])];
		int adjacencyIdx = 0;
		for (int i = 0; i < adjacencyMatrix.length; i++) {
			adjacencyIdx = getAdjecenciesFor(nodeIdx, adjacencyIndexArr,
					adjacencyIdx, i);
		}
		return adjacencyIndexArr;
	}

	protected int getAdjecenciesFor(int nodeIdx, int[] adjacencyIndexArr,
			int adjacencyIdx, int i) {
		if (adjacencyMatrix[nodeIdx][i] != 0) {
			adjacencyIndexArr[adjacencyIdx++] = i;
		}
		return adjacencyIdx;
	}

	/**
	 * Liefert alle Kosten von allen Kanten eiens Knotens
	 * 
	 * @param nodeIdx zu pruefender Knoten
	 * @return alle Kosten
	 */
	@Override
	public int[] getWeights(int nodeIdx) {
		if (nodeIdx < 0 || nodeIdx > adjacencyMatrix.length) {
			 throw new ArrayIndexOutOfBoundsException();
		}
		int[] weightArr = new int[getCountOfAdjacenciesForNode(adjacencyMatrix[nodeIdx])];
		int weightIdx = 0;
		for (int i = 0; i < adjacencyMatrix.length; i++) {
			weightIdx = getWeightsFor(nodeIdx, weightArr, weightIdx, i);
		}
		return weightArr;
	}

	protected int getWeightsFor(int nodeIdx, int[] weightArr, int weightIdx, int i) {
		if (adjacencyMatrix[nodeIdx][i] != 0) {
			weightArr[weightIdx++] = adjacencyMatrix[nodeIdx][i];
		}
		return weightIdx;
	}

	/**
	 * Hilfsmethode: Ermittelt die Anzahl an Nachbarn fuer einen Knoten
	 * (fuer eine Zeile in der Matrix)
	 * 
	 * @param arr ein Knoten (eine Zeile in der Matrix)
	 * @return Anzahl der Nachbarn eines Knotens
	 */
	private int getCountOfAdjacenciesForNode(int[] arr) {
		int count = 0;
		for (int i = 0; i < arr.length; i++) {
			count = getCountOfAdjacenciesForNodeFor(arr, count, i);
		}
		return count;
	}

	protected int getCountOfAdjacenciesForNodeFor(int[] arr, int count, int i) {
		if (arr[i] != 0) {
			count++;
		}
		return count;
	}


	/**
	 * Liefert die Anzahl der Knoten des Graphen
	 * 
	 * @return Anzahl der Knoten
	 */
	@Override
	public int getOrder() {
		return adjacencyMatrix.length;
	}

	/**
	 * Liefert die Anzahl der Kanten des Graphen
	 * 
	 * @return Anzahl der Kanten
	 */
	@Override
	public int getHeight() {
		int height = 0;
		for (int i = 0; i < adjacencyMatrix.length; i++) {
			for (int j = 0; j < adjacencyMatrix[0].length; j++) {
				height = getHeightFor(height, i, j);
			}
		}
		return height;
	}

	protected int getHeightFor(int height, int i, int j) {
		if (adjacencyMatrix[i][j] != 0) {
			height++;
		}
		return height;
	}

	/**
	 * Liefert die niedrigstens Kosten von allen Kanten des 
	 * uebergebenen Knotens
	 * 
	 * @param nodeIdx zu pruefender Knoten
	 * @return niedrigste Kosten
	 */
	@Override
	public int getLowestNodeWeight(int nodeIdx) {
		int[] adjacencyArr = getWeights(nodeIdx);
		int lowestWeight = adjacencyArr[0];
		int adjacencyIdx = 0;
		for (int i = 1; i < adjacencyArr.length; i++) {
			if (adjacencyArr[i] < lowestWeight) {
				lowestWeight = adjacencyArr[i];
				adjacencyIdx = i;
			}
		}
		return adjacencyIdx;
	}
	
	/**
	 * Hilfsmethode: Passende String-Repraesentation des Graphen
	 * 
	 * @return String-Repraesentation des Graphen
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < adjacencyMatrix.length; i++) {
			sb.append("knoten " + i + ": ");
			for (int j = 0; j < adjacencyMatrix.length; j++) {
				sb.append(adjacencyMatrix[i][j] + "\t");	
			}
			sb.append("\n");
		}
		
		return sb.toString();
	}
}
