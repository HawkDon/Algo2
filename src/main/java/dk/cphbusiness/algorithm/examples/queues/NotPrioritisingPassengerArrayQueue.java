package dk.cphbusiness.algorithm.examples.queues;

import dk.cphbusiness.airport.template.Passenger;

import java.util.ArrayList;
import java.util.NoSuchElementException;

/**
 * An implementation of a queue using a fixed, non-expandable array whose
 * capacity is set in its constructor.
 *
 * @author mbeg
 */
public class NotPrioritisingPassengerArrayQueue implements PriorityQueue<Passenger> {
    private Passenger[] items;
    private int size = 0;
    private int head = 0; // index of the current front item, if one exists
    private int tail = 0; // index of next item to be added

    public NotPrioritisingPassengerArrayQueue(int capacity) {
        //items = (T[])new Object[capacity];
        items = new Passenger[capacity];
    }

    public void enqueue(Passenger item) {
        if (size == items.length)
            throw new IllegalStateException("Cannot add to full queue");
        items[tail] = item;
        tail = (tail + 1) % items.length;
        size++;
        this.items = mergeSort(items);
    }

    public Passenger dequeue() {
        if (size == 0)
            throw new NoSuchElementException("Cannot remove from empty queue");
        Passenger item = items[head];
        head = (head + 1) % items.length;
        size--;
        return item;
    }

    public Passenger peek() {
        if (size == 0)
            throw new NoSuchElementException("Cannot peek into empty queue");
        return items[head];
    }

    public int size() {
        return size;
    }

    public static Passenger[] mergeSort(Passenger[] array) {
        if (array.length <= 1) { // Already sorted
            return array;
        }

        int midpoint = array.length / 2; // get the mid point

        Passenger[] left = new Passenger[midpoint]; // create an empty array with 0 to midpoint
        Passenger[] right;

        if (array.length % 2 == 0) { // is even?
            right = new Passenger[midpoint]; // yes take from mid point
        } else {
            right = new Passenger[midpoint + 1]; // its not even so right is one bigger in size
        }

        for (int i = 0; i < midpoint; i++) { // Fill the left array with all array values
            left[i] = array[i];
        }

        for (int j = 0; j < right.length; j++) { // Fill the right array from mid point to end
            right[j] = array[midpoint + j];
        }

        left = mergeSort(left); // recurse
        right = mergeSort(right); // recurse

        return merge(left, right);
    }

    public static Passenger[] merge(Passenger[] left, Passenger[] right) {
        Passenger[] result = new Passenger[left.length + right.length];

        int leftPointer, rightPointer, resultPointer;
        leftPointer = rightPointer = resultPointer = 0;

        while (leftPointer < left.length || rightPointer < right.length) {

            if (leftPointer < left.length && rightPointer < right.length) {

                if (left[leftPointer] == null && right[rightPointer] == null) {
                    result[resultPointer++] = left[leftPointer++];
                } else if (left[leftPointer] != null && right[rightPointer] == null) {
                    result[resultPointer++] = left[leftPointer++];
                } else if (left[leftPointer] == null && right[rightPointer] != null) {
                    result[resultPointer++] = right[rightPointer++];
                } else {
                    if (left[leftPointer].compareTo(right[rightPointer]) < 0) {

                        result[resultPointer++] = left[leftPointer++];

                    } else {

                        result[resultPointer++] = right[rightPointer++];

                    }
                }
            } else if (leftPointer < left.length) {

                result[resultPointer++] = left[leftPointer++];

            } else {
                result[resultPointer++] = right[rightPointer++];
            }
        }
        return result;
    }
}