package genericfactory;

public class GenericObject<T> {

	private T tee;
	
	public GenericObject(IFactory<T> factory) {
		// Hier erzeugt sich GenericObject selber ein Objekt vom Typ "T" �ber die Factory
		this.tee = factory.create();
	}
	
}


