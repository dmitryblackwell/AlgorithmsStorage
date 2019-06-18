package com.blackwell.patterns.strategy.strategies.impl;

import com.blackwell.patterns.strategy.strategies.FlyBehavior;

public class FlyNoWay implements FlyBehavior {
    @Override
    public void fly() {
        System.out.println("I cant fly!");
    }
}
