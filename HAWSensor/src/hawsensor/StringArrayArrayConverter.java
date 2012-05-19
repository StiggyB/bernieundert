package hawsensor;

import java.util.ArrayList;

import net.java.dev.jaxb.array.StringArray;
import net.java.dev.jaxb.array.StringArrayArray;

public class StringArrayArrayConverter extends StringArrayArray {
	
	public StringArrayArrayConverter(String[][] array) {
		item = new ArrayList<StringArray>();
		for (String[] strings : array) {
			item.add(new StringArrayConverter(strings));
		}
	}
}
