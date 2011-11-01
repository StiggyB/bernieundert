package starter;

import static akka.actor.Actors.remote;

public class Starter {

	public static void main(String[] args) {
		remote().start("localhost", 2554);
//		remote().start("localhost", 2553);
		
	}
}
