package hawsensor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "StringStringMap")
@XmlAccessorType(XmlAccessType.FIELD)
public class StringStringMap {
	// produce a wrapper XML element around this collection
	@XmlElementWrapper(name = "sensorList")
	// map each member of this list to an XML element named appointment
	@XmlElement(name = "sensorUrls")
	private List<StringString> users;

	public StringStringMap() {
	}

	public StringStringMap(Map<String, String> map) {
		users = new ArrayList<StringString>();
		Set<Entry<String, String>> set = map.entrySet();
		for (Entry<String, String> idUserEntry : set) {
			users.add(new StringString(idUserEntry.getKey(), idUserEntry.getValue()));
		}
	}

	public List<StringString> getUsers() {
		return users;
	}

	protected static class StringString {
		@XmlElement(name = "id")
		private String id;
		@XmlElement(name = "user")
		private String user;

		protected StringString() {
		}

		protected StringString(String id, String user) {
			this.id = id;
			this.user = user;
		}

		protected String getId() {
			return id;
		}

		protected String getUser() {
			return user;
		}
	}
}
