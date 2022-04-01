package utils;
import gui.MainUI;
import main.Result;
import main.PerformTrade;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.LogAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.renderer.xy.XYSplineRenderer;
import org.jfree.data.Range;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.time.Day;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;


public class DataVisualizationCreator {
	
	private static Object[] columnNames = {"Trader","Strategy","CryptoCoin","Action","Quantity","Price","Date"};
	private DefaultTableModel model = new DefaultTableModel(columnNames, 0);	//added this tablemodel to JTable so we can add rows later on 
    // Create the JTable
    private JTable table = new JTable(model);

	public void createCharts() {
		createTableOutput();
		createBar();
	}
	
	public static void createTableOutput() {
		Object[][] resultData = PerformTrade.masterResultsList.getDoubleArray();
		// Dummy dates for demo purposes. These should come from selection menu
		
		// Dummy data for demo purposes. These should come from actual fetcher
		// Object[][] data = {
		// 		{"Trader-1", "Strategy-A", "ETH", "Buy", "500", "150.3","13-January-2022"},
		// 		{"Trader-2", "Strategy-B", "BTC", "Sell", "200", "50.2","13-January-2022"},
		// 		{"Trader-3", "Strategy-C", "USDT", "Buy", "1000", "2.59","15-January-2022"},
		// 		{"Trader-1", "Strategy-A", "USDC", "Buy", "500", "150.3","16-January-2022"},
		// 		{"Trader-2", "Strategy-B", "ADA", "Sell", "200", "50.2","16-January-2022"},
		// 		{"Trader-3", "Strategy-C", "SOL", "Buy", "1000", "2.59","17-January-2022"},
		// 		{"Trader-1", "Strategy-A", "ONE", "Buy", "500", "150.3","17-January-2022"},
		// 		{"Trader-2", "Strategy-B", "MANA", "Sell", "200", "50.2","17-January-2022"},
		// 		{"Trader-3", "Strategy-C", "AVAX", "Buy", "1000", "2.59","19-January-2022"},
		// 		{"Trader-1", "Strategy-A", "LUNA", "Buy", "500", "150.3","19-January-2022"},
		// 		{"Trader-2", "Strategy-B", "FTM", "Sell", "200", "50.2","19-January-2022"},
		// 		{"Trader-3", "Strategy-C", "HNT", "Buy", "1000", "2.59","20-January-2022"}
		// };
		
		JTable table = new JTable(resultData, columnNames); //takes masterlist and makes table

		//table.setPreferredSize(new Dimension(600, 300));
		
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBorder (BorderFactory.createTitledBorder (BorderFactory.createEtchedBorder (),
                "Trader Actions",
                TitledBorder.CENTER,
                TitledBorder.TOP));
		
	
		
		scrollPane.setPreferredSize(new Dimension(800, 300));
		table.setFillsViewportHeight(true);;
		
		MainUI.getInstance().updateStats(scrollPane);
	}
	
	
	public static void createBar() {
		
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		Object[][] resultData = PerformTrade.masterResultsList.getDoubleArray();
//		Those are hard-coded values!!!! 
//		You will have to come up with a proper datastructure to populate the BarChart with live data!
		//dataset.setValue(6, "Trader-1", "Strategy-A");
		//dataset.setValue(5, "Trader-2", "Strategy-B");
		//dataset.setValue(0, "Trader-3", "Strategy-E");
		//dataset.setValue(1, "Trader-4", "Strategy-C");
		//dataset.setValue(10, "Trader-5", "Strategy-D");
		dataset.setValue(2, resultData[0][0].toString(), resultData[0][1].toString());
		// for (int i = 0; i<resultData.length; i++){	//for each result

		// }

		CategoryPlot plot = new CategoryPlot();
		BarRenderer barrenderer1 = new BarRenderer();

		plot.setDataset(0, dataset);
		plot.setRenderer(0, barrenderer1);
		CategoryAxis domainAxis = new CategoryAxis("Strategy");
		plot.setDomainAxis(domainAxis);
		LogAxis rangeAxis = new LogAxis("Actions(Buys or Sells)");
		rangeAxis.setRange(new Range(1.0, 20.0));
		plot.setRangeAxis(rangeAxis);

		//plot.mapDatasetToRangeAxis(0, 0);// 1st dataset to 1st y-axis
		//plot.mapDatasetToRangeAxis(1, 1); // 2nd dataset to 2nd y-axis

		JFreeChart barChart = new JFreeChart("Actions Performed By Traders So Far", new Font("Serif", java.awt.Font.BOLD, 18), plot,
				true);

		ChartPanel chartPanel = new ChartPanel(barChart);
		chartPanel.setPreferredSize(new Dimension(600, 300));
		chartPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		chartPanel.setBackground(Color.white);
		MainUI.getInstance().updateStats(chartPanel);
	}

}
