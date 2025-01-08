package ru.job4j.assertj;

import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class NameLoadTest {
    @Test
    void checkEmpty() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(nameLoad::getMap)
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("no data");
    }

    @Test
    void checkNamesLengthIsEmpty() {
        String[] names = {};
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(() -> nameLoad.parse(names))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Names array is empty");
    }

    @Test
    void checkNamesIsNotSymbol() {
        String[] names = {"John", "Jack", "Jane"};
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(() -> nameLoad.parse(names))
        .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("this name: " + names[0] + " does not contain the symbol '='");
    }

    @Test
    void checkNamesIsSymbolStart() {
        String[] names = {"=John", "Jack", "Jane"};
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(() -> nameLoad.parse(names))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("this name: " + names[0] + " does not contain a key");
    }

    @Test
    void checkNamesIsSymbolEnd() {
        String[] names = {"John=", "Jack", "Jane"};
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(() -> nameLoad.parse(names))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("this name: " + names[0] + " does not contain a value");
    }

    @Test
    void checkGetMapIsEmpty() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(nameLoad::getMap)
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("collection contains no data");
    }

    @Test
    void checkNameLoad() {
        String[] names = {"11=John ", " 2=Jack", "30=Jane"};
        NameLoad nameLoad = new NameLoad();
        nameLoad.parse(names);
        Map<String, String> result = nameLoad.getMap();
        Map<String, String> expected = Map.of(
                "11", "John",
                "2", "Jack",
                "30", "Jane"
        );
        assertThat(result).isEqualTo(expected);
    }
}