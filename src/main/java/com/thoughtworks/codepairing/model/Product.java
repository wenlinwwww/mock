package com.thoughtworks.codepairing.model;

public class Product {
    private final double price;
    private final String productCode;
    private final String name;
    private final String tag;

    public Product(double price, String productCode, String name, String tag) {
        this.price = price;
        this.productCode = productCode;
        this.name = name;
        this.tag = tag;
    }

    public double getPrice() {
        return price;
    }

    public String getProductCode() {
        return productCode;
    }

    public String getName() {
        return name;
    }

    public String getTag() {
        return tag;
    }

}
