import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class CalculatorFrame extends JFrame {
	
	//Serial Version ID
	private static final long serialVersionUID = -176354308319475544L;
	
	//Private Instance Variables
	private MenuFrame MenuFrame;
	private TrigFrame TrigFrame;
	private final int FRAME_WIDTH = 450, FRAME_HEIGHT = 600;
	private final int SCREEN_WIDTH = FRAME_WIDTH - 50, SCREEN_HEIGHT = FRAME_HEIGHT/3;
	private final int BUTTON_WIDTH = 70, BUTTON_HEIGHT = 50;
	private final int FONT_SIZE = 16;
	private final Color FRAME_COLOR = Color.BLACK;
	private final Color BUTTON_COLOR = Color.WHITE;
	private final Color FONT_COLOR = Color.CYAN;
	private boolean radSelected;
	private JLabel title, label;
	private JTextArea results;
	private JScrollPane screen;
	private JPanel buttonPanel;
	private JButton menu, trig, seven, eight, nine, del, mod, four, five, six, multiply, divide, one, two, three, plus, minus,
	openParentheses, closeParentheses, zero, dot, negative, enter, clear;
	
	//Constructor
	public CalculatorFrame() {
		CalculatorFramework();
		CalculatorContents();
	}
	
	//Creates framework for main frame
	private void CalculatorFramework() {
		getContentPane().setBackground(FRAME_COLOR); //sets color background
		setSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT)); //sets frame size
		setLocationRelativeTo(null); //centers frame on screen
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Default close operation
		setTitle("Calculator"); //Sets title of frame
		setResizable(false); //Disables resize
	}
	
	//Creates contents included in main frame
	private void CalculatorContents() {
		getContentPane().setLayout(new FlowLayout()); //sets frame layout
		radSelected = true; //Determines if trig functions need to calculate radians or degrees
		
		title = new JLabel("Calculator"); //sets label above screen
		title.setFont(new Font("", 0, FONT_SIZE)); //sets font size
		title.setForeground(FONT_COLOR); //sets font color
		getContentPane().add(title);
		
		results = new JTextArea(" "); //set screen
		results.setEditable(false); //prevents user from editing the screen
		screen = new JScrollPane(results); //place screen in scroll pane
		screen.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT)); //set screen size
		getContentPane().add(screen);
		
		label = new JLabel("Created by Benjamin Tycksen"); //sets label below screen
		label.setFont(new Font("", 0, FONT_SIZE)); //sets font size
		label.setForeground(FONT_COLOR); //sets font color
		getContentPane().add(label);
		
		buttonPanel = new JPanel(new FlowLayout()); //set buttons' label, color, size, layout, and listener
		buttonPanel.setBackground(FRAME_COLOR); //sets background to match the frame background
		buttonPanel.setPreferredSize(new Dimension(SCREEN_WIDTH, FRAME_HEIGHT/2));
		trig = new JButton("Trig"); trig.setBackground(BUTTON_COLOR); trig.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
		trig.addActionListener(new ButtonListener()); buttonPanel.add(trig);
		seven = new JButton("7"); seven.setBackground(BUTTON_COLOR); seven.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
		seven.addActionListener(new ButtonListener()); buttonPanel.add(seven);
		eight = new JButton("8"); eight.setBackground(BUTTON_COLOR); eight.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
		eight.addActionListener(new ButtonListener()); buttonPanel.add(eight);
		nine = new JButton("9"); nine.setBackground(BUTTON_COLOR); nine.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
		nine.addActionListener(new ButtonListener()); buttonPanel.add(nine);
		menu = new JButton("Menu"); menu.setBackground(BUTTON_COLOR); menu.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
		menu.addActionListener(new ButtonListener()); buttonPanel.add(menu);
		mod = new JButton("%"); mod.setBackground(BUTTON_COLOR); mod.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
		mod.addActionListener(new ButtonListener()); buttonPanel.add(mod);
		four = new JButton("4"); four.setBackground(BUTTON_COLOR); four.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
		four.addActionListener(new ButtonListener()); buttonPanel.add(four);
		five = new JButton("5"); five.setBackground(BUTTON_COLOR); five.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
		five.addActionListener(new ButtonListener()); buttonPanel.add(five);
		six = new JButton("6"); six.setBackground(BUTTON_COLOR); six.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
		six.addActionListener(new ButtonListener()); buttonPanel.add(six);
		del = new JButton("Del"); del.setBackground(BUTTON_COLOR); del.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
		del.addActionListener(new ButtonListener()); buttonPanel.add(del);
		multiply = new JButton("*"); multiply.setBackground(BUTTON_COLOR); multiply.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
		multiply.addActionListener(new ButtonListener()); buttonPanel.add(multiply);
		one = new JButton("1"); one.setBackground(BUTTON_COLOR); one.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
		one.addActionListener(new ButtonListener()); buttonPanel.add(one);
		two = new JButton("2"); two.setBackground(BUTTON_COLOR); two.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
		two.addActionListener(new ButtonListener()); buttonPanel.add(two);
		three = new JButton("3"); three.setBackground(BUTTON_COLOR); three.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
		three.addActionListener(new ButtonListener()); buttonPanel.add(three);
		divide = new JButton("/"); divide.setBackground(BUTTON_COLOR); divide.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
		divide.addActionListener(new ButtonListener()); buttonPanel.add(divide);
		plus = new JButton("+"); plus.setBackground(BUTTON_COLOR); plus.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
		plus.addActionListener(new ButtonListener()); buttonPanel.add(plus);
		zero = new JButton("0"); zero.setBackground(BUTTON_COLOR); zero.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
		zero.addActionListener(new ButtonListener()); buttonPanel.add(zero);
		dot = new JButton("."); dot.setBackground(BUTTON_COLOR); dot.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
		dot.addActionListener(new ButtonListener()); buttonPanel.add(dot);
		negative = new JButton("(-)"); negative.setBackground(BUTTON_COLOR); negative.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
		negative.addActionListener(new ButtonListener()); buttonPanel.add(negative);
		minus = new JButton("-"); minus.setBackground(BUTTON_COLOR); minus.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
		minus.addActionListener(new ButtonListener()); buttonPanel.add(minus);
		openParentheses = new JButton("("); openParentheses.setBackground(BUTTON_COLOR); openParentheses.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
		openParentheses.addActionListener(new ButtonListener()); buttonPanel.add(openParentheses);
		closeParentheses = new JButton(")"); closeParentheses.setBackground(BUTTON_COLOR); closeParentheses.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
		closeParentheses.addActionListener(new ButtonListener()); buttonPanel.add(closeParentheses);
		clear = new JButton("C"); clear.setBackground(BUTTON_COLOR); clear.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
		clear.addActionListener(new ButtonListener()); buttonPanel.add(clear);
		enter = new JButton("Enter"); enter.setBackground(BUTTON_COLOR); enter.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
		enter.addActionListener(new ButtonListener()); buttonPanel.add(enter);
		getContentPane().add(buttonPanel);
		
		MenuFrame = new MenuFrame(this); MenuFrame.setVisible(false); //Creates a new MenuFrame and sets it invisible
		TrigFrame = new TrigFrame(this); TrigFrame.setVisible(false); //Creates a new TrigFrame and sets it invisible
	}
	
	//Edits screen by concatenating result
	public void setScreen(String result) {
		results.setText(results.getText() + result);
	}
	
	//Sets Calculator to Radian/Degree mode
	public void setRad(boolean state) {
		radSelected = state;
	}
	
	//Runs Program
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new CalculatorFrame().setVisible(true);
			}
		});
	}
	
	//Button Listener Class
	private class ButtonListener implements ActionListener {
		//Button Commands
		public void actionPerformed(ActionEvent event) {
			String input, result;
			if(event.getSource() == trig) { //Reveals Trigonometry Functions Frame
				TrigFrame.setVisible(true);
			}
			else if(event.getSource() == seven) { results.setText(results.getText() + "7");	} //Inputs 7
			else if(event.getSource() == eight) { results.setText(results.getText() + "8");	} //Inputs 8
			else if(event.getSource() == nine) { results.setText(results.getText() + "9");	} //Inputs 9
			else if(event.getSource() == menu) { //Reveals Menu Functions Frame
				MenuFrame.setVisible(true);
			}
			else if(event.getSource() == mod) {	results.setText(results.getText() + "%");	} //Inputs % (modulus)
			else if(event.getSource() == four) { results.setText(results.getText() + "4");	} //Inputs 4
			else if(event.getSource() == five) { results.setText(results.getText() + "5");	} //Inputs 5
			else if(event.getSource() == six) {	results.setText(results.getText() + "6");	} //Inputs 6
			else if(event.getSource() == del) { //Deletes previous character
				if(results.getText().length() != 0 && !(results.getText().endsWith(" "))) {
					results.setText(results.getText().substring(0, results.getText().length()-1));
				}
			}
			else if(event.getSource() == multiply) { results.setText(results.getText() + "*");	} //Inputs * (multiply)
			else if(event.getSource() == one) {	results.setText(results.getText() + "1");	} //Inputs 1
			else if(event.getSource() == two) {	results.setText(results.getText() + "2");	} //Inputs 2
			else if(event.getSource() == three) { results.setText(results.getText() + "3");	} //Inputs 3
			else if(event.getSource() == divide) { results.setText(results.getText() + "/");	} //Inputs / (divide)
			else if(event.getSource() == plus) { results.setText(results.getText() + "+");	} //Inputs + (add)
			else if(event.getSource() == zero) { results.setText(results.getText() + "0");	} //Inputs 0
			else if(event.getSource() == dot) { results.setText(results.getText() + ".");	} //Inputs . (decimal point)
			else if(event.getSource() == negative) { results.setText(results.getText() + "~");	} //Inputs ~ (negative sign)
			else if(event.getSource() == minus) { results.setText(results.getText() + "-");	} //Inputs - (minus)
			else if(event.getSource() == openParentheses) { results.setText(results.getText() + "(");	} //Inputs open parentheses
			else if(event.getSource() == closeParentheses) { results.setText(results.getText() + ")");	} //Inputs close parentheses
			else if(event.getSource() == clear) { results.setText(" ");	} //Clears the Calculator screen
			else if(event.getSource() == enter) { //Enters input into Calculator, then produces the output on screen
				input = results.getText().substring(results.getText().lastIndexOf(" ")+1).trim();
				if(input.length() != 0) {
					result = Compute.parseInput(input, radSelected);
					results.setText(results.getText() + "\n> " + result + "\n ");
				}
				input = ""; result = "";
			}
			else { results.setText(results.getText() + "ERROR");	}
		}		
	}

}