package main;
import java.util.*;
import java.io.*;

import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;  

public interface Strategy {
    public Object[] doTrade(String[] coinList, ArrayList<Double> coinPriceList);
}  
