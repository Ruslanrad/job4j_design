package ru.job4j.io;

import java.io.FileInputStream;
import java.io.IOException;

public class EvenNumberFile {
    public static void main(String[] args) {
        try (FileInputStream input = new FileInputStream("data/even.txt")){
            StringBuilder text = new StringBuilder();
            int read;
            while ((read = input.read()) != -1) {
                text.append((char) read);
            }
            int result;
            String[] lines = text.toString().split(System.lineSeparator());
            for (String line : lines) {
                result = Integer.parseInt(line);
                if (result % 2 == 0) {
                    System.out.println(result + " - even");
                }else {
                    System.out.println(result + " - notEven");
                }
            }
        } catch (IOException e){
            e.printStackTrace();
        }
    }

}
