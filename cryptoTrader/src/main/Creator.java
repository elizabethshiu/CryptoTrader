/**
 * @author all
 * This class represents the Creator in a Factory Method design pattern 
 * 
 */

package main;

/**
 * factory methodcreator class 
 */
public abstract class Creator {

    /**
     * calls factoryMethod()
     * @return Strategy object
     */
    public abstract Strategy factoryMethod();
}
