import java.awt.Color;
import javax.swing.JFrame;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYDataset;

public class PlotFrame extends JFrame {

	public PlotFrame(XYDataset dataset) {
		super("Attendance Plot");

		// Create chart  
		JFreeChart chart = ChartFactory.createScatterPlot(  
			"Attendance Plot", "Percentage", "Count", dataset, 
			PlotOrientation.VERTICAL, true, true, false);  
      
		//Changes background color  
		XYPlot plot = (XYPlot) chart.getPlot();  
		plot.setBackgroundPaint(new Color(255,228,196));  
     
		// Create Panel  
		ChartPanel panel = new ChartPanel(chart);  
		setContentPane(panel);  

		setTitle("CSE360 Final Project");
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setSize(500,500);
        setVisible(true); 
    }
}