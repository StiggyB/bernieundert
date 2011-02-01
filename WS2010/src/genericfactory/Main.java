package genericfactory;

public class Main {

	
	public static void main(String[] args) {
		GenericObject<Number> test = new GenericObject<Number>(new IFactory<Number>() {
			@Override
			public Number create() {
				return new Integer(1);
			}
		});
		Number bla = test.getTee();
		System.out.println(bla);
	}
}
