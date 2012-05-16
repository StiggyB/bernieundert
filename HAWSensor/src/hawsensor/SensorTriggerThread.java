package hawsensor;

import hawmetering.HAWSensorWebservice;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class SensorTriggerThread extends Thread {

	private final Map<String, hawmetering.HAWSensorWebservice> sensorUrls;

	public SensorTriggerThread(Map<String, HAWSensorWebservice> sensorUrls) {
		this.sensorUrls = sensorUrls;
	}

	@Override
	public void run() {
		while (true) {
			sendTriggers();
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	private void sendTriggers() {
		for (Iterator<Entry<String, hawmetering.HAWSensorWebservice>> iterator = sensorUrls.entrySet().iterator(); iterator.hasNext();) {
			Entry<String, hawmetering.HAWSensorWebservice> entry = iterator.next();
			try {
//				System.out.println("triggerung url: " + entry.getKey() + "---" + entry.getValue());
				entry.getValue().trigger();
			} catch (Exception e) {
				// wenn nicht erreichbar
				e.printStackTrace();
				iterator.remove();
				System.out.println("Sensor '" + entry.getKey() + "' failed, removing... ");
			}
		}
	}
}