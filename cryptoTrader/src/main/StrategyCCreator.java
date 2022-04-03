package main;
/**
 * Strategy C Creator class for factory design pattern
 * @author all
 */
public class StrategyCCreator extends Creator{
    /**
     * Creates new strategy C
     */
    public Strategy factoryMethod(){
        return new StrategyC();
    }
}
