package main;
import java.util.*;

import utils.DataFetcher;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/** This class handles all trading actions and processes outlined in UC3 when the user wants to perform a trade. DOES THIS CLASS USE A DESIGN PATTERN?? 
 *
 *  @author all
 */
public class PerformTrade {
    /**
     * click perform trade
     * fetch data to update all coin prices
     * pass updated coin price list to trading broker
     * trading broker calls strategy factory
     * corresponding strategies are created and decide whether or not to trade, returns result
     * result object updates visualizations
     * use AvailableCryptoList to verify user entered valid cypto ID
     */
    
    private static String currentDate = DateTimeFormatter.ofPattern("dd-MM-yyyy").format(LocalDateTime.now());
    private static ArrayList<Double> masterPriceList = new ArrayList<Double>();  //only returns pricelist, no marketcap or volume
    private static ArrayList<String> masterCoinList = UISelection.getMasterCoinList();
    private static ArrayList<TradingBroker> masterBrokerList = UISelection.getInstance().getBrokerList();
    public static Result masterResultsList = masterResultsList = new Result();;
    
    /**
    * pressing perform trade button will:
    * get prices for all coins
    * update prices for each broker
    * broker will create a Strategy that determines whether or not to trade
    */
    public static void initiateTrade(){
        getPrices(masterCoinList);
        updatePrices(masterBrokerList);
        createBrokerStrategy(masterBrokerList);
    }
    
    /**
     * get prices for all coins, add to master price list
     * @param coinListAll
     */
    public static ArrayList<Double> getPrices(ArrayList<String> coinListAll) {

        DataFetcher data = new DataFetcher();
        int count = 0;

        while(count < coinListAll.size()) {
            String currentCoin = coinListAll.get(count);
            masterPriceList.add(data.getPriceForCoin(currentCoin, currentDate));
            count++;
        }
        return masterPriceList;
    }

    /**
     * @param brokerlist a list of all brokers to update their list of coins
     * update coinprice list for all brokers
     */
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

    /**
     * @param brokerList a list of brokers to create a strategy for
     * creates strategy for each broker
     */
    private static void createBrokerStrategy(ArrayList<TradingBroker> brokerList){
        masterResultsList = new Result();
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
            if(result != null) {
                result[0] = currentBroker.getBrokerName();    //add broker name to result from trade
                masterResultsList.addResult(result);
                //add trade to trade log by writing to text file
            try {
             
                BufferedWriter bw = new BufferedWriter(new FileWriter("TradingBroker.txt", true));
                
                //includes all trade info such as trading broker name, strategy, action (buy/sell),  
                bw.write(result[0].toString() + "\n");
                bw.write(result[1].toString() + "\n");
                bw.write(result[2].toString() + "\n");
                bw.write(result[3].toString() + "\n");
                bw.write(result[4].toString() + "\n");
                bw.write(result[5].toString() + "\n");
                bw.write(result[6].toString() + "\n");
                
                bw.close();
                
            } catch(IOException e) {
    
                e.printStackTrace();
    
            }
        }
        
        } 
        masterResultsList.updateView();
    }

    //do trade, returns result
    //call update view after result is returned
    //update view updates visualizations

    //call new table view
    //new table view calls create table in visCreateor class


}
