class MyCircularQueue {
    private final int[] queue;
    private int head;
    private int count;
    private final int capacity;

    public MyCircularQueue(int k) {
        this.capacity = k;
        this.queue = new int[k];
        this.head = 0;
        this.count = 0;
    }

    public boolean enQueue(int value) {
        if (isFull()) {
            return false;
        }
        int tail = (head + count) % capacity;
        queue[tail] = value;
        count++;
        return true;
    }

    public boolean deQueue() {
        if (isEmpty()) {
            return false;
        }
        head = (head + 1) % capacity;
        count--;
        return true;
    }

    public int Front() {
        if (isEmpty()) {
            return -1;
        }
        return queue[head];
    }

    public int Rear() {
        if (isEmpty()) {
            return -1;
        }
        int rearIdx = (head + count - 1) % capacity;
        return queue[rearIdx];
    }

    public boolean isEmpty() {
        return count == 0;
    }

    public boolean isFull() {
        return count == capacity;
    }
}