package a08;


public class ObjectHolder implements Runnable {

	private final Object object;
	private ObjectChangedListener objectChangedListener;
	
	public ObjectHolder(Object object) {
		this.object = object;
	}

//	public void set(Field field, Object value) throws Exception {
//		field.set(object, value);
//		objectChangedListener.objectChanged();
//	}

	public Object getObject() {
		return object;
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public void run() {
		try {
			while (true) {
				if (object instanceof DummyClass) {
					DummyClass dummyClass = (DummyClass) object;
					dummyClass.setPublicInteger(dummyClass.getPublicInteger() + 1);
					Thread.sleep(5000);
					objectChangedListener.objectChanged();
				} else {
					synchronized (this) {
						wait();
					}
				}
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void setObjectChangedListener(ObjectChangedListener objectChangedListener) {
		this.objectChangedListener = objectChangedListener;
	}

}
