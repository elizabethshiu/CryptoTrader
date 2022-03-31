package main;

public class StrategyDCreator extends Creator{
    public Strategy factoryMethod(){
        return new StrategyD();
    }
}
