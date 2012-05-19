package hawsensor;

import java.util.ArrayList;

import net.java.dev.jaxb.array.StringArray;
import net.java.dev.jaxb.array.StringArrayArray;

public class BlubStringArrayArray extends StringArrayArray {
	
	public BlubStringArrayArray(String[][] array) {
		item = new ArrayList<StringArray>();
		for (String[] strings : array) {
			item.add(new BlubStringArray(strings));
		}
	}
}
