package main;
/**
 * Strategy D Creator class for factory design pattern
 * @author all
 */
public class StrategyDCreator extends Creator{
    /**
     * Creates new strategy D
     */
    public Strategy factoryMethod(){
        return new StrategyD();
    }
}
