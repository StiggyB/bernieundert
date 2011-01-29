package testing;


public class Test implements Runnable {

	private String number;

	@Override
	public void run() {
		System.out.println("bla: " + number);
	}
	
	public static void main(String[] args) {
		Test t = new Test();
		new Thread(t).start();
	}
	
}
