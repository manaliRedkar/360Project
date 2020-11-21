import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class AboutWindow  extends JDialog{
	
	JLabel label, label2, label3, label4, label5;
	
	public AboutWindow (JFrame frame){
		
		super(frame, "About", true);
		setLayout(new FlowLayout());
		
		label = new JLabel("Manali Redkar");
		label2 = new JLabel("Christine Pascua");
		label3 = new JLabel("Tazreen Khan");
		label4 = new JLabel("Anjali Singh");
		label5 = new JLabel("Pallavi Koyye");
		add(label);
		add(label2);
		add(label3);
		add(label4);
		add(label5);
		
	}

}
