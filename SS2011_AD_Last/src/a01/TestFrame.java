package a01;

/**
 * 
 * @author Team TugendUndLaster
 * 
 *         In dieser Klasse testen wir f�r eigene Zwecke mit der Listen-
 *         implementierung. Unteraderem erfassen wir hier die Daten, 
 *         die zum Erstellen der Diagramme n�tig sind.
 *         
 * 
 */

import java.util.ArrayList;
import java.util.Random;

public class TestFrame {
	public static void main(String[] args) throws Exception {

		IList<String> list = new SingleLinkedList<String>();
		System.out.println("Anzahl Ops: " + Benchmark.ops);
		list.append("a");
		list.append("b");
		list.append("d");
		list.append("e");
		System.out.println("Anzahl Ops: " + Benchmark.ops);
		System.out.println(list);

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
		IList<String> list1 = new SingleLinkedList<String>();
		list1.append("a");
		list1.append("b");
		list1.append("c");
		System.out.println(list1);
		IList<String> list2 = new SingleLinkedList<String>();
		list2.append("d");
		list2.append("e");
		list2.append("f");
		System.out.println(list2);

		list1.concat(list2);
		System.out.println("Anzahl Ops: " + Benchmark.ops);
		System.out.println(list1);

		System.out.println("Beginn Auswertung");
		System.out.println("=================");
		IList<String> benchlist = new SingleLinkedList<String>();
		System.out.println("Ops auf 0 setzen");
		Benchmark.ops = 0;

		java.util.List<Node<String>> references1 = new ArrayList<Node<String>>();
		
		
		
		// Find Operationen z�hlen
		System.out.println("Find\n");
		System.out.println("Ops auf 0 setzen");
		Benchmark.ops = 0;
		Random rnd = new Random();
		int[] rndnumbers = new int[200];
		for (int i = 0; i < 100; i++) {
			benchlist.append("a" + i);
			Benchmark.ops -= 10;
			rndnumbers[i] = rnd.nextInt(100);
			references1.add(benchlist.find("a" + rndnumbers[i]));
			System.out.println("Groesse der Liste: " + benchlist.size()	+ " Anzahl Ops: " + Benchmark.ops);
			Benchmark.ops=0;
		}

		
		// Delete Operationen z�hlen
		System.out.println("Delete\n");
		System.out.println("Ops auf 0 setzen");
		for (int i = 99; i >= 0; i--) {
			benchlist.delete(references1.get(rndnumbers[i]));
			System.out.println("Groesse der Liste: " + benchlist.size()	+ " Anzahl Ops: " + Benchmark.ops);
			Benchmark.ops=0;
		}
		
		
		// Insert Operationen z�hlen
		System.out.println("Insert\n");
		for (int i = 0; i < 100; i++) {
			benchlist.append("a" + i);
			System.out.println("Groesse der Liste: " + benchlist.size()	+ " Anzahl Ops: " + Benchmark.ops);
			Benchmark.ops = 0;
		}
		System.out.println("Anzahl Ops: " + Benchmark.ops);


		// Retrieve Operationen z�hlen
		System.out.println("Retrieve\n");
		System.out.println("Ops auf 0 setzen");
		
		for (int i = 0; i < 100; i++) {
			list.retrieve(references1.get(rndnumbers[i]));
			benchlist.delete(references1.get(i));
			System.out.println("Groesse der Liste: " + benchlist.size()	+ " Anzahl Ops: " + Benchmark.ops);
			Benchmark.ops=0;
		}

		
		// Concat Operationen z�hlen
		System.out.println("Concat\n");
		System.out.println("Ops auf 0 setzen");
		Benchmark.ops = 0;

		java.util.List<IList<String>> listList = new ArrayList<IList<String>>();

		for (int i = 0; i < 100; i++) {
			IList<String> newList = new SingleLinkedList<String>();
			listList.add(newList);
			for (int j = 0; j < 10; j++) {
				newList.append("a" + j);
				Benchmark.ops -= 10;
			}
		}
		for (int i = 0; i < 50; i++) {
			IList<String> tmpList = listList.get(i);
			tmpList.concat(listList.get(++i));
			System.out.println(tmpList);
			System.out.println("Groesse der Liste: " + tmpList.size()
					+ " Anzahl Ops: " + Benchmark.ops);
			Benchmark.ops = 0;
		}
		System.out.println("Anzahl Ops: " + Benchmark.ops);

	}
}
