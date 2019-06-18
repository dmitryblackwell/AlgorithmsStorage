package com.blackwell.patterns.strategy.strategies.impl;

import com.blackwell.patterns.strategy.strategies.QuackBehavior;

public class MuteQuack implements QuackBehavior {
    @Override
    public void quack() {
        System.out.println("<silence>");
    }
}
