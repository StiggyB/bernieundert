package a02;

import static akka.actor.Actors.poisonPill;
import static akka.actor.Actors.remote;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import akka.actor.ActorRef;
import akka.actor.UntypedActor;

public class Worker extends UntypedActor {
	private static int idGenerator = 0;
	private int actorId;
	private ActorRef master;
	private Calculator calc;
	private BigInteger accPrime;
	private int numCalculators;
	private boolean workerBusy = false;
	private List<BigInteger> factorList = new ArrayList<BigInteger>();
	private Map<Thread, Calculator> threadList = new HashMap<Thread, Calculator>();


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
	
	// TODO Thread control and start some Threads impl
	// TODO Timer CPU WallTime & GUI representation
	@Override
	public void onReceive(Object message) {
		if (message instanceof CalculateMessage) {
			CalculateMessage calculateMessage = (CalculateMessage) message;
			if (accPrime == null) {
				this.master = getContext().getSender().get();
				accPrime = calculateMessage.getN();
				numCalculators = calculateMessage.getnThreads();
			}
			BigInteger newPrime = calculateMessage.getN();
			startAndStopCalculators(newPrime);
		} else if (message instanceof ResultMessage) {
			master.tell(message);
			getContext().tell(poisonPill());
		}else if (message instanceof CheckMessage) {
			checkPrime(((CheckMessage)message).getN());
		} else {
			throw new IllegalArgumentException("Unknown message [" + message
					+ "]");
		}
	}

	synchronized private void startAndStopCalculators(BigInteger newPrime) {
		for (Thread calcThread : threadList.keySet()) {
			System.out.println("STOP: " + calcThread.getName());
			threadList.get(calcThread).setRunning(false);
			calcThread.interrupt();
//			calcThread.stop();
		}
		threadList.clear();
		for (int i = 0; i < numCalculators; i++) {
			calc = new Calculator(this, newPrime);
			Thread calcThread = new Thread(calc, "Calcularor" + i);
			calcThread.start();
			System.out.println("START: " + calcThread.getName());
			threadList.put(calcThread, calc);
			calcThread = null;
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
		if (!workerBusy) {
			workerBusy = true;
			for (Thread calcThread : threadList.keySet()) {
				System.out.println("STOP: " + calcThread.getName());
				threadList.get(calcThread).setRunning(false);
				calcThread.interrupt();
//			calcThread.stop();
			}
			threadList.clear();
			CheckMessage checkMessage = new CheckMessage(result);
			System.out.println("NEW MSG: " + checkMessage);
			getContext().tell(checkMessage);
		}
		workerBusy = false;
	}

	private void checkPrime(BigInteger result) {
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
			 CalculateMessage calculateMessage = new CalculateMessage(prime, numCalculators);
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
		System.out.println("Aktor wurde beendet: " + this.actorId);
		super.postStop();
	}
	
	public static void main(String[] args) {
		remote().start("localhost", 2500);
	}
}
