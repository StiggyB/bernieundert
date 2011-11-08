package a02;

/**
 * Praktikum: VSP<br>
 * Semester: WS11<br>
 * Aufgaben-Nr.: 02<br>
 * 
 * Version: V0.1<br>
 * Aenderungen:
 * 
 * Quellen: API, Swing, VS Folien
 * 
 * @author Mueller-Pettenpohl, Tell #1989982, Benjamin, Burchart #1863248<br>
 */
import static akka.actor.Actors.remote;

import java.awt.EventQueue;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import akka.actor.ActorRef;
import akka.actor.Actors;
import akka.actor.UntypedActor;

public class Master extends UntypedActor {

	private Timer timer;
	private List<WorkerInfo> wInfoList = new ArrayList<WorkerInfo>();
	private static GUI_Master_Impl impl;
	private static GUI_Master gui;
	private List<BigInteger> results;

	@Override
	public void onReceive(Object message) throws Exception {
		if (message instanceof RequestMessage) {
			this.wInfoList = ((RequestMessage) message).getwInfoList();
		} else if (message instanceof CalculateMessage) {
			timer = new Timer(0);
			timer.startTimer();
			CalculateMessage calculate = (CalculateMessage) message;
			for (WorkerInfo workerInfo : wInfoList) {
				startNWorkers(workerInfo);
			}
			ActorRef me = getContext();
			for (ActorRef worker : WorkerInfo.getWorkerRefs()) {
				worker.tell(calculate, me);
			}
		} else if (message instanceof ResultMessage) {
			timer.stopTimer();
			timer.setCpuTimes(((ResultMessage) message).getCpuTimes());
			stopNWorkers();
			results = ((ResultMessage) message).getResults();
			if (results == null) {
				impl.setResult("NO PRIME!" + "/n" + timer.toString());
			} else {
				impl.setResult(timer.toString() + "\nPrimes: "
						+ results.toString() + "\n");
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
		WorkerInfo.getWorkerRefs().clear();
		Actors.registry().shutdownAll();
	}

	public static void main(String[] args) {
		remote().start("localhost", 2553); 
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					gui = new GUI_Master();
					impl = new GUI_Master_Impl(gui);
					impl.start();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
