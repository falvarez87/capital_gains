package com.nubank.capitalgains.enums;

public enum Operation {
    buy("buy"),
    sell("sell");

    private String operation;

    Operation(String operation) {
        this.operation = operation;
    }

    public String getOperation() {
        return operation;
    }

}
