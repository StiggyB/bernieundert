package a06;

/**
 * 
 * @author Team TugendUndLaster
 * 
 *         Diese Klasse stellt einen einfachen
 *         Datensatz dar. Er beinhaltet nur einen Key.
 * 
 */
public class Dataset {
	int key;

	public Dataset(int key) {
		this.key = key;
	}
	
	@Override
	public String toString() {
		return String.valueOf(key);
	}


}
