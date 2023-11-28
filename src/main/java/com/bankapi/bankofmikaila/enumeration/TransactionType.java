package com.bankapi.bankofmikaila.enumeration;

public enum TransactionType {
    P2P("P2P"),
    DEPOSIT("DEPOSIT"),
    WITHDRAWAL("WITHDRAWAL"),

    P2P_WITHDRAWAL("P2P_WITHDRAWAL"),

    P2P_DEPOSIT("P2P_DEPOSIT");

    private final String description;

    TransactionType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
