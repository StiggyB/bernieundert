package a01;

import java.util.ArrayList;
import java.util.Random;

public class Test {
	public static void main(String[] args) throws Exception {

		LinkedList<String> list = new LinkedList<String>();
		System.out.println("Anzahl Ops: " + Benchmark.ops);
		list.insert("a");
		list.insert("b");
		list.insert("d");
		list.insert("e");
		System.out.println("Anzahl Ops: " + Benchmark.ops);
		System.out.println(list);
		System.out.println(list.size());

		Node<String> node = list.find("b");
		list.insert(node, "c");
		System.out.println(list);
		System.out.println("Anzahl Ops: " + Benchmark.ops);
		// System.out.println(node.data);
		// Node<String> n1 = new Node<String>();

		// System.out.println(list.retrieve(n1));
		// list.delete(node);

		// System.out.println(list);
		System.out.println("Ops auf 0 setzen");
		Benchmark.ops = 0;
		System.out.println("Anzahl Ops: " + Benchmark.ops);
		LinkedList<String> list1 = new LinkedList<String>();
		list1.insert("a");
		list1.insert("b");
		list1.insert("c");
		System.out.println(list1);
		LinkedList<String> list2 = new LinkedList<String>();
		list2.insert("d");
		list2.insert("e");
		list2.insert("f");
		System.out.println(list2);

		list1.concat(list2);
		System.out.println("Anzahl Ops: " + Benchmark.ops);
		System.out.println(list1);
		System.out.println(list1.size());

		System.out.println("Beginn Auswertung");
		System.out.println("=================");
		LinkedList<String> benchlist = new LinkedList<String>();
		System.out.println("Ops auf 0 setzen");
		Benchmark.ops = 0;

		java.util.List<Node<String>> references1 = new ArrayList<Node<String>>();
		

		// Insert
		System.out.println("Insert\n");
		for (int i = 0; i < 200; i++) {
			benchlist.insert("a" + i);
			System.out.println("Groesse der Liste: " + benchlist.size()	+ " Anzahl Ops: " + Benchmark.ops);
			Benchmark.ops = 0;
		}
		System.out.println("Anzahl Ops: " + Benchmark.ops);

		// Find
		System.out.println("Find\n");
		System.out.println("Ops auf 0 setzen");
		Benchmark.ops = 0;
		Random rnd = new Random();
		int[] rndnumbers = new int[200];
		for (int i = 0; i < 200; i++) {
			rndnumbers[i] = rnd.nextInt(100);
			references1.add(benchlist.find("a" + rndnumbers[i]));
			System.out.println("Groesse der Liste: " + benchlist.size()	+ " Anzahl Ops: " + Benchmark.ops);
			Benchmark.ops=0;
		}

		// Retrieve
		System.out.println("Retrieve\n");
		System.out.println("Ops auf 0 setzen");
		
		for (int i = 100; i < 200; i++) {
			list.retrieve(references1.get(rndnumbers[i]));
			benchlist.delete(references1.get(i));
			System.out.println("Groesse der Liste: " + benchlist.size()	+ " Anzahl Ops: " + Benchmark.ops);
			Benchmark.ops=0;
		}

		// Delete
		System.out.println("Delete\n");
		System.out.println("Ops auf 0 setzen");
		for (int i = 99; i >= 0; i--) {
			benchlist.delete(references1.get(rndnumbers[i]));
			System.out.println("Groesse der Liste: " + benchlist.size()	+ " Anzahl Ops: " + Benchmark.ops);
			Benchmark.ops=0;
		}
		
		
		
		// Concat
		System.out.println("Concat\n");
		System.out.println("Ops auf 0 setzen");
		Benchmark.ops = 0;

		java.util.List<List<String>> listList = new ArrayList<List<String>>();

		for (int i = 0; i < 100; i++) {
			List<String> newList = new LinkedList<String>();
			listList.add(newList);
			for (int j = 0; j < 10; j++) {
				newList.insert("a" + j);
				Benchmark.ops -= 10;
			}
		}
		for (int i = 0; i < 50; i++) {
			List<String> tmpList = listList.get(i);
			tmpList.concat(listList.get(++i));
			System.out.println(tmpList);
			System.out.println("Groesse der Liste: " + tmpList.size()
					+ " Anzahl Ops: " + Benchmark.ops);
			Benchmark.ops = 0;
		}
		System.out.println("Anzahl Ops: " + Benchmark.ops);

	}
}
