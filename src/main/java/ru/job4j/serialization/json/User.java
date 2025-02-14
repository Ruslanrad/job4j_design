package ru.job4j.serialization.json;

import java.util.Arrays;

public class User {
    private String name;
    private String surname;
    private int age;
    private boolean worker;
    private Account account;
    private String[] characteristic;

    public User(String name, String surname, int age, boolean worker, Account account, String[] characteristic) {
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.worker = worker;
        this.account = account;
        this.characteristic = characteristic;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public int getAge() {
        return age;
    }

    public boolean getWorker() {
        return worker;
    }

    public Account getAccount() {
        return account;
    }

    public String[] getCharacteristic() {
        return characteristic;
    }

    @Override
    public String toString() {
        return "User{"
                + "name='" + name + '\''
                + ", surname='" + surname + '\''
                + ", age=" + age
                + ", worker=" + worker
                + ", account_balance" + account
                + ", characteristic" + Arrays.toString(characteristic)
                + '}';
    }
}