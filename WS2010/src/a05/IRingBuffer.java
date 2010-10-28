package a05;

public interface IRingBuffer<T> {

	boolean isEmpty();
	boolean isFull();
	int size();
	void enqueue(T item);
	T dequeue();
	T peek();

}