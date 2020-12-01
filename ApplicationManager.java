import java.io.File;

import java.lang.Integer;

import java.util.HashMap;

import javax.swing.JOptionPane;

import org.jfree.data.xy.XYDataset;

public class ApplicationManager {
	private MainFrame mainframe;
	private TableModel table = null;

	public ApplicationManager() {
		ManagedActionListener.setManager(this);

        mainframe = new MainFrame();
	}

	public void loadRoster(File file) {
		table = new TableModel(CSVUtils.loadRoster(file));
		mainframe.setContentPane(new TablePane(table));
		mainframe.revalidate();
	}

	public void showCalendar() {
		new CalendarFrame();
	}

	public void save(File file) {
		CSVUtils.save(table, file);
	}

	public void showPlot() {
		XYDataset dataset = table.getPlotData();
		if (dataset != null)
			new PlotFrame(dataset);
		else
			alert("Must add at least one attendance column first");
	}

	public void loadAttendance(String date, File file) {
		HashMap<String, Integer> attendance = CSVUtils.loadAttendance(file);
		int total = attendance.size();
		table.addAttendanceColumn(date, attendance);
		mainframe.revalidate();
		new AttendanceSummaryDialog(total, attendance);
	}

	public boolean ready() {
		return table != null;
	}

	public void alert(String message) {
		JOptionPane.showMessageDialog(mainframe, message);
	}

    public static void main(String[] args ) {
		new ApplicationManager();
   }
}