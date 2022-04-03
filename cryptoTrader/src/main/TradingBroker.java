package main;

import java.util.ArrayList;

/**
 * This class represents trading brokers in the app
 * @author all
 */
public class TradingBroker {

    private String brokerName;
    private String strategy;
    private String[] coinList;            
    private ArrayList<Double> priceList = null;
    
    /**
     * Trading Broker constructor
     * @param brokerName
     * @param strategy
     * @param coinList
     */
    public TradingBroker (String brokerName, String strategy, String[] coinList) {
        this.brokerName = brokerName;
        this.strategy = strategy;
        this.coinList = coinList;
        priceList = new ArrayList<Double>();
        for(int x = 0; x<coinList.length; x++) {
            priceList.add(0.0);
        }

    }

    /**
     * Returns broker name
     */
    public String getBrokerName() {
        return brokerName;
    }

    /**
     * Returns broker's coin list
     */
    public String[] getCoinList() {
        return coinList;
    }

    /**
     * Returns list of prices for all broker's coins
     */
    public ArrayList<Double> getPriceList() {
        return priceList;
    }

    /**
     * Returns broker strategy
     */
    public String getStrategy() {
        return strategy;
    }

}