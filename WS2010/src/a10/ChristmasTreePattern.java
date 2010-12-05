package a10;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author aay973
 * 
 */
public class ChristmasTreePattern {

	public String getChristmasTreePattern(int i, int n) {
		List<List<String>> lines = new ArrayList<List<String>>();
		// n = 1
		List<String> firstLine = new ArrayList<String>();
		firstLine.add("0");
		firstLine.add("1");
		lines.add(firstLine);

		iterate(lines, i, n);
		StringBuilder sb = new StringBuilder();
		sb.append("<html><body><p align=\"center\">");
		for (List<String> line : lines) {
			for (String element : line) {
				sb.append(element + " ");
			}
			sb.append("<br>");
		}
		sb.append("</p></body></html>");
		return sb.toString();

	}

	private void iterate(List<List<String>> lines, int i, int n) {
		if (i < n) {
			List<List<String>> newLines = new ArrayList<List<String>>();
			for (List<String> line : lines) {
				List<String> newLine;
				if (line.size() == 1) {
					// nur die untere Anweisung machen
					// zweite Anweisung
					newLine = new ArrayList<String>();
					newLine.add(line.get(0).concat("0"));
					for (int j = 0; j < line.size(); j++) {
						String s = line.get(j);
						newLine.add(s.concat("1"));
					}
					newLines.add(newLine);
				} else {
					// beide Anweisungen machen
					// erste Anweisung
					newLine = new ArrayList<String>();
					for (int j = 1; j < line.size(); j++) {
						String s = line.get(j);
						newLine.add(s.concat("0"));
					}
					newLines.add(newLine);

					// zweite Anweisung
					newLine = new ArrayList<String>();
					newLine.add(line.get(0).concat("0"));
					for (int j = 0; j < line.size(); j++) {
						String s = line.get(j);
						newLine.add(s.concat("1"));
					}
					newLines.add(newLine);

				}
			}
			// ohne lines.clear(); erhalten wir als Ausgabe nicht das Muster von
			// n=x sondern alle Durchl�ufe von n.
			lines.clear();
			lines.addAll(newLines);
			iterate(lines, ++i, n);

		}
	}

}
