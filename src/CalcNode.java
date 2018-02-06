
public class CalcNode {

	//Private Instance Variables
	private CalcNode left, right;
	private String value;
	
	//Constructor
	public CalcNode() {
		value = null;
		left = null;
		right = null;
	}
	
	//Constructor
	public CalcNode(String value) {
		this.value = value;
		left = null;
		right = null;
	}
	
	//Access Methods
	public CalcNode getLeft() {
		return left;
	}
	
	public CalcNode getRight() {
		return right;
	}
	
	public String getValue() {
		return value;
	}
	
	//Mutator methods
	public void setLeft(CalcNode left) {
		this.left = left;
	}
	
	public void setRight(CalcNode right) {
		this.right = right;
	}
	
	public void setValue(String value) {
		this.value = value;
	}
	
}