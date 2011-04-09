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
	 * 
	 * @param element Element zum Hinzufuegen
	 */
	@Override
	public void push(T element) {
		list.add(element);
	}

	/**
	 * Diese Methode loescht das zuletzt hinzugefuegte Element
	 */
	@Override
	public void pop() {
		list.removeLast();
	}

	/**
	 * Diese Methode liefert das zuletzt eingefuegte Element zurueck
	 * 
	 * @return Zuletzt hinzugefuegtes Element
	 */
	@Override
	public T top() {
		return list.getLast();
	}

	/**
	 * Liefert <b>true</b>, wenn die Queue leer ist, andernfalls <b>false</b>
	 * 
	 * @return  <b>true</b>, wenn die Queue leer ist, andernfalls <b>false</b>
	 */
	@Override
	public boolean isEmpty() {
		return list.size() == 0;
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
