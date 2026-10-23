import java.util.HashMap;
import java.util.Map;

class LFUCache {
    class Node {
        int key, val, freq;
        Node prev, next;
        Node(int key, int val) {
            this.key = key;
            this.val = val;
            this.freq = 1;
        }
    }

    class DoublyLinkedList {
        Node head,tail;
        int size;

        DoublyLinkedList() {
            head = new Node(0, 0);
            tail = new Node(0, 0);
            head.next = tail;
            tail.prev = head;
            size = 0;
        }

        void addNode(Node node) {
            // Insert right after head (most recently used in this bucket)
            node.next = head.next;
            node.prev = head;
            head.next.prev = node;
            head.next = node;
            size++;
        }

        void removeNode(Node node) {
            node.prev.next = node.next;
            node.next.prev = node.prev;
            size--;
        }

        Node removeTail() {
            // Remove least recently used (node before dummy tail)
            if (size > 0) {
                Node lru = tail.prev;
                removeNode(lru);
                return lru;
            }
            return null;
        }
    }

    private final int capacity;
    private int minFreq;
    private final Map<Integer, Node> nodeMap;
    private final Map<Integer, DoublyLinkedList> freqMap;

    public LFUCache(int capacity) {
        this.capacity = capacity;
        this.minFreq = 0;
        this.nodeMap = new HashMap<>();
        this.freqMap = new HashMap<>();
    }

    private void updateFreq(Node node) {
        int curFreq = node.freq;
        DoublyLinkedList curList = freqMap.get(curFreq);
        curList.removeNode(node);

        if (curFreq == minFreq && curList.size == 0) {
            minFreq++;
        }

        node.freq++;
        freqMap.computeIfAbsent(node.freq, k -> new DoublyLinkedList()).addNode(node);
    }

    public int get(int key) {
        Node node = nodeMap.get(key);
        if (node == null) return -1;
        updateFreq(node);
        return node.val;
    }

    public void put(int key, int value) {
        if (capacity == 0) return;

        if (nodeMap.containsKey(key)) {
            Node node = nodeMap.get(key);
            node.val = value;
            updateFreq(node);
            return;
        }

        if (nodeMap.size() >= capacity) {
            DoublyLinkedList minList = freqMap.get(minFreq);
            Node evicted = minList.removeTail();
            nodeMap.remove(evicted.key);
        }

        Node newNode = new Node(key, value);
        nodeMap.put(key, newNode);
        freqMap.computeIfAbsent(1, k -> new DoublyLinkedList()).addNode(newNode);
        minFreq = 1;
    }
}