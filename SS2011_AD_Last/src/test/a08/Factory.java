package test.a08;

import java.lang.reflect.Array;

public class Factory<T> {

	T[] t;
	final IFactoryPattern<T> factory;
	
	public Factory(IFactoryPattern<T> factory) {
		this.factory = factory;
	}
	
	public T newNode() {
		return factory.create();
	}

	public Factory<Node<T>> createNode() {
		return new Factory<Node<T>>(new IFactoryPattern<Node<T>>() {
			@Override
			public Node<T> create() {
				return new Node<T>();
			}
		});
	}
	
	
    /**
     * This method uses newInstance from the class Array.
     * 
     * @param clazz
     * @param capacity
     */
    @SuppressWarnings("unchecked")
	public Node<T>[] create(Class<? extends Node<T>> clazz, int capacity) {
        return (Node<T>[])Array.newInstance(clazz, capacity);
    }
}
