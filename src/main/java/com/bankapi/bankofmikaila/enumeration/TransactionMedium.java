package com.bankapi.bankofmikaila.enumeration;

public enum TransactionMedium {
    BALANCE("BALANCE"),
    REWARDS("REWARDS");

    private final String description;

    TransactionMedium(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
