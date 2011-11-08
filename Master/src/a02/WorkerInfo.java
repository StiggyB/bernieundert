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
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import akka.actor.ActorRef;

public class WorkerInfo implements Serializable {
	private static final long serialVersionUID = -8790719200862664366L;
	private String host;
	private int port;
	private int nWorkers;
	private static List<ActorRef> workerRefs = new ArrayList<ActorRef>();

	public WorkerInfo(String identifier, int port, int nWorkers) {
		this.host = identifier;
		this.port = port;
		this.nWorkers = nWorkers;
	}
	
	public static List<ActorRef> getWorkerRefs() {
		return workerRefs;
	}

	public void setIdentifier(String identifier) {
		this.host = identifier;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public void setAmountWorkers(int amountWorkers) {
		nWorkers = amountWorkers;
	}

	public int getAmountWorkers() {
		return nWorkers;
	}

	public int getPort() {
		return port;
	}

	public String getIdentifier() {
		return host;
	}

	@Override
	public boolean equals(Object obj) {
		if (!this.getClass().equals(obj.getClass())) {
			return false;
		}
		if (obj instanceof WorkerInfo) {
			WorkerInfo w = (WorkerInfo) obj;
			if (w.host.equals(this.host) && w.port == this.port) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	@Override
	public String toString() {
		return "WorkerInfo [host=" + host + ", port=" + port + ", nWorkers="
				+ nWorkers + "]";
	}

}
