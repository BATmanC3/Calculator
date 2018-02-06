import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class AlgebraPanel extends JPanel {

	//Serial Version ID
	private static final long serialVersionUID = 5339903911470486590L;

	//Private Instance Variables
	private CalculatorFrame cf;
	private JLabel label, subLabel, errorLabel;
	private final int BUTTON_WIDTH = 70, BUTTON_HEIGHT = 30;
	private final int FONT_SIZE = 16;
	private final Color PANEL_COLOR = Color.BLACK;
	private final Color BUTTON_COLOR = Color.WHITE;
	private final Color FONT_COLOR = Color.CYAN;
	private final Color ERROR_FONT_COLOR = Color.RED;
	private JComboBox<String> options;
	private JTextField input1, input2;
	private JButton enter;

	//Constructor
	public AlgebraPanel(CalculatorFrame cf, MenuFrame mf) {
		this.cf = cf;
		
		this.setBackground(PANEL_COLOR); //sets background
		
		label = new JLabel("Please select an option below:");
		label.setFont(new Font("", 0, FONT_SIZE)); //sets font size
		label.setForeground(FONT_COLOR); //sets font color
		this.add(label);
		
		String[] list = {"None", "Pi", "Power", "Square Root", "Log", "Natural Log (ln)", "Euler (e^x)"};
		options = new JComboBox<String>(list);
		options.setPreferredSize(new Dimension(((int) (mf.getWidth()/1.2)), mf.getHeight()/10)); //sets size of scroll bar
		options.setSelectedIndex(0); //sets scroll bar to None
		options.addActionListener(new OptionsListener());
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
				case "Pi":
					subLabel.setVisible(false); errorLabel.setVisible(false);
					input1.setVisible(false);  input2.setVisible(false);
					enter.setVisible(false); enter.setVisible(true);
					break;
				case "Power":
					subLabel.setVisible(true); errorLabel.setVisible(false);
					input1.setText("[Enter a Base Here (Integer or Float)]"); input1.setVisible(true);
					input2.setText("[Enter an Exponent Here (Integer or Float)]"); input2.setVisible(true);
					enter.setVisible(false); enter.setVisible(true);
					break;
				case "Square Root":
					subLabel.setVisible(true); errorLabel.setVisible(false);
					input1.setText("[Enter an Integer or Float Here]"); input1.setVisible(true);
					input2.setVisible(false);
					enter.setVisible(false); enter.setVisible(true);
					break;
				case "Log":
					subLabel.setVisible(true); errorLabel.setVisible(false);
					input1.setText("[Enter a Base Here (Integer or Float)]"); input1.setVisible(true);
					input2.setText("[Enter an Integer or Float Here]"); input2.setVisible(true);
					enter.setVisible(false); enter.setVisible(true);
					break;
				case "Natural Log (ln)":
					subLabel.setVisible(true); errorLabel.setVisible(false);
					input1.setText("[Enter an Integer or Float Here]"); input1.setVisible(true);
					input2.setVisible(false);
					enter.setVisible(false); enter.setVisible(true);
					break;
				case "Euler (e^x)":
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
			String result = ""; double dub1, dub2; //Storage for inputs and result
			try {
				if(options.getSelectedIndex() == 1) {
					result = "PI";
				}
				else if(options.getSelectedIndex() == 2) {
					Double.parseDouble(input1.getText().trim()); Double.parseDouble(input2.getText().trim());//Tests for valid inputs
					result = "pow(" + input1.getText().trim() + "," + input2.getText().trim() + ")";
					errorLabel.setVisible(false);
				}
				else if(options.getSelectedIndex() == 3) {
					dub1 = Double.parseDouble(input1.getText().trim()); //Tests for valid input
					if(dub1 < 0) { throw new NumberFormatException(); }
					result = "sqrt(" + input1.getText().trim() + ")";
					errorLabel.setVisible(false);
				}
				else if(options.getSelectedIndex() == 4) {
					dub1 = Double.parseDouble(input1.getText().trim()); dub2 = Double.parseDouble(input2.getText().trim());//Tests for valid inputs
					if(dub1 < 0 || dub2 < 0) { throw new NumberFormatException(); }
					result = "log(" + input1.getText().trim() + "," + input2.getText().trim() + ")";
					errorLabel.setVisible(false);
				}
				else if(options.getSelectedIndex() == 5) {
					dub1 = Double.parseDouble(input1.getText().trim()); //Tests for valid input
					if(dub1 <= 0) { throw new NumberFormatException(); }
					result = "ln(" + input1.getText().trim() + ")";
					errorLabel.setVisible(false);
				}
				else if(options.getSelectedIndex() == 6) {
					Double.parseDouble(input1.getText().trim()); //Tests for valid input
					result = "euler(" + input1.getText().trim() + ")";
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