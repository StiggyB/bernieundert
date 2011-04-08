package a02.stack;

public class StackTest {

	public static void main(String[] args) {
		IStack<Character> operators = new Stack<Character>();
		IStack<Integer> operands = new Stack<Integer>();
		
		String term = "( ( 6 * ( 4 * 28 ) ) + ( 9 - ( ( 12 / 4 ) * 2 ) ) )";
		String[] splits = term.split(" ");

		
		for (int i = 0; i < splits.length; i++) {
			String split = splits[i];
			if (split.equals("*") || split.equals("/") || split.equals("+") || split.equals("-")) {
				operators.push(split.charAt(0));
			} else if (split.equals("(")) {
			} else if (split.equals(")")) {
				System.out.println(operands);
				System.out.println(operators);
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
		System.out.println(operands);
		System.out.println(operators);
		
	}
	
}

//		Stack<String> stack = new Stack<String>();
//		stack.push("a");
//		stack.push("b");
//		
//		System.out.println(stack.top());
//		System.out.println(stack.top());
//		
//		stack.pop();
//		System.out.println(stack.top());