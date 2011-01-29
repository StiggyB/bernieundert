package testing;

import java.util.LinkedList;


public class GenericElement04<T> {
	private T data;

	public GenericElement04(Factory<T> factory) {
		this.data = factory.create();
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public static void main(String[] args) {
		Factory<Test2> factory = new Factory<Test2>() {
			@Override
			public Test2 create() {
				return new Test2();
			}
		};
		
	}

	private void set(T teeee) {
		
	}

}

interface Factory<T> {
	T create();
}