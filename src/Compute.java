import java.lang.Math;
import java.util.Stack;
import java.util.InputMismatchException;

public class Compute {

	//Evaluates input. Returns ERROR if an exception is caught.
	public static String parseInput(String input, boolean radSelected) {
		try {
			input = evaluateMenuFunctions(input); //Evaluates menu functions (if any)
			input = evaluateParentheses(input, radSelected); //Evaluates parentheses (if any)
			CalcNode root = buildTree(input); //Builds input as a binary tree
			postOrderTraversal(root, radSelected); //Solves for solution by traversing through binary tree
			return root.getValue(); //Returns solution
		}
		catch(ArithmeticException ex) { return "ERROR"; }
		catch(IndexOutOfBoundsException ex) { return "ERROR"; }
		catch(InputMismatchException ex) { return "ERROR"; }
		catch(NullPointerException ex) { return "ERROR"; }
		catch(NumberFormatException ex) { return "ERROR"; }
	}
	
	//Evaluates menu functions of input before evaluating parentheses
	private static String evaluateMenuFunctions(String input) {
		if(input.indexOf("lcm") >= 0) {
			int a = Integer.parseInt(input.substring(input.indexOf("lcm")+4, input.indexOf(",", input.indexOf("lcm"))));
			int b = Integer.parseInt(input.substring(input.indexOf(",", input.indexOf("lcm"))+1, input.indexOf(")", input.indexOf("lcm"))));
			return evaluateMenuFunctions(input.substring(0, input.indexOf("lcm")) + String.valueOf(lcm(Math.min(a, b), Math.max(a, b))) +
					input.substring(input.indexOf(")", input.indexOf("lcm"))+1)); 
		}
		else if(input.indexOf("gcd") >= 0) {
			int a = Integer.parseInt(input.substring(input.indexOf("gcd")+4, input.indexOf(",", input.indexOf("gcd"))));
			int b = Integer.parseInt(input.substring(input.indexOf(",", input.indexOf("gcd"))+1, input.indexOf(")", input.indexOf("gcd"))));
			return evaluateMenuFunctions(input.substring(0, input.indexOf("gcd")) + String.valueOf(gcd(Math.min(a, b), Math.max(a, b))) +
					input.substring(input.indexOf(")", input.indexOf("gcd"))+1));
		}
		else if(input.indexOf("abs") >= 0) {
			int a = Integer.parseInt(input.substring(input.indexOf("abs")+4, input.indexOf(")", input.indexOf("abs"))));
			return evaluateMenuFunctions(input.substring(0, input.indexOf("abs")) + String.valueOf(Math.abs(a)) +
					input.substring(input.indexOf(")", input.indexOf("abs"))+1));
		}
		else if(input.indexOf("ceil") >= 0) {
			double a = Double.parseDouble(input.substring(input.indexOf("ceil")+5, input.indexOf(")", input.indexOf("ceil"))));
			return evaluateMenuFunctions(input.substring(0, input.indexOf("ceil")) + String.valueOf(Math.ceil(a)) +
					input.substring(input.indexOf(")", input.indexOf("ceil"))+1));
		}
		else if(input.indexOf("floor") >= 0) {
			double a = Double.parseDouble(input.substring(input.indexOf("floor")+6, input.indexOf(")", input.indexOf("floor"))));
			return evaluateMenuFunctions(input.substring(0, input.indexOf("floor")) + String.valueOf(Math.floor(a)) +
					input.substring(input.indexOf(")", input.indexOf("floor"))+1));
		}
		else if(input.indexOf("PI") >= 0) {
			return evaluateMenuFunctions(input.substring(0, input.indexOf("PI")) + String.valueOf(Math.PI) +
					input.substring(input.indexOf("PI")+2));
		}
		else if(input.indexOf("pow") >= 0) {
			double a = Double.parseDouble(input.substring(input.indexOf("pow")+4, input.indexOf(",", input.indexOf("pow"))));
			double b = Double.parseDouble(input.substring(input.indexOf(",", input.indexOf("pow"))+1, input.indexOf(")", input.indexOf("pow"))));
			return evaluateMenuFunctions(input.substring(0, input.indexOf("pow")) + String.valueOf(Math.pow(a, b)) +
					input.substring(input.indexOf(")", input.indexOf("pow"))+1));
		}
		else if(input.indexOf("sqrt") >= 0) {
			double a = Double.parseDouble(input.substring(input.indexOf("sqrt")+5, input.indexOf(")", input.indexOf("sqrt"))));
			return evaluateMenuFunctions(input.substring(0, input.indexOf("sqrt")) + String.valueOf(Math.sqrt(a)) +
					input.substring(input.indexOf(")", input.indexOf("sqrt"))+1));
		}
		else if(input.indexOf("log") >= 0) {
			double a = Double.parseDouble(input.substring(input.indexOf("log")+4, input.indexOf(",", input.indexOf("log"))));
			double b = Double.parseDouble(input.substring(input.indexOf(",", input.indexOf("log"))+1, input.indexOf(")", input.indexOf("log"))));
			return evaluateMenuFunctions(input.substring(0, input.indexOf("log")) + String.valueOf(log(a, b)) +
					input.substring(input.indexOf(")", input.indexOf("log"))+1));
		}
		else if(input.indexOf("ln") >= 0) {
			double a = Double.parseDouble(input.substring(input.indexOf("ln")+3, input.indexOf(")", input.indexOf("ln"))));
			return evaluateMenuFunctions(input.substring(0, input.indexOf("ln")) + String.valueOf(Math.log(a)) +
					input.substring(input.indexOf(")", input.indexOf("ln"))+1));
		}
		else if(input.indexOf("euler") >= 0) {
			double a = Double.parseDouble(input.substring(input.indexOf("euler")+6, input.indexOf(")", input.indexOf("euler"))));
			return evaluateMenuFunctions(input.substring(0, input.indexOf("euler")) + String.valueOf(Math.exp(a)) +
					input.substring(input.indexOf(")", input.indexOf("euler"))+1));
		}
		else if(input.indexOf("fact") >= 0) {
			int a = Integer.parseInt(input.substring(input.indexOf("fact")+5, input.indexOf(")", input.indexOf("fact"))));
			return evaluateMenuFunctions(input.substring(0, input.indexOf("fact")) + String.valueOf(fact(a)) +
					input.substring(input.indexOf(")", input.indexOf("fact"))+1));
		}
		else if(input.indexOf("perm") >= 0) {
			int a = Integer.parseInt(input.substring(input.indexOf("perm")+5, input.indexOf(",", input.indexOf("perm"))));
			int b = Integer.parseInt(input.substring(input.indexOf(",", input.indexOf("perm"))+1, input.indexOf(")", input.indexOf("perm"))));
			return evaluateMenuFunctions(input.substring(0, input.indexOf("perm")) + String.valueOf(perm(a, b)) +
					input.substring(input.indexOf(")", input.indexOf("perm"))+1));
		}
		else if(input.indexOf("comb") >= 0) {
			int a = Integer.parseInt(input.substring(input.indexOf("comb")+5, input.indexOf(",", input.indexOf("comb"))));
			int b = Integer.parseInt(input.substring(input.indexOf(",", input.indexOf("comb"))+1, input.indexOf(")", input.indexOf("comb"))));
			return evaluateMenuFunctions(input.substring(0, input.indexOf("comb")) + String.valueOf(comb(a, b)) +
					input.substring(input.indexOf(")", input.indexOf("comb"))+1));
		}
		else if(input.indexOf("rng") >= 0) {
			int a = Integer.parseInt(input.substring(input.indexOf("rng")+4, input.indexOf(")", input.indexOf("rng"))));
			return evaluateMenuFunctions(input.substring(0, input.indexOf("rng")) + String.valueOf(rng(a)) +
					input.substring(input.indexOf(")", input.indexOf("rng"))+1));
		}
		else {
			return input;
		}
	}
	
	//Evaluates parentheses of input before calculating result
	private static String evaluateParentheses(String input, boolean radSelected) {
		if((input.indexOf("(") == -1 && input.indexOf(")") >= 0) || (input.indexOf(")") == -1 && input.indexOf("(") >= 0) ||
				(input.indexOf(")") < input.indexOf("("))) { throw new InputMismatchException(); } //Catches if there are no matching parentheses
		else {
			Stack<Integer> in = new Stack<Integer>(); //Creates stack to hold ( indexes
			for(int i = 0; i < input.length(); i++) {
				if(input.charAt(i) == '(') { in.push(i); } //Pushes index of a ( to stack
				else if(!(in.isEmpty()) && input.charAt(i) == ')') { //Evaluates between previous ( on stack and ) index found
					int target = in.pop(); in = null;
					CalcNode root = buildTree(input.substring(target+1, i));
					postOrderTraversal(root, radSelected);
					return evaluateParentheses(input.substring(0, target) + root.getValue().replace('-', '~') + input.substring(i+1), radSelected); //Reevaluates for further parentheses
				}
			}
			return input;
		}
	}
	
	//Creates Binary tree from input
	private static CalcNode buildTree(String input) {
		CalcNode newNode = new CalcNode();
		if(input.length() != 0) {
			String result = orderOfOperations(input);
			if(result.charAt(0) == '+' || result.charAt(0) == '-' || result.charAt(0) == '*' || result.charAt(0) == '/' || result.charAt(0) == '%') {
				newNode.setValue(result); //sets value to node
				newNode.setLeft(buildTree(input.substring(0, input.lastIndexOf(result)))); //Creates left node from chars left of value
				newNode.setRight(buildTree(input.substring(input.lastIndexOf(result)+1))); //Creates right node from chars right of value
			}
			else if(result.charAt(0) == '~') {
				newNode.setValue(result);
				newNode.setRight(buildTree(input.substring(input.indexOf(result)+1)));
			}
			else if(result.indexOf("sin") == 0 || result.indexOf("cos") == 0 || result.indexOf("tan") == 0 || result.indexOf("csc") == 0
					|| result.indexOf("sec") == 0 || result.indexOf("cot") == 0) {
				newNode.setValue(result);
				newNode.setRight(buildTree(input.substring(input.lastIndexOf(result)+3)));
			}
			else if(result.indexOf("arcsin") == 0 || result.indexOf("arccos") == 0 || result.indexOf("arctan") == 0 || result.indexOf("arccsc") == 0
					|| result.indexOf("arcsec") == 0 || result.indexOf("arccot") == 0) {
				newNode.setValue(result);
				newNode.setRight(buildTree(input.substring(input.lastIndexOf(result)+6)));
			}
			else {
				newNode.setValue(input); //sets number value to node
			}
			return newNode;
		}
		return null;
	}
	
	//Scans input for Order of Operations
	private static String orderOfOperations(String input) {
		if(input.lastIndexOf("+") >= 0 && input.lastIndexOf("+") > input.lastIndexOf("-")) {
			return "+";
		}
		else if(input.lastIndexOf("-") >= 0 && input.lastIndexOf("-") > input.lastIndexOf("+")) {
			return "-";
		}
		else if(input.lastIndexOf("*") >= 0 && input.lastIndexOf("*") > input.lastIndexOf("/") && input.lastIndexOf("*") > input.lastIndexOf("%")) {
			return "*";
		}
		else if(input.lastIndexOf("/") >= 0 && input.lastIndexOf("/") > input.lastIndexOf("*") && input.lastIndexOf("/") > input.lastIndexOf("%")) {
			return "/";
		}
		else if(input.lastIndexOf("%") >= 0 && input.lastIndexOf("%") > input.lastIndexOf("*") && input.lastIndexOf("%") > input.lastIndexOf("/")) {
			return "%";
		}
		else if(input.indexOf("~") >= 0) {
			return "~";
		}
		else if(input.lastIndexOf("arcsin") >= 0) {
			return "arcsin";
		}
		else if(input.lastIndexOf("arccos") >= 0) {
			return "arccos";
		}
		else if(input.lastIndexOf("arctan") >= 0) {
			return "arctan";
		}
		else if(input.lastIndexOf("arccsc") >= 0) {
			return "arccsc";
		}
		else if(input.lastIndexOf("arcsec") >= 0) {
			return "arcsec";
		}
		else if(input.lastIndexOf("arccot") >= 0) {
			return "arccot";
		}
		else if(input.lastIndexOf("sin") >= 0) {
			return "sin";
		}
		else if(input.lastIndexOf("cos") >= 0) {
			return "cos";
		}
		else if(input.lastIndexOf("tan") >= 0) {
			return "tan";
		}
		else if(input.lastIndexOf("csc") >= 0) {
			return "csc";
		}
		else if(input.lastIndexOf("sec") >= 0) {
			return "sec";
		}
		else if(input.lastIndexOf("cot") >= 0) {
			return "cot";
		}
		else {
			return input;
		}
	}
	
	//Post-order traverses binary tree to solve the input using order of operations.
	private static void postOrderTraversal(CalcNode node, boolean radSelected) {
		if(node != null) {
			postOrderTraversal(node.getLeft(), radSelected); //traverses to left node
			postOrderTraversal(node.getRight(), radSelected); //traverses to right node
			double result; //Initialize result
			if(node.getValue().charAt(0) == '*') {
				result = Double.parseDouble(node.getLeft().getValue())*Double.parseDouble(node.getRight().getValue()); //Calculate child nodes
			}
			else if(node.getValue().charAt(0) == '/') {
				if(Double.parseDouble(node.getRight().getValue()) == 0.0) { throw new ArithmeticException(); } //Catches division by 0
				result = Double.parseDouble(node.getLeft().getValue())/Double.parseDouble(node.getRight().getValue());
			}
			else if(node.getValue().charAt(0) == '%') {
				if(Double.parseDouble(node.getRight().getValue()) == 0.0) { throw new ArithmeticException(); } //Catches modulus by 0
				result = Double.parseDouble(node.getLeft().getValue())%Double.parseDouble(node.getRight().getValue());
			}
			else if(node.getValue().charAt(0) == '~') {
				result = Double.parseDouble(node.getRight().getValue())*-1;
			}
			else if(node.getValue().charAt(0) == '+') {
				result = Double.parseDouble(node.getLeft().getValue())+Double.parseDouble(node.getRight().getValue());
			}
			else if(node.getValue().charAt(0) == '-') {
				result = Double.parseDouble(node.getLeft().getValue())-Double.parseDouble(node.getRight().getValue());
			}
			else if(node.getValue().indexOf("sin") == 0) {
				if(radSelected) { result = Math.sin(Double.parseDouble(node.getRight().getValue())); }
				else { result = Math.sin(Math.toRadians(Double.parseDouble(node.getRight().getValue()))); }
			}
			else if(node.getValue().indexOf("cos") == 0) {
				if(radSelected) { result = Math.cos(Double.parseDouble(node.getRight().getValue())); }
				else { result = Math.cos(Math.toRadians(Double.parseDouble(node.getRight().getValue()))); }
			}
			else if(node.getValue().indexOf("tan") == 0) {
				if(radSelected) { result = Math.tan(Double.parseDouble(node.getRight().getValue())); }
				else { result = Math.tan(Math.toRadians(Double.parseDouble(node.getRight().getValue()))); }
			}
			else if(node.getValue().indexOf("csc") == 0) {
				if(Math.sin(Double.parseDouble(node.getRight().getValue())) == 0.0) { throw new ArithmeticException(); }
				if(radSelected) { result = 1/(Math.sin(Double.parseDouble(node.getRight().getValue()))); }
				else { result = 1/(Math.sin(Math.toRadians(Double.parseDouble(node.getRight().getValue())))); }
			}
			else if(node.getValue().indexOf("sec") == 0) {
				if(Math.cos(Double.parseDouble(node.getRight().getValue())) == 0.0) { throw new ArithmeticException(); }
				if(radSelected) { result = 1/(Math.cos(Double.parseDouble(node.getRight().getValue()))); }
				else { result = 1/(Math.cos(Math.toRadians(Double.parseDouble(node.getRight().getValue())))); }
			}
			else if(node.getValue().indexOf("cot") == 0) {
				if(Math.tan(Double.parseDouble(node.getRight().getValue())) == 0.0) { throw new ArithmeticException(); }
				if(radSelected) { result = 1/(Math.tan(Double.parseDouble(node.getRight().getValue()))); }
				else { result = 1/(Math.tan(Math.toRadians(Double.parseDouble(node.getRight().getValue())))); }
			}
			else if(node.getValue().indexOf("arcsin") == 0) {
				if(radSelected) { result = Math.asin(Double.parseDouble(node.getRight().getValue())); }
				else { result = Math.toDegrees(Math.asin(Double.parseDouble(node.getRight().getValue()))); }
			}
			else if(node.getValue().indexOf("arccos") == 0) {
				if(radSelected) { result = Math.acos(Double.parseDouble(node.getRight().getValue())); }
				else { result = Math.toDegrees(Math.acos(Double.parseDouble(node.getRight().getValue()))); }
			}
			else if(node.getValue().indexOf("arctan") == 0) {
				if(radSelected) { result = Math.atan(Double.parseDouble(node.getRight().getValue())); }
				else { result = Math.toDegrees(Math.atan(Double.parseDouble(node.getRight().getValue()))); }
			}
			else if(node.getValue().indexOf("arccsc") == 0) {
				if(Math.asin(Double.parseDouble(node.getRight().getValue())) == 0.0) { throw new ArithmeticException(); }
				if(radSelected) { result = Math.asin(1/(Double.parseDouble(node.getRight().getValue()))); }
				else { result = Math.toDegrees(Math.asin(1/(Double.parseDouble(node.getRight().getValue())))); }
			}
			else if(node.getValue().indexOf("arcsec") == 0) {
				if(Math.acos(Double.parseDouble(node.getRight().getValue())) == 0.0) { throw new ArithmeticException(); }
				if(radSelected) { result = Math.acos(1/(Double.parseDouble(node.getRight().getValue()))); }
				else { result = Math.toDegrees(Math.acos(1/(Double.parseDouble(node.getRight().getValue())))); }
			}
			else if(node.getValue().indexOf("arccot") == 0) {
				if(Math.atan(Double.parseDouble(node.getRight().getValue())) == 0.0) { throw new ArithmeticException(); }
				if(radSelected) { result = Math.atan(1/(Double.parseDouble(node.getRight().getValue()))); }
				else { result = Math.toDegrees(Math.atan(1/(Double.parseDouble(node.getRight().getValue())))); }
			}
			else {
				result = Double.parseDouble(node.getValue()); //no change to node therefore must be a number
			}
			node.setLeft(null); node.setRight(null); //Deletes child nodes
			node.setValue(String.valueOf(result)); //set current node to result
		}
	}
	
	//Evaluates Least Common Multiple of two numbers
	private static int lcm(int a, int b) {
		return a*b/gcd(Math.min(a, b), Math.max(a, b));
	}
	
	//Evaluates Greatest Common Denominator of two numbers
	private static int gcd(int a, int b) {
		int c = b % a;
		if(c == 0) { return a; }
		else { return gcd(c, a); }
	}
	
	//Evaluates the logarithm using the base and number
	private static double log(double base, double num) {
		return Math.log(num)/Math.log(base);
	}
	
	//Evaluates the factorial of a number
	private static int fact(int n) {
		if( n == 0 ) { return 1; }
		else { return n*fact(n-1); }
	}
	
	//Evaluates the permutation of a number
	private static int perm(int n, int r) {
		if( r == 0) { return 1; }
		else { return n*perm(n-1, r-1); }
	}
	
	//Evaluates the combination of a number
	private static int comb(int n, int r) {
		return fact(n)/(fact(r)*fact(n-r));
	}
	
	//Generates a random number given a range
	private static int rng(int n) {
		return (int) (n*Math.random()) + 1;
	}
	
}