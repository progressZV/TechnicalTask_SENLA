package com.senla.entity;

public class BankCard {
    private String validNumber;
    private String pinCode;
    private double balance;
    private boolean status;

    public void setValidNumber(String validNumber) {
        this.validNumber = validNumber;
    }

    public void setPinCode(String pinCode) {
        this.pinCode = pinCode;
    }

    public String getValidNumber() {
        return validNumber;
    }

    public String getPinCode() {
        return pinCode;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }


    public BankCard(String validNumber, String pinCode, double balance, boolean status) {
        this.validNumber = validNumber;
        this.pinCode = pinCode;
        this.balance = balance;
        this.status = status;
    }

    public String convertToString(){
        return getValidNumber() + " " + getPinCode() + " " + getBalance() + " " + getStatus();
    }
}
