package main.resources.teacher;

public class MapEntry<K, E> {
    public K key;  // Made public
    public E value;  // Made public

    public MapEntry(K key, E val) {
        this.key = key;
        this.value = val;
    }

    public String toString() {
        return "<" + key + "," + value + ">";
    }
}

