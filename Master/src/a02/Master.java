package a02;

import static akka.actor.Actors.poisonPill;
import static akka.actor.Actors.remote;

import java.math.BigInteger;

import akka.actor.ActorRef;
import akka.actor.UntypedActor;
import akka.remoteinterface.RemoteServerModule;


public class Master extends UntypedActor { 
	static int resultsReceived = 0;
	static int numberOfWorker;
	static RemoteServerModule remoteSupport;

	@Override
	public void onReceive(Object message) throws Exception { 
		System.out.println("RECEIVE");
		if (message instanceof CalculateMessage) {
	           CalculateMessage calculate = (CalculateMessage) message;
	           // Worker auf dem Remote-Host erstellen
	           ActorRef worker = remote().actorFor(Worker.class.getName(), "localhost", 2552);
	           WorkerInfo.getAcRefList().add(worker);
	           // getContext() gibt eine Referenz auf diesen Aktor zurück
	           ActorRef me = getContext();
	           // tell verschickt eine Message an einen Aktor. Zusätzlich kann
	           // man eine Referenz auf einen anderen Aktor übergeben 
	           worker.tell(calculate, me);
		} else if (message instanceof ResultMessage) { 
			if (((ResultMessage) message).getResult() == null) {
				System.out.println("No prime!");
			} else {
				System.out.println(((ResultMessage) message).getResult());
			}
			getContext().tell(poisonPill());
		} else {
			throw new IllegalArgumentException("Unknown message [" +
	                             message + "]");
		} 
	}

	public static void main(String[] args) throws Exception {
		// Der "Client" muss auch als Remote-Aktor gestartet werden um 
		// später Nachrichten vom Server empfangen zu können.
		remoteSupport = remote().start("localhost", 2553);
		ActorRef client = remote().actorFor(Master.class.getName(),
				"localhost", 2553);
		CalculateMessage calculate = new CalculateMessage(new BigInteger("8806715679"), new BigInteger("10")); 
		client.tell(calculate);
	} 
}

