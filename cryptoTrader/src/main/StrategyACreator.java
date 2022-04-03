package main;
/**
 * Strategy A Creator class for factory design pattern
 * @author all
 */
public class StrategyACreator extends Creator{
    /**
     * Creates new strategy A
     */
    public Strategy factoryMethod(){
        return new StrategyA();
    }
}
