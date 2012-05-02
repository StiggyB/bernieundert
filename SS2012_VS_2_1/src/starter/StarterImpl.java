package starter;

import java.util.ArrayList;
import java.util.List;

import org.omg.CORBA.ORB;

import ggtCorba.Coordinator;
import ggtCorba.StarterPOA;

public class StarterImpl extends StarterPOA {

	private String starterName;
	private List<ggtProcessImpl> ggtProcesses;
	private Coordinator coordRef;
	private Thread hook;
	private ORB orb;

	public StarterImpl(String starterName) {
		this.starterName = starterName;
	}

	@Override
	public String getName() {
		return this.starterName;
	}

	@Override
	public void createProcesses(int count) {
		ggtProcesses = new ArrayList<ggtProcessImpl>();
		for (int i = 0; i < count; i++) {
			ggtProcesses.add(new ggtProcessImpl(i, this, coordRef));
		}
	}

	public void setCoordRef(Coordinator coordRef) {
		this.coordRef = coordRef;

	}

	@Override
	public void shutdown() {
		System.out.println("Starter>recved shutdown from Coordinator");
		new Thread(new Runnable() {

			@Override
			public void run() {
				System.out.print("Starter>Killing all processes...");
				 killProcesses();
				System.out.print("OK\nStarter>shutting down ORB...");
				orb.shutdown(true);
				System.out.println("OK\nStarter>shutdown was successful...");
			}
		}).start();
	}

	public void setOrb(ORB orb) {
		this.orb = orb;
	}

	@Override
	public void killProcesses() {
		if (ggtProcesses != null) {
			for (ggtProcessImpl s : ggtProcesses) {
				s.kill();
			}
		}
	}
}