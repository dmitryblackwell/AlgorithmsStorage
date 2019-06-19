package com.blackwell.strategy;

import com.blackwell.strategy.duck.DecoyDuck;
import com.blackwell.strategy.duck.MallardDuck;

public class Demo {
    public static void main(String[] args) {
        DecoyDuck decoyDuck = new DecoyDuck();
        decoyDuck.quack();

        MallardDuck mallardDuck = new MallardDuck();
        mallardDuck.quack();
    }
}
