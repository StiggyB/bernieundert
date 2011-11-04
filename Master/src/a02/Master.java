package a02;

import static akka.actor.Actors.poisonPill;
import static akka.actor.Actors.remote;

import java.math.BigInteger;

import akka.actor.ActorRef;
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
	           startNWorkers(1);
	           ActorRef me = getContext();
	           for (ActorRef worker : WorkerInfo.getWorkerRefs()) {
	        	   System.out.println("TELL");
	        	   worker.tell(calculate, me);
	           }
		} else if (message instanceof ResultMessage) { 
			if (((ResultMessage) message).getResults().isEmpty()) {
				System.out.println("NO PRIME!");
			} else {
				for (BigInteger factor : ((ResultMessage) message).getResults()) {
					System.out.println("PRIME(S): " + factor);
				}
				System.out.println("PRIME(S): " + ((ResultMessage) message).getResults());
			}
			getContext().tell(poisonPill());
		} else {
			throw new IllegalArgumentException("Unknown message [" +
	                             message + "]");
		} 
	}
	
	public void startNWorkers(int nWorkers) {
		for (int iPort = DEFAULT_PORT; iPort < (nWorkers + DEFAULT_PORT); iPort++) {
			ActorRef worker = remote().actorFor(Worker.class.getName(), "localhost", iPort);
			 WorkerInfo.getWorkerRefs().add(worker);
		}
	}

	public static void main(String[] args) {
			remoteSupport = remote().start("localhost", 2553);
			ActorRef client = remote().actorFor(Master.class.getName(),
					"localhost", 2553);
			BigInteger n = new BigInteger("8806715679");
//			int nThreads = 2;
			CalculateMessage calculate = new CalculateMessage(n); 
			client.tell(calculate);
	}
}

