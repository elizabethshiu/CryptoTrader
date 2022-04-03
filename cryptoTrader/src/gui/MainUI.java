package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import utils.AvailableCryptoList;
import utils.DataVisualizationCreator;
import main.PerformTrade;
import main.TradingBroker;
import main.UISelection;

/**
 * This class represents the main UI of the app and implements a Singleton design pattern
 * @author all
 */

public class MainUI extends JFrame implements ActionListener {
	
	private static final long serialVersionUID = 1L;

	private static MainUI instance;
	private JPanel stats;

	private DefaultTableModel dtm;
	private JTable table;

	private ArrayList<String> masterList = UISelection.getMasterCoinList();
	private AvailableCryptoList cryptolist = AvailableCryptoList.getInstance();
    private List<String> availableCoins = Arrays.asList(cryptolist.getAvailableCryptos());		//list of available cryptocoins

	/**
	 * Fetches the mainUI instance if exists, if not creates a new one
	 * @return
	 */
	public static MainUI getInstance() {
		if (instance == null)
			instance = new MainUI();

		return instance;
	}
	
	/**
	 * Constructor for MainUI
	 */
	private MainUI() {

		// Set window title
		super("Crypto Trading Tool");

		// Set top bar
		JPanel north = new JPanel();

		JButton trade = new JButton("Perform Trade");
		trade.setActionCommand("refresh");
		trade.addActionListener(this);

		JPanel south = new JPanel();
		
		south.add(trade);

		dtm = new DefaultTableModel(new Object[] { "Trading Client", "Coin List", "Strategy Name" }, 1);
		table = new JTable(dtm);
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Trading Client Actions",
				TitledBorder.CENTER, TitledBorder.TOP));
		Vector<String> strategyNames = new Vector<String>();
		strategyNames.add("None");
		strategyNames.add("Strategy-A");
		strategyNames.add("Strategy-B");
		strategyNames.add("Strategy-C");
		strategyNames.add("Strategy-D");
		TableColumn strategyColumn = table.getColumnModel().getColumn(2);
		JComboBox comboBox = new JComboBox(strategyNames);
		strategyColumn.setCellEditor(new DefaultCellEditor(comboBox));
		JButton addRow = new JButton("Add Row");
		JButton remRow = new JButton("Remove Row");
		addRow.setActionCommand("addTableRow");
		addRow.addActionListener(this);
		remRow.setActionCommand("remTableRow");
		remRow.addActionListener(this);

		scrollPane.setPreferredSize(new Dimension(800, 300));
		table.setFillsViewportHeight(true);
		
		JPanel east = new JPanel();
		east.setLayout(new BoxLayout(east, BoxLayout.Y_AXIS));
		east.add(scrollPane);
		JPanel buttons = new JPanel();
		buttons.setLayout(new BoxLayout(buttons, BoxLayout.X_AXIS));
		buttons.add(addRow);
		buttons.add(remRow);
		east.add(buttons);

		// Set charts region
		JPanel west = new JPanel();
		west.setPreferredSize(new Dimension(1250, 650));
		stats = new JPanel();
		stats.setLayout(new GridLayout(2, 2));

		west.add(stats);

		getContentPane().add(north, BorderLayout.NORTH);
		getContentPane().add(east, BorderLayout.EAST);
		getContentPane().add(west, BorderLayout.CENTER);
		getContentPane().add(south, BorderLayout.SOUTH);
	}

	public void updateStats(JComponent component) {
		stats.add(component);
		stats.revalidate();
	}

	public static void main(String[] args) {
		JFrame frame = MainUI.getInstance();
		frame.setSize(900, 600);
		frame.pack();
		frame.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
		if ("refresh".equals(command)) {
			for (int count = 0; count < dtm.getRowCount(); count++){
					Object traderObject = dtm.getValueAt(count, 0);
					if (traderObject == null) {
						JOptionPane.showMessageDialog(this, "please fill in Trader name on line " + (count + 1) );
						return;
					}
					String traderName = traderObject.toString();
					Object coinObject = dtm.getValueAt(count, 1);
					if (coinObject == null) {
						JOptionPane.showMessageDialog(this, "please fill in cryptocoin list on line " + (count + 1) );
						return;
					}
					String[] coinNames = coinObject.toString().split(",");
					for(int i = 0; i<coinNames.length; i++){
						coinNames[i] = coinNames[i].replaceAll("\\s+","");	//remove spaces from coin list
					}
					Object strategyObject = dtm.getValueAt(count, 2);
					if (strategyObject == null) {
						JOptionPane.showMessageDialog(this, "please fill in strategy name on line " + (count + 1) );
						return;
					}
					String strategyName = strategyObject.toString();
					

					for(int i = 0; i < coinNames.length; i++){	//everytime you press perform trade
						String coinID = null;
						String currentCoin = coinNames[i];
						//if coin is supported or already matches ID
						if(availableCoins.contains(currentCoin) || currentCoin.equals(cryptolist.getCryptoID(currentCoin))){
							coinID = cryptolist.getCryptoID(currentCoin);
							coinNames[i] = coinID;		//set name in coin list to ID
						} else {
							JOptionPane.showMessageDialog(this, currentCoin + " not recognized, please enter a recognized coin name.");
							return;
						}
						
						//add coin by ID
						if (coinID != null && !masterList.contains(coinID))
							masterList.add(coinID);	   //add new coins to master coin list
					}
					
					TradingBroker broker = new TradingBroker(traderName, strategyName, coinNames);
					UISelection.getInstance().addTradingBroker(broker, strategyName, coinNames);

					System.out.println(traderName + " " + Arrays.toString(coinNames) + " " + strategyName);
	        }
			PerformTrade.initiateTrade();
			stats.removeAll();
			DataVisualizationCreator creator = new DataVisualizationCreator();
			creator.createCharts();
		} else if ("addTableRow".equals(command)) {
			dtm.addRow(new String[3]);
		} else if ("remTableRow".equals(command)) {
			int selectedRow = table.getSelectedRow();
			if (selectedRow != -1)
				dtm.removeRow(selectedRow);
		}
	}

}
