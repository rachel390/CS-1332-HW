import java.util.NoSuchElementException;

/**
 * Your implementation of a non-circular SinglyLinkedList with a tail pointer.
 *I used the textbook, the student test JUnits, the JUnits created by Alon Baruch,
 * and the JUnits created by Tahlee Jaynes.
 * I also received help from the TAs on Piazza.
 * @author Rachel Mills
 * @version 1.0
 * @userid rmills30
 * @GTID 903394578
 */
public class SinglyLinkedList<T> {

    // Do not add new instance variables or modify existing ones.
    private SinglyLinkedListNode<T> head;
    private SinglyLinkedListNode<T> tail;
    private int size;

    // Do not add a constructor.

    /**
     * Adds the element to the specified index.
     *
     * Must be O(1) for indices 0 and size and O(n) for all other cases.
     *
     * @param index the index to add the new element
     * @param data  the data to add
     * @throws java.lang.IndexOutOfBoundsException if index < 0 or index > size
     * @throws java.lang.IllegalArgumentException  if data is null
     */
    public void addAtIndex(int index, T data) {
        if ((index < 0) || (index > size)) {
            throw new IndexOutOfBoundsException("The data cannot be added because the given index is out of range.");
        } else if (data == null) {
            throw new IllegalArgumentException("Cannot add null data into Singly Linked List.");
        } else {
            SinglyLinkedListNode<T> tempNode = head;
            SinglyLinkedListNode<T> newNode = new SinglyLinkedListNode<>(data);
            if (index == 0) {
                addToFront(data);
            } else if (index == size) {
                addToBack(data);
            } else {
                size += 1;
                for (int i = 0; i < index - 1; i++) {
                    tempNode = tempNode.getNext();
                }
                SinglyLinkedListNode<T> nextOne = tempNode.getNext();
                tempNode.setNext(newNode);
                newNode.setNext(nextOne);
            }
        }

    }

    /**
     * Adds the element to the front of the list.
     *
     * Must be O(1).
     *
     * @param data the data to add to the front of the list
     * @throws java.lang.IllegalArgumentException if data is null
     */
    public void addToFront(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Cannot add null data into Singly Linked List.");
        } else {
            SinglyLinkedListNode<T> newNode = new SinglyLinkedListNode<>(data);
            newNode.setNext(head);
            head = newNode;
            size += 1;
            if (size == 1) {
                tail = newNode;
            }
        }

    }

    /**
     * Adds the element to the back of the list.
     *
     * Must be O(1).
     *
     * @param data the data to add to the back of the list
     * @throws java.lang.IllegalArgumentException if data is null
     */
    public void addToBack(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Cannot add null data into Singly Linked List.");
        } else {
            SinglyLinkedListNode<T> newNode = new SinglyLinkedListNode<>(data);
            if (size == 0) {
                head = newNode;
                tail = head;
            } else {
                tail.setNext(newNode);
                tail = newNode;
            }
            size += 1;
        }

    }

    /**
     * Removes and returns the element at the specified index.
     *
     * Must be O(1) for indices 0 and size - 1 and O(n) for all other
     * cases.
     *
     * @param index the index of the element to remove
     * @return the data that was removed
     * @throws java.lang.IndexOutOfBoundsException if index < 0 or index >= size
     */
    public T removeAtIndex(int index) {
        if ((index < 0) || (index >= size) || (isEmpty())) {
            throw new IndexOutOfBoundsException("The data cannot be added because the given index is out of range.");
        } else if (index == 0) {
            return removeFromFront();
        } else if (index == size - 1) {
            return removeFromBack();
        } else {
            SinglyLinkedListNode<T> tempNode = head;
            //finds node previous to the one being deleted
            for (int i = 0; i < index - 1; i++) {
                tempNode = tempNode.getNext();
            }
            SinglyLinkedListNode<T> nextNode = tempNode.getNext();
            T removed = nextNode.getData();
            nextNode = nextNode.getNext();
            tempNode.setNext(nextNode);
            size -= 1;
            return removed;

        }

    }

    /**
     * Removes and returns the first data of the list.
     *
     * Must be O(1).
     *
     * @return the data formerly located at the front of the list
     * @throws java.util.NoSuchElementException if the list is empty
     */
    public T removeFromFront() {
        if (isEmpty()) {
            throw new NoSuchElementException("Head cannot be removed because the List is empty.");
        } else {
            T removed = head.getData();
            if (size == 1) {
                clear();
                return removed;
            } else {
                head = head.getNext();
                size -= 1;
                return removed;

            }
        }
    }

    /**
     * Removes and returns the last data of the list.
     *
     * Must be O(1). --> typo should be O(n)
     *
     * @return the data formerly located at the back of the list
     * @throws java.util.NoSuchElementException if the list is empty
     */
    public T removeFromBack() {
        if (isEmpty()) {
            throw new NoSuchElementException("Tail cannot be removed because the List is empty.");
        }
        T removed = tail.getData();
        if (size == 1) {
            size = 0;
            head = null;
            tail = null;
            return removed;
        } else {
            SinglyLinkedListNode<T> newTail = head;
            for (int i = 0; i < size - 2; i++) {
                newTail = newTail.getNext();
            }
            tail = newTail;
            tail.setNext(null);
            size -= 1;
            return removed;
        }


    }

    /**
     * Returns the element at the specified index.
     *
     * Must be O(1) for indices 0 and size - 1 and O(n) for all other cases.
     *
     * @param index the index of the element to get
     * @return the data stored at the index in the list
     * @throws java.lang.IndexOutOfBoundsException if index < 0 or index >= size
     */
    public T get(int index) {
        if ((index < 0) || (index >= size) || (isEmpty())) {
            throw new IndexOutOfBoundsException("The data cannot be added because the given index is out of range.");
        } else if (index == 0) {
            return head.getData();
        // idk if minus 1 or naw
        } else if (index == size - 1) {
            return tail.getData();
        } else {
            SinglyLinkedListNode<T> tempNode = head;
            for (int i = 0; i < index; i++) {
                tempNode = tempNode.getNext();
            } return tempNode.getData();
        }

    }

    /**
     * Returns whether or not the list is empty.
     *
     * Must be O(1).
     *
     * @return true if empty, false otherwise
     */
    public boolean isEmpty() {
        return head == null;
    }

    /**
     * Clears the list.
     *
     * Clears all data and resets the size.
     *
     * Must be O(1).
     */
    public void clear() {
        head = null;
        tail = null;
        size = 0;
    }

    /**
     * Removes and returns the last copy of the given data from the list.
     *
     * Do not return the same data that was passed in. Return the data that
     * was stored in the list.
     *
     * Must be O(n).
     *
     * @param data the data to be removed from the list
     * @return the data that was removed
     * @throws java.lang.IllegalArgumentException if data is null
     * @throws java.util.NoSuchElementException   if data is not found
     */
    public T removeLastOccurrence(T data) {
        SinglyLinkedListNode<T> searchNode = head;
        SinglyLinkedListNode<T> rightBefore = null;
        SinglyLinkedListNode<T> lastFound = null;
        T removed;
        if (data == null) {
            throw new IllegalArgumentException("Cannot add null data into Singly Linked List.");
        } else {
            for (int i = 0; i < size; i++) {
                if (i != size - 1) {
                    if (((searchNode.getNext()).getData()).equals(data)) {
                        rightBefore = searchNode;
                    }
                }
                if ((searchNode.getData()).equals(data)) {
                    lastFound = searchNode;
                }
                searchNode = searchNode.getNext();
            }
            if (lastFound == null || isEmpty()) {
                throw new NoSuchElementException("The data was not found.");
            } else if (lastFound.equals(head)) {
                return removeFromFront();
            } else if (lastFound.equals(tail)) {
                return removeFromBack();
            } else {
                removed = lastFound.getData();
                rightBefore.setNext(lastFound.getNext());
                size -= 1;
                return removed;
            }
        }
    }

    /*** Returns an array representation of the linked list.
    * Must be O(n) for all cases.
    *
    * @return the array of length size holding all of the data (not the
    * nodes) in the list in the same order
    */
    @SuppressWarnings("unchecked")
    public T[] toArray() {
        T[] arr = (T[]) new Object[size];
        if (size == 0) {
            return arr;
        }
        SinglyLinkedListNode<T> currentNode = head.getNext();
        arr[0] = head.getData();
        if (size > 1) {
            for (int i = 1; i < size; i++) {
                arr[i] = currentNode.getData();
                currentNode = currentNode.getNext();
            }
        }
        return arr;
    }

    /**
     * Returns the head node of the list.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return the node at the head of the list
     */
    public SinglyLinkedListNode<T> getHead() {
        // DO NOT MODIFY!
        return head;
    }

    /**
     * Returns the tail node of the list.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return the node at the tail of the list
     */
    public SinglyLinkedListNode<T> getTail() {
        // DO NOT MODIFY!
        return tail;
    }

    /**
     * Returns the size of the list.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return the size of the list
     */
    public int size() {
        // DO NOT MODIFY!
        return size;
    }
}

