package a10;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

abstract class AbstractTestHashTable {

	protected List<String> ipsGUI = new ArrayList<String>();
	protected List<String> values = new ArrayList<String>();
	protected List<String> ips = new ArrayList<String>();

	public void readAndStoreDataFromLog() throws IOException {
		Scanner sc = new Scanner(new File("./src/a10/apache.txt"));

		String[] nextLine = null;

		while (sc.hasNextLine()) {
			nextLine = sc.nextLine().trim().split(" - - ");
			ips.add(nextLine[0]);
			if (!ipsGUI.contains(nextLine[0])) {
				ipsGUI.add(nextLine[0]);
			}
			values.add(nextLine[0] + " - " + nextLine[1]);
		}
	}
}
