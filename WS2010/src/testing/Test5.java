package testing;

public class Test5 {

	public static <T extends Number> void m1(T t) {
		t.toString();
	}
	
	public static <X> void m2(X t) {
		t.toString();
	}
	
}
