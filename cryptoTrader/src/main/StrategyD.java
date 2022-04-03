/**
 * @author all
 * Trade strategy for Strategy D.
 * Performs trade if requirements are met, otherwise trade is not performed
*/

package main;
import java.util.*;

import utils.DataFetcher;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class StrategyD implements Strategy {

    private static String currentDate = DateTimeFormatter.ofPattern("dd-MM-yyyy").format(LocalDateTime.now());  //keep track of current date

    public Object[] doTrade(String[] coinList, ArrayList<Double> coinPriceList) {
        
        //get data from datafetcher object 
        DataFetcher data = new DataFetcher();

        Object[] result = null;

        int count = 0;
        String currentCoin = null;  //set currentCoin to null
        //loop through all coins in coin list 
        while(count<coinList.length){
            currentCoin = coinList[count];

            //trading logic 
            //get price from a week ago
            double priceLastWeek = data.getPriceForCoin(currentCoin, getDateFromCurrent(7));
            if(coinPriceList.get(count) < priceLastWeek){
                //buy
                result = new Object[]{null, "Strategy D", coinList[count], "buy", 100, coinPriceList.get(count), currentDate};
            }
            //selling logic, sell if it is 2% higher than the price a week ago 
            else if(coinPriceList.get(count) > priceLastWeek * 1.02 ){
                //sell  
                result = new Object[]{null, "Strategy D", coinList[count], "sell", 100, coinPriceList.get(count), currentDate};
            }else{
                System.out.println("No suitable trade");
            }
            count++;    //go to next coin
        }
        
        //return trade
        return result;

    }
    
    /**
     * gets date from specified number of days ago 
     * @param days
     */
    public String getDateFromCurrent(long days){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDateTime newDate = LocalDateTime.now().minusDays(days);
        return dtf.format(newDate);
    }

 
}