package a02;

import java.util.ArrayList;
import java.util.List;

import akka.actor.ActorRef;

public class WorkerInfo {
	
	static private List<ActorRef> workerRefs = new ArrayList<ActorRef>();

	public static List<ActorRef> getWorkerRefs() {
		return workerRefs;
	}
}
