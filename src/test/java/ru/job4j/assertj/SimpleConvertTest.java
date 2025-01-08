package ru.job4j.assertj;

import org.assertj.core.data.Index;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.assertj.core.api.Assertions.*;

class SimpleConvertTest {
    @Test
    void checkArray() {
        SimpleConvert simpleConvert = new SimpleConvert();
        String[] array = simpleConvert.toArray("first", "second", "three", "four", "five");
        assertThat(array).hasSize(5)
                .contains("second")
                .contains("first", Index.atIndex(0))
                .containsAnyOf("zero", "second", "six")
                .doesNotContain("first", Index.atIndex(1));
    }

    @Test
    void checkList() {
        SimpleConvert simpleConvert = new SimpleConvert();
        List<String> array = simpleConvert.toList("first", "second", "three", "four", "five");
        assertThat(array).hasSize(5)
                .isNotEmpty()
                .hasSizeGreaterThan(1)
                .hasSizeGreaterThanOrEqualTo(5)
                .hasSizeBetween(1, 10)
                .contains("second")
                .contains("three", "four")
                .containsOnly("second", "first",  "five", "three", "four")
                .containsExactly("first", "second", "three", "four", "five")
                .containsAnyOf("zero", "six", "three")
                .doesNotContain("nine")
                .startsWith("first", "second")
                .endsWith("four", "five")
                .containsSequence("second", "three")
                .contains("first", Index.atIndex(0))
                .containsAnyOf("zero", "second", "six")
                .doesNotContain("first", Index.atIndex(1));
    }

    @Test
    void checkSet() {
        SimpleConvert simpleConvert = new SimpleConvert();
        Set<String> array = simpleConvert.toSet("first", "second", "three", "four", "five");
        assertThat(array).hasSize(5)
                .isNotEmpty()
                .containsOnly("first", "second", "three", "four", "five")
                .contains("second")
                .containsAnyOf("zero", "second", "six")
                .doesNotContain("nine");
    }

    @Test
    void checkMap() {
        SimpleConvert simpleConvert = new SimpleConvert();
        Map<String, Integer> array = simpleConvert.toMap("first", "second", "three", "four", "five");
        assertThat(array).hasSize(5)
                .isNotEmpty()
                .containsKey("second")
                .containsValue(1)
                .containsOnlyKeys("first", "second", "three", "four", "five")
                .doesNotContainKey("nine")
                .containsEntry("second", 1)
                .containsEntry("four", 3)
                .doesNotContainEntry("first", 5);
    }
}