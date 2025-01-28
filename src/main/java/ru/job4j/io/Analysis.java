package ru.job4j.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Analysis {
    public void unavailable(String source, String target) {
        List<String> list = new ArrayList<String>();
        boolean work = true;
        try (BufferedReader reader = new BufferedReader(new FileReader(source))) {
            reader.lines().forEach(list::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try (PrintWriter output = new PrintWriter(
                new BufferedOutputStream(
                        new FileOutputStream(target)
                ))) {
            String regex = "^\\d+\\s";
            Pattern pattern = Pattern.compile(regex);
            for (String line : list) {
                if (work && (line.contains("400") || line.contains("500"))) {
                    work = false;
                    Matcher matcher = pattern.matcher(line);
                    output.print(matcher.replaceFirst(""));
                }
                if (!work && (line.contains("200") || line.contains("300"))) {
                    work = true;
                    Matcher matcher = pattern.matcher(line);
                    output.println(" ; " + matcher.replaceFirst(""));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Analysis analysis = new Analysis();
        analysis.unavailable("data/server1.log", "data/target1.csv");
        analysis.unavailable("data/server2.log", "data/target2.csv");

    }
}