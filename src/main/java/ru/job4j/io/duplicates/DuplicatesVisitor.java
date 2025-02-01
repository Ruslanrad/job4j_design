package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {
    private List<Path> paths = new ArrayList<>();
    private Map<FileProperty, Path> visitorMap = new HashMap<>();
    private Set<Path> duplicates = new TreeSet<>();

    @Override
    public FileVisitResult visitFile(Path file,
                                     BasicFileAttributes attributes) throws IOException {

        paths.add(file);
        return super.visitFile(file, attributes);
    }

    public Set<Path> getDuplicates() {
        for (Path path : paths) {
            FileProperty file = new FileProperty(path.toFile().length(), path.getFileName().toString());
            if (visitorMap.containsKey(file)) {
                duplicates.add(visitorMap.get(file));
                duplicates.add(path);
                continue;
            }
            visitorMap.put(file, path);
        }
        return duplicates;
    }
}