import java.util.Iterator; // defines the Iterator interface
import java.util.ArrayList;
import java.util.Collections; // contains a shuffle function

public class LinkedList<T> implements Iterable<T> {
    private int size;
    private Node<T> head;

    public Iterator<T> iterator() {
        return new LLIterator(this.head);
    }

    private class Node<T> {
        private T data;
        private Node<T> next;
        // reference pointing the opposite direction. Points to null when it reaches the
        // head

        // a constructor that initializes next to null and the container field to item.
        public Node(T item) {
            next = null;
            data = item;
        }

        // returns the value of the container field.
        public T getThing() {
            return data;
        }

        // sets next to the given node.
        public void setNext(Node<T> n) {
            next = n;
        }

        // returns the next field.
        public Node<T> getNext() {
            return next;
        }
    }

    // constructor that initializes the fields so it is an empty list.
    public LinkedList() {
        size = 0;
        head = null;
    }

    // empties the list (resets the fields so it is an empty list).
    public void clear() {
        size = 0;
        head = null;
    }

    // returns the size of the list.
    public int size() {
        return size;
    }

    // inserts the item at the beginning of the list.
    public void addFirst(T item) {
        // FIXME: but it is always going to point to null?
        Node<T> newNode = new Node<T>(item);
        newNode.setNext(head);
        this.head = newNode;
        size++;
    }

    // appends the item at the end of the list.
    public void addLast(T item) {
        Node<T> current = head;
        Node<T> previous = head;
        Node<T> newNode = new Node<T>(item);
        // when next is null
        // reallocaiton of pointers
        if (size != 0) {
            while (current != null) {
                previous = current;
                current = current.getNext();
            }
            previous.setNext(newNode);
            size++;
        } else {
            addFirst(item);
        }

    }

    // inserts the item at the specified poistion in the list.
    public void add(int index, T item) {
        Node<T> newNode = new Node<T>(item);
        Node<T> current = head;
        Node<T> previous = null;
        int count = 0;
        if (index == 0) {
            addFirst(item);
            System.out.println("Size: " + size());
            return;
        }
        // FIXME: the size is not working correctly

        while (current != null) {
            if (count == index) {
                //previous.setNext(newNode);
                newNode.setNext(current);
                //size++;
                System.out.println("Size in while loop: " + size());
                break;
            }
            previous = current;
            current = current.getNext();
            count++;
        }
        previous.setNext(newNode);
        size++;
    }

    // removes the item at the specified position in the list.
    public T remove(int index) {
        Node<T> current = head;
        Node<T> previous = head;
        int count = 0;
        // FIXME: cannot remove the first index
        if (index == 0) {
            //current.setNext(current.getNext());
            head = current.getNext();
            size--;
            return current.getThing();
        }
        while (current != null) {
            if (count == index) {
                previous.setNext(current.getNext());
                size--;
                System.out.println("setting previous to current.getNext");
                return current.getThing();
            }
            previous = current;
            current = current.getNext();
            count++;
        }
        System.out.println("length of list:" + findlength());
        return null;

    }

    // Function to return the length of the Linked List
    public int findlength() {
        Node<T> curr = head;
        int cnt = 0;
        while (curr != null) {
            cnt++;
            curr = curr.getNext();
        }
        return cnt;
    }

    // returns an ArrayList of the list contents in order.
    public ArrayList<T> toArrayList() {
        Node<T> current = head;
        ArrayList<T> alist = new ArrayList<>();

        while (current != null) {
            //FIXME: Exception in thread "main" java.lang.NullPointerException: Cannot invoke "LinkedList$Node.getThing()" because "current" is null
            // when it initialises it will just be head? head will then be added into the list?
            alist.add(current.getThing());
            current = current.getNext();
            
        }
        return alist;

    }

    public ArrayList<T> toShuffledList() {
        ArrayList<T> list = toArrayList();
        Collections.shuffle(list);
        return list;

    }

    private class LLIterator implements Iterator<T> {

        private Node<T> nextNode;

        // the constructor for the LLIterator given the head of a list.
        public LLIterator(Node head) {
            nextNode = head;
        }

        // returns true if there are still values to traverse (if the current node
        // reference is not null).
        public boolean hasNext() {
            if (nextNode != null) {
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
                T data = nextNode.getThing();
                nextNode = nextNode.getNext();
                return data;
            }
            return null;
        }

        // does nothing. Implementing this function is optional for an Iterator.
        public void remove() {

        }
    }
}

//Question: is the while loop implementaiton better or the for loop implementation?