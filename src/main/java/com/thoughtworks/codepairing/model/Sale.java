package com.thoughtworks.codepairing.model;

public enum Sale {
    NULL(1, 0),
    BUY_TWO_GET_ONE_FREE(2, 1),
    BUY_THREE_GET_ONE_FREE(3, 1),
    BUY_THREE_GET_TWO_FREE(3, 1),
    BUY_FIVE_GET_TWO_FREE(5, 2);


    private final int buyNumber;
    private final int freeNumber;

    Sale(int buyNumber, int freeNumber) {
        this.buyNumber = buyNumber;
        this.freeNumber = freeNumber;
    }

    public int getBuyNumber() {
        return buyNumber;
    }

    public int getFreeNumber() {
        return freeNumber;
    }
}
