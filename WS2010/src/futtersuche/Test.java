package futtersuche;

public class Test {

	public class InnerTest {
		public InnerTest() {
			System.out.println("InnerTest");
		}
	}

	public Test() {
		System.out.println("Test");
	}

	
	public static void main(String[] args) {
		Test t = new Test();
		t.new InnerTest();
	}
}
