package main;
import java.util.*;

import utils.DataFetcher;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class StrategyB implements Strategy {

    private static String currentDate = DateTimeFormatter.ofPattern("dd-MM-yyyy").format(LocalDateTime.now());

    public Object[] doTrade(String[] coinList, ArrayList<Double> coinPriceList) {
        
        //get data
        DataFetcher data = new DataFetcher();
        ArrayList<Double> priceList = new ArrayList<Double>();
        ArrayList<Double> marketCapList = new ArrayList<Double>();
        ArrayList<Double> volumeList = new ArrayList<Double>();

        int count = 0;
        //loop through all coins in coin list 
        String action;  //buy or sell

        while(count<coinList.length){
            String currentCoin = coinList[count];
            data.getPriceForCoin(currentCoin, currentDate); //gets all the data 
            data.getMarketCapForCoin(currentCoin, currentDate);
            data.getVolumeForCoin(currentCoin, currentDate);
            //Strategy B is focused on safe and stable long-term investments, prioritizing trades with higher 24h volumes and market caps
            for(int i = 0; i < coinList.length; i++) { // loop through all coins in coin list
                if(data.getVolumeForCoin(coinList[i], getDateFromCurrent(1)) > 1000000000) { // if 24h market volume is greater than $1B, buy
                    return new Object[]{null, "Strategy-B", "buy", coinList[i], 15, data.getPriceForCoin(coinList[i], currentDate), currentDate};       
                }
                if(data.getVolumeForCoin(coinList[i], getDateFromCurrent(1)) < 1000000) { // if 24h market volume is less than $1M, sell
                    return new Object[]{null, "Strategy-B", "sell", coinList[i], 5, data.getPriceForCoin(coinList[i], currentDate), currentDate};
                }
                if(data.getMarketCapForCoin(coinList[i], currentDate) > 2000000000 * 4) { // if market cap is greater than $10B, buy 
                // multiplication by 4 is required since the max int value in JAva is ~2 billion
                    return new Object[]{null, "Strategy-B", "buy", coinList[i], 20, data.getPriceForCoin(coinList[i], currentDate), currentDate};
                }
            }

            //(String tradeBroker, String strategyName, action, String coin, String quantity, String price, String date){  
//            result.addTradeData("tradeBroker", "Strategy A", action, coinList.get(count), "quantity", coinPriceList.get(count), currentDate);
            count++;
        }
        
        return null;
    }

    
    public String getDateFromCurrent(long days){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDateTime newDate = LocalDateTime.now().minusDays(days);
        return dtf.format(newDate);
    }


 
}