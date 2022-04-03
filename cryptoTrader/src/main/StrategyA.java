/**
 * @author all
 * Trade strategy for Strategy A.
 * Performs trade if requiremtns are met, otherwise trade is not performed
*/

package main;
import java.util.*;

import utils.DataFetcher;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class StrategyA implements Strategy{
    
    private static String currentDate = DateTimeFormatter.ofPattern("dd-MM-yyyy").format(LocalDateTime.now());
    
    //this only updates the coin data
    //if trade broker 1 and 3 have strategy A, do trade will do it for all of them  
    //when we press perform trade - all coins in coinlist will be updated and sent to trading brokers. Then trading brokers decide if they want to trade or not  
    //facade design strategy


    //after button press, it creates datavisualization creator object, 
    //then datavisualization object  calls createCharts() which creates chart and table 
    public Object[] doTrade(String[] coinList, ArrayList<Double> coinPriceList) {
        
        System.out.println("Strategy A");

        Object[] result = null;

        //get data
        DataFetcher data = new DataFetcher();
        ArrayList<Double> priceList = new ArrayList<Double>();
        ArrayList<Double> marketCapList = new ArrayList<Double>();
        ArrayList<Double> volumeList = new ArrayList<Double>();

        int count = 0;
        //loop through all coins in coin list 

        while(count<coinList.length){
            String currentCoin = coinList[count];
            data.getPriceForCoin(currentCoin, currentDate); //gets all the data 
            data.getMarketCapForCoin(currentCoin, currentDate);
        
            //buying logic 
            if ((coinPriceList.get(count) >10)){
                result = new Object[]{null, "Strategy-A", currentCoin, "buy", 10, coinPriceList.get(count), currentDate};
            } else if ((coinPriceList.get(count) > 2)) {
                result = new Object[]{null, "Strategy-A", currentCoin, "buy", 10, coinPriceList.get(count), currentDate};
            } else {
                //no trade done if rules are not met
                System.out.println("No suitable trade");
            }

            count++;
        }
        
        return result;
    }



 
}