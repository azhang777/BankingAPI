package com.bankapi.bankofmikaila.enumeration;

public enum TransactionType {
    P2P("P2P"),
    DEPOSIT("DEPOSIT"),
    WITHDRAWAL("WITHDRAWAL");
    private final String description;

    TransactionType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
