package com.bankapi.bankofmikaila.enumeration;

public enum AccountType {
    SAVINGS("SAVINGS"),CHECKING("CHECKING"),CREDIT("CREDIT");
    private final String description;
    AccountType(String description) {
        this.description = description;
    }
    public String getDescription() {
        return description;
    }
}
