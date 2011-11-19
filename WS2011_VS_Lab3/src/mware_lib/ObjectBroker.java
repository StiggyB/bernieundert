package mware_lib;

import java.io.IOException;
import java.net.UnknownHostException;

import namensdienst.LocalNameService;
import namensdienst.NameServiceServer;

public class ObjectBroker {

	private String host;
	private int port;
	private LocalNameService nameService;
//	private NameServiceServer nameServiceServer;
//	private Thread nsThread;

	public ObjectBroker(String host, int port) throws UnknownHostException, IOException {
		this.host = host;
		this.port = port;
		this.nameService = LocalNameService.getInstance();
		new NameServiceServer(this.host, this.port, nameService);
//		this.nameServiceServer = new NameServiceServer(this.host, this.port, nameService);
	}

	public static ObjectBroker getBroker(String serviceHost, int listenPort) throws UnknownHostException, IOException {
		return new ObjectBroker(serviceHost, listenPort);
	}
	// Das hier zurückgelieferte Objekt soll der zentrale Einstiegspunkt
	// der Middleware aus Anwendersicht sein.
	// Parameter: Host und Port, bei dem die Dienste (Namensdienst)
	// kontaktiert werden sollen.

	public NameService getNameService() {
		return nameService;
	}
	// Liefert den Namensdienst (Stellvetreterobjekt).
}
