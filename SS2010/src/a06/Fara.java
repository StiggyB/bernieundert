package a06;

/**
 * 
 * @author Martin Slowikowski
 * 
 */

public class Fara {
	private Fu fu;
	private String name;

	public Fara(String faraNama, String fuName) {
		this.name = faraNama;
		this.fu = new Fu(this, fuName, faraNama);
	}

	public Fara(Fu fu, String faraName, String fuName) {
		this.name = faraName;
		if (fu.getFara() == null) {
			this.fu = fu;
		} else {
			this.fu = new Fu(this, fuName, faraName);
		}
	}

	public Fu getFu() {
		return this.fu;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

}
