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
 * @author Mueller-Pettenpohl, Tell #1989982, Benjamin, Burchard #1863248<br>
 */

import static akka.actor.Actors.remote;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import akka.actor.ActorRef;

public class GUI_Master_Impl {

	private GUI_Master gui;
	private WorkerInfo wInfo;
	private List<WorkerInfo> wInfoList = new ArrayList<WorkerInfo>();
	private String result = "";
	ActorRef client;

	public GUI_Master_Impl(GUI_Master gui) {
		this.gui = gui;
	}

	public void start() {
		gui = new GUI_Master();
		gui.setBtnStartWorkerListener(new BtnStartWorkerListener());
		gui.setBtnCreateWorkerListener(new BtnCreateWorkerListener());
		gui.setBtnCloseListener(new BtnCloseListener());
		gui.setBtnGetResultListener(new BtnGetResultListener());
		gui.setBtnRemoveWorkerListener(new BtnRemoveWorkerListener());
		gui.setVisible(true);
	}

	private class BtnStartWorkerListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			client = remote().actorFor(Master.class.getName(), "localhost",
					2553);
			gui.setTxtrResult("Workers are calculating...");
			CalculateMessage calculate = new CalculateMessage(new BigInteger(
					gui.getTextFieldNumbertoCalc()), Integer.parseInt(gui
					.getTextFieldNumberOfIterations()), 0);
			RequestMessage worker = new RequestMessage(wInfoList);
			client.tell(worker);
			client.tell(calculate);
			wInfoList.clear();
		}
	}

	private class BtnCreateWorkerListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			wInfo = new WorkerInfo(gui.getTextFieldHost(), Integer.parseInt(gui
					.getTextFieldPort()), Integer.parseInt(gui
					.getTextFieldNumberOfActors()));
			wInfoList.add(wInfo);
			gui.addWorkerToList(wInfo.toString());
		}
	}

	private class BtnCloseListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			gui.dispose();
			System.exit(0);
		}
	}

	public class BtnGetResultListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			gui.setTxtrResult(result);
			result = "";
		}
	}
	
	private class BtnRemoveWorkerListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			int terminatedWorker = gui.removeWorkerFromList();
			if (terminatedWorker == -1) {
				return;
			} else {
				wInfoList.remove(terminatedWorker);
			}
			
		}
	}

	public void setResult(String result) {
		gui.setTxtrResult(" calculation finished.\n");
		gui.getListOfWorkers().removeAll();
		this.result = result;
	}
}
