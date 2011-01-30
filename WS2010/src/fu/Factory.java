package fu;

public class Factory<T> implements IFactory<T>{

	@Override
	public T create() {
		Fu fu = new Fu();
		fu.setFuname("Fu");
		Fara fara = new Fara();
		fara.setFuname("Fara");
		fu.setFara(fara);
		fara.setFu(fu);
		
		return (T) fu;
	}
	

}
