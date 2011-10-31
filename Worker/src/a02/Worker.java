package a02;

import static akka.actor.Actors.poisonPill; 
import static akka.actor.Actors.remote; 
import akka.actor.ActorRef;
import akka.actor.UntypedActor;


public class Worker extends UntypedActor { 
	private static int idGenerator = 0; 
	private int actorId;
	private ActorRef master;
	
	public Worker() {
		// Wichtig: Wenn die ID nicht gesetzt wird, wird immer dieselbe In- 
		// stanz des Aktors für alle Remote-Aufrufe eines Clients verwendet! getContext().setId(idGenerator + "");
		actorId = idGenerator;
		System.out.println("Aktor wurde erstellt: " + idGenerator); 
		idGenerator++;
	}
	
	private Integer calculate(int a, int b) { 
		return new Integer(a + b);
	
	}

	// message handler
	@Override
	public void onReceive(Object message) {
		if (message instanceof CalculateMessage) {
         // Beim ersten Aufruf wird der Sender ermittel
			this.master = getContext().getSender().get();
			CalculateMessage calculateMessage = (CalculateMessage) message;
			Integer result = calculate(calculateMessage.getA(),
                                    calculateMessage.getB());
			ResultMessage resultMessage = new ResultMessage(result);
			// Ergebnis an den Master senden
			master.tell(resultMessage);
			// Durch this.getContext().tell([Nachricht]) kann der Aktor
			// sich selbst eine Nachricht schicken. In diesem Fall schickt
			// sich der Aktor eine "poisonPill". Empfängt ein Aktor diese,
			// beendet er sich und postStop() wird aufgerufen.
			getContext().tell(poisonPill());
		} else {
			throw new IllegalArgumentException("Unknown message [" +
					message + "]");
		}
	}
	
	@Override
	public void postStop() {
		System.out.println("Aktor wurde beendet: " + this.actorId); super.postStop();
	}
	
				
	public static void main(String[] args) throws Exception {
		remote().start("workerserver", 2552);
	}
}
