package genericfactory;

public class Main {

	
	public static void main(String[] args) {
		new GenericObject<Number>(new IFactory<Number>() {
			@Override
			public Number create() {
				return new Integer(1);
			}
		});
	}
}
