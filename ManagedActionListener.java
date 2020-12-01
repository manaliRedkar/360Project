import java.awt.event.ActionListener;

public abstract class ManagedActionListener implements ActionListener {
	
	protected static ApplicationManager app = null;

	public static void setManager (ApplicationManager app) {
		ManagedActionListener.app = app;
	}
}