package main;
import java.util.*;

import utils.DataFetcher;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class StrategyC implements Strategy {

    private static String currentDate = DateTimeFormatter.ofPattern("dd-MM-yyyy").format(LocalDateTime.now());

    public Object[] doTrade(String[] coinList, ArrayList<Double> coinPriceList) {
        
        Object[] result = null;

        // DataFetcher data = new DataFetcher();
        // data.getPriceForCoin(currentCoin, currentDate); //gets all the data 
        // data.getMarketCapForCoin(currentCoin, currentDate);
        // data.getVolumeForCoin(currentCoin, currentDate);

        if(Arrays.asList(coinList).contains("bitcoin") && Arrays.asList(coinList).contains("ethereum")){
            int btcIndex = Arrays.asList(coinList).indexOf("bitcoin");
            int ethIndex = Arrays.asList(coinList).indexOf("ethereum");
            
            if(coinPriceList.get(btcIndex) <= 500000 && coinPriceList.get(ethIndex) >= 2000){   //if btc <= $50,000 and eth >= $5000
                result = new Object[]{null, "Strategy-C", "ETH", "sell", 10, coinPriceList.get(ethIndex), currentDate}; //sell 10 ETH coins
            }
        } else {
            return null;
        }
        return result;
    }



 
}