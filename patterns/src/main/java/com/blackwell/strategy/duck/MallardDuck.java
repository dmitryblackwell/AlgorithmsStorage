package com.blackwell.strategy.duck;

import com.blackwell.strategy.strategies.impl.FlyWithWings;
import com.blackwell.strategy.strategies.impl.Quack;

public class MallardDuck extends Duck {

    public MallardDuck() {
        setFlyBehavior(new FlyWithWings());
        setQuackBehavior(new Quack());
    }

    @Override
    protected void display() {
        System.out.println("I am Mallard duck!");
    }
}
