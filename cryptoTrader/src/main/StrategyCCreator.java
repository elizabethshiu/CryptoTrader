package main;

public class StrategyCCreator extends Creator{
    public Strategy factoryMethod(){
        return new StrategyC();
    }
}
