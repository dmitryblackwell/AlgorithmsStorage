package com.blackwell.patterns.strategy;

import com.blackwell.patterns.strategy.strategies.FlyBehavior;
import com.blackwell.patterns.strategy.strategies.QuackBehavior;
import lombok.Setter;

public abstract class Duck {

    @Setter
    FlyBehavior flyBehavior;

    @Setter
    QuackBehavior quackBehavior;

    abstract void display();

    public void fly() {
        flyBehavior.fly();
    }

    public void quack() {
        quackBehavior.quack();
    }

}
