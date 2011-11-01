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

	public List<BigInteger> getFactorList() {
		return factorList;
	}

	public Worker() {
		// Wichtig: Wenn die ID nicht gesetzt wird, wird immer dieselbe In-
		// stanz des Aktors für alle Remote-Aufrufe eines Clients verwendet!
		getContext().setId(idGenerator + "");
		actorId = idGenerator;
		System.out.println("Aktor wurde erstellt: " + idGenerator);
		idGenerator++;
	}
	
	@Override
	public void onReceive(Object message) {
		BigInteger result = null;
		if (message instanceof CalculateMessage) {
			// Beim ersten Aufruf wird der Sender ermittel
			this.master = getContext().getSender().get();
			CalculateMessage calculateMessage = (CalculateMessage) message;
			Calculator calc = new Calculator(this, calculateMessage.getA(),
					calculateMessage.getB());
			calc.run();
			result = calc.getResult();
			if (result == null) {
				// calculate one more time
			} else if (factorList.contains(result))  {
				// find next factor of this product
			}
//			result = calculate(calculateMessage.getA(),
//					calculateMessage.getB());
			if (WorkerInfo.getAcRefList().contains(result)) {
				
			}
			ResultMessage resultMessage = new ResultMessage(result);
			System.out.println("TELL");
			// Ergebnis an den Master senden
			master.tell(resultMessage);
			for (ActorRef ar : WorkerInfo.getAcRefList()) {
				ar.tell(resultMessage);
			}
			// Durch this.getContext().tell([Nachricht]) kann der Aktor
			// sich selbst eine Nachricht schicken. In diesem Fall schickt
			// sich der Aktor eine "poisonPill". Empfängt ein Aktor diese,
			// beendet er sich und postStop() wird aufgerufen.
			getContext().tell(poisonPill());
		} else if (message instanceof ResultMessage) {
			result = ((ResultMessage) message).getResult();
			if(!(factorList.contains(result))) {
				factorList.add(result);
			}
		} else {
			throw new IllegalArgumentException("Unknown message [" + message
					+ "]");
		}
	}

	@Override
	public void postStop() {
		System.out.println("Aktor wurde beendet: " + this.actorId);
		super.postStop();
	}

	public void start() {
		remote().start("localhost", 2554);
	}
	
	public static void main(String[] args) {
		remote().start("localhost", 2554);
	}
}
