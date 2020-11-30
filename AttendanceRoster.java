import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.AbstractTableModel;


import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.GridPane;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.control.DatePicker;
import javafx.scene.control.DateCell;
import javafx.scene.control.Label;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.util.Callback;
import javafx.util.StringConverter;
import javafx.util.converter.LocalDateStringConverter;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.AbstractTableModel;

import javax.swing.SwingUtilities;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Point;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.time.LocalDate;

public class AttendanceRoster extends JPanel {
    private final JTable table;


    public AttendanceRoster(String fileName) {
        System.out.println(fileName);
       // super(frame, "Load a Roaster", true);
        setLayout(new BorderLayout ());
        //super(frame1, "Load Roaster", true);
        this.table2 = new JTable(new MyModel());
        this.table2.setPreferredScrollableViewportSize(new Dimension(700, 70));
        this.table2.setFillsViewportHeight(true);
        this.table2.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        //this.table.setTableHeader(null);
        JPanel ButtonOpen = new JPanel(new FlowLayout(FlowLayout.CENTER));
        add(ButtonOpen, BorderLayout.SOUTH);
        // Create the scroll pane and add the table to it.
        JScrollPane scrollPane = new JScrollPane(table);
        // Add the scroll pane to this panel.
        add(scrollPane, BorderLayout.CENTER);
        // add a nice border
        setBorder(new EmptyBorder(5, 5, 5, 5));
        CSVFile Rd = new CSVFile();
        MyModel NewModel = new MyModel();
        this.table2.setModel(NewModel);
        
    
        
        File DataFile = new File(fileName);
        
        ArrayList<String[]> Rs2 = Rd.ReadCSVfile(DataFile);
        NewModel.AddCSVData(Rs2);
        System.out.println("Rows: " + NewModel.getRowCount());
        System.out.println("Cols: " + NewModel.getColumnCount());
    }

    // Method for reading CSV file
    public class CSVFile {
        private final ArrayList<String[]> Rs = new ArrayList<String[]>();
        private String[] OneRow;

        public ArrayList<String[]> ReadCSVfile(File DataFile) {
            try {
                BufferedReader brd = new BufferedReader(new FileReader(DataFile));
                while (brd.ready()) {
                    String st = brd.readLine();
                    OneRow = st.split(",|\\s|;");
                    Rs.add(OneRow);
                    System.out.println(Arrays.toString(OneRow));
                } // end of while
            } // end of try
            catch (Exception e) {
                String errmsg = e.getMessage();
                System.out.println("File not found:" + errmsg);
            } // end of Catch
            return Rs;
        }// end of ReadFile method
    }// end of CSVFile class

    
    private static void createAndShowGUI(String fileName) {
        // Create and set up the window.
        JFrame frame = new JFrame("AttendanceRoster");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Create and set up the content pane.

        AttendanceRoster newContentPane = new AttendanceRoster(fileName);
        frame.setContentPane(newContentPane);

        // Display the window
        frame.pack();
        frame.setVisible(true);
    }

    class MyModel extends AbstractTableModel {
        private final String[] columnNames = { "ASURite", "Time"};
        private ArrayList<String[]> Data = new ArrayList<String[]>();

        public void AddCSVData(ArrayList<String[]> DataIn) {
            this.Data = DataIn;
            this.fireTableDataChanged();
        }

        @Override
        public int getColumnCount() {
            return columnNames.length;// length;
        }

        @Override
        public int getRowCount() {
            return Data.size();
        }

        @Override
        public String getColumnName(int col) {
            return columnNames[col];
        }

        @Override
        public Object getValueAt(int row, int col) {
            return Data.get(row)[col];
        }
    }



    
    /*
    public static void main(String[] args) {
        // Schedule a job for the event-dispatching thread:
        // creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                createAndShowGUI();
            }
        });
    }*/
    
}