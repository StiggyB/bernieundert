package a02;

import static akka.actor.Actors.remote;

import java.math.BigInteger;
import java.util.List;

import akka.actor.ActorRef;
import akka.actor.Actors;
import akka.actor.UntypedActor;
import akka.remoteinterface.RemoteServerModule;


public class Master extends UntypedActor { 
	
	public static final int DEFAULT_PORT = 2500;

	static int resultsReceived = 0;
	static int numberOfWorker;
	static RemoteServerModule remoteSupport;	

	@Override
	public void onReceive(Object message) throws Exception { 
		System.out.println("RECEIVE");
		if (message instanceof CalculateMessage) {
	           CalculateMessage calculate = (CalculateMessage) message;
	           startNWorkers(4);
	           ActorRef me = getContext();
	           for (ActorRef worker : WorkerInfo.getWorkerRefs()) {
	        	   System.out.println("TELL");
	        	   worker.tell(calculate, me);
	           }
		} else if (message instanceof ResultMessage) {
			for (ActorRef actor : WorkerInfo.getWorkerRefs()) {
				actor.stop();
			}
			Actors.registry().shutdownAll();
			List<BigInteger> results = ((ResultMessage) message).getResults();
			if (results == null) {
				System.out.println("NO PRIME!");
			} else {
				System.out.println("PRIME(S): " + results);
			}
		} else {
			throw new IllegalArgumentException("Unknown message [" +
	                             message + "]");
		} 
	}
	
	public void startNWorkers(int nWorkers) {
		for (int iPort = DEFAULT_PORT; iPort < (nWorkers + DEFAULT_PORT); iPort++) {
			System.out.println("WORKER: " + iPort);
			ActorRef worker = remote().actorFor(Worker.class.getName(), "localhost", DEFAULT_PORT);
			WorkerInfo.getWorkerRefs().add(worker);
		}
	}

	public static void main(String[] args) {
			remoteSupport = remote().start("localhost", 2553);
			ActorRef client = remote().actorFor(Master.class.getName(),
					"localhost", 2553);
			BigInteger n = new BigInteger("9398726230209357241");
			CalculateMessage calculate = new CalculateMessage(n);
			client.tell(calculate);
	}
}

