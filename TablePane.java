import java.awt.Dimension;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class TablePane extends JScrollPane {
	public TablePane (DefaultTableModel model) {
		super(new InternalTablePane(model));
	}

	static class InternalTablePane extends JTable {
		InternalTablePane(DefaultTableModel model) {
			super(model);

			setPreferredScrollableViewportSize(new Dimension(700, 70));
			setFillsViewportHeight(true);
			setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			setBorder(new EmptyBorder(5, 5, 5, 5));
		}
	}
}
