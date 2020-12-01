import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class FileMenu extends JMenu {

	JMenuItem roster, attendance, save, plot;

	public FileMenu () {
		super("File");

		add(roster 		= new FileMenuItem("Load a Roster"));
        add(attendance 	= new FileMenuItem("Add attendance"));
		add(save 		= new FileMenuItem("Save"));
        add(plot 		= new FileMenuItem("Plot Data"));

	}

	class FileMenuItem extends JMenuItem {
		FileMenuItem(String name) {
			super(name);
			addActionListener(new FileMenuListener());
		}
	}

	class FileMenuListener extends ManagedActionListener {
	    public void actionPerformed(ActionEvent e) {
			if(e.getSource() == roster) {
				JFileChooser fileChooser = new JFileChooser();
    	    	if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
					app.loadRoster(fileChooser.getSelectedFile());
        	}
			else if (!app.ready())
				app.alert("Must select roster file first");
			else if (e.getSource() == attendance)
				app.showCalendar();
			else if (e.getSource() == save) {
				JFileChooser fileChooser = new JFileChooser();
				if (fileChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION)
					app.save(fileChooser.getSelectedFile());
			}
			else if (e.getSource() == plot)
				app.showPlot();
		}
	}
}