package gui;

import main.Result;
import main.Subject;
import utils.DataVisualizationCreator;

/**
 * This class represents the table visualization, it is a component of the Observer design pattern
 */

public class TableView implements Observer{
    private Result subject;

    /** 
     * Table constructor
     * @param subject
     * add table to observer list
     */
    public TableView(Result subject){
        this.subject = subject;
        subject.attach(this);
    }
    
    /**
     * if master list changes, update
     * @param changedSubject
     */
    @Override
    public void update(Subject changedSubject) {
        if(changedSubject.equals(subject)){    
            draw();
        }
    }

    /**
     * recreate table with updated results list
     */
    private void draw(){
        DataVisualizationCreator.createTableOutput();  
    }   

}
