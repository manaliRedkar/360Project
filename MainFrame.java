import java.util.Scanner;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;


public class MainFrame extends JFrame //implements ActionListener 
{

    static JMenuBar mymenubar; 
  
    // JMenu 
    static JMenu componentX,componentY; 
  
    // Menu items 
    static JMenuItem menuitem1, menuitem2, menuitem3,menuitem4;


    MainFrame(){
        
    }

    public static void main(String[] args ){

        MainFrame m = new MainFrame();
        m.setTitle("CSE360 Final Project");
        m.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  

        mymenubar = new JMenuBar(); 
        componentX = new JMenu("File"); 
        componentY = new JMenu("About");

        menuitem1 = new JMenuItem("Load a Roaster"); 
        menuitem2 = new JMenuItem("Add attendance"); 
        menuitem3 = new JMenuItem("Save");
        menuitem4 = new JMenuItem("Plot Data");

       

        componentX.add(menuitem1); 
        componentX.add(menuitem2); 
        componentX.add(menuitem3);
        componentX.add(menuitem4);


        mymenubar.add(componentX); 
        mymenubar.add(componentY);

        m.setJMenuBar(mymenubar); 

        m.setSize(500,500);
        m.setVisible(true); 

        /*
        JTabbedPane t = new JTabbedPane();
        panel1 = new JPanel();
        panel2 = new JPanel();

        t.addTab("File", panel1);
        t.addTab("About", panel2);
        m.add(t);
        */
        
    }   

    
    /*
    public void actionPerformed(ActionEvent e) 
    {
        String s = e.getActionCommand(); 
        if (s.equals("About")) { 
            // create a dialog Box 
            JDialog d = new JDialog(y, "dialog Box"); 
  
            // create a label 
            JLabel l = new JLabel("this is a dialog box"); 
  
            d.add(l); 
  
            // setsize of dialog 
            d.setSize(100, 100); 
  
            // set visibility of dialog 
            d.setVisible(true); 
        } 
        
    } 
    */
}
