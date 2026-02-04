/**
 * CheckOutAgent.java
 * create an CheckOutAgents
 * Jade Chang
 * CS231 Lab B
 * Apr 4 2022
 */

import java.awt.Graphics;
import java.util.Iterator;

public class CheckoutAgent {
    private int x_pos;
    private int y_pos;
    private MyQueue<Customer> line;
    // where do we initialise our queue

    // constructor. The queue should be initialized to an empty MyQueue<Customer>.
    public CheckoutAgent(int x, int y) {
        x_pos = x;
        y_pos = y;
        line = new MyQueue<>();

    }

    // add a Customer to its queue.
    public void addCustomerToQueue(Customer c) {
        // FIXME: how to add to the queue that is in the CheckoutAgent class?
        line.offer(c);

    }

    // returns the number of Customers in its queue.
    public int getNumInQueue() {
        return line.size();

    }

    public void updateState(Landscape scape) {
      
        /*
        Iterator it2 = line.iterator();
        while (it2.hasNext()) {
            Customer obj = (Customer) it2.next();
            obj.incrementTime();
            while (obj.getNumItems() > 0) {
                obj.giveUpItem();
                System.out.println(obj.getNumItems());
            }
            System.out.println("finshed checkout");
            line.poll();
            scape.addFinishedCustomer(obj);
        }
        */

        for (Customer item:line){
            item.incrementTime();
        }

        if(line.peek()!=null){
            if (line.peek().getNumItems()!=0){
                line.peek().giveUpItem();
            }
            else{
                scape.addFinishedCustomer(line.poll());
            }
        }
    }

    // draws the CheckoutAgent as a rectangle near the bottom of the window with a
    // height proportional to the number of Customers in the queue. A height of 10*N
    // (where N is the number of customers in the queue) and width of 10 should
    // work. Assume that (this.x,this.y) is the bottom left corner of the rectangle
    public void draw(Graphics g) {
        g.drawRect(x_pos, y_pos - 10 * getNumInQueue(), 10, getNumInQueue() * 10);
    }
}
