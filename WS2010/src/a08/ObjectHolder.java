package a08;

import java.lang.reflect.Field;

public class ObjectHolder {

	final Object object;
	
	public ObjectHolder(Object object) {
		this.object = object;
	}

	public void set(Field field, Object value) throws IllegalArgumentException, IllegalAccessException {
		field.set(object, value);
		System.out.println("set with " + value);
	}
	
	public static void main(String[] args) throws SecurityException, NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
		DummyClass dummy = new DummyClass();
		dummy.publicInteger = 2;
		ObjectHolder oh = new ObjectHolder(dummy);
		Field field = dummy.getClass().getField("publicInteger");
		oh.set(field , 2);
	}

}
