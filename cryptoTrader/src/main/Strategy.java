package main;
import java.util.*;

/**
 * This is an interface for all trade strategies within the app
 * @author all
 */

public interface Strategy {
    public Object[] doTrade(String[] coinList, ArrayList<Double> coinPriceList);
}  
