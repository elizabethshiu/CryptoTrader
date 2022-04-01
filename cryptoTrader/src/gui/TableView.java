package gui;
import java.util.*;

import main.PerformTrade;
import main.Result;
import main.Subject;
import utils.DataVisualizationCreator;


public class TableView implements Observer{
    private Result subject;

    public TableView(Result subject){
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

        DataVisualizationCreator.createTableOutput();       //re creating table with updated results list
    }   

}
