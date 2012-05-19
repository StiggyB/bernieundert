package hawsensor;

import java.util.ArrayList;

import net.java.dev.jaxb.array.StringArray;

public class BlubStringArray extends StringArray {

	public BlubStringArray(String[] strings) {
		item = new ArrayList<String>();
		for (String string : strings) {
			item.add(string);
		}
	}

}
