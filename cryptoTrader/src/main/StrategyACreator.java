package main;

public class StrategyACreator extends Creator{
    public Strategy factoryMethod(){
        return new StrategyA();
    }
}
