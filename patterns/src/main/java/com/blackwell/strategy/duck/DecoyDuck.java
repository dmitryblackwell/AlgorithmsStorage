package com.blackwell.strategy.duck;

import com.blackwell.strategy.strategies.impl.FlyNoWay;
import com.blackwell.strategy.strategies.impl.MuteQuack;

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
