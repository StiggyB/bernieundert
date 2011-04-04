package test;

import a01.LinkedList;
import a01.Node;

public class Test {
	public static void main(String[] args) throws Exception {
		
		LinkedList<String> list = new LinkedList<String>();
		list.insert("a");
		list.insert("b");
		list.insert("c");
		System.out.println(list);
		
		Node<String> node = list.find("a");
		
		Node<String> n1 = new Node<String>();
		
		System.out.println(list.retrieve(n1));
		list.delete(node);
		
		System.out.println(list);
		
		LinkedList<String> list2 = new LinkedList<String>();
		list2.insert("d");
		list2.insert("e");
		list2.insert("f");
		System.out.println(list2);
		
		list.concat(list2);
		System.out.println(list);
		System.out.println(list.size());
		
	}

}
