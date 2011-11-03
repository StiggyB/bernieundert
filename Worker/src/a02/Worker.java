package a02;

import static akka.actor.Actors.poisonPill;
import static akka.actor.Actors.remote;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import akka.actor.ActorRef;
import akka.actor.UntypedActor;

public class Worker extends UntypedActor {
	private static int idGenerator = 0;
	private int actorId;
	private ActorRef master;
	private List<BigInteger> factorList = new ArrayList<BigInteger>();
//	private List<Calculator> threadList = new ArrayList<Calculator>();


	public Worker() {
		getContext().setId(idGenerator + "");
		actorId = idGenerator;
		System.out.println("Aktor wurde erstellt: " + idGenerator);
		idGenerator++;
	}
	
	public List<BigInteger> getFactorList() {
		return factorList;
	}
	
	@Override
	public void onReceive(Object message) {
		List<BigInteger> result = null;
		if (message instanceof CalculateMessage) {
			this.master = getContext().getSender().get();
			CalculateMessage calculateMessage = (CalculateMessage) message;
			for (int i = 0; i < calculateMessage.getnThreads(); i++) {
				Calculator calc = new Calculator(this, calculateMessage.getN());
				calc.run();
			}
		} else if (message instanceof ResultMessage) {
			result = ((ResultMessage) message).getResults();
			if(!(factorList.contains(result))) {
				factorList.addAll(result);
			}
			this.master.tell(result);
		} else {

			throw new IllegalArgumentException("Unknown message [" + message
					+ "]");
		}
	}
	
	public void add(BigInteger factor) {
		if(factor != null) {
			this.factorList.add(factor);
		} else {
			throw new NullPointerException();
		}
	}
	
	public void pollardFinished() {
		if (factorList.isEmpty()) {
			System.out.println("NUMBER IS NO PRIME!");
		} else {
			ResultMessage resultMessage = new ResultMessage(factorList);
			master.tell(resultMessage);
		}
		getContext().tell(poisonPill());
	}

	@Override
	public void postStop() {
		System.out.println("Aktor wurde beendet: " + this.actorId);
		super.postStop();
	}
	
	public static void main(String[] args) {
		remote().start("localhost", 2500);
	}
}
