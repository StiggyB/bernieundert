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

	List<Node> adjancencyList = new ArrayList<Node>();

	@Override
	public void readXML(File xml) throws FileNotFoundException {
		try {
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(xml);
			doc.getDocumentElement().normalize();

			Element docEle = doc.getDocumentElement();

			NodeList nl = docEle.getElementsByTagName("node");
			if (nl != null) {
				for (int i = 0; i < nl.getLength(); i++) {
					Element el = (Element) nl.item(i);
					String idString = el.getAttribute("id");
					int id = Integer.parseInt(idString);

					adjancencyList.add(new Node(id, new ArrayList<Edge>()));
				}
				for (int i = 0; i < nl.getLength(); i++) {
					Element el = (Element) nl.item(i);
					String idString = el.getAttribute("id");
					int id = Integer.parseInt(idString);

					Node node = findNodeWithId(id);

					NodeList childNodes = el.getChildNodes();
					for (int j = 0; j < childNodes.getLength(); j++) {
						if (childNodes.item(j).getNodeType() == org.w3c.dom.Node.ELEMENT_NODE) {
							Element childNode = (Element) childNodes.item(j);
							int edgeNodeId = Integer.parseInt(childNode.getAttribute("id"));
							Node edgeNode = findNodeWithId(edgeNodeId);
							int cost = Integer.parseInt(childNode.getAttribute("cost"));
							node.adjacencies.add(new Edge(edgeNode, cost));
						}
						}
				}
			}

		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private Node findNodeWithId(int id) {
		for (Node node : adjancencyList) {
			if (node.data == id) {
				return node;
			}
		}
		return null;
	}

	private String getTextValue(Element ele, String tagName) {
		String textVal = null;
		NodeList nl = ele.getElementsByTagName(tagName);
		if (nl != null && nl.getLength() > 0) {
			Element el = (Element) nl.item(0);
			textVal = el.getFirstChild().getNodeValue();
		}

		return textVal;
	}

	private int getIntValue(Element ele, String tagName) {
		// in production application you would catch the exception
		return Integer.parseInt(getTextValue(ele, tagName));
	}

	@Override
	public int[] getAdjacencys(int nodeIdx) {
		Node node = adjancencyList.get(nodeIdx);
		if (!(node.equals(null))) {
			// throw NullPointerException;
		}
		int[] adjancencyArr = new int[node.adjacencies.size()];
		for (int i = 0; i < adjancencyArr.length; i++) {
			adjancencyArr[i] = node.adjacencies.get(i).node.data;
		}
		return adjancencyArr;
	}

	@Override
	public int[] getWeights(int nodeIdx) {
		Node node = adjancencyList.get(nodeIdx);
		if (!(node.equals(null))) {
			// throw NullPointerException;
		}
		int[] weightArr = new int[node.adjacencies.size()];
		for (int i = 0; i < weightArr.length; i++) {
			weightArr[i++] = node.adjacencies.get(i).weight;
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
			for (Edge edge : node.adjacencies) {
				height += edge.weight;
			}
		}
		return height;
	}

	@Override
	public int getLowestNodeWeight(int nodeIdx) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (Node node : adjancencyList) {
			sb.append("node id: " + node.data);
//			sb.append(node.adjacencies.size());
			for (Edge edges : node.adjacencies) {
				sb.append("(" + edges.toString() + ")");
			}
			sb.append(",\n");
		}
		
		return sb.toString();
	}

}
