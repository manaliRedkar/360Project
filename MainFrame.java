/**
 * CSE360 Final Project
 * This program loads a roster, adds attendance, save and plots data.
 * 
 * @author
 * @author
 * @author
 * @author
 * @author
 *
 * @since 2020-11-12
 * 
 * Class ID: 70605  
 */


import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import java.io.File;
import javax.swing.JComboBox;

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
import javax.swing.table.DefaultTableModel;
import javax.swing.table.*;


public class MainFrame extends JFrame implements ActionListener, MenuListener 
{
      JComboBox day,month;
      JMenuBar mymenubar; 
      JButton b;
      JFrame fr;
      String [][]myArr;
      String [][]tempArr; 
      JFrame frame;

      int row1,col1;
      int row2,col2;
      MyModel1 NewModel;

      JScrollPane scrollPane;



    // JMenu 
      JMenu file, about; 

      public JTable table; // Load a roster
      JTable table2; //Atendance
  
    // Menu items 
      JMenuItem menuitem1, menuitem2, menuitem3, menuitem4, ab;

    public void calender()
    {
        fr = new JFrame();
        String[] dayStr = {"Select a day", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"};
        String[] monthStr = {"Select a month", "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sept", "Oct", "Nov", "Dec", };

        
        day = new JComboBox(dayStr);
        month = new JComboBox(monthStr);

        b = new JButton("Set");
        b.addActionListener(this);

        day.setSelectedIndex(0);
        day.setEditable(true);
        month.setSelectedIndex(0);
        month.setEditable(true);

        fr.add(day, BorderLayout.WEST);
        fr.add(month, BorderLayout.EAST);
        fr.add(b, BorderLayout.SOUTH);
        fr.setSize(255,100);
        fr.setLocationRelativeTo(null);
        fr.setVisible(true);
     
    }

    //constructor
    public MainFrame()
    {
    	
    	setLayout(new FlowLayout());
    	 //create a Jmenubar
        mymenubar = new JMenuBar(); 
        add(mymenubar);

        //create two new Jmenu objects for "File" and "About"
        file = new JMenu("File"); 
        about = new JMenu("About");
        
        menuitem1 = new JMenuItem("Load a Roster"); 
        menuitem2 = new JMenuItem("Add attendance"); 
        menuitem3 = new JMenuItem("Save");
        menuitem4 = new JMenuItem("Plot Data");

        menuitem1.addActionListener(this);
        menuitem2.addActionListener(this);
        menuitem3.addActionListener(this);
        menuitem4.addActionListener(this);

        file.add(menuitem1); 
        file.add(menuitem2); 
        file.add(menuitem3);
        file.add(menuitem4);
        
        mymenubar.add(file);
        mymenubar.add(about);

        setJMenuBar(mymenubar);

        about.addMenuListener(this);

    }

//*************************************************************************************************************** */
      public void LoadRoster(String fileName) {
        System.out.println(fileName);
       // super(frame, "Load a Roaster", true);
        setLayout(new BorderLayout ());
        //super(frame1, "Load Roaster", true);
        table = new JTable(new MyModel1());
        table.setPreferredScrollableViewportSize(new Dimension(700, 70));
        table.setFillsViewportHeight(true);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        //this.table.setTableHeader(null);
        JPanel ButtonOpen = new JPanel(new FlowLayout(FlowLayout.CENTER));
        add(ButtonOpen, BorderLayout.SOUTH);
        // Create the scroll pane and add the table to it.
        scrollPane = new JScrollPane(table);
        // Add the scroll pane to this panel.
        add(scrollPane, BorderLayout.CENTER);
        // add a nice border
        table.setBorder(new EmptyBorder(5, 5, 5, 5));
        CSVFile1 Rd = new CSVFile1();
        NewModel = new MyModel1();
        this.table.setModel(NewModel);
        
    
        
        File DataFile = new File(fileName);
        
        ArrayList<String[]> Rs2 = Rd.ReadCSVfile(DataFile);
        NewModel.AddCSVData(Rs2);

        row1= NewModel.getRowCount();
        col1=NewModel.getColumnCount();
        System.out.println("Rows: " + row1);
        System.out.println("Cols: " + col1);

       // m = new JFrame();
        //m.add(scrollPane);
        // m.setSize(1000,1000);
        // m.setVisible(true);
    }

    public class CSVFile1 {
        private final ArrayList<String[]> Rs = new ArrayList<String[]>();
        private String[] OneRow;

        public ArrayList<String[]> ReadCSVfile(File DataFile) {
            try {
                BufferedReader brd = new BufferedReader(new FileReader(DataFile));
                while (brd.ready()) {
                    String st = brd.readLine();
                    OneRow = st.split(",|\\s|;");
                    Rs.add(OneRow);
                    //System.out.println(Arrays.toString(OneRow));
                } // end of while
            } // end of try
            catch (Exception e) {
                String errmsg = e.getMessage();
                System.out.println("File not found:" + errmsg);
            } // end of Catch
            return Rs;
        }// end of ReadFile method
    }// end of CSVFile class

  class MyModel1 extends AbstractTableModel {
        private final String[] columnNames = { "ID", "First Name", "Last Name", "Program", "Level", "ASURITE"};
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

//****************************************************************************************************************************** */

      public void AttendanceRoster(String fileName2) {
        System.out.println(fileName2);
        //tempArr = new String[row1][col1];

        /*
       // super(frame, "Load a Roaster", true);
        setLayout(new BorderLayout ());
        //super(frame1, "Load Roaster", true);
        table2 = new JTable(new MyModel2());
        table2.setPreferredScrollableViewportSize(new Dimension(700, 70));
        table2.setFillsViewportHeight(true);
        table2.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        //this.table.setTableHeader(null);
        JPanel ButtonOpen = new JPanel(new FlowLayout(FlowLayout.CENTER));
        add(ButtonOpen, BorderLayout.SOUTH);
        // Create the scroll pane and add the table to it.
        JScrollPane scrollPane = new JScrollPane(table2);
        // Add the scroll pane to this panel.
        add(scrollPane, BorderLayout.CENTER);
        // add a nice border
        table2.setBorder(new EmptyBorder(5, 5, 5, 5));
        CSVFile2 Rd = new CSVFile2();
        MyModel2 NewModel2 = new MyModel2();
        this.table2.setModel(NewModel2);
        

        File DataFile = new File(fileName2);
        
        ArrayList<String[]> Rs2 = Rd.ReadCSVfile(DataFile);
        NewModel2.AddCSVData(Rs2);

        row2= NewModel2.getRowCount();
        col2=NewModel2.getColumnCount();
        System.out.println("Rows: " + row2);
        System.out.println("Cols: " + col2);
    }

    public class CSVFile2 {
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

  class MyModel2 extends AbstractTableModel {
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

     */  



    }
//******************************************************************************************************************** */

//Method for init..

public void arrayAssignment(String fileName2){
  System.out.println(fileName2);
  //CSVFile2 Rd = new CSVFile2();
  //MyModel2 NewModel2 = new MyModel2();

  File DataFile = new File(fileName2);
  System.out.println("Rows: " + row1);
  System.out.println("Cols: " + col1);

  //DefaultTableModel dtm = (DefaultTableModel) table.getModel();
  tempArr = new String[row1][col1];
  myArr = new String[row1][col1+1];

  
  //int nRow = dtm.getRowCount(), nCol = dtm.getColumnCount();
    for (int i = 0 ; i < row1 ; i++)
    {
      //System.out.println("yyyy");
        for (int j = 0 ; j < col1 ; j++)
        {
          tempArr[i][j] = table.getValueAt(i,j).toString();
        }    
    }

/*
    for (int i = 0 ; i < row1 ; i++)
    {
      //System.out.println("yyyy");
        for (int j = 0 ; j < col1 ; j++)
        {
          System.out.print(tempArr[i][j]);
        }  

        System.out.println("");        
    }
*/

for (int i = 0 ; i < row1 ; i++)
  {
      //System.out.println("yyyy");
        for (int j = 0 ; j < col1+1 ; j++)
        {
          if(col1 != j ) 
            myArr[i][j] = tempArr[i][j]; 
          else
             myArr[i][col1]= "0";
        }  
        //tempArr[i][j] = dtm.getValueAt(i,j).toString();
  }

  
  for(int i=0;i<row1;i++)
  {
    for(int j=0;j<col1+1;j++)
    {
      //if(col1 != j ) 
      System.out.print(myArr[i][j]);
      //else
        //System.out.println("SURE");
    }
    System.out.println("");

  }


String colList[] = { "ID", "First Name", "Last Name", "Program", "Level", "ASURITE", "wtv"};
JTable newTab = new JTable(myArr, colList);
Font font = new Font("Verdana", Font.PLAIN, 12);

//scrollPane = new JScrollPane(newTab);
/*m.add(scrollPane);
m.setSize(1000,1000);
m.setVisible(true);*/
table = newTab;

  
  frame = new JFrame();
  frame.setSize(600, 400);
  frame.add(new JScrollPane(table));
  frame.setVisible(true);
  

  

  /*
    for (int i = 0 ; i < myArr.length ; i++)
    {
      //System.out.println("yyyy");
        for (int j = 0 ; j < myArr[0].length ; j++)
        {
          System.out.print(myArr[i][j]);
        } 
            //tempArr[i][j] = dtm.getValueAt(i,j).toString();
    }
  */

      
}

/*
 public class CSVFile2 {
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


    class MyModel2 extends AbstractTableModel {
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

*/


//******************************************************************************************************************** */


//USER CHOOSES
    public void actionPerformed(ActionEvent e){
      if(e.getSource() == menuitem1)
      {
        System.out.println("roasttt");
        JFileChooser fileChooser = new JFileChooser();
        int returnValue = fileChooser.showOpenDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
          File selectedFile = fileChooser.getSelectedFile();
          String fileName = selectedFile.getName();
          System.out.println(fileName);
          LoadRoster(fileName);
          //LoadRoster l = new LoadRoster(fileName);
        //arrayAssignment();
          //this.setContentPane(l);
          revalidate();
          
        }
      }

      else if (e.getSource() == menuitem2)
      {
          calender();
	    }

      else if(e.getSource() == b)
      {
        fr.dispose();
        System.out.println(day.getSelectedItem());
        System.out.println(month.getSelectedItem());
        System.out.println("ADD attendants");
        JFileChooser fileChooser = new JFileChooser();
        int returnValue = fileChooser.showOpenDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
          File selectedFile = fileChooser.getSelectedFile();
          String fileName2 = selectedFile.getName();
          System.out.println(fileName2);
          //AttendanceRoster(fileName2);
          //this.setContentPane(a);
          arrayAssignment(fileName2);
          revalidate();
        }
        
      }

      

      else if (e.getSource() == menuitem3)
      {
		    System.out.println("SAVE ");
	    }
	    else //(e.getsource() == menuitem4)
	    {
		    System.out.println("Plott");
	    }

    }

  /*
    class event implements ActionListener{
      public void actionPerformed(ActionEvent e){
        LoadRoaster lr = new LoadRoaster(MainFrame.this);
        lr.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        lr.setSize(300,100);
        lr.setLocation(300,300);
        lr.setVisible(true);
      }
    }
  */


  
    @Override
    public void menuSelected(MenuEvent e)
    {
    	 
    	 AboutWindow gui = new AboutWindow(MainFrame.this);
    	 gui.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
    	 gui.setSize(300,300);
    	 gui.setLocation(300,300);
    	 gui.setVisible(true);
    
    }
    @Override
    public void menuDeselected(MenuEvent e)
    {
    	 
    
    }
    
    @Override
    public void menuCanceled(MenuEvent e)
    {
    	 
    
    }
    

    /**
    *
    * main method that has the JFrame gui
    * @param args unsused
    *
    **/
    

    public static void main(String[] args ){

        //create a MainFrame object
        MainFrame m = new MainFrame();
        
        m.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        //window title
        m.setTitle("CSE360 Final Project");

        //set the frame size to 500x500
        m.setSize(500,500);
        
        //make the frame visible
        m.setVisible(true); 

    
    
   }

}