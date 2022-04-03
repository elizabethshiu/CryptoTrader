/**
 * @author all
 * Trade strategy for Strategy B
 * Strategy B is focused on safe and stable long-term investments, prioritizing trades with higher 24h volumes and market caps
 * Performs trade if requirements are met, otherwise trade is not performed
 */

package main;
import java.util.*;

import utils.DataFetcher;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class StrategyB implements Strategy {

    private static String currentDate = DateTimeFormatter.ofPattern("dd-MM-yyyy").format(LocalDateTime.now());

    public Object[] doTrade(String[] coinList, ArrayList<Double> coinPriceList) {
        
        DataFetcher data = new DataFetcher();
        ArrayList<Double> priceList = new ArrayList<Double>();
        ArrayList<Double> marketCapList = new ArrayList<Double>();
        ArrayList<Double> volumeList = new ArrayList<Double>();

        int count = 0;
        //loop through all coins in coin list 

        Object[] result = null;

        while(count<coinList.length) { // loop through coin list
            String currentCoin = coinList[count];
            data.getPriceForCoin(currentCoin, currentDate); //gets all the data 
            data.getMarketCapForCoin(currentCoin, currentDate);
            data.getVolumeForCoin(currentCoin, currentDate);

            if(data.getVolumeForCoin(coinList[count], getDateFromCurrent(1)) > 1000000000) { // if 24h market volume is greater than $1B, buy
                result = new Object[]{null, "Strategy-B", coinList[count], "buy", 15, coinPriceList.get(count), currentDate};       
            }
            if(data.getVolumeForCoin(coinList[count], getDateFromCurrent(1)) < 1000000) { // if 24h market volume is less than $1M, sell
                result = new Object[]{null, "Strategy-B", coinList[count], "sell", 5, coinPriceList.get(count), currentDate};
            }
            if(data.getMarketCapForCoin(coinList[count], currentDate) > 2000000000 * 4) { // if market cap is greater than $10B, buy 
            // multiplication by 4 is required since the max int value in Java is ~2 billion
                result = new Object[]{null, "Strategy-B", coinList[count], "buy", 20, coinPriceList.get(count), currentDate};
            }

            //(String tradeBroker, String strategyName, String coin, action, String quantity, String price, String date){  
            //result.addTradeData("tradeBroker", "Strategy A", coinList.get(count), action, coinList.get(count), "quantity", coinPriceList.get(count), currentDate);
            count++;
        }
        
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