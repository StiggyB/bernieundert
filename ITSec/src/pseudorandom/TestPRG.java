package pseudorandom;

public class TestPRG {

	public static void main(String[] args) {
		LCG lcg = new LCG(1337);
		for (int i = 0; i < 10; i++) {
			System.out.println(lcg.nextValue());
		}
	}

}
