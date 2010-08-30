package a06;

/**
 * 
 * @author Martin Slowikowski
 * 
 */

public class Fu {
	private Fara fara;
	private String name;

	public Fu(String fuName, String faraName) {
		this.name = fuName;
		this.fara = new Fara(this, faraName, fuName);

	}

	public Fu(Fara fara, String fuName, String faraName) {
		this.name = fuName;
		if (fara.getFu() == null) {
			this.fara = fara;
		} else {
			this.fara = new Fara(this, faraName, fuName);
		}
	}

	public Fara getFara() {
		return this.fara;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

}
