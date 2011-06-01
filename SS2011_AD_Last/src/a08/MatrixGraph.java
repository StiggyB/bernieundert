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

public class MatrixGraph implements IGraph {

	int[][] adjacencyMatrix = new int[3][3];

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

	@Override
	public int[] getAdjacencies(int nodeIdx) {
		if (nodeIdx < 0 || nodeIdx > adjacencyMatrix.length) {
			 throw new ArrayIndexOutOfBoundsException();
		}
		int[] adjacencyIndexArr = new int[getLength(adjacencyMatrix[nodeIdx])];
		int adjacencyIdx = 0;
		for (int i = 0; i < adjacencyMatrix.length; i++) {
			if (adjacencyMatrix[nodeIdx][i] != 0) {
				adjacencyIndexArr[adjacencyIdx++] = i;
			}
		}
		return adjacencyIndexArr;
	}

	@Override
	public int[] getWeights(int nodeIdx) {
		if (nodeIdx < 0 || nodeIdx > adjacencyMatrix.length) {
			 throw new ArrayIndexOutOfBoundsException();
		}
		int[] weightArr = new int[getLength(adjacencyMatrix[nodeIdx])];
		int weightIdx = 0;
		for (int i = 0; i < adjacencyMatrix.length; i++) {
			if (adjacencyMatrix[nodeIdx][i] != 0) {
				weightArr[weightIdx++] = adjacencyMatrix[nodeIdx][i];
			}
		}
		return weightArr;
	}

	private int getLength(int[] arr) {
		int count = 0;
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] != 0) {
				count++;
			}
		}
		return count;
	}

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

	@Override
	public int getOrder() {
		return adjacencyMatrix.length;
	}

	@Override
	public int getHeight() {
		int height = 0;
		for (int i = 0; i < adjacencyMatrix.length; i++) {
			for (int j = 0; j < adjacencyMatrix[0].length; j++) {
				if (adjacencyMatrix[i][j] != 0) {
					height++;
				}
			}
		}
		return height;
	}

}
