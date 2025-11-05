package com.example.demo.web;

public class TransferRequest {
    private Long fromId;
    private Long toId;
    private double amount;
    private boolean failAfterDebit;

    public Long getFromId() {
        return fromId;
    }

    public void setFromId(Long fromId) {
        this.fromId = fromId;
    }

    public Long getToId() {
        return toId;
    }

    public void setToId(Long toId) {
        this.toId = toId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public boolean isFailAfterDebit() {
        return failAfterDebit;
    }

    public void setFailAfterDebit(boolean failAfterDebit) {
        this.failAfterDebit = failAfterDebit;
    }
}
