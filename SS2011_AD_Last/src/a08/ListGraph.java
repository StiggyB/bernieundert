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
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	    DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(xml);
	    doc.getDocumentElement().normalize();
	 
	    System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
		Element docEle = doc.getDocumentElement();

		NodeList nl = docEle.getElementsByTagName("node");
		if (nl != null) {
			for(int i = 0 ; i < nl.getLength(); i++) {
				Element el = (Element)nl.item(i);
				String idString = el.getAttribute("id");
				int id = Integer.parseInt(idString);
				
				adjancencyList.add(new Node(id, new ArrayList<Edge>()));
			}
			for (int i = 0 ; i < nl.getLength(); i++) {
				Element el = (Element)nl.item(i);
				String idString = el.getAttribute("id");
				int id = Integer.parseInt(idString);
				
				Node node = findNodeWithId(id);

				NodeList childNodes = el.getChildNodes();
				for (int j = 0 ; j < childNodes.getLength(); j++) {
					Element childNode = (Element) childNodes.item(i);
					int edgeNodeId = Integer.parseInt(childNode.getAttribute("id"));
					Node edgeNode = findNodeWithId(edgeNodeId);
					int cost = Integer.parseInt(childNode.getAttribute("cost"));
					node.adjacencies.add(new Edge(edgeNode, cost));
				}
			}
		}
		
//				//TODO how to implement the chain of nodes?
//				NodeList nlAdj = docEle.getElementsByTagName("adjacency");
//				List<Edge> edgeList = new ArrayList<Edge>();
//				for (int j = 0; j < nlAdj.getLength(); j++) {
//					Element el2 = (Element)nl.item(i);
//					Node node = new Node(getIntValue(el2, "id"), null);
//					Edge e = new Edge(node, getIntValue(el2, "cost"));
//					edgeList.add(e);
//				}
//				Node node = new Node(data, edgeList);
//
//				//add it to list
//				adjancencyList.add(node);
//			}
//		}
	    
	    NodeList nList = doc.getElementsByTagName("node");
	    
	    System.out.println("-----------------------");
	 
	    for (int temp = 0; temp < nList.getLength(); temp++) {
	 
	    	org.w3c.dom.Node nNode = nList.item(temp);	    
	       if (nNode.getNodeType() == org.w3c.dom.Node.ELEMENT_NODE) {
	 
	          Element eElement = (Element) nNode;
	 
//	          System.out.println("First Name : "  + getTagValue("firstname",eElement));
//	          System.out.println("Last Name : "  + getTagValue("lastname",eElement));
//	          System.out.println("Nick Name : "  + getTagValue("nickname",eElement));
//	          System.out.println("Salary : "  + getTagValue("salary",eElement));
	 
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
		if(nl != null && nl.getLength() > 0) {
			Element el = (Element)nl.item(0);
			textVal = el.getFirstChild().getNodeValue();
		}

		return textVal;
	}
	
	private int getIntValue(Element ele, String tagName) {
		//in production application you would catch the exception
		return Integer.parseInt(getTextValue(ele,tagName));
	}

	@Override
	public int[] getAdjacencys(int nodeIdx) {
		Node node = adjancencyList.get(nodeIdx);
		if(!(node.equals(null))) {
//			throw NullPointerException;
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
		if(!(node.equals(null))) {
//			throw NullPointerException;
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

}
