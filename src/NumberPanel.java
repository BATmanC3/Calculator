import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class NumberPanel extends JPanel {

	//Serial Version ID
	private static final long serialVersionUID = -6446805700885082274L;
	
	//Private Instance Variables
	private CalculatorFrame cf;
	private final Color PANEL_COLOR = Color.BLACK;
	private JLabel label, subLabel, errorLabel;
	private final int FONT_SIZE = 16;
	private final int BUTTON_WIDTH = 70, BUTTON_HEIGHT = 30;
	private final Color FONT_COLOR = Color.CYAN;
	private final Color ERROR_FONT_COLOR = Color.RED;
	private final Color BUTTON_COLOR = Color.WHITE;
	private JComboBox<String> options;
	private JTextField input1, input2;
	private JButton enter;

	//Constructor
	public NumberPanel(CalculatorFrame cf, MenuFrame mf) {
		this.cf = cf;
		
		this.setBackground(PANEL_COLOR);
		
		label = new JLabel("Please select an option below:");
		label.setFont(new Font("", 0, FONT_SIZE)); //sets font size
		label.setForeground(FONT_COLOR); //sets font color
		this.add(label);
		
		String[] list = {"None", "Least Common Multiple", "Greatest Common Denominator", "Absolute Value", "Ceiling", "Floor"};
		options = new JComboBox<String>(list); //Creates ComboBox with the above selections
		options.setPreferredSize(new Dimension(((int) (mf.getWidth()/1.2)), mf.getHeight()/10)); //sets size
		options.setSelectedIndex(0); //sets ComboBox to default option
		options.addActionListener(new OptionsListener()); //enables listener
		this.add(options);
		
		subLabel = new JLabel("Please input value(s) below:");
		subLabel.setFont(new Font("", 0, FONT_SIZE)); //sets font size
		subLabel.setForeground(FONT_COLOR); //sets font color
		subLabel.setVisible(false); //Makes text invisible until an option is selected
		this.add(subLabel);
		
		errorLabel = new JLabel();
		errorLabel.setFont(new Font("", 0, FONT_SIZE)); //sets font size
		errorLabel.setForeground(ERROR_FONT_COLOR); //sets font color
		errorLabel.setVisible(false); //Makes text invisible until an option is selected
		this.add(errorLabel);
		
		input1 = new JTextField();
		input1.setPreferredSize(new Dimension(((int) (mf.getWidth()/1.2)), mf.getHeight()/10)); //sets size
		input1.setVisible(false); //Makes text field invisible until an option is selected
		this.add(input1);
		
		input2 = new JTextField();
		input2.setPreferredSize(new Dimension(((int) (mf.getWidth()/1.2)), mf.getHeight()/10)); //sets size
		input2.setVisible(false); //Makes text field invisible until an option is selected
		this.add(input2);
		
		enter = new JButton("Enter"); enter.setBackground(BUTTON_COLOR); enter.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
		enter.addActionListener(new ButtonListener()); enter.setVisible(false);
		this.add(enter);
	}
	
	//Options Listener Class
	private class OptionsListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			switch(options.getSelectedItem().toString()) {
				case "None":
					subLabel.setVisible(false); errorLabel.setVisible(false);
					input1.setVisible(false);  input2.setVisible(false);
					enter.setVisible(false);
					break;
				case "Least Common Multiple":
					subLabel.setVisible(true); errorLabel.setVisible(false);
					input1.setText("[Enter an Integer Greater than 0 Here]"); input1.setVisible(true);
					input2.setText("[Enter an Integer Greater than 0 Here]"); input2.setVisible(true);
					enter.setVisible(false); enter.setVisible(true);
					break;
				case "Greatest Common Denominator":
					subLabel.setVisible(true); errorLabel.setVisible(false);
					input1.setText("[Enter an Integer Greater than 0 Here]"); input1.setVisible(true);
					input2.setText("[Enter an Integer Greater than 0 Here]"); input2.setVisible(true);
					enter.setVisible(false); enter.setVisible(true);
					break;
				case "Absolute Value":
					subLabel.setVisible(true); errorLabel.setVisible(false);
					input1.setText("[Enter an Integer or Float Here]"); input1.setVisible(true);
					input2.setVisible(false);
					enter.setVisible(false); enter.setVisible(true);
					break;
				case "Ceiling":
					subLabel.setVisible(true); errorLabel.setVisible(false);
					input1.setText("[Enter an Integer or Float Here]"); input1.setVisible(true);
					input2.setVisible(false);
					enter.setVisible(false); enter.setVisible(true);
					break;
				case "Floor":
					subLabel.setVisible(true); errorLabel.setVisible(false);
					input1.setText("[Enter an Integer or Float Here]"); input1.setVisible(true);
					input2.setVisible(false);
					enter.setVisible(false); enter.setVisible(true); 
					break;
			}
		}		
	}
	
	//Button Listener Class
	private class ButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			String result = ""; int in1, in2; //Storage for inputs and result
			try {
				if(options.getSelectedIndex() == 1) {
					in1 = Integer.parseInt(input1.getText().trim()); in2 = Integer.parseInt(input2.getText().trim()); //Tests for valid inputs
					if(in1 < 1 || in2 < 1) { throw new NumberFormatException(); }
					result = "lcm(" + input1.getText().trim() + "," + input2.getText().trim() + ")";
					errorLabel.setVisible(false);
				}
				else if(options.getSelectedIndex() == 2) {
					in1 = Integer.parseInt(input1.getText().trim()); in2 = Integer.parseInt(input2.getText().trim()); //Tests for valid inputs
					if(in1 < 1 || in2 < 1) { throw new NumberFormatException(); }
					result = "gcd(" + input1.getText().trim() + "," + input2.getText().trim() + ")";
					errorLabel.setVisible(false);
				}
				else if(options.getSelectedIndex() == 3) {
					Double.parseDouble(input1.getText().trim()); //Tests for valid input
					result = "abs(" + input1.getText().trim() + ")";
					errorLabel.setVisible(false);
				}
				else if(options.getSelectedIndex() == 4) {
					Double.parseDouble(input1.getText().trim()); //Tests for valid input
					result = "ceil(" + input1.getText().trim() + ")";
					errorLabel.setVisible(false);
				}
				else if(options.getSelectedIndex() == 5) {
					Double.parseDouble(input1.getText().trim()); //Tests for valid input
					result = "floor(" + input1.getText().trim() + ")";
					errorLabel.setVisible(false);
				}
				else {
					errorLabel.setText("UNKNOWN ERROR"); errorLabel.setVisible(true);
				}
			}
			catch(NumberFormatException ex) {
				errorLabel.setText("ERROR: Invalid Input(s)"); errorLabel.setVisible(true);
			}
			finally {
				cf.setScreen(result);
			}
		}		
	}
	
}