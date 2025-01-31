package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class DuplicatesFinder {
    public static void main(String[] args) throws IOException {
        DuplicatesVisitor visitor = new DuplicatesVisitor();
        Files.walkFileTree(Path.of("./"), visitor);
        Map<FileProperty, Path> visitorMap = new HashMap<>();
        Set<Path> duplicates = new TreeSet<>();
        for (Path path : visitor.getDuplicates()) {
            FileProperty file = new FileProperty(path.toFile().length(), path.getFileName().toString());
            if (visitorMap.containsKey(file)) {
                duplicates.add(visitorMap.get(file));
                duplicates.add(path);
                continue;
            }
            visitorMap.put(file, path);
        }
        duplicates.forEach(System.out::println);
    }
}