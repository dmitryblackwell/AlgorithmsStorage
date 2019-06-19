package com.blackwell.strategy.strategies.impl;

import com.blackwell.strategy.strategies.FlyBehavior;

public class FlyNoWay implements FlyBehavior {
    @Override
    public void fly() {
        System.out.println("I cant fly!");
    }
}
