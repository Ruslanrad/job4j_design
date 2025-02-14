package ru.job4j.serialization.json;

public class Account {
    private int id;
    private int balance;

    public Account(int id, int balance) {
        this.id = id;
        this.balance = balance;
    }

    public int getId() {
        return id;
    }

    public int getBalance() {
        return balance;
    }

    @Override
    public String toString() {
        return "Account{"
                + "id=" + id
                + ", balance=" + balance
                + '}';
    }
}
