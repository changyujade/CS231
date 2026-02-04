import java.util.Iterator; // defines the Iterator interface
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections; // contains a shuffle function
import java.util.*;


public class arrayBasedLL<T> implements Iterable<T> {
    private int size;
    private int top=0;
    private T[] array;
    private Class<T> clazz;
    private int capacity = 1024;

    public Iterator<T> iterator() {
        return new LLIterator(this.top);
    }

    /*
     * private class Node<T> {
     * private T data;
     * private Node<T> next;
     * 
     * // reference pointing the opposite direction. Points to null when it reaches
     * the
     * // head
     * 
     * // a constructor that initializes next to null and the container field to
     * item.
     * public Node(T item) {
     * next = null;
     * data = item;
     * }
     * 
     * // returns the value of the container field.
     * public T getThing() {
     * return data;
     * }
     * 
     * // sets next to the given node.
     * public void setNext(Node<T> n) {
     * next = n;
     * }
     * 
     * // returns the next field.
     * public Node<T> getNext() {
     * return next;
     * }
     * }
     */

    // constructor that initializes the fields so it is an empty list.
    public arrayBasedLL(Class<T> clazz, int capacity) {
        array = (T[]) Array.newInstance(clazz, capacity);
        this.clazz = clazz;
    }

    // empties the list (resets the fields so it is an empty list).
    public void clear() {
        Arrays.fill(array, null);
    }

    // returns the size of the list.
    public int size() {
        return top;
    }

    // inserts the item at the beginning of the list.
    public void addFirst(T item) {

        if (array.length == top) {
            // throw new IllegalStateException("Stack is full");
            this.capacity = capacity * 2;
            T[] array2 = (T[]) Array.newInstance(clazz, capacity * 2);
            // T[] array2 = new T[cellArray.length * 2];
            for (int i = 0; i < array.length; i++) {
                array2[i + 1] = array[i];
            }
            // System.arraycopy(cellArray, 0, cellArray2, 0, cellArray.length);
            array = array2;
        }

        // nextFreeLocation++;
        array[0] = item;
    }

    // appends the item at the top of the list. Similar to Push
    public void addLast(T item) {
        array[top++] = item;
        top++;
    }

    // inserts the item at the specified poistion in the list.
    public void add(int index, T item) {
        if (array.length == top) {
            // throw new IllegalStateException("Stack is full");
            this.capacity = capacity * 2;
            T[] array2 = (T[]) Array.newInstance(clazz, capacity);

            for (int i = 0; i < array.length; i++) {
                array2[i] = array[i];
            }
            array = array2;
        }

        //shifting the array to their next position
        
        for (int i = index; i < array.length + 1; i++) {
            array[index + 1] = array[i];
        }
        array[index] = item;
        
        top++;

    }

    // removes the item at the specified position in the list.
    public T remove(int index) {
        if (top ==0){
            return null;
        }
        for (int i = index; i < array.length + 1; i++) {
            array[i] = array[i+1];
        }
        
        top--;
        return array[index];
    }

    // returns an ArrayList of the list contents in order.
    public ArrayList<T> toArrayList() {
        ArrayList<T> al = new ArrayList<T>();
        Collections.addAll(al, array);
        return al;

    }

    public ArrayList<T> toShuffledList() {
        ArrayList<T> list = toArrayList();
        Collections.shuffle(list);
        return list;

    }

    private class LLIterator implements Iterator<T> {

        private int next;

        // the constructor for the LLIterator given the head of a list.
        public LLIterator(int top) {
            next = top;
        }

        // returns true if there are still values to traverse (if the current node
        // reference is not null).
        public boolean hasNext() {
            if (next > 0) {
                // T data = nextNode.getThing();
                // nextNode = nextNode.getNext();
                return true;
            }
            return false;

        }

        // returns the next item in the list, which is the item contained in the current
        // node. The method also needs to move the traversal along to the next node in
        // the list.
        public T next() {
            if (hasNext()) {
                next = next-1;
                T data = array[next];
                return data;
            }
            return null;
        }

        // does nothing. Implementing this function is optional for an Iterator.
        public void remove() {

        }
    }
}

// Question: is the while loop implementaiton better or the for loop
// implementation?