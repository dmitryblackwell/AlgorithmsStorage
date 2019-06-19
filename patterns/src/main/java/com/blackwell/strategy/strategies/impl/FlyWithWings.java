package com.blackwell.strategy.strategies.impl;

import com.blackwell.strategy.strategies.FlyBehavior;

public class FlyWithWings implements FlyBehavior {
    @Override
    public void fly() {
        System.out.println("I am flying with wings!");
    }
}
