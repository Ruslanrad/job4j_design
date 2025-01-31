package ru.job4j.io;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Path;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ConfigTest {

    @Test
    void whenNotKey() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> {
                    String path = "./data/notKey.txt";
                    Config config = new Config(path);
                    config.load();
                });
        assertThat(exception).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void whenNotValue() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> {
                    String path = "./data/notValue.txt";
                    Config config = new Config(path);
                    config.load();
                });
        assertThat(exception).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void whenNotKeyAndNotValue() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> {
                    String path = "./data/notKeyNotValue.txt";
                    Config config = new Config(path);
                    config.load();
                });
        assertThat(exception).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void whenPairWithComment() {
        String path = "./data/withComment.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("key")).isEqualTo("value");
    }

    @Test
    void whenPairWithCommentAndEmptyLines() {
        String path = "./data/withCommentEmptyLines.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("key1")).isEqualTo("value1");
        assertThat(config.value("key2")).isEqualTo("value2");
    }

    @Test
    void whenPairValueWithEqually() {
        String path = "./data/valueWithEqually.txt";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("key1")).isEqualTo("value=");
    }

    @Test
    void whenPairValueWithEquallyOne() {
        String path = "./data/valueWithEquallyOne.txt";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("key1")).isEqualTo("value=1");
    }

    @Test
    void whenNotKeyWithTempDir(@TempDir Path tempDir) throws IOException {
        File source = tempDir.resolve("source.txt").toFile();
        try (PrintWriter output = new PrintWriter(source)) {
            output.println("=value");
        }
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> {
                    Config config = new Config(source.getAbsolutePath());
                    config.load();
                });
        assertThat(exception).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void whenNotValueWithTempDir(@TempDir Path tempDir) throws IOException {
        File source = tempDir.resolve("source.txt").toFile();
        try (PrintWriter output = new PrintWriter(source)) {
            output.println("value=");
        }
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> {
                    String path = source.getAbsolutePath();
                    Config config = new Config(path);
                    config.load();
                });
        assertThat(exception).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void whenNotKeyAndNotValueWithTempDir(@TempDir Path tempDir) throws IOException {
        File source = tempDir.resolve("source.txt").toFile();
        try (PrintWriter output = new PrintWriter(source)) {
            output.println("=");
        }
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> {
                    String path = source.getAbsolutePath();
                    Config config = new Config(path);
                    config.load();
                });
        assertThat(exception).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void whenPairWithCommentWithTempDir(@TempDir Path tempDir) throws IOException {
        File source = tempDir.resolve("source.txt").toFile();
        try (PrintWriter output = new PrintWriter(source)) {
            output.println("#Comment");
            output.println("java=the_best");
        }
        Config config = new Config(source.getAbsolutePath());
        config.load();
        assertThat(config.value("java")).isEqualTo("the_best");
    }

    @Test
    void whenPairWithCommentAndEmptyLinesWithTempDir(@TempDir Path tempDir) throws IOException {
        File source = tempDir.resolve("source.txt").toFile();
        try (PrintWriter output = new PrintWriter(source)) {
            output.println("#Comment");
            output.println();
            output.println();
            output.println("java=the_best");
            output.println();
            output.println("php=bad");
        }
        Config config = new Config(source.getAbsolutePath());
        config.load();
        assertThat(config.value("java")).isEqualTo("the_best");
        assertThat(config.value("php")).isEqualTo("bad");
    }

    @Test
    void whenPairValueWithEquallyWithTempDir(@TempDir Path tempDir) throws IOException {
        File source = tempDir.resolve("source.txt").toFile();
        try (PrintWriter output = new PrintWriter(source)) {
            output.println("java=the=");
        }
        Config config = new Config(source.getAbsolutePath());
        config.load();
        assertThat(config.value("java")).isEqualTo("the=");
    }

    @Test
    void whenPairValueWithEquallyOneWithTempDir(@TempDir Path tempDir) throws IOException {
        File source = tempDir.resolve("source.txt").toFile();
        try (PrintWriter output = new PrintWriter(source)) {
            output.println("java=the=1");
        }
        Config config = new Config(source.getAbsolutePath());
        config.load();
        assertThat(config.value("java")).isEqualTo("the=1");
    }
}