package ru.job4j.io;

import java.io.File;

public class Dir {
    public static void main(String[] args) {
        File file = new File("c:\\projects");
        if (!file.exists()) {
            throw new IllegalArgumentException(String.format("Директория не существует: %s", file.getAbsoluteFile()));
        }
        if (!file.isDirectory()) {
            throw new IllegalArgumentException(String.format("Это не директория: %s", file.getAbsoluteFile()));
        }
        for (File subfile : file.listFiles()) {
            if (subfile.isFile()) {
                System.out.println("Название файла - " + subfile.getName() + ", размер = " + subfile.length());
            }
        }
    }
}
