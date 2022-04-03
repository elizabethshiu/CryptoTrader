package utils;
import gui.MainUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

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

/**
 * @author all
 * This class creates the trade history table and trade frequency histogram
 */

public class DataVisualizationCreator {
	
	private static Object[] columnNames = {"Trader","Strategy","CryptoCoin","Action","Quantity","Price","Date"};
	private DefaultTableModel model = new DefaultTableModel(columnNames, 0);	//added this tablemodel to JTable so we can add rows later on 
	private static HashMap<String, Integer> tradeCount = new HashMap<String, Integer>();

	/**
	 * Create visualizations
	 */
	public void createCharts() {
		createTableOutput();
		createBar();
	}
	
	/**
	 * Create table with trade history
	 */
	public static void createTableOutput() {
		
		Object[][] resultData = readResults();
		
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
	
	/**
	 * Create histogram showing trade frequency for each trader by strategy
	 */
	public static void createBar() {
		
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		Object[][] resultData = readResults();

		for (int i=0; i<resultData.length;i++) {
			if(resultData[i][0] != null) {
				String traderName = resultData[i][0].toString();
				dataset.setValue(tradeCount.get(traderName), traderName, resultData[i][1].toString());
			} else {
				break;
			}
		}

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

	/**
	 * Reads and returns trade history from stored file
	 */
	private static Object[][] readResults() {
		Object[][] resultData = new Object[250][7];

		BufferedReader objReader = null;
		try {
			String strCurrentLine;
			String filePath = new File("").getAbsolutePath();
			objReader = new BufferedReader(new FileReader(filePath + "/TradingBroker.txt"));
			int i = 0;
				while ((strCurrentLine = objReader.readLine()) != null) {         	
					
					resultData[i][0] = strCurrentLine;
					String traderName = (String) resultData[i][0]; //cast to string
					resultData[i][1] = objReader.readLine();
					resultData[i][2] = objReader.readLine();
					resultData[i][3] = objReader.readLine();
					resultData[i][4] = objReader.readLine();
					resultData[i][5] = objReader.readLine();
					resultData[i][6] = objReader.readLine();					
					System.out.println(resultData[i].toString());
					i += 1;
					if(tradeCount.get(traderName) == null) {	//if trader is not in dictionary
						tradeCount.put(traderName, 1);
					} else {
						int numTrades = tradeCount.get(traderName);
						tradeCount.replace(traderName, numTrades + 1);
					}
				}
			return resultData;
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (objReader != null) {
					objReader.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
			return null;
	}
}
