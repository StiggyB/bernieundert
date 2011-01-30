package fufara;

import fu.Fara;
import fu.Fu;

public class FuFaraFactory {

	public Fu createFuFara() {
		Fu fu = new Fu();
		fu.setFuname("fu");
		Fara fara = new Fara();
		fara.setFuname("fara");
		
		fu.setFara(fara);
		fara.setFu(fu);
		
		return fu;
	}
}
