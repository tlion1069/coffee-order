package edu.iu.habahram.coffeeorder.model;

public class Decaf extends Beverage {
    @Override
    public float cost() {
        return 0;
    }

    @Override
    public String getDescription() {
        return null;
    }
}
