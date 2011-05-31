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
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(xml);
			doc.getDocumentElement().normalize();

			Element docEle = doc.getDocumentElement();

			NodeList nl = docEle.getElementsByTagName("node");
			adjacencyMatrix = new int[nl.getLength()][nl.getLength()];
			if (nl != null) {
				for (int i = 0; i < nl.getLength(); i++) {
					Element el = (Element) nl.item(i);
					String idString = el.getAttribute("id");
					int id = Integer.parseInt(idString);

					NodeList childNodes = el.getChildNodes();
					for (int j = 0; j < childNodes.getLength(); j++) {
						if (childNodes.item(j).getNodeType() == org.w3c.dom.Node.ELEMENT_NODE) {
							Element childNode = (Element) childNodes.item(j);
							int edgeNodeId = Integer.parseInt(childNode.getAttribute("id"));
							int cost = Integer.parseInt(childNode.getAttribute("cost"));
							adjacencyMatrix[id][edgeNodeId] = cost; 
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

	@Override
	public int[] getAdjacencies(int nodeIdx) {
		if(nodeIdx < 0 || nodeIdx > adjacencyMatrix.length) {
			//Do something!
		}
		int[] adjacencyIndexArr = new int[adjacencyMatrix.length];
		for (int i = 0; i < adjacencyMatrix.length; i++) {
			if(adjacencyMatrix[nodeIdx][i] != 0) {
				adjacencyIndexArr[i] = i;
			}
		}
		return adjacencyIndexArr;
	}

	@Override
	public int[] getWeights(int nodeIdx) {
		if(nodeIdx < 0 || nodeIdx > adjacencyMatrix.length) {
			//Do something!
		}
		int[] weightArr = new int[adjacencyMatrix.length];
		for (int i = 0; i < adjacencyMatrix.length; i++) {
			weightArr[i] = adjacencyMatrix[nodeIdx][i];
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
				if(adjacencyMatrix[i][j] != 0) {
					height++;
				}
			}
		}
		return height;
	}


}
