package ru.job4j.io;

import org.junit.jupiter.api.Test;

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
}