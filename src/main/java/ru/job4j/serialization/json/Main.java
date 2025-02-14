package ru.job4j.serialization.json;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        JSONObject jsonAccount = new JSONObject("{\"id\":2,\"balance\":5000}");

        List<String> characteristic = new ArrayList<>();
        characteristic.add("Fun");
        characteristic.add("Smile");
        JSONArray jsonArray = new JSONArray(characteristic);

        final User user = new User("Petr", "Petrovich", 35, true, new Account(1, 3000), new String[]{"Cute", "Evil"});

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", user.getName());
        jsonObject.put("surname", user.getSurname());
        jsonObject.put("age", user.getAge());
        jsonObject.put("worker", user.getWorker());
        jsonObject.put("account", jsonAccount);
        jsonObject.put("characteristic", jsonArray);

        System.out.println(jsonObject.toString());

        System.out.println(new JSONObject(user).toString());
    }
}
