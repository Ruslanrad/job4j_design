package ru.job4j.map;

import java.util.*;

public class NonCollisionMap<K, V> implements SimpleMap<K, V> {
    private static final float LOAD_FACTOR = 0.75f;

    private int capacity = 8;
    private int count = 0;
    private int modCount = 0;
    private MapEntry<K, V>[] table = new MapEntry[capacity];

    @Override
    public boolean put(K key, V value) {
        if (count >= capacity * LOAD_FACTOR) {
            expand();
        }
        int i = indexForKey(key);
        boolean result = table[i] == null;
        if (result) {
            table[i] = new MapEntry<>(key, value);
            count++;
            modCount++;
        }
        return result;
    }

    @Override
    public V get(K key) {
        V result = null;
        int i = indexForKey(key);
        if (checkKey(i, key)) {
            result = table[i].value;
        }
        return result;
    }

    @Override
    public boolean remove(K key) {
        int i = indexForKey(key);
        boolean result = checkKey(i, key);
        if (result) {
            table[i] = null;
            count--;
            modCount++;
        }
        return result;
    }

    @Override
    public Iterator<K> iterator() {
        return new Iterator<K>() {
            private int expectedModCount = modCount;
            private int currentIndex = 0;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                while (currentIndex < capacity && table[currentIndex] == null) {
                    currentIndex++;
                }
                return currentIndex < capacity;
            }

            @Override
            public K next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return table[currentIndex++].key;
            }
        };
    }

    private int hash(int hashCode) {
        return hashCode ^ (hashCode >>> 16);
    }

    private int indexFor(int hash) {
        return hash % capacity;
    }

    private int indexForKey(K key) {
        return indexFor(hash(Objects.hashCode(key)));
    }

    private void expand() {
        capacity *= 2;
        MapEntry<K, V>[] newTable = new MapEntry[capacity];
        for (int i = 0; i < table.length; i++) {
            if (table[i] != null) {
                newTable[indexFor(hash(Objects.hashCode(table[i].key)))] = new MapEntry<>(table[i].key, table[i].value);
            }
        }
        table = newTable;
    }

    private boolean checkKey(int i, K key) {
        boolean result = false;
        if (table[i] != null
                && Objects.hashCode(table[i].key) == Objects.hashCode(key)
                && Objects.equals(table[i].key, key)) {
            result = true;
        }
        return result;
    }

    private static class MapEntry<K, V> {
        K key;
        V value;

        public MapEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
}
