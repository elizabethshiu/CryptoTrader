package main;
import java.util.*;

import utils.DataVisualizationCreator;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Result {   //needs to hold multiple trades
    
    private Object[] resultArray = new Object[7];

    public Result(TradingBroker tradeBroker, String strategyName, String coin, String action, int quantity, double price, String currentDate) {
        resultArray[0] = tradeBroker;
        resultArray[1] = strategyName;
        resultArray[2] = coin;
        resultArray[3] = action;
        resultArray[4] = quantity;
        resultArray[5] = price;
        resultArray[6] = currentDate;
    }

    public Result(String strategyName, String coin, String action, int quantity, double price, String currentDate) {
        resultArray[1] = strategyName;
        resultArray[2] = coin;
        resultArray[3] = action;
        resultArray[4] = quantity;
        resultArray[5] = price;
        resultArray[6] = currentDate;
    }

    public void setBroker(TradingBroker tradeBroker) {
        resultArray[0] = tradeBroker;
    }

    public Object[] getResultArray(){
        return resultArray;
    }

    public void notifyObservers(){
        DataVisualizationCreator.addTableRow(resultArray);
    }
}