/**
 * Landscape.java
 * create an landscape
 * Jade Chang
 * CS231 Lab B
 * Apr 4 2022
 */

import java.util.ArrayList;
import java.awt.Graphics;

public class Landscape {
    private int w;
    private int h;
    // constructor. The list of finished customers should be initialized to an empty
    // list.
    private ArrayList<CheckoutAgent> listCheckoutAgent;
    private LinkedList<Customer> finishedCustomer;

    // Question: where do we initialis our array
    public Landscape(int w, int h, ArrayList<CheckoutAgent> checkouts) {
        this.w = w;
        this.h = h;
        listCheckoutAgent = checkouts;
        finishedCustomer = new LinkedList<Customer>();
    }

    // return the height of the Landscape.
    public int getHeight() {
        return h;

    }

    // return the width of the Landscape.
    public int getWidth() {
        return w;

    }

    // return a string indicating how many checkouts and finished customers are in
    // the landscape.
    public String toString() {
        int size = listCheckoutAgent.size();
        int num_finishedCustomer = finishedCustomer.findlength();
        String str = "number of checkout agents:" + size + "\nnumber of finished customoers: " + num_finishedCustomer;
        return str;
    }

    // add the Customer to the list of finished customers.
    public void addFinishedCustomer(Customer c) {
        finishedCustomer.addLast(c);
    }

    // loop through the CheckoutAgents, calling the draw method on each.
    public void draw(Graphics g) {
        for (CheckoutAgent item : listCheckoutAgent) {
            item.draw(g);
        }
    }

    public void updateCheckouts() {
        for (CheckoutAgent item : listCheckoutAgent) {
            item.updateState(this);
        }
    }

    public void printFinishedCustomerStatistics() {
        double stats = 0;
        double stats_time = 0;
        double sq = 0;
        double res = 0;
        for (Customer item : finishedCustomer) {
            stats += 1;
            stats_time += item.getTime();
        }
        double mean = stats_time/stats;
        double standardDeviation = 0;

        for (Customer item : finishedCustomer) {
            standardDeviation = standardDeviation + Math.pow((item.getTime() - mean), 2);
        }
        sq = standardDeviation / stats;
        res = Math.sqrt(sq);
        

        System.out.println("number of finished customers: " + stats);
        System.out.println("average time to checkout: " + mean);
        System.out.println("standard deviation: "+ res);

    }
}
