package main;
import java.util.*;


/**
 * This class represents a list of all results from trades performed
 * @author all
 */
public class Result extends Subject{
    
    private Object[] resultArray = new Object[7];
    private ArrayList<Object[]> masterResultsList;

    /**
     * Result array constructor
     */
    public Result(){
        masterResultsList = new ArrayList<Object[]>();
    }

    /**
     * Set first index in result array as tradebroker
     * @param tradeBroker
     */
    public void setBroker(TradingBroker tradeBroker) {
        resultArray[0] = tradeBroker;
    }

    /**
     * Add result to master result list
     * @param data
     */
    public void addResult(Object[] data) {
        masterResultsList.add(data);
    }

    /**
     * called when results are added to the master results list
     * calls update() on views which re-renders views
     */
    public void updateView(){
        notifyObservers();      
    }
}

