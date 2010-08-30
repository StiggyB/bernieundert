package a09;

/** 
 * @author Bernie und Ert
 * 
 * Eine Klasse, welche die Logik für den
 * Taschenrechner verwaltet und u.a.
 * ueber zwei sog. Stacks verfuegt.
 * 
 * Diese ermoeglichen redo, undo und repeat.
 * 
 *
 */
import java.util.Stack;

public class Calculator {

	private Stack<Command> history = new Stack<Command>();
	private Stack<Command> undoHistory = new Stack<Command>();

	private CalculatorIO calcIO = new CalculatorIO();
	private double currentValue;

	public void run() {

		currentValue = calcIO.readFirstOperand();

		String input = null;
		do {

			calcIO.printMenu();
			input = calcIO.readUserInput();
			Command command = null;
			if (input.equals("w")) {
				if (history.isEmpty()) {
					calcIO.printErrorEmptyHistory();
				} else {
					command = history.peek();
					calcIO.printRepeatLastCommand(command);
					currentValue = command.execute(currentValue);
					history.push(command);
				}
			} else if (input.equals("u")) {
				if (history.isEmpty()) {
					calcIO.printErrorEmptyHistory();
				} else {
					undoHistory.push(history.peek());
					command = history.pop().getReverse();
					calcIO.printUndoLastCommand(command);
					currentValue = command.execute(currentValue);
				}
			} else if (input.equals("r")) {
				if (undoHistory.isEmpty()) {
					calcIO.printErrorEmptyHistory();
				} else {
					history.push(undoHistory.peek());
					command = undoHistory.pop();
					calcIO.printRedoLastCommand(command);
					currentValue = command.execute(currentValue);
				}
			} else if (input.equals("n")) {
				history.removeAllElements();
				undoHistory.removeAllElements();
				currentValue = calcIO.readFirstOperand();

			} else {

				String operator = input.substring(0, 1);
				String value = input.substring(1);
				if (operator.equals("+")) {
					command = new Addition(Double.parseDouble(value));
				} else if (operator.equals("-")) {
					command = new Subtraktion(Double.parseDouble(value));
				} else if (operator.equals("*")) {
					command = new Multiplikation(Double.parseDouble(value));
				} else if (operator.equals("/")) {
					command = new Division(Double.parseDouble(value));
				}
				if (command != null) {
					currentValue = command.execute(currentValue);
					history.push(command);
				}
			}
			calcIO.printCurrentValue(currentValue);
			calcIO.outputStacks(history, undoHistory);
		} while (!input.equals("e"));

	}

}