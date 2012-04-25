package starter;

import java.util.ArrayList;
import java.util.List;

import ggt.Coordinator;
import ggt.StarterPOA;

public class StarterImpl extends StarterPOA {

	private String starterName;
	private List<ggtProcess> ggtProcesses = new ArrayList<ggtProcess>();
	private Coordinator coordRef;

	public StarterImpl(String starterName) {
		this.starterName = starterName;
	}

	@Override
	public String getName() {
		return this.starterName;
	}

	@Override
	public void createProcesses(int count) {
		for (int i = 0; i < count; i++) {
			ggtProcesses.add(new ggtProcess(i, this, coordRef));
		}
	}

	public void setCoordRef(Coordinator coordRef) {
		this.coordRef = coordRef;
		
	}

}
