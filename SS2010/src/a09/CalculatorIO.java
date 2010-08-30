package a09;

/**
 * 
 * @author Bernie und Ert
 *
 * Eine Klasse, welche die Ein- und Ausgaben
 * ermoeglicht.	
 *
 */

import java.util.Scanner;
import java.util.Stack;

public class CalculatorIO {

	Scanner sc = new Scanner(System.in);

	public void printCurrentValue(double currentValue) {
		System.err.println("Aktueller Wert: " + currentValue);
	}

	public void printRedoLastCommand(Command command) {
		System.out.println("Schritt vorwärts: " + command);
	}

	public void printUndoLastCommand(Command command) {
		System.out.println("Schritt rückwärts: " + command);
	}

	public void printErrorEmptyHistory() {
		System.err.println("Die History ist leer; wiederholen nicht möglich!");
	}

	public void printRepeatLastCommand(Command command) {
		System.out.println("Schritt wiederholen: " + command);
	}

	public void printMenu() {
		System.out.println("");
		System.out.println("==============================================");
		System.out.println("Syntax: [Rechenart >>>  * / + -  <<<] [Zahl]");
		System.out.println("==============================================");
		System.out.println("Zum Beispiel: +13.37");
		System.out.println("[r]edo [u]ndo [w]iederholen [n]eustart [e]xit");
	}

	public void outputStacks(Stack<Command> history, Stack<Command> undoHistory) {
		System.out.println("history: " + history);
		System.out.println("undo-history: " + undoHistory);
	}

	public String readUserInput() {
		System.out.println("Bitte Operation eingeben: ");
		return sc.next();
	}

	public double readFirstOperand() {
		System.out.println("Bitte geben Sie den ersten Operanden ein!");
		return sc.nextDouble();
	}

}
