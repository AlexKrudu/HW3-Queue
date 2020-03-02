package queue;

import java.util.Arrays;
// Inv: size >= 0 && ∀ i = 0..size-1 queue[i] != null

public class ArrayQueueADT {
    private int head;
    private int tail;
    private Object[] elements = new Object[5];

    // Pre: elem != null
    public static void enqueue(ArrayQueueADT queue, Object elem) {
        assert elem != null;
        ensureCapacity(queue, size(queue) + 1);
        queue.elements[queue.tail] = elem;
        queue.tail = (queue.tail + 1) % queue.elements.length;
    }
    // Post: size = size' + 1 && ∀ i = 0..size'-1 queue[i] = queue[i]' && queue[size - 1] = elem


    private static void ensureCapacity(ArrayQueueADT queue, int capacity) {
        if (capacity < queue.elements.length) {
            return;
        }
        Object[] newElements = new Object[2 * capacity];
        for (int i = 0; i < size(queue); i++) {
            newElements[i] = queue.elements[(queue.head + i) % queue.elements.length];
        }
        queue.tail = size(queue);
        queue.head = 0;
        queue.elements = newElements;

    }
    // Post: if capacity > size': size = 2 * capacity && queue is immutable


    public static String toStr(ArrayQueueADT queue) {
        StringBuilder res = new StringBuilder("[");
        for (int i = 0; i < size(queue); i++) {
            res.append(String.valueOf(queue.elements[(i + queue.head) % queue.elements.length]));
            if (i != size(queue) - 1) {
                res.append(", ");
            }
        }
        res.append("]");
        return res.toString();
    }
    // Post: res = '['queue[0]', queue[1], .., [queue[size - 1]]' && type(res) = String && queue is immutable


    public static Object[] toArray(ArrayQueueADT queue) {
        Object[] res = new Object[size(queue)];
        for (int i = 0; i < size(queue); i++) {
            res[i] = queue.elements[(queue.head + i) % queue.elements.length];
        }
        return res;
    }
    // Post: res = queue[0..size - 1] && type(res) = array && queue is immutable


    // Pre: size > 0
    public static Object dequeue(ArrayQueueADT queue) {
        if (isEmpty(queue)) {
            return null;
        }
        Object res = queue.elements[queue.head];
        queue.elements[queue.head] = null;
        queue.head = (queue.head + 1) % queue.elements.length;
        return res;
    }
    // Post: res = queue[0] && size = size' - 1 && ∀ i = 1..size queue[i] = queue[i]'


    public static boolean isEmpty(ArrayQueueADT queue) {
        return queue.head == queue.tail;
    }
    // Post: Res = size > 0? && queue is immutable


    public static void clear(ArrayQueueADT queue) {
        Arrays.fill(queue.elements, null);
        queue.head = 0;
        queue.tail = 0;
    }
    // Post: queue is empty


    // Pre: size > 0
    public static Object element(ArrayQueueADT queue) {
        if (isEmpty(queue)) {
            return null;
        }
        return queue.elements[queue.head];
    }
    // Post: res = queue[0] && queue is immutable


    public static int size(ArrayQueueADT queue) {
        if (queue.head > queue.tail) {
            return queue.elements.length - queue.head + queue.tail;
        }
        return queue.tail - queue.head;
    }
    // Post: res = size && queue is immutable

}
