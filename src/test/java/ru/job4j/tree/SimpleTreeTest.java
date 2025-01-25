package ru.job4j.tree;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class SimpleTreeTest {

    @Test
    void when6ElFindLastThen6() {
        Tree<Integer> tree = new SimpleTree<>(1);
        tree.add(1, 2);
        tree.add(1, 3);
        tree.add(1, 4);
        tree.add(4, 5);
        tree.add(5, 6);
        assertThat(tree.findBy(6)).isPresent();
    }

    @Test
    void whenElFindNotExistThenOptionEmpty() {
        Tree<Integer> tree = new SimpleTree<>(1);
        tree.add(1, 2);
        assertThat(tree.findBy(7)).isEmpty();
    }

    @Test
    void whenChildExistOnLeafThenNotAdd() {
        Tree<Integer> tree = new SimpleTree<>(1);
        tree.add(1, 2);
        tree.add(1, 3);
        tree.add(1, 4);
        tree.add(4, 5);
        tree.add(5, 6);
        assertThat(tree.add(2, 6)).isFalse();
    }

    @Test
    void whenRoot2AddParent1IsFalse() {
        Tree<Integer> tree = new SimpleTree<>(2);
        assertThat(tree.add(1, 2)).isFalse();
    }

    @Test
    void whenRoot2AddChild1IsTrue() {
        Tree<Integer> tree = new SimpleTree<>(2);
        assertThat(tree.add(2, 1)).isTrue();
    }

    @Test
    void whenRoot2AddChildNullIsTrue() {
        Tree<Integer> tree = new SimpleTree<>(2);
        assertThat(tree.add(2, null)).isTrue();
    }

    @Test
    void whenRoot2AddParentNullIsFalse() {
        Tree<Integer> tree = new SimpleTree<>(2);
        assertThat(tree.add(null, 1)).isFalse();
    }

    @Test
    void whenRoot2AddParentNullIsFalse111() {
        Tree<Integer> tree = new SimpleTree<>(1);
        tree.add(1, 2);
        tree.add(1, 3);
        tree.add(2, 4);
        tree.add(2, 5);
        assertThat(tree.isBinary()).isTrue();

    }

    @Test
    void whenRoot2AddParentNullIsFalse1112() {
        Tree<Integer> tree = new SimpleTree<>(1);
        tree.add(1, 2);
        tree.add(1, 3);
        tree.add(2, 4);
        tree.add(2, 5);
        tree.add(2, 6);
        assertThat(tree.isBinary()).isFalse();

    }
}