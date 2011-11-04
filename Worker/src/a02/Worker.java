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
	private Calculator calc;
	private BigInteger accPrime;
	private List<BigInteger> factorList = new ArrayList<BigInteger>();
	private List<Calculator> threadList = new ArrayList<Calculator>();


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
		if (message instanceof CalculateMessage) {
			CalculateMessage calculateMessage = (CalculateMessage) message;
			if (this.accPrime == null) {
				this.master = getContext().getSender().get();
				this.accPrime = calculateMessage.getN();
			}
			calc = new Calculator(this, calculateMessage.getN());
			System.out.println("START IT");
			calc.run();
			threadList.add(calc);
		} else if (message instanceof ResultMessage) {
			System.out.println("FINISH");
			ResultMessage resultMessage = new ResultMessage(factorList);
			for (Calculator calc : threadList) {
				calc.setRunning(false);
			}
			if (factorList.isEmpty()) {
				resultMessage = null;
			}
			System.out.println("SENT");
			master.tell(resultMessage);
			getContext().tell(poisonPill());
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
	
	public void pollardFinished(BigInteger result) {
		System.out.println("RESULT: " + result);
		BigInteger prime = calc.getPrime();
		prime = prime.divide(result);
		if (prime != result && result != null) {
			if(Calculator.isPrime(result)) {
				add(result);
				 if (!(factorList.isEmpty()) && isCompletePrime()) {
						System.out.println("RESULT");
						ResultMessage resultMessage = new ResultMessage(factorList);
						getContext().tell(resultMessage);
				 } else {
					 CalculateMessage calculateMessage = new CalculateMessage(prime);
					 System.out.println("NEW MSG: " + calculateMessage);
					 getContext().tell(calculateMessage);
				 }
			}
		}
	}
	
	private boolean isCompletePrime() {
        boolean bool = false;
        BigInteger result = BigInteger.ONE;
        for (BigInteger factor : factorList) {
                result = result.multiply(factor); 
        }
        System.out.println(result + " EQUALS " + accPrime);
        if(result.equals(accPrime)) {
                bool = true;
        }
        return bool;
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
