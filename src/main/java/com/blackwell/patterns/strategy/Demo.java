package com.blackwell.patterns.strategy;

public class Demo {
    public static void main(String[] args) {
        DecoyDuck decoyDuck = new DecoyDuck();
        decoyDuck.quack();

        MallardDuck mallardDuck = new MallardDuck();
        mallardDuck.quack();
    }
}
