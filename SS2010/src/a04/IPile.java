package a04;
/**
 * Diese Klasse stellt ein Interface für verschiedene Implementierungen
 * Baumartiger Strukturen zur Verfügung.
 * @author Bernd Kahlbrandt
 *
 */
public interface IPile {
	/**
	 * Liefert den Wert des Elements an Position i.
	 * @param i Index (größer 0) eines Elements
	 * @return Wert
	 */
	int get(int i);
	/**
	 * Setzt den Wert des Elements an Position i auf den übergebenen Wert.
	 * @param i Index (größer 0) eines Elements
	 * @param value Wert des Elements;
	 */
	void set(int i,int value);
	/**
	 * Liefert den Index des Vorgängers oder 0, wenn es keinen Vorgänger gibt.
	 * @param i Index (größer 0) eines Elements
	 * @return Index des Vorgängers (parents)
	 */
	int parent(int i);
	/**
	 * Liefert den Index des linken Kindes oder 0, wenn es kein linkes Kind gibt.
	 * @param i Index (größer 0) eines Elements.
	 * @return Index des linken Kindes (leftChild).
	 */
	int leftChild(int i);
	/**
	 * Liefert den Index des rechten Kindes oder 0, wenn es kein rechtes Kind gibt.
	 * @param i Index (größer 0) eines Elements
	 * @return Index des rechten Kindes (rightChild)
	 */
	int rightChild(int i);
	/**
	 * Liefert den Index des Nachbars (Bruder/Schwester/Geschwister), 0 wenn es keinen gibt.
	 * @param i Index (größer 0) eines Elements
	 * @return Index des Nachbars (Bruder/Schwester/Geschwister)
	 */
	int sibling(int i);
}
