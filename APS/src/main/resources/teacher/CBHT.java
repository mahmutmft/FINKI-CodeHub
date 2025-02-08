package main.resources.teacher;

public class CBHT<K, E> {
    private SLLNode<MapEntry<K, E>>[] buckets;

    @SuppressWarnings("unchecked")
    public CBHT(int m) {
        buckets = (SLLNode<MapEntry<K, E>>[]) new SLLNode[m];
    }

    private int hash(K key) {
        return Math.abs(key.hashCode()) % buckets.length;
    }

    public SLLNode<MapEntry<K, E>> search(K targetKey) {
        int b = hash(targetKey);
        SLLNode<MapEntry<K, E>> currNode = buckets[b];
        while (currNode != null) {
            MapEntry<K, E> currEntry = currNode.element;
            if (currEntry.key.equals(targetKey)) return currNode;
            currNode = currNode.succ;
        }
        return null;
    }

    public void insert(K key, E val) {
        MapEntry<K, E> newEntry = new MapEntry<>(key, val);
        int b = hash(key);
        SLLNode<MapEntry<K, E>> currNode = buckets[b];
        while (currNode != null) {
            MapEntry<K, E> currEntry = currNode.element;
            if (currEntry.key.equals(key)) {
                currNode.element = newEntry;
                return;
            }
            currNode = currNode.succ;
        }
        buckets[b] = new SLLNode<>(newEntry, buckets[b]);
    }

    public void delete(K key) {
        int b = hash(key);
        SLLNode<MapEntry<K, E>> predNode = null, currNode = buckets[b];
        while (currNode != null) {
            MapEntry<K, E> currEntry = currNode.element;
            if (currEntry.key.equals(key)) {
                if (predNode == null) buckets[b] = currNode.succ;
                else predNode.succ = currNode.succ;
                return;
            }
            predNode = currNode;
            currNode = currNode.succ;
        }
    }

    public String toString() {
        String temp = "";
        for (int i = 0; i < buckets.length; i++) {
            temp += i + ":";
            SLLNode<MapEntry<K, E>> curr = buckets[i];
            while (curr != null) {
                temp += curr.element.toString() + " ";
                curr = curr.succ;
            }
            temp += "\n";
        }
        return temp;
    }
}
