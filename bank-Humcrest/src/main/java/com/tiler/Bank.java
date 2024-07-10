package com.tiler;

import java.util.HashMap;
import java.util.Map;

public class Bank {
    private Map<String, String> users;
    private double balance;

    public Bank() {
        users = new HashMap<>();
        users.put("user1", "password1");
        users.put("user2", "password2");
        balance = 1000.0;
    }

    public boolean authenticate(String username, String password) {
        if (users.containsKey(username) && users.get(username).equals(password)) {
            return true;
        }
        return false;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        balance += amount;
    }

    public boolean withdraw(double amount) {
        if (amount > balance) {
            return false;
        }
        balance -= amount;
        return true;
    }

    public boolean transfer(Bank toBank, double amount) {
        if (this.getBalance() >= amount) {
            this.withdraw(amount);
            toBank.deposit(amount);
            return true;
        } else {
            return false;
        }
    }
}
