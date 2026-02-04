
/*
***Jade Chang***

PQHeap
Fall 2020
CS 231 Project 8
creates the heap data structure
*/


import java.util.Comparator;

public class PQHeap<T> {

    private T[] heapData;
    private Comparator<T> comparator;
    private int size;
    private int capacity;

    // a constructor that initializes the empty heap, sets the size to zero, and
    // stores the comparator.
    public PQHeap(Comparator<T> comparator) {
        heapData = (T[]) new Object[16];
        size = 0;
        this.comparator = comparator;
    }

    // returns the number of elements in the heap.
    public int size() {
        return size;
    }

    // public String toString() {

    //     StringBuilder sb = new StringBuilder();

    //     for (int i = 0; i < heapData.length; i++) {
    //         sb.append("[");
    //         sb.append(((KeyValuePair) heapData[i]).toString()); // FIXME: cannot invoke Object.toString
    //         sb.append(",");
    //         sb.append("]");
    //     }
    //     return sb.toString();
    // }

    // adds the value to the heap and increments the size. Be sure to use the
    // Comparator to reshape/reheap the heap. You may want to add private methods to
    // handle the reheap process.
    public void add(T obj) {
        if (size>=heapData.length){
        resize();
        }

        // FIXME: resize funciton also doesn't seem to work.
        heapData[size] = obj;
        percolateUp(size);
        size++;
    }

    private void percolateUp(int childID) {
        int parentID = (childID - 1) / 2;
        // percolate up if the child is larger then the parent.

        // System.out.println("Percolate Up");
        // System.out.println("child ID: "+childID);
        // System.out.println("parent child ID: "+parentID);

        if (comparator.compare(heapData[childID], heapData[parentID]) > 0) {
            // heap data contains key value pairs, so it needs to be heapData.getValue? but
            // I can't do that because heapData is a T?
            swap(childID, parentID);
            percolateUp(parentID);
        }
    }

    // resize when you reach the capacity and copy over all the items
    public void resize() {
        size = 0;
        T[] temp = heapData.clone();
        createTable(getCapacity() * 2);
        for (T e : temp) {
            //System.out.println("e: "+e);
            add(e);
        }

    }

    private void createTable(int capacity) {
        heapData = (T[]) new Object[capacity];
    }

    private int getCapacity() {
        return heapData.length;
    }



    private void swap(int childID, int parentID) {
        T temp = heapData[childID];
        heapData[childID] = heapData[parentID];
        heapData[parentID] = temp;
    }

    // removes and returns the highest priority element from the heap. Be sure to
    // use the Comparator to reshape/reheap the heap. You may want to add private
    // methods to handle the reheap process.
    // If you are creating an array-based heap, it may be useful to create internal
    // methods parent, leftChild, and rightChild that take in an index and return
    // the appropriate child/parent index.
    public T remove() {
        if (size == 0) {
            return null;
        }
        T itemToRemove = heapData[0];
        heapData[0] = null;
        swap(size - 1, 0);
        size--;
        percolateDown(0);
        return itemToRemove;
    }

    private void percolateDown(int parentID) {

        int leftChildId = (2 * parentID) + 1;
        int rightChildId = (2 * parentID) + 2;
        int biggerChildId;
        // System.out.println("Percolate Down");
        // System.out.println("left child ID: "+leftChildId);
        // System.out.println("right child ID: "+rightChildId);
        // System.out.println("parent child ID: "+parentID);

        if (rightChildId >= size && leftChildId >= size) {
            return;
        } else if (rightChildId >= size) {
            biggerChildId = leftChildId;
        } else if (leftChildId >= size) {
            biggerChildId = rightChildId;
        } else if (comparator.compare(heapData[leftChildId], heapData[rightChildId]) < 0) {
            biggerChildId = rightChildId;
        } else {
            biggerChildId = leftChildId;
        }

        if (comparator.compare(heapData[parentID], heapData[biggerChildId]) < 0) {
            swap(biggerChildId, parentID);
            percolateDown(biggerChildId);
        }

    }
}
