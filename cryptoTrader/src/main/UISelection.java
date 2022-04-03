package main;
import java.util.ArrayList;

/**
 * This class handles the user interactions with the GUI. It allows the user to add and remove trading brokers, as well as storing 
 * the necessary trading broker data 
 * It uses the singleton design pattern
 * @author all
 */
public class UISelection {
    
    //has a broker list, strategy list, coins list list
    //holds all user brokers
    //broker[0] corresponds with strategy [0] and coin list [0]
    // coinLists = LIST
    // coinList = variable
    
    private ArrayList<String> strategyList;
    private ArrayList<TradingBroker> brokerList;       //trading broker arraylist now instead of string  
    private ArrayList<String[]> coinLists; //list of list of coins
    private static ArrayList<String> masterCoinList = new ArrayList<String>(); 
    private static UISelection selectionInstance = null;

    /**
     * UISelection constructor, initializes all data structures required to hold trading broker data
     */
    public UISelection() {
        strategyList = new ArrayList<String>();
        brokerList = new ArrayList<TradingBroker>(); 
        coinLists= new ArrayList<String[]>(); //list of list of coins 
    }

    /**
     * Singleton design pattern for UISelection
     */
    public static UISelection getInstance() {   //call this everytime we want 
        if(selectionInstance == null)
            selectionInstance = new UISelection();
        
        return selectionInstance;
    }

    /**
     * returns list of all coins
     */
    public static ArrayList<String> getMasterCoinList(){
        return masterCoinList;
    }

    /**
     * @param brokerName trading broker to do trade
     * @param strategyName strategy being used
     * @param coinList list of coins to trade
     * adds trading broker, the strategy they will use, and coins they will trade
     */
    public boolean addTradingBroker(TradingBroker brokerName, String strategyName, String[] coinList) {
        int index = brokerList.indexOf(brokerName);
        if(index == -1) {    //if no pre-existing trading broker exists with that name
            brokerList.add(brokerName); //then we can add trading broker 
            strategyList.add(strategyName);
            coinLists.add(coinList);

            System.out.println("broker added successfully");
            return true;    
        }
        System.out.println("broker could not be added");
        return false;
    } 
        
    /**
     * @param brokerName tradingbroker to be removes
     * removes trading broker from brokerarraylist
     */
    public boolean removeTradingBroker(String brokerName) {
        
        int index = brokerList.indexOf(brokerName);
        
        if(index == -1) { //if broker is not in broker list
            return false;
        }

        strategyList.remove(index);
        coinLists.remove(index);
        brokerList.remove(index);
        
        return true;
    } 
    
    /**
     * @return an arraylist of brokers
     */
    public ArrayList<TradingBroker> getBrokerList() {
        return brokerList;
    }


}