package a08;

public class MainApp {
	public static void main(String[] args) {
//		TestListGraph.test();
//		TestMatrixGraph.test();
		CostNode tmp1 = new CostNode(0, null, 5, false);
		CostNode tmp2 = new CostNode(1, tmp1, 10, false);
		CostNode tmp3 = new CostNode(2, tmp1, 15, false);
		
		System.out.println(tmp1.toString());
		System.out.println(tmp2.toString());
		System.out.println(tmp3.toString());
	}
}
