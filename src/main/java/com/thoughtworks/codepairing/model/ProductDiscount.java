package com.thoughtworks.codepairing.model;

public enum ProductDiscount {
    NULL(0),
    DIS_10(0.1),
    DIS_15(0.15),
    DIS_20(0.2);

    private final double discount;

    ProductDiscount(double discount) {
        this.discount = discount;
    }

    public double getDiscount() {
        return discount;
    }
}
