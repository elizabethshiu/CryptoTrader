package main;
import java.util.ArrayList;

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

    public UISelection() {
        strategyList = new ArrayList<String>();
        brokerList = new ArrayList<TradingBroker>(); 
        coinLists= new ArrayList<String[]>(); //list of list of coins 
    }

    public static UISelection getInstance() {   //call this everytime we want 
        if(selectionInstance == null)
            selectionInstance = new UISelection();
        
        return selectionInstance;
    }

    public static ArrayList<String> getMasterCoinList(){
        return masterCoinList;
    }

    //singleton design
    public UISelection getBrokerData(String brokerName) {
        //TODO return UIselection class with data from brokername 
        
        return null;
    }

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
    
    public ArrayList<TradingBroker> getBrokerList() {
        return brokerList;
    }
    public ArrayList<String> getStrategyList() {
        return strategyList;
    }
    
    public ArrayList<String[]> getCoinLists() {
        return coinLists;
    }


}