import java.lang.Integer;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;

import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;  

public class TableModel extends DefaultTableModel {

	private static final String ASURITE = "ASURITE";
	private static final ArrayList<String> DEFAULT_COLUMNS;
	private static final float FULL_ATTENDANCE = 75;

	static {
		DEFAULT_COLUMNS = new ArrayList<String>();
		DEFAULT_COLUMNS.addAll(Arrays.asList("ID", "First Name", "Last Name", "Program", "Level", ASURITE));
	}

	TableModel(String[][] rows) {
		super(rows, DEFAULT_COLUMNS.toArray());
	}

	public XYDataset getPlotData() {
		XYSeriesCollection dataset = new XYSeriesCollection();
		for (int col = 0; col < getColumnCount(); ++col) {

			if (DEFAULT_COLUMNS.contains(getColumnName(col)))
				continue;
	
			XYSeries series = new XYSeries(getColumnName(col));
			int [] count = new int [11];
			for (int row = 0; row < getRowCount(); ++row) {
				int minutes = Integer.parseInt(getValueAt(row, col).toString());
				int bucket = Math.round(minutes / FULL_ATTENDANCE * 10);
				bucket = Math.min(bucket, 10);
				count[bucket]++;
			}

			for (int bucket = 0; bucket <= 10; ++bucket)
				series.add(bucket * 10, count[bucket]);

			dataset.addSeries(series);
		}
		return dataset.getSeriesCount() != 0 ? dataset : null;
	}

	private int getAsuriteCol() {
		for (int col = 0; col < getColumnCount(); ++col)
			if (getColumnName(col).equals(ASURITE))
				return col;
		return -1;
	}

	public void addAttendanceColumn(String date, HashMap<String, Integer> attendance) {
		final int ASURITE_COL = getAsuriteCol();
		if (ASURITE_COL == -1)
			return;

		final int NEW_COLUMN = getColumnCount();
		addColumn(date);
		
		for (int row = 0; row < getRowCount(); ++row) {
			Integer time = attendance.remove(getValueAt(row, ASURITE_COL));
			setValueAt(time != null ? time.toString() : "0", row, NEW_COLUMN);
		}
	}
}