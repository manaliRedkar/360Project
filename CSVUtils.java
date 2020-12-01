import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

import java.lang.Integer;

import java.util.HashMap;
import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;

public class CSVUtils {
	private static final String CSV_DELIM = ",|\\s|;";

	public static String[][] loadRoster(File file) {
        ArrayList<String[]> rows = new ArrayList<String[]>();

		try {
			BufferedReader brd = new BufferedReader(new FileReader(file));
			while (brd.ready())
				rows.add(brd.readLine().split(CSV_DELIM));
		} catch (Exception e) {
			System.out.println("Error loading roster:" + e.getMessage());
		}

		String[][] arrayForm = new String[rows.size()][];
		for (int i = 0; i < rows.size(); ++i)
			arrayForm[i] = rows.get(i);

		return arrayForm;
	}

	public static HashMap<String, Integer> loadAttendance(File file) {
		HashMap<String,Integer> map = new HashMap<String,Integer> ();
		String[] row;

		try {
			BufferedReader brd = new BufferedReader(new FileReader(file));
			while (brd.ready()) {
				row = brd.readLine().split(CSV_DELIM);
				
				Integer currentSum = map.getOrDefault(row[0],0);
				map.put(row[0], currentSum + Integer.parseInt(row[1]));
			}
		}
		catch (Exception e) {
			System.out.println("Error loading roster:" + e.getMessage());
		}
		return map;
	}

	public static void save(DefaultTableModel table, File file) {
		int numRows = table.getRowCount(), numCols = table.getColumnCount();

		try {
			BufferedWriter bwr = new BufferedWriter(new FileWriter(file));

			bwr.write(table.getColumnName(0));
			for (int col = 1; col < numCols; ++col) {
				bwr.write(',');
				bwr.write(table.getColumnName(col));
			}

			bwr.write('\n');

			for (int row = 0; row < numRows; ++row) {
				bwr.write(table.getValueAt(row,0).toString());
				for (int col = 1; col < numCols; ++col) {
					bwr.write(',');
					bwr.write(table.getValueAt(row, col).toString());
				}
				bwr.write('\n');
			}
			bwr.close();
		} catch (IOException e) {
			System.out.println("Unable to save CSV: " + e.getMessage());
		}
	}
}