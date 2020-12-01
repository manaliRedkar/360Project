import java.awt.BorderLayout;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;

public class CalendarFrame extends JDialog {
	private static final String [] DAY_STR = {"Select a day", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"};
	private static final String [] MONTH_STR = {"Select a month", "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sept", "Oct", "Nov", "Dec" };


	private JComboBox dayBox = null;
	private JComboBox monthBox = null;
	private JButton setButton = null;

	public CalendarFrame() {
		super();

        dayBox = new JComboBox(DAY_STR);
        monthBox = new JComboBox(MONTH_STR);

        dayBox.setSelectedIndex(0);
        monthBox.setSelectedIndex(0);

		setButton = new JButton("Set");
		setButton.addActionListener(new SetButtonListener());

		setLayout(new BorderLayout());
        add(dayBox, BorderLayout.WEST);
        add(monthBox, BorderLayout.EAST);
        add(setButton, BorderLayout.SOUTH);
        
		setSize(255,100);
		setLocationRelativeTo(null);
		setVisible(true);
    }


	class SetButtonListener extends ManagedActionListener {
	    public void actionPerformed(ActionEvent e) {
			if(e.getSource() == setButton) {
				if (dayBox.getSelectedIndex() != 0 && monthBox.getSelectedIndex() != 0) {
					String date = dayBox.getSelectedItem().toString() + " " + monthBox.getSelectedItem().toString();
					dispose();

					JFileChooser fileChooser = new JFileChooser();
					if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
						app.loadAttendance(date, fileChooser.getSelectedFile());
						revalidate();
					}
				}
			}        
      }
	}
}