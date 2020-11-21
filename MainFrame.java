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


public class MainFrame extends JFrame implements MenuListener
{

      JMenuBar mymenubar; 
  
    // JMenu 
      JMenu file, about; 
  
    // Menu items 
      JMenuItem menuitem1, menuitem2, menuitem3, menuitem4, ab;

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
        
        menuitem1 = new JMenuItem("Load a Roaster"); 
        menuitem2 = new JMenuItem("Add attendance"); 
        menuitem3 = new JMenuItem("Save");
        menuitem4 = new JMenuItem("Plot Data");
        
        file.add(menuitem1); 
        file.add(menuitem2); 
        file.add(menuitem3);
        file.add(menuitem4);
        
        mymenubar.add(file);
        mymenubar.add(about);

        setJMenuBar(mymenubar);

        about.addMenuListener(this);

    }
    
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