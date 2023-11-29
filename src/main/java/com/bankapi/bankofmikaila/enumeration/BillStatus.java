package com.bankapi.bankofmikaila.enumeration;

public enum BillStatus {
    PENDING("PENDING"),
    CANCELLED("CANCELLED"),
    COMPLETED("COMPLETED"),
    RECURRING("RECURRING");
    private final String description;
    BillStatus(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

}
