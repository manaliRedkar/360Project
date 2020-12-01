import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

public class AboutMenu extends JMenu {
	public AboutMenu () {
		super("About");
		addMenuListener(new AboutListener());
	}

	class AboutListener implements MenuListener {
		    @Override
			public void menuSelected(MenuEvent e) {
				new AboutWindow();
			}
			
			@Override
			public void menuDeselected(MenuEvent e) {}
			
			@Override
			public void menuCanceled(MenuEvent e) {}
	}

	class AboutWindow extends JFrame {
		public AboutWindow (){
			super("About");
			setLayout(new FlowLayout());
			
			add(new JLabel("Manali Redkar"));
			add(new JLabel("Christine Pascua"));
			add(new JLabel("Tazreen Khan"));
			add(new JLabel("Anjali Singh"));
			add(new JLabel("Pallavi Koyye"));
			
			setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
			setSize(300,300);
			setLocation(300,300);
			setVisible(true);
		}
	}
}