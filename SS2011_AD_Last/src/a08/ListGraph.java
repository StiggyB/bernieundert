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

public class ListGraph implements IGraph {

	List<Node> adjacencyList = new ArrayList<Node>();

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

	private Node findNodeWithId(int id) {
		for (Node node : adjacencyList) {
			if (node.data == id) {
				return node;
			}
		}
		return null;
	}

	@Override
	public int[] getAdjacencies(int nodeIdx) {
		if (nodeIdx < 0 || nodeIdx > adjacencyList.size()) {
			 throw new NullPointerException("Invalid index!");
		}
		Node node = adjacencyList.get(nodeIdx);
		int[] adjancencyArr = new int[node.adjacencies.size()];
		for (int i = 0; i < adjancencyArr.length; i++) {
			adjancencyArr[i] = node.adjacencies.get(i).node.data;
		}
		return adjancencyArr;
	}

	@Override
	public int[] getWeights(int nodeIdx) {
		if (nodeIdx < 0 || nodeIdx > adjacencyList.size()) {
			throw new NullPointerException("Invalid index!");
		}
		Node node = adjacencyList.get(nodeIdx);
		int[] weightArr = new int[node.adjacencies.size()];
		for (int i = 0; i < weightArr.length; i++) {
			weightArr[i] = node.adjacencies.get(i).weight;
		}
		return weightArr;
	}

	@Override
	public int getOrder() {
		return adjacencyList.size();
	}

	@Override
	public int getHeight() {
		int height = 0;
		for (Node node : adjacencyList) {
			for (Edge edge : node.adjacencies) {
				height += edge.weight;
			}
		}
		return height;
	}

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
