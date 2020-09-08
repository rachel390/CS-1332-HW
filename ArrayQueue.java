/**
 * Your implementation of an ArrayQueue.
 *
 * @author Rachel Mills
 * @version 1.0
 * @userid YOUR USER ID HERE rmills30
 * @GTID YOUR GT ID HERE 903394578
 *
 * Collaborators: none
 *
 * Resources: used Tahlee Jaynes JUnits from Piazza
 */
public class ArrayQueue<T> {

    /*
     * The initial capacity of the ArrayQueue.
     *
     * DO NOT MODIFY THIS VARIABLE.
     */
    public static final int INITIAL_CAPACITY = 9;

    /*
     * Do not add new instance variables or modify existing ones.
     */
    private T[] backingArray;
    private int front;
    private int size;

    /**
     * Constructs a new ArrayQueue with a backing array with capacity
     * INITIAL_CAPACITY.
     */
    public ArrayQueue() {
        front = size = 0;
        backingArray = (T[]) new Object[INITIAL_CAPACITY];
    }

    /**
     * Adds the data to the back of the queue.
     * Remember that the queue MUST behave circularly.
     *
     * If sufficient space is not available in the backing array, resize it to
     * double the current length. When resizing, copy elements to the
     * beginning of the new array. Do not forget to reset front to index 0 of
     * the new array.
     *
     * Must be amortized O(1).
     *
     * @param data the data to add to the back of the queue
     * @throws java.lang.IllegalArgumentException if data is null
     */
    public void enqueue(T data) {
        if (data == null) {
            throw new java.lang.IllegalArgumentException("Data must not be null.");
        }
        if (backingArray.length == size) {
            T[] temp = (T[]) new Object[backingArray.length * 2];
            for (int i = 0; i < size; i++) {
                temp[i] = backingArray[front];
                front = (front + 1) % backingArray.length;
            }
            backingArray = temp;
            front = 0;
        }
        backingArray[((front + size) % backingArray.length)] = data;
        size++;
    }

    /**
     * Removes and returns the data from the front of the queue.
     *
     * Do not shrink the backing array.
     *
     * Replace any spots that you dequeue from with null.
     *
     * If the queue becomes empty as a result of this call, do not reset
     * front to 0.
     *
     * Must be O(1).
     *
     * @return the data formerly located at the front of the queue
     * @throws java.util.NoSuchElementException if the queue is empty
     */
    public T dequeue() {
        if (size == 0) {
            throw new java.util.NoSuchElementException("The Queue is empty.");
        }
        T item = backingArray[front % backingArray.length];
        backingArray[front] = null;
        front = (front + 1) % backingArray.length;
        size--;
        return item;
    }

    /**
     * Returns the data from the front of the queue without removing it.
     *
     * Must be O(1).
     *
     * @return the data located at the front of the queue
     * @throws java.util.NoSuchElementException if the queue is empty
     */
    public T peek() {
        if (size == 0) {
            throw new java.util.NoSuchElementException("The queue is empty.");
        }
        return backingArray[front % backingArray.length];
    }

    /**
     * Returns the backing array of the queue.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return the backing array of the queue
     */
    public T[] getBackingArray() {
        // DO NOT MODIFY THIS METHOD!
        return backingArray;
    }

    /**
     * Returns the size of the queue.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return the size of the queue
     */
    public int size() {
        // DO NOT MODIFY THIS METHOD!
        return size;
    }
}
