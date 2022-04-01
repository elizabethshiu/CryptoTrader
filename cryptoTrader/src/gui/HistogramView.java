package gui;
import java.util.*;

import main.PerformTrade;
import main.Result;
import main.Subject;
import utils.DataVisualizationCreator;

public class HistogramView implements Observer{
    private Result subject;

    public HistogramView(Result subject){
        this.subject = subject;
        subject.attach(this);   //adds table to observer list
    }
    
    //if master list changes
    @Override
    public void update(Subject changedSubject) {
        if(changedSubject.equals(subject)){    
            draw();
        }
    }

    //re-render table
    private void draw(){
        Object[][] doubleArrResults = subject.getDoubleArray();

        DataVisualizationCreator.createBar();       //re creating table with updated results list
    }   

}
