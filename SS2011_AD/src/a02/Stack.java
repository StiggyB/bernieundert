package a02;

import java.util.LinkedList;

/**
 * Implementierung eines Stack mit dem IStack Interface.
 * Die Realisierung wurde mittels einer LinkedList aus 
 * java.util umgesetzt.
 * 
 * @author Tugend und Laster
 * @param <T> Typ der Elemente des Stack.
 */
public class Stack<T> implements IStack<T> {

	LinkedList<T> list = new LinkedList<T>();
	
	/**
	 * Diese Methode fuegt dem Stack ein neues Element hinzu
	 * Der Aufwand ist gleich dem Aufwand der enqueue Methode aus der 
	 * ListQueue Implementation, da wieder die java.util.LinkedList
	 * verwendet wird. Also gilt ein konstanter Aufwand von O(1). 
	 * 
	 * @param element Element zum Hinzufuegen
	 */
	@Override
	public void push(T element) {
		list.add(element);
	}

	/**
	 * Diese Methode loescht das zuletzt hinzugefuegte Element
	 * Der Aufwand dieser Methode ist konstant, es gilt also ein konstanter
	 * Aufwand von O(1).
	 * Siehe auch die Methoden removeLast() und remove() aus
	 * java.util.LinkedList
	 */
	@Override
	public void pop() {
		list.removeLast();
	}

	/**
	 * Diese Methode liefert das zuletzt eingefuegte Element zurueck
	 * Der Aufwand dieser Methode ist O(1). Wenn die intern verwendete Liste 
	 * nicht leer ist, wird das letzte Element zurueckgeliefert.
	 * 
	 * @return Zuletzt hinzugefuegtes Element
	 */
	@Override
	public T top() {
		return list.getLast();
	}

	/**
	 * Liefert <b>true</b>, wenn die Queue leer ist, andernfalls <b>false</b>
	 * Der Aufwand ist gleich dem Aufwand aus der ListQueue Implementation, es 
	 * gilt O(1). Es wird wieder nur intern ein Intgervergleich durchgef�hrt.
	 * @return  <b>true</b>, wenn die Queue leer ist, andernfalls <b>false</b>
	 */
	@Override
	public boolean isEmpty() {
//		return list.size() == 0;
		return list.isEmpty();
	}
	
	/**
	 * Hilfsmethode zum Ausgeben der Elemente auf dem Stack
	 * 
	 * @return Elemente auf dem Stack als String
	 */
	@Override
	public String toString() {
		return list.toString();
	}

}
