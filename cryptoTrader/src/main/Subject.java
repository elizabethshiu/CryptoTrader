package main;

import java.util.ArrayList;
import java.util.List;

import gui.Observer;

/**
 * This class represents the subject in a Observer design pattern
 * @author all
 */

public abstract class Subject {

    private List<Observer> observers = new ArrayList<>();

    /**
     * @param observer observer to be added to the list
     * adds observer to list
     */
    public void attach(Observer observer){
        observers.add(observer);
    }
    
    /**
     * @param observer observer to be removed to the list
     * removes observer to list
     */
    public void detach (Observer observer) {
        observers.remove(observer);
    }

    /**
     * goes through list of observer and calls update method on each observer
     */
    public void notifyObservers(){
        for(Observer observer : observers)
            observer.update(this);      //calls update on each observer
    }
}
