package hawsensor;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class SensorUrlMapAdapter extends XmlAdapter<StringStringMap, Map<String, String> >{

	@Override
	public StringStringMap marshal(Map<String, String> v) throws Exception {
        StringStringMap map = new StringStringMap();
        for (Map.Entry<String, String> e : v.entrySet()) { 
        	StringStringMap.StringStringEntry iue = new StringStringMap.StringStringEntry();
            iue.setValue(e.getValue());
            iue.setKey(e.getKey());
            map.getEntries().add(iue);
        }
        return map;
	}

	@Override
	public Map<String, String> unmarshal(StringStringMap v) throws Exception {
        Map<String, String> map = new LinkedHashMap<String, String>();
        for (StringStringMap.StringStringEntry e : v.getEntries()) {
            map.put(e.getKey(), e.getValue());
        }
        return map;
    }

}
