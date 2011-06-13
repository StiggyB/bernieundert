package a10;

public class Example {
	
	public static void main(String[] args) {
		String ip = "1.2.3.4";
		String ip2 = "1.2.3.5";
		System.out.println(ip.hashCode());
		System.out.println(ip2.hashCode());
		
	}
	
	
//	public void put(String key, String value) {
//		if (needsResize()) {
//			doResize();
//			doRehash();
//		}
//		internalPut(key, value);
//	}
//	
//	private void internalPut(String key, String value) {
//		// do put
//		for (int i; i < 5; i++) {
//			// hashen
//		}
//		if (i ==5) {
//			doResize();
//			doRehash();
//			internalPut(key, value);
//		}
//		
//	}
//	
//	
//	int hash(Object obj) {
//		String str = obj.toString();
//		obj.hashCode();
//	}

}
