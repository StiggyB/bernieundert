package starter;

import org.omg.PortableServer.POAPackage.ServantNotActive;
import org.omg.PortableServer.POAPackage.WrongPolicy;

import monitor.Monitor;
import ggt.Coordinator;
import ggt.ggtProcess;
import ggt.ggtProcessHelper;
import ggt.ggtProcessPOA;

public class ggtProcessImpl extends ggtProcessPOA{
	

	private String processName;
	
	public ggtProcessImpl(int i, StarterImpl starterImpl, Coordinator coordRef) {
		// Der ggT-Prozess wird vom Starter gestartet und muss sich als erstes
		// mit einer Identifikation (String), die sich aus dem Namen des
		// Starters und der vom Starter vergebenen fortlaufenden Nummer ergibt,
		// beim Koordinator registrieren.
		// Müsste man doch erst idl noch wieder anpassen oder?
		this.processName = starterImpl.getName() + i;	
		ggtProcess ggtProcess;
		try {
			ggtProcess = ggtProcessHelper.narrow(starterImpl._poa().servant_to_reference(this));
			coordRef.registerProcess(ggtProcess, processName);
		} catch (ServantNotActive e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (WrongPolicy e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void initProcess(ggt.ggtProcess left, ggt.ggtProcess right,int startValue, int delay, int timeout, Monitor mntr) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void start() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void calc(int mi) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean terminate(String processName) {
		// TODO Auto-generated method stub
		return false;
	}

}
