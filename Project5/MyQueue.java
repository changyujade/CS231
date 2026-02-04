import java.util.Iterator;
import java.util.Queue;

public class MyQueue<T> implements Iterable<T> {
    // class body goes here
    private Node<T> head;
    private Node<T> tail;
    private int size;

    public MyQueue() {
        size = 0;
        head = null;
        tail = null;
    }

    private class Node<T> {
        private T data;
        private Node<T> next;
        private Node<T> prev;

        public Node(T val, Node<T> next, Node<T> prev) {
            data = val;
            this.next = next;
            this.prev = prev;
        }

        public T getData() {
            return data;
        }

        public Node<T> getNext() {
            return next;
        }

        public void setNext(Node<T> n) {
            next = n;
        }

        public Node<T> getPrev() {
            return prev;
        }

        public void setPrev(Node<T> n) {
            prev = n;
        }

    }

    /**
     * Returns the number of elements in the stack.
     * 
     * @return number of elements in the stack
     */
    public int size() {
        return size;
    }

    /**
     * Tests whether the stack is empty.
     * 
     * @return true if the stack is empty, false otherwise
     */
    public boolean isEmpty() {
        if (size() == 0) {
            return true;
        } else {
            return false;
        }
    }

    public boolean offer(T item) {
        // Inserts item into this queue if possible. Returns true if successful.
        // FIXME: isn't it always going to be successful?
        Node<T> newNode = new Node<T>(item, null, tail);
        if (!isEmpty()) {
            tail.setNext(newNode);
        } else {
            head = newNode;
        }
        size++;
        tail = newNode;
        return true;
    }

    public T peek() {
        // Returns, but does not remove, the head of this queue, or returns null if this
        // queue is empty.

        // was originally a T
        if (this.isEmpty() == true) {
            //System.out.println("\n Empty Queue");
            // When stack is empty
            return null;
        } else {
            return this.head.getData();
        }
    }

    public T poll() {
        // Returns and removes the head of this queue, or returns null if this queue is
        // empty.
        // FIXME: some potential issue with this code?
        if (head==null) {
            return null;
        } else {
            T data = head.getData();
            if (size > 1) {
                // T temp = head;
                // the head here is the one that we want to remove. Why does it still work?
                head = head.getNext();
                head.setPrev(null);

            }
            else {
                // when the queue contains only one node
                this.head = null;
                this.tail = null;
            }
            size--;
            return data;

        }
    }

    public String toString() {

        Node current = head;
        if (current != null) {
            String s = "";
            while (current.getNext() != null) {
                s += current.getData() + ", ";
                current = current.getNext();
            }
            s += current.getData();
            return s;
        } else {
            return (" list is empty");
        }
    }

    public Iterator<T> iterator() {
        return new MyLinkedListIterator(head);

    }

    private class MyLinkedListIterator implements Iterator<T> {

        private Node<T> nextNode;

        public MyLinkedListIterator(Node<T> head) {
            nextNode = head;
        }

        public boolean hasNext() {
            if (nextNode != null) {
                return true;
            } else {
                return false;
            }
        }

        public T next() {
            if (hasNext()) {
                T data = nextNode.getData();
                nextNode = nextNode.getNext();
                return data;
            } else {
                return null;
            }
        }
    }

    public static void main(String[] args) {
        MyQueue<Integer> newQueue = new MyQueue<>();
        newQueue.offer(37);
        newQueue.offer(2);
        newQueue.offer(9);
        Iterator it = newQueue.iterator();
        while (it.hasNext()) {
            int obj = (int) it.next();
            System.out.println(obj);
        }
        System.out.println("Print out content" + newQueue.toString());

        newQueue.poll();
        System.out.println("size: " + newQueue.size());
        System.out.println("remove 1 item " + newQueue.toString());
        Iterator it2 = newQueue.iterator();
        while (it2.hasNext()) {
            int obj = (int) it2.next();
            System.out.println(obj);
        }
        

        newQueue.poll();
        System.out.println("Print out content " + newQueue.toString());
        newQueue.poll();
        System.out.println("Print out content " + newQueue.toString());
        System.out.println("size: " + newQueue.size());
    }
}

// Why can I not remove the last item in the Queue?
// Wouldn't offer always be true?