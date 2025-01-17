package ru.job4j.iterator;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.*;
import java.util.function.Predicate;

class ListUtilsTest {

    private List<Integer> input;

    @BeforeEach
    void setUp() {
        input = new ArrayList<>(Arrays.asList(1, 3));
    }

    @Test
    void whenAddBefore() {
        ListUtils.addBefore(input, 1, 2);
        assertThat(input).hasSize(3).containsSequence(1, 2, 3);
    }

    @Test
    void whenAddBeforeFirstIndex() {
        ListUtils.addBefore(input, 0, 0);
        assertThat(input).hasSize(3).containsSequence(0, 1, 3);
    }

    @Test
    void whenAddBeforeWithInvalidIndex() {
        assertThatThrownBy(() -> ListUtils.addBefore(input, 3, 2))
                .isInstanceOf(IndexOutOfBoundsException.class);
    }

    @Test
    void whenAddAfter() {
        ListUtils.addAfter(input, 0, 2);
        assertThat(input).hasSize(3).containsSequence(1, 2, 3);
    }

    @Test
    void whenAddAfterLastIndex() {
        ListUtils.addAfter(input, 1, 4);
        assertThat(input).hasSize(3).containsSequence(1, 3, 4);
    }

    @Test
    void whenAddAfterFirstIndex() {
        ListUtils.addAfter(input, 0, 2);
        assertThat(input).hasSize(3).containsSequence(1, 2, 3);
    }

    @Test
    void whenRemoveIfPredicateMoreOne() {
        Predicate<Integer> filter = i -> i > 1;
        ListUtils.removeIf(input, filter);
        assertThat(input).hasSize(1).containsSequence(1);
    }

    @Test
    void whenRemoveIfPredicateLessThree() {
        Predicate<Integer> filter = i -> i < 3;
        ListUtils.removeIf(input, filter);
        assertThat(input).hasSize(1).containsSequence(3);
    }

    @Test
    void whenReplaceIfPredicateLessThree() {
        Predicate<Integer> filter = i -> i < 3;
        ListUtils.replaceIf(input, filter, 5);
        assertThat(input).hasSize(2).containsSequence(5, 3);
    }

    @Test
    void whenReplaceIfPredicateMoreOne() {
        Predicate<Integer> filter = i -> i > 1;
        ListUtils.replaceIf(input, filter, 5);
        assertThat(input).hasSize(2).containsSequence(1, 5);
    }

    @Test
    void  whenRemoveAllListOneThoThree() {
        List<Integer> remove = new ArrayList<>(Arrays.asList(1, 2, 3));
        ListUtils.removeAll(input, remove);
        assertThat(input).isEmpty();
    }

    @Test
    void  whenRemoveAllListThoThree() {
        List<Integer> remove = new ArrayList<>(Arrays.asList(2, 3));
        ListUtils.removeAll(input, remove);
        assertThat(input).hasSize(1).containsSequence(1);
    }
}