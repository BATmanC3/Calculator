import java.awt.*;
import javax.swing.*;

public class MenuFrame extends JFrame {

	//Serial Version ID
	private static final long serialVersionUID = 1328756640538025065L;
	
	//Private Instance Variables
	private CalculatorFrame cf;
	private final int MENU_FRAME_WIDTH = 300, MENU_FRAME_HEIGHT = 250;
	private final Color MENU_FRAME_COLOR = Color.BLACK;
	private NumberPanel NumPanel;
	private AlgebraPanel AlgePanel;
	private ProbabilityPanel ProbPanel;
	private JTabbedPane options;

	public MenuFrame(CalculatorFrame cf) {
		this.cf = cf;
		MenuFramework();
		MenuContents();
	}
	
	//Creates framework for menu frame
	private void MenuFramework() {
		getContentPane().setBackground(MENU_FRAME_COLOR); //sets color background
		setSize(new Dimension(MENU_FRAME_WIDTH, MENU_FRAME_HEIGHT)); //sets frame size
		setLocationRelativeTo(null); //centers frame on screen
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); //Default close operation
		setTitle("Menu"); //Sets title of frame
		setResizable(false); //Disables resize
	}
	
	//Creates contents included in menu frame
	private void MenuContents() {
		NumPanel = new NumberPanel(cf, this); //Initializes NumberPanel
		AlgePanel = new AlgebraPanel(cf, this); //Initializes AlgebraPanel
		ProbPanel = new ProbabilityPanel(cf, this); //Initializes ProbabilityPanel
		
		options = new JTabbedPane(); //Creates Panel to separate the three panels above
		options.addTab("Number", NumPanel);
		options.addTab("Algebra", AlgePanel);
		options.addTab("Probability", ProbPanel);
		
		getContentPane().add(options);
	}
	
}