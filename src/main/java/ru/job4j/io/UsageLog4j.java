package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageLog4j {

    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        String name = "Petr Arsentev";
        int age = 33;
        byte b = 1;
        short s = 2000;
        long l = 50000L;
        float f = 105.14f;
        double d = 200.114d;
        boolean bool = true;
        char c = 'c';

        LOG.debug("User info name : {}, age : {}, byte : {}, short : {}, long : {}, float : {}, double : {}, boolean : {}, char : {}",
                name, age, b, s, l, f, d, bool, c);
    }
}