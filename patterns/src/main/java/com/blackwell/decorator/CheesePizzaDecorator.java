package com.blackwell.decorator;

public class CheesePizzaDecorator extends ToppingDecorator {

    public CheesePizzaDecorator(Pizza pizza) {
        super.pizza = pizza;
    }

    @Override
    public String getDescription() {
        return super.pizza.getDescription() + " with cheese";
    }

    @Override
    public double cost() {
        return pizza.cost() + 0.99d;
    }
}
