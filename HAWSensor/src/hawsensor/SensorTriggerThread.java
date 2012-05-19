package hawsensor;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public class SensorTriggerThread extends Thread {

	private final HAWSensor hawSensor;

	public SensorTriggerThread(HAWSensor hawSensor) {
		this.hawSensor = hawSensor;
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
		boolean updateNeeded = false;
		Map<String, hawmetering.HAWSensorWebservice> sensorWebservices = hawSensor.getSensorWebservices();
		Map<String, String> hawmeterUrlMap = hawSensor.getHawmeterUrls();
		synchronized (sensorWebservices) {
			for (Iterator<Entry<String, hawmetering.HAWSensorWebservice>> iterator = sensorWebservices.entrySet().iterator(); iterator.hasNext();) {
				Entry<String, hawmetering.HAWSensorWebservice> sensorUrlsEntry = iterator.next();
				try {
					// System.out.println("triggerung url: " + entry.getKey() + "---" + entry.getValue());
					sensorUrlsEntry.getValue().trigger();
				} catch (Exception e) {
					updateNeeded = true;
					// wenn nicht erreichbar
					System.out.println(e.toString());
					iterator.remove();
					// remove sensor from hawmeterUrl
					for (Iterator<Entry<String, String>> iterator2 = hawmeterUrlMap.entrySet().iterator(); iterator2.hasNext();) {
						Entry<String, String> hawmeterUrls = iterator2.next();
						if (hawmeterUrls.getValue().equals(sensorUrlsEntry.getKey())) {
							iterator2.remove();
						}
					}
					System.out.println("Sensor '" + sensorUrlsEntry.getKey() + "' failed, removing... ");
				}
			}
		}
		if (updateNeeded) {
			hawSensor.sendUpdateAllSensors();
		}
		
	}
}