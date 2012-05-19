package hawsensor;

import java.util.ArrayList;

import net.java.dev.jaxb.array.StringArray;

public class StringArrayConverter extends StringArray {

	public StringArrayConverter(String[] strings) {
		item = new ArrayList<String>();
		for (String string : strings) {
			item.add(string);
		}
	}

}
