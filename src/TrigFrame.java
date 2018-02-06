import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TrigFrame extends JFrame{

	//Serial Version ID
	private static final long serialVersionUID = -5943702384630662797L;
	
	//Private Instance Variables
	private CalculatorFrame cf;
	private final int TRIG_FRAME_WIDTH = 300, TRIG_FRAME_HEIGHT = 150;
	private final int TRIG_FONT_SIZE = 16;
	private final Color TRIG_FRAME_COLOR = Color.BLACK;
	private final Color TRIG_BUTTON_COLOR = Color.WHITE;
	private final Color TRIG_FONT_COLOR = Color.CYAN;
	private JLabel info;
	private JComboBox<String> trigBox;
	private JRadioButton radians, degrees;
	private JButton enter;
	
	public TrigFrame(CalculatorFrame cf) {
		this.cf = cf;
		TrigFramework();
		TrigContents();
	}
	
	//Creates framework for trig frame
	private void TrigFramework() {
		getContentPane().setBackground(TRIG_FRAME_COLOR); //sets color background
		setSize(new Dimension(TRIG_FRAME_WIDTH, TRIG_FRAME_HEIGHT)); //sets frame size
		setLocationRelativeTo(null); //centers frame on screen
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); //Default close operation
		setTitle("Trigonometry"); //Sets title of frame
		setResizable(false); //Disables resize
	}
	
	//Creates contents included in trig frame
	private void TrigContents() {
		getContentPane().setLayout(new FlowLayout()); //Sets frame layout
			
		info = new JLabel("Select a trig option below:");
		info.setFont(new Font("", 0, TRIG_FONT_SIZE)); //sets font size
		info.setForeground(TRIG_FONT_COLOR); //sets font color
		getContentPane().add(info);
			
		String[] options = {"sin", "cos", "tan", "csc", "sec", "cot", "arcsin", "arccos", "arctan", "arccsc", "arcsec", "arccot"};
		trigBox = new JComboBox<String>(options); //Creates a ComboBox with the above options
		trigBox.setPreferredSize(new Dimension(TRIG_FRAME_WIDTH-50, TRIG_FRAME_HEIGHT/10+5)); //sets size of ComboBox
		trigBox.setSelectedIndex(0); //sets the default value
		getContentPane().add(trigBox);
		
		radians = new JRadioButton("Radians"); radians.setPreferredSize(new Dimension(TRIG_FRAME_WIDTH/3, TRIG_FRAME_HEIGHT/5-5));
		radians.setFont(new Font("", 0, TRIG_FONT_SIZE));
		radians.setForeground(TRIG_FONT_COLOR); radians.setBackground(TRIG_FRAME_COLOR);
		radians.setSelected(true); radians.addActionListener(new RadioButtonListener());
		getContentPane().add(radians);
		
		degrees = new JRadioButton("Degrees"); degrees.setPreferredSize(new Dimension(TRIG_FRAME_WIDTH/3, TRIG_FRAME_HEIGHT/5-5));
		degrees.setFont(new Font("", 0, TRIG_FONT_SIZE));
		degrees.setForeground(TRIG_FONT_COLOR); degrees.setBackground(TRIG_FRAME_COLOR);
		degrees.setSelected(false); degrees.addActionListener(new RadioButtonListener());
		getContentPane().add(degrees);
			
		enter = new JButton("Enter"); enter.setBackground(TRIG_BUTTON_COLOR); enter.setPreferredSize(new Dimension(TRIG_FRAME_WIDTH/3, TRIG_FRAME_HEIGHT/5));
		enter.addActionListener(new ButtonListener()); getContentPane().add(enter);
	}
	
	//Radio Button Listener Class
	private class RadioButtonListener implements ActionListener {

		public void actionPerformed(ActionEvent event) {
			if(event.getSource() == radians && radians.isSelected() && degrees.isSelected()) {
				degrees.setSelected(false); cf.setRad(true);
			}
			else if(event.getSource() == degrees && degrees.isSelected() && radians.isSelected()) {
				radians.setSelected(false); cf.setRad(false);
			}
		}	
	}
		
	//Button Listener Class
	private class ButtonListener implements ActionListener {

		//Button Command
		public void actionPerformed(ActionEvent event) {
			if(event.getSource() == enter) {
				cf.setScreen(trigBox.getSelectedItem().toString());
				setVisible(false);
			}
		}		
	}

}