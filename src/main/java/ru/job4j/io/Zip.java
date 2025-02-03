package ru.job4j.io;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {

    public void packFiles(List<Path> sources, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            for (Path source : sources) {
                zip.putNextEntry(new ZipEntry(source.toString()));
                try (BufferedInputStream output = new BufferedInputStream(new FileInputStream(source.toString()))) {
                    zip.write(output.readAllBytes());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void packSingleFile(File source, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            zip.putNextEntry(new ZipEntry(source.getPath()));
            try (BufferedInputStream output = new BufferedInputStream(new FileInputStream(source))) {
                zip.write(output.readAllBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String[] validateArguments(String[] args) {
        String[] arguments = new String[args.length];
        if (args.length != 3) {
            throw new IllegalArgumentException("Wrong number of arguments.");
        }
        ArgsName argsName = ArgsName.of(args);
        String directory = argsName.get("d");
        String exclude = argsName.get("e");
        String output = argsName.get("o");
        File directoryFile = new File(directory);
        if (!directoryFile.exists()) {
                throw new IllegalArgumentException("Wrong is directory.");
        }
        Pattern patternExtension = Pattern.compile("^\\..+");
        if (!patternExtension.matcher(exclude).matches()) {
            throw new IllegalArgumentException("Wrong file extension.");
        }
        Pattern patternZip = Pattern.compile(".+\\.zip$");
        if (!patternZip.matcher(output).matches()) {
            throw new IllegalArgumentException("Wrong output file.");
        }
        arguments[0] = directory;
        arguments[1] = exclude;
        arguments[2] = output;
        return arguments;
    }

    public static void main(String[] args) throws IOException {
        String[] arguments = validateArguments(args);
        String directory = arguments[0];
        String exclude = arguments[1];
        String output = arguments[2];
        Path start = Paths.get(directory);
        List<Path> sources = Search.search(start, path -> !path.toFile().getName().endsWith(exclude)).stream().toList();
        Zip zip = new Zip();
        zip.packFiles(sources, new File(output));
    }
}