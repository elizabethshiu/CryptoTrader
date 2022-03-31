package main;
import java.util.*;

import utils.DataFetcher;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class PerformTrade {
    //click perform trade
    //fetch data to update all coin prices
    //pass updated coin price list to trading broker ?
    //trading broker calls strategy factory
    //corresponding strategies are created and decide whether or not to trade, returns result

    //result object updates visualizations*
    
    private static String currentDate = DateTimeFormatter.ofPattern("dd-MM-yyyy").format(LocalDateTime.now());
    private static ArrayList<Double> masterPriceList = new ArrayList<Double>();  //only returns pricelist, no marketcap or volume
    private static ArrayList<String> masterCoinList = UISelection.getMasterCoinList();
    private static ArrayList<TradingBroker> masterBrokerList = UISelection.getInstance().getBrokerList();
    private static ArrayList<Object[]> masterResultsList = new ArrayList<Object[]>();
    
    //pressing perform trade button will:
    //get prices for all coins
    //update prices for each broker
    //broker will create a Strategy that determines whether or not to trade
    public static void initiateTrade(){
        // getPrices(masterCoinList);
        // updatePrices(masterBrokerList);
        createBrokerStrategy(masterBrokerList);
    }
    
    //get prices for all coins
    public static ArrayList<Double> getPrices(ArrayList<String> coinListAll) {

        DataFetcher data = new DataFetcher();
        int count = 0;

        //loop through all coins in coin list 
        while(count < coinListAll.size()) {
            String currentCoin = coinListAll.get(count);
            masterPriceList.add(data.getPriceForCoin(currentCoin, currentDate)); //get price for coin and add to master price list 
            count++;
        }
        return masterPriceList;
    }

    //update coinprice list for all brokers
    public static void updatePrices(ArrayList<TradingBroker> brokerList) {
        for (int i = 0; i < brokerList.size(); i++) {                                         //loop through all brokers
            for(int j = 0; j < masterCoinList.size(); j++) {                                  //look through master coin list
                String[] brokerCoinList = brokerList.get(i).getCoinList();                    //broker's coin list
                String currentCoin = masterCoinList.get(j);
                if(Arrays.asList(brokerCoinList).contains(currentCoin)) {                     //if broker's coin list contains the coin from the master list
                    int index = Arrays.asList(brokerCoinList).indexOf(currentCoin);           //get index of coin from the master list
                    Double currentCoinPrice = masterPriceList.get(index);
                    ArrayList<Double> brokerPriceList = brokerList.get(i).getPriceList();     //broker's price list
                    brokerPriceList.set(index, currentCoinPrice);                             // get updated price of broker's coins
                }
            }
        }
    }

    //creates strategy for each broker
    private static void createBrokerStrategy(ArrayList<TradingBroker> brokerList){
        //loop through all brokers
        for (int i = 0; i < brokerList.size(); i++) {  
            TradingBroker currentBroker = brokerList.get(i);
            String brokerStrategy = brokerList.get(i).getStrategy();
            Creator strategyCreator = null;

            //based on each broker's strategy, a Strategy will be created
            switch(brokerStrategy) {
                        case "Strategy-A":
                            strategyCreator = new StrategyACreator();
                                break;
                        case "Strategy-B":
                            strategyCreator = new StrategyBCreator();
                                break;
                        case "Strategy-C":
                            strategyCreator = new StrategyCCreator();
                                break;
                        case "Strategy-D":
                            strategyCreator = new StrategyDCreator();
                                break;
                        }
            
            Strategy strat = strategyCreator.factoryMethod();
            Object[] result = strat.doTrade(currentBroker.getCoinList(), currentBroker.getPriceList());  
            if(result != null)
                result[0] = currentBroker;    //add broker name to result from trade
            masterResultsList.add(result);
        } 
    
    }

    private static void updateVisualizations(Result result) {
        Object[] resultArray = result.getResultArray();
        
    }

}
