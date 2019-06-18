package com.blackwell.patterns.strategy.strategies.impl;

import com.blackwell.patterns.strategy.strategies.FlyBehavior;

public class FlyWithWings implements FlyBehavior {
    @Override
    public void fly() {
        System.out.println("I am flying with wings!");
    }
}
