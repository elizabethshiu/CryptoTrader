package main;
import java.util.*;

import utils.DataVisualizationCreator;
import gui.Observer;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Result extends Subject{   //needs to hold multiple trades
    
    private Object[] resultArray = new Object[7];
    private ArrayList<Object[]> masterResultsList;

    //Result object is now master list
    public Result(){
        masterResultsList = new ArrayList<Object[]>();
    }

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

    public Result(Object[] data){
        data[0] = resultArray[0];
        data[1] = resultArray[1];
        data[2] = resultArray[2];
        data[3] = resultArray[3];
        data[4] = resultArray[4];
        data[5] = resultArray[5];
        data[6] = resultArray[6];
    }

    public void setBroker(TradingBroker tradeBroker) {
        resultArray[0] = tradeBroker;
    }

    public void addResult(Object[] data) {
        masterResultsList.add(data);
    }

    public Object[] getResultArray(){
        return resultArray;
    }

    public Object[][] getDoubleArray() {
        Object[][] doubleArray = new Object[masterResultsList.size()][7];
        
        for(int i = 0; i<masterResultsList.size(); i++){
            doubleArray[i]=masterResultsList.get(i);
        }
        
        return doubleArray;
    }

    //called when you have an updated to master list
    public void updateView(){
        notifyObservers();      //calls update() on views which re-renders views
    }
}

