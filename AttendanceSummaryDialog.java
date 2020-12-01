import java.lang.Integer;
import java.util.HashMap;

import java.awt.FlowLayout;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class AttendanceSummaryDialog extends JDialog {
	public AttendanceSummaryDialog(int usersLoaded, HashMap<String, Integer> extras) {
		super (new JFrame(), "Attendance Loaded", true);  
		
		setLayout(new FlowLayout());  
		add(new JLabel ("Attendance loaded for " + (usersLoaded) + " users"));  
		add(new JLabel(extras.size() + " additional attendees were found"));

		for (HashMap.Entry<String, Integer> extra : extras.entrySet())
			add(new JLabel(extra.getKey() + " connected for " + extra.getValue() + " minutes"));

		setSize(300,300);    
		setVisible(true);  
	}
}