package com.blackwell.strategy.strategies.impl;

import com.blackwell.strategy.strategies.QuackBehavior;

public class Quack implements QuackBehavior {
    @Override
    public void quack() {
        System.out.println("Quack!");
    }
}
