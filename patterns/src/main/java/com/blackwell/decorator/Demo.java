package com.blackwell.decorator;

public class Demo {
    public static void main(String[] args) {
        Pizza pizza = new ThinCrustPizza();
        Pizza cheesePizza = new CheesePizzaDecorator(pizza);

        System.out.println("Description: " + cheesePizza.getDescription());
        System.out.println("Cost: " + cheesePizza.cost());
    }
}
