package gui;

import main.Result;
import main.Subject;
import utils.DataVisualizationCreator;
/**
 * Histogram view class, with observer design pattern
 * @author all
 */
public class HistogramView implements Observer{
    private Result subject;

    /** 
     * Histogram constructor
     * @param subject
     * add histogram to observer list
     */
    public HistogramView(Result subject){
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
        DataVisualizationCreator.createBar();
    }   

}
