package main; 
/**
 * Strategy B Creator class for factory design pattern
 * @author all
 */
public class StrategyBCreator extends Creator {
    /**
     * Creates new strategy B
     */
    public Strategy factoryMethod() {
        return new StrategyB();
    }
}