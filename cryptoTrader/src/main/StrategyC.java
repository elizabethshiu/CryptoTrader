/**
 * @author all
 * Trade strategy for Strategy C.
 * Performs trade if requiremtns are met, otherwise trade is not performed
*/

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

        if(Arrays.asList(coinList).contains("bitcoin") && Arrays.asList(coinList).contains("ethereum")){
            int btcIndex = Arrays.asList(coinList).indexOf("bitcoin");
            int ethIndex = Arrays.asList(coinList).indexOf("ethereum");
            
            if(coinPriceList.get(btcIndex) <= 500000 && coinPriceList.get(ethIndex) >= 2000){   //if btc <= $50,000 and eth >= $5000
                result = new Object[]{null, "Strategy-C", "ethereum", "sell", 10, coinPriceList.get(ethIndex), currentDate}; //sell 10 ETH coins
            } else {
                return null;
            }
        } else {
            result = new Object[]{null, "Strategy-C", "ethereum", "Fail", null, null, currentDate};
        }
        return result;
    }



 
}