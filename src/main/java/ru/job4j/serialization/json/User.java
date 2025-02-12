package ru.job4j.serialization.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

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

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean isWorker() {
        return worker;
    }

    public void setWorker(boolean worker) {
        this.worker = worker;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public String[] getCharacteristic() {
        return characteristic;
    }

    public void setCharacteristic(String[] characteristic) {
        this.characteristic = characteristic;
    }

    @Override
    public String toString() {
        return "User{"
                + "name='" + name + '\''
                + ", surname='" + surname + '\''
                + ", age=" + age
                + ", worker=" + worker
                + ", account_id=" + account.getId()
                + ", account_balance" + account
                + ", characteristic" + Arrays.toString(characteristic)
                + '}';
    }

    public static void main(String[] args) {
        User user1 = new User("John", "Smith", 19, true, new Account(1, 5000), new String[] {"kind", "cute"});
        final Gson gson = new GsonBuilder().create();
        System.out.println(gson.toJson(user1));

        final String userJson =
                "{"
                        + "\"name\":\"Vasya\","
                        + "\"surname\":\"Petrovich\","
                        + "\"age\":35,"
                        + "\"worker\":true,"
                        + "\"account\":"
                        + "{"
                        + "\"id\":10,"
                        + "\"balance\":10000"
                        + "},"
                        + "\"characteristic\":"
                        + "[\"rude\",\"evil\"]"
                        + "}";
        final User fromJson = gson.fromJson(userJson, User.class);
        System.out.println(fromJson);
    }
}