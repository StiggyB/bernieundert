package a02;

import static akka.actor.Actors.remote;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import akka.actor.ActorRef;
import akka.actor.Actors;
import akka.actor.UntypedActor;

//TODO GUI representation
//GUI: To set max_iter, host, port, numberOfWorker

public class Master extends UntypedActor {

	private Timer timer;
	private List<WorkerInfo> wInfoList = new ArrayList<WorkerInfo>();
	
	@Override
	public void onReceive(Object message) throws Exception {
		if (message instanceof RequestMessage) {
			this.wInfoList = ((RequestMessage) message).getwInfoList();
		}
		if (message instanceof CalculateMessage) {
			timer = new Timer(0);
			timer.startTimer();
			CalculateMessage calculate = (CalculateMessage) message;
			// If GUI implemented comment next 2 line!
			WorkerInfo wInfo = new WorkerInfo("localhost", 2500, 2);
			startNWorkers(wInfo);
			// --
			/*
			for (WorkerInfo workerInfo : wInfoList) {
				startNWorkers(workerInfo);
			}
			 */
			ActorRef me = getContext();
			for (ActorRef worker : WorkerInfo.getWorkerRefs()) {
				worker.tell(calculate, me);
			}
		} else if (message instanceof ResultMessage) {
			timer.stopTimer();
			timer.setCpuTimes(((ResultMessage) message).getCpuTimes());
			timer.printTimes();
			stopNWorkers();
			List<BigInteger> results = ((ResultMessage) message).getResults();
			if (results == null) {
				System.out.println("NO PRIME!");
			} else {
				System.out.println("PRIME(S): \t\t" + results);
			}
		} else {
			throw new IllegalArgumentException("Unknown message [" + message
					+ "]");
		}
	}

	private void startNWorkers(WorkerInfo wInfo) {
		for (int i = 0; i < wInfo.getAmountWorkers(); i++) {
			ActorRef worker = remote().actorFor(Worker.class.getName(),
					wInfo.getIdentifier(), wInfo.getPort());
			WorkerInfo.getWorkerRefs().add(worker);
		}
	}

	private void stopNWorkers() {
		for (ActorRef actor : WorkerInfo.getWorkerRefs()) {
			actor.stop();
		}
		Actors.registry().shutdownAll();
	}

	public static void main(String[] args) {
		remote().start("localhost", 2553);
		ActorRef client = remote().actorFor(Master.class.getName(),
				"localhost", 2553);
		//This Message sends the GUI & GUI starts here
		BigInteger n = new BigInteger(
				"1137047281562824484226171575219374004320812483047");
		CalculateMessage calculate = new CalculateMessage(n, 50, 0);
		client.tell(calculate);
	}
}
