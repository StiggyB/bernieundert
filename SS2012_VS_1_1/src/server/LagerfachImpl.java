package server;

import lagern.FachPOA;
import lagern.LagerPOA;
import lagern.FachPackage.exInvalidCount;
import lagern.FachPackage.exNotEnoughPieces;

public class LagerfachImpl extends FachPOA{

	@Override
	public int anzahl() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String name() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void einlagern(String user, int anzahl) throws exInvalidCount {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void auslagern(String user, int anzahl) throws exInvalidCount,
			exNotEnoughPieces {
		// TODO Auto-generated method stub
		
	}

}
