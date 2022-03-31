package main;

import java.util.ArrayList;

public class TradingBroker {

    private String brokerName;
    private String strategy;
    private String[] coinList;                  //String[] because thats what the main UI has it as
    private ArrayList<Double> priceList = null;
    

    public TradingBroker (String brokerName, String strategy, String[] coinList) {
        this.brokerName = brokerName;
        this.strategy = strategy;
        this.coinList = coinList;
        priceList = new ArrayList<Double>();
        for(int x = 0; x<coinList.length; x++){
            priceList.add(0.0);
        }

    }

    public String[] getCoinList(){
        return coinList;
    }

    public ArrayList<Double> getPriceList(){
        return priceList;
    }

    public String getStrategy() {
        return strategy;
    }

}