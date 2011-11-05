package a02;

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
	private Thread calcThread;
	private BigInteger accPrime;
	private List<BigInteger> factorList = new ArrayList<BigInteger>();

	public Worker() {
		getContext().setId(idGenerator + "");
		actorId = idGenerator;
		System.out.println("Aktor wurde erstellt: " + idGenerator);
		idGenerator++;
	}
	
	synchronized public List<BigInteger> getFactorList() {
		synchronized (factorList) {
			return factorList;
		}
	}
	
	// TODO Timer CPU WallTime & GUI representation
	@Override
	public void onReceive(Object message) {
		if (message instanceof CalculateMessage) {
			CalculateMessage calculateMessage = (CalculateMessage) message;
			if (accPrime == null) {
				this.master = getContext().getSender().get();
				accPrime = calculateMessage.getN();
			}
			BigInteger newPrime = calculateMessage.getN();
			calc = new Calculator(this, newPrime);
			calcThread = new Thread(calc);
			calcThread.start();
			System.out.println("START: " + calcThread.getName());
		} else if (message instanceof ResultMessage) {
			master.tell(message);
		} else {
			throw new IllegalArgumentException("Unknown message [" + message
					+ "]");
		}
	}
	
	synchronized public void add(BigInteger factor) {
		if(factor != null) {
			synchronized (factorList) {
				this.factorList.add(factor);
			}
		} else {
			throw new NullPointerException();
		}
	}
	
	synchronized public void pollardFinished(BigInteger result) {
		System.out.println("RESULT: " + result);
		BigInteger prime = null;
		if (prime != result) {
			prime = calc.getPrime();
			prime = prime.divide(result);
			if(Calculator.isPrime(result)) {
				add(result);
				buildMessage(prime);
			}
		} else {
			ResultMessage resultMessage = new ResultMessage(null);
			getContext().tell(resultMessage);
		}
	}

	private void buildMessage(BigInteger prime) {
		ResultMessage resultMessage;
		if (!(factorList.isEmpty()) && isCompletePrime()) {
			System.out.println("RESULT");
			resultMessage = new ResultMessage(factorList);
			getContext().tell(resultMessage);
		 } else {
			 CalculateMessage calculateMessage = new CalculateMessage(prime);
			 System.out.println("NEW MSG: " + calculateMessage);
			 getContext().tell(calculateMessage);
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
		if(calc != null) {
			calc.setRunning(false);
		}
		if (calcThread != null) {
			calcThread.interrupt();
		}
		System.out.println("Aktor wurde beendet: " + this.actorId);
		super.postStop();
	}
	
	public static void main(String[] args) {
		remote().start("localhost", 2500);
	}
}
