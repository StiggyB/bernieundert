package hawsensor;

import java.util.HashMap;
import java.util.Map;

public class MapArrayAdapter {

	public static String[][] toArray(Map<String, String> map) {
		String[][] array = new String[map.size()][];
		int i = 0;
		for (Map.Entry<String, String> entry : map.entrySet()) {
			array[i] = new String[] { entry.getKey(), entry.getValue() };
			i++;
		}
		return array;
	}

	public static Map<String, String> toMap(String[][] array) {
		Map<String, String> map = new HashMap<String, String>();
		for (String[] strings : array) {
			map.put(strings[0], strings[1]);
		}
		return map;
	}
	

}
