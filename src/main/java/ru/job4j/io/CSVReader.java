package ru.job4j.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringJoiner;

public class CSVReader {
    public static void handle(ArgsName argsName) throws Exception {
        String path = argsName.get("path");
        String delimiter = argsName.get("delimiter");
        String out = argsName.get("out");
        String filter = argsName.get("filter");

        if (!"stdout".equals(out) && !out.endsWith(".csv")) {
            throw new RuntimeException("Invalid output format");
        }

        List<String> outputLines = new ArrayList<>();
        try (var scanner = new Scanner(new FileInputStream(path))) {
            StringJoiner lineHeaders = new StringJoiner(delimiter);
            List<Integer> indexColumns = new ArrayList<>();
            String[] header = scanner.nextLine().split(delimiter);
            String[] filterColumn = filter.split(",");
            for (String column : filterColumn) {
                for (int i = 0; i < header.length; i++) {
                    if (header[i].equals(column)) {
                        indexColumns.add(i);
                        lineHeaders.add(column);
                    }
                }
            }
            outputLines.add(lineHeaders.toString());
            while (scanner.hasNextLine()) {
                StringJoiner lineOther = new StringJoiner(delimiter);
                String[] line = scanner.nextLine().split(delimiter.trim());
                for (int i : indexColumns) {
                    lineOther.add(line[i]);
                }
                outputLines.add(lineOther.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        if ("stdout".equals(out)) {
            for (String line : outputLines) {
                System.out.println(line);
            }
        }

        if (out.endsWith(".csv")) {
            try (PrintWriter output = new PrintWriter(new BufferedOutputStream(new FileOutputStream(out)))) {
                outputLines.forEach(output::println);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws Exception {
        if (args.length != 4) {
            throw new IllegalArgumentException("Invalid number of arguments.");
        }

        ArgsName argsName = ArgsName.of(args);
        try {
            handle(argsName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}