package hawsensor;

import hawmetering.HAWSensorWebservice;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public class SensorTriggerThread extends Thread {

	private Map<String, hawmetering.HAWSensorWebservice> sensorUrls;
	private Map<String, String> hawmeterUrls = new HashMap<String, String>();

	public SensorTriggerThread(Map<String, HAWSensorWebservice> sensorUrls, Map<String, String> hawmeterUrls) {
		this.sensorUrls = sensorUrls;
		this.hawmeterUrls = hawmeterUrls;
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
			Entry<String, hawmetering.HAWSensorWebservice> sensorUrlsEntry = iterator.next();
			try {
//				System.out.println("triggerung url: " + entry.getKey() + "---" + entry.getValue());
				sensorUrlsEntry.getValue().trigger();
			} catch (Exception e) {
				// wenn nicht erreichbar
				System.out.println(e.toString());
				iterator.remove();
				for (Iterator<Entry<String, String>> iterator2 = hawmeterUrls.entrySet().iterator(); iterator2.hasNext();) {
					Entry<String, String> hawmeterUrls = iterator2.next();
					if(hawmeterUrls.getValue().equals(sensorUrlsEntry.getKey())){
						iterator2.remove();
					}
				}
				System.out.println("Sensor '" + sensorUrlsEntry.getKey() + "' failed, removing... ");
			}
		}
	}
}