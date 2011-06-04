package a08;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * Implementierung des Interface IGraph mit Adjazenzliste.
 * 
 * @author Tugend und Laster
 */
public class ListGraph implements IGraph {

	List<Node> adjacencyList = new ArrayList<Node>();

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
		if (nl != null) {
			for (int i = 0; i < nl.getLength(); i++) {
				Element el = (Element) nl.item(i);
				String idString = el.getAttribute("id");
				int id = Integer.parseInt(idString);
				adjacencyList.add(new Node(id, new ArrayList<Edge>()));
			}
			readAdjacencies(nl);
		}
	}

	/**
	 * Hilfsmethode: Diese Methode liest aus einem Knoten seine Nachbarn aus
	 * und traegt diese in die Adjazenzmatrix ein.
	 * 
	 * @param nl Knoten als Element
	 */
	private void readAdjacencies(NodeList nl) {
		for (int i = 0; i < nl.getLength(); i++) {
			Element el = (Element) nl.item(i);
			String idString = el.getAttribute("id");
			int id = Integer.parseInt(idString);
			Node node = findNodeWithId(id);
			NodeList childNodes = el.getChildNodes();
			for (int j = 0; j < childNodes.getLength(); j++) {
				if (childNodes.item(j).getNodeType() == org.w3c.dom.Node.ELEMENT_NODE) {
					Element childNode = (Element) childNodes.item(j);
					int edgeNodeId = Integer.parseInt(childNode
							.getAttribute("id"));
					Node edgeNode = findNodeWithId(edgeNodeId);
					int cost = Integer.parseInt(childNode
							.getAttribute("cost"));
					node.adjacencies.add(new Edge(edgeNode, cost));
				}
			}
		}
	}

	/**
	 * Hilfsmethode: Sucht in der Adjazenzliste Knoten mit der uebergebenen ID
	 * 
	 * @param id Knotenname (ID)
	 * @return Knoten mit entsprechender ID
	 */
	private Node findNodeWithId(int id) {
		for (Node node : adjacencyList) {
			if (node.data == id) {
				return node;
			}
		}
		return null;
	}

	/**
	 * Liefert alle Nachbarn eines Knotens 
	 * @param nodeIdx zu pruefender Knoten
	 * @return alle Nachbarn des Knotens
	 */
	@Override
	public int[] getAdjacencies(int nodeIdx) {
		if (nodeIdx < 0 || nodeIdx > adjacencyList.size()) {
			 throw new NullPointerException("Invalid index!");
		}
		Node node = adjacencyList.get(nodeIdx);
		int[] adjancencyArr = new int[node.adjacencies.size()];
		for (int i = 0; i < adjancencyArr.length; i++) {
			getAdjacenciesFor(node, adjancencyArr, i);
		}
		return adjancencyArr;
	}

	/**
	 * Hilfsmethode: Inhalt der for-Schleife aus der getAdjacencies()-Methode
	 * ausgelagert, damit spaeter via AOP der Counter hier ansetzen kann.
	 * 
	 * @param node
	 * @param adjancencyArr
	 * @param i
	 */
	protected void getAdjacenciesFor(Node node, int[] adjancencyArr, int i) {
		adjancencyArr[i] = node.adjacencies.get(i).node.data;
	}

	/**
	 * Liefert alle Kosten von allen Kanten eiens Knotens
	 * 
	 * @param nodeIdx zu pruefender Knoten
	 * @return alle Kosten
	 */
	@Override
	public int[] getWeights(int nodeIdx) {
		if (nodeIdx < 0 || nodeIdx > adjacencyList.size()) {
			throw new NullPointerException("Invalid index!");
		}
		Node node = adjacencyList.get(nodeIdx);
		int[] weightArr = new int[node.adjacencies.size()];
		for (int i = 0; i < weightArr.length; i++) {
			getWeightsFor(node, weightArr, i);
		}
		return weightArr;
	}

	/**
	 * Hilfsmethode: Inhalt der for-Schleife aus der getWeights()-Methode
	 * ausgelagert, damit spaeter via AOP der Counter hier ansetzen kann.
	 * @param node
	 * @param weightArr
	 * @param i
	 */
	protected void getWeightsFor(Node node, int[] weightArr, int i) {
		weightArr[i] = node.adjacencies.get(i).weight;
	}

	/**
	 * Liefert die Anzahl der Knoten des Graphen
	 * 
	 * @return Anzahl der Knoten
	 */
	@Override
	public int getOrder() {
		return adjacencyList.size();
	}

	/**
	 * Liefert die Anzahl der Kanten des Graphen
	 * 
	 * @return Anzahl der Kanten
	 */
	@Override
	public int getHeight() {
		int height = 0;
		for (Node node : adjacencyList) {
			for (Edge edge : node.adjacencies) {
				height = getHeightFor(height, edge);
			}
		}
		return height;
	}

	/**
	 * Hilfsmethode: Inhalt der for-Schleife aus der getHeight()-Methode
	 * ausgelagert, damit spaeter via AOP der Counter hier ansetzen kann.
	 * @param height
	 * @param edge
	 * @return
	 */
	protected int getHeightFor(int height, Edge edge) {
		height += edge.weight;
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
		if (nodeIdx < 0 || nodeIdx > adjacencyList.size()) {
			 throw new NullPointerException("Invalid index!");
		}		
		Node node = adjacencyList.get(nodeIdx);
		int lowestWeight = node.adjacencies.get(0).weight;
		int adjacencyIdx = 0;
		for (int i = 0; i < node.adjacencies.size(); i++) {
			if (node.adjacencies.get(i).weight < lowestWeight) {
				lowestWeight = node.adjacencies.get(i).weight;
				adjacencyIdx = i;
			}
		}
		return node.adjacencies.get(adjacencyIdx).node.data;
	}

	/**
	 * Hilfsmethode: Passende String-Repraesentation des Graphen
	 * 
	 * @return String-Repraesentation des Graphen
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (Node node : adjacencyList) {
			sb.append("node id: " + node.data);
			// sb.append(node.adjacencies.size());
			for (Edge edges : node.adjacencies) {
				sb.append("(" + edges.toString() + ")");
			}
			sb.append(",\n");
		}

		return sb.toString();
	}

}
