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

import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JMenuBar;


public class MainFrame extends JFrame {
    public MainFrame() {
		super();

    	setLayout(new FlowLayout());
        JMenuBar menu = new JMenuBar(); 
        menu.add(new FileMenu());
        menu.add(new AboutMenu());

        add(menu);
		setJMenuBar(menu);

		setTitle("CSE360 Final Project");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500,500);
        setVisible(true);
    }
}