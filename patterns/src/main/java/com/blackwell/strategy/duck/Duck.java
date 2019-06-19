package com.blackwell.strategy.duck;

import com.blackwell.strategy.strategies.FlyBehavior;
import com.blackwell.strategy.strategies.QuackBehavior;
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
