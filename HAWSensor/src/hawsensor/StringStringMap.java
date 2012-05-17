package hawsensor;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


@XmlType(name = "StringStringMap")
@XmlAccessorType(XmlAccessType.FIELD)
public class StringStringMap {
	@XmlElement(nillable = false, name = "entry")
    List<StringStringEntry> entries = new ArrayList<StringStringEntry>();

    public List<StringStringEntry> getEntries() {
        return entries;
    }

    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "urlsInMap")
    static class StringStringEntry {
        //Map keys cannot be null
        @XmlElement(required = true, nillable = false)
        String key;

        String value;

        public void setKey(String k) {
            key = k;
        }
        public String getKey() {
            return key;
        }

        public void setValue(String u) {
            value = u;
        }
        public String getValue() {
            return value;
        }
    }

}
