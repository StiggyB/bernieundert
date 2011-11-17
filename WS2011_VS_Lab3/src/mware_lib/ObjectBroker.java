package mware_lib;

import namensdienst.NameService;

public class ObjectBroker {
	
	private String host;
	private int port;
	
	public ObjectBroker(String host, int port) {
		super();
		this.host = host;
		this.port = port;
	}
	public static ObjectBroker getBroker(String serviceHost,int listenPort) {
		return new ObjectBroker(serviceHost, listenPort);
	}		
		// Das hier zurückgelieferte Objekt soll der zentrale Einstiegspunkt
	   	// der Middleware aus Anwendersicht sein.
	   	// Parameter: Host und Port, bei dem die Dienste (Namensdienst)
	   	//            kontaktiert werden sollen.
	public NameService getNameService() {
		return new LocalNameService();
	}
		// Liefert den Namensdienst (Stellvetreterobjekt).
}
