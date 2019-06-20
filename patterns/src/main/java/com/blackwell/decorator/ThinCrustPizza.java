package com.blackwell.decorator;

public class ThinCrustPizza extends Pizza {
    @Override
    public String getDescription() {
        return "ThinCrust " + super.getDescription();
    }

    @Override
    public double cost() {
        return 8.99d;
    }
}
