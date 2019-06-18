package com.blackwell.patterns.strategy;

import com.blackwell.patterns.strategy.strategies.impl.FlyNoWay;
import com.blackwell.patterns.strategy.strategies.impl.MuteQuack;

public class DecoyDuck extends Duck {

    public DecoyDuck() {
        setFlyBehavior(new FlyNoWay());
        setQuackBehavior(new MuteQuack());
    }

    @Override
    void display() {
        System.out.println("I'm a duck Decoy");
    }
}
