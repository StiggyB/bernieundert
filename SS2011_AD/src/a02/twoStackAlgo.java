package a02;

/**
 * Diese Klasse implementiert den sogenannten "zwei Stack Algorithmus" für
 * vollstaendig geklammerte Ausdruecke. TIB3-AD_Script. Siehe auch im Script auf
 * Seite 21.
 * 
 * Klammerausdruecke sind vollstaendig geklammert und mit Leerzeichnen je
 * Operand und Operator zu trennen.
 * 
 * @author Tugend und Laster
 */
public class twoStackAlgo {

	public static void main(String[] args) {

		IStack<Character> operators = new Stack<Character>();
		IStack<Integer> operands = new Stack<Integer>();

//		String term = "( ( 6 * ( 4 * 28 ) ) + ( 9 - ( ( 12 / 4 ) * 2 ) ) )"; //675
//		String term = "( ( ( 9 - ( 6 * 3 ) ) + 4 ) + ( ( 18 / 3 ) - 6 ) )"; //-5
		String term = "( ( ( ( 3 * 5 ) - 9 ) / 2 ) + ( ( 4 / 2 ) + 5 ) )"; //10
		String[] splits = term.split(" ");

		for (int i = 0; i < splits.length; i++) {
			String split = splits[i];
			
			//Es koennte auch eine Liste/Array von Operatoren erstellt werden, 
			//der zusaetzliche Aufwand zum Suchen/Vergleichen in der Liste und
			//der ggf. entstehende Mehraufwand im Code wuerde nichts einsparen.
			//So bleibt der Code leserlich und schnell verstaendlich
			if (split.equals("*") || split.equals("/") || split.equals("+")
					|| split.equals("-")) {
				operators.push(split.charAt(0));
			//oeffnende Klammern werden ignoriert
			} else if (split.equals("(")) {
			} else if (split.equals(")")) {
				System.out.println("Operanden: " + operands);
				System.out.println("Operatoren" + operators);
				Character operator = operators.top();
				operators.pop();
				Integer operand2 = operands.top();
				operands.pop();
				Integer operand1 = operands.top();
				operands.pop();
				Integer result = 0;
				switch (operator) {
				case '*':
					result = operand1 * operand2;
					break;
				case '/':
					result = operand1 / operand2;
					break;
				case '-':
					result = operand1 - operand2;
					break;
				case '+':
					result = operand1 + operand2;
					break;
				}
				operands.push(result);
			} else {
				operands.push(Integer.parseInt(split));
			}
		}
		System.out.println("\nOperanden (Ergebnis): " + operands);
		System.out.println("Operatoren: " + operators);

	}

}