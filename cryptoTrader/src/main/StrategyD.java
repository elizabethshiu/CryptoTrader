package main;
import java.util.*;

import utils.DataFetcher;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class StrategyD implements Strategy {

    private static String currentDate = DateTimeFormatter.ofPattern("dd-MM-yyyy").format(LocalDateTime.now());

    public Object[] doTrade(String[] coinList, ArrayList<Double> coinPriceList) {
        
        //get data
        DataFetcher data = new DataFetcher();
        ArrayList<Double> priceList = new ArrayList<Double>();
        ArrayList<Double> marketCapList = new ArrayList<Double>();
        ArrayList<Double> volumeList = new ArrayList<Double>();

        Object[] result = null;

        int count = 0;
        //loop through all coins in coin list 
        while(count<coinList.length){
            String currentCoin = coinList[count];
            data.getPriceForCoin(currentCoin, currentDate); //gets all the data 
            data.getMarketCapForCoin(currentCoin, currentDate);
            data.getVolumeForCoin(currentCoin, currentDate);
            //buying logic 

            //get price from a week ago
            double priceLastWeek = data.getPriceForCoin(currentCoin, getDateFromCurrent(7));
            if(coinPriceList.get(count) < priceLastWeek){
                //buy
                result = new Object[]{null, "Strategy D", coinList[count], "buy", 100, coinPriceList.get(count), currentDate};
            }
            //selling logic, sell if it is 2% higher than the price a week ago 
            if(coinPriceList.get(count) > priceLastWeek * 1.02 ){
                //sell  
                result = new Object[]{null, "Strategy D", coinList[count], "sell", 100, coinPriceList.get(count), currentDate};
            }

            //(String tradeBroker, String strategyName, String coin, String action, String quantity, String price, String date)
            count++;
        }
        
        //if no trade, return null 
        return null;

    }

     
    //get the date from input days ago 
    public String getDateFromCurrent(long days){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDateTime newDate = LocalDateTime.now().minusDays(days);
        return dtf.format(newDate);
    }

 
}