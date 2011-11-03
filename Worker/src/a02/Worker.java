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


	public Worker() {
		// Wichtig: Wenn die ID nicht gesetzt wird, wird immer dieselbe In-
		// stanz des Aktors für alle Remote-Aufrufe eines Clients verwendet!
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
			// Beim ersten Aufruf wird der Sender ermittel
			this.master = getContext().getSender().get();
			CalculateMessage calculateMessage = (CalculateMessage) message;
			Calculator calc = new Calculator(this, calculateMessage.getN());
			calc.run();
		} else if (message instanceof ResultMessage) {
			result = ((ResultMessage) message).getResults();
			if(!(factorList.contains(result))) {
				factorList.addAll(result);
			}
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
		// Durch this.getContext().tell([Nachricht]) kann der Aktor
		// sich selbst eine Nachricht schicken. In diesem Fall schickt
		// sich der Aktor eine "poisonPill". Empfängt ein Aktor diese,
		// beendet er sich und postStop() wird aufgerufen.
		getContext().tell(poisonPill());
	}

	@Override
	public void postStop() {
		System.out.println("Aktor wurde beendet: " + this.actorId);
		super.postStop();
	}

	public static void start() {
		remote().start("localhost", 2551);
	}
	
	public static void main(String[] args) {
		remote().start("localhost", 2552);
	}
}
