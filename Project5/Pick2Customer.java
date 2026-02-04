/**
 * Pick2Customer.java
 * create an customer objects that pick two random lines
 * Jade Chang
 * CS231 Lab B
 * Apr 4 2022
 */

import java.util.ArrayList;
import java.util.Random;

public class Pick2Customer extends Customer {
    // This should call the super class's constructor with the given number of items
    // and 2 as the initial value for the time steps. The Pick2Customer spends two
    // time-step choosing a line because it randomly examines two lines before
    // picking one.
    public Pick2Customer(int num_items) {
        super(num_items, 2);
    }

    @Override
    // returns the index of the shorter of two randomly chosen queues. (Note: write
    // your code to ensure that there are two different lines chosen.)
    public int chooseLine(ArrayList<CheckoutAgent> checkouts) {
        Random gen = new Random();
        int ran1 = gen.nextInt(checkouts.size() - 0) + 0;
        int ran2 = gen.nextInt(checkouts.size() - 0) + 0;
        if (checkouts.get(ran1).getNumInQueue()<checkouts.get(ran2).getNumInQueue()){
            return ran1;
        }   
        return ran2;
        
    }

}