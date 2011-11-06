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
	private int maxIterations;
	private ActorRef master;
	private Calculator calc;
	private Thread calcThread;
	private BigInteger accPrime;
	private List<BigInteger> factorList = new ArrayList<BigInteger>();
	private List<Long> cpuTimes = new ArrayList<Long>();

	public Worker() {
		getContext().setId(idGenerator + "");
		actorId = idGenerator;
		System.out.println("Aktor wurde erstellt: " + idGenerator);
		idGenerator++;
	}

	public int getMaxIterations() {
		return maxIterations;
	}

	public List<BigInteger> getFactorList() {
		return factorList;
	}

	@Override
	public void onReceive(Object message) {
		if (message instanceof CalculateMessage) {
			CalculateMessage calculateMessage = (CalculateMessage) message;
			cpuTimes.add(calculateMessage.getCpuTime());
			if (accPrime == null) {
				this.master = getContext().getSender().get();
				this.accPrime = calculateMessage.getN();
				this.maxIterations = calculateMessage.getMaxIterations();
			}
			BigInteger newPrime = calculateMessage.getN();
			calc = new Calculator(this, newPrime);
			calcThread = new Thread(calc);
			calcThread.start();
		} else if (message instanceof ResultMessage) {
			master.tell(message);
		} else {
			throw new IllegalArgumentException("Unknown message [" + message
					+ "]");
		}
	}

	synchronized public void add(BigInteger factor) {
		if (factor != null) {
			synchronized (factorList) {
				this.factorList.add(factor);
			}
		} else {
			throw new NullPointerException();
		}
	}

	synchronized public void pollardFinished(BigInteger result, long cpuTime) {
		BigInteger prime = null;
		if (prime != result) {
			prime = calc.getPrime();
			prime = prime.divide(result);
			if (Calculator.isPrime(result)) {
				add(result);
				buildMessage(prime, cpuTime);
			}
		} else {
			ResultMessage resultMessage = new ResultMessage(null, null);
			getContext().tell(resultMessage);
		}
	}

	private void buildMessage(BigInteger prime, long cpuTime) {
		ResultMessage resultMessage;
		if (!(factorList.isEmpty()) && isCompletePrime()) {
			resultMessage = new ResultMessage(factorList, cpuTimes);
			if (getContext() != null) {
				getContext().tell(resultMessage);
			}
		} else {
			CalculateMessage calculateMessage = new CalculateMessage(prime,
					maxIterations, cpuTime);
			getContext().tell(calculateMessage);
		}
	}

	private boolean isCompletePrime() {
		boolean bool = false;
		BigInteger result = BigInteger.ONE;
		for (BigInteger factor : factorList) {
			result = result.multiply(factor);
		}
		if (result.equals(accPrime)) {
			bool = true;
		}
		return bool;
	}

	@Override
	public void postStop() {
		if (calc != null) {
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
