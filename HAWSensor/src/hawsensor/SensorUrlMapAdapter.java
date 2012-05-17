package hawsensor;

import hawsensor.StringStringMap.StringString;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class SensorUrlMapAdapter extends XmlAdapter<StringStringMap, Map<String, String>> {
	
	@Override
	public StringStringMap marshal(Map<String, String> map) throws Exception {
		return new StringStringMap(map);
	}

	@Override
	public Map<String, String> unmarshal(StringStringMap type) throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		List<StringString> users = type.getUsers();
		for (StringStringMap.StringString idUser : users) {
			map.put(idUser.getId(), idUser.getUser());
		}
		return map;
	}

}
