package main; 

public class StrategyBCreator extends Creator {
    public Strategy factoryMethod() {
        return new StrategyB();
    }
}