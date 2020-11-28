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



public class MainFrame extends JFrame implements ActionListener, MenuListener 
{
      JComboBox day,month;
      JMenuBar mymenubar; 
      JButton b;
      JFrame fr;
    // JMenu 
      JMenu file, about; 
  
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
          //LoadRoaster(fileName);
          LoadRoster l = new LoadRoster(fileName);
          this.setContentPane(l);
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
          String fileName = selectedFile.getName();
          System.out.println(fileName);
          AttendanceRoster a = new AttendanceRoster(fileName);
          this.setContentPane(a);
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