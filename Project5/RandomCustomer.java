/**
 * Customer.java
 * create an customer that joins a random line
 * Jade Chang
 * CS231 Lab B
 * Apr 4 2022
 */

import java.util.ArrayList;
import java.util.Random;

public class RandomCustomer extends Customer{
    // constructor. This should call the super class's constructor with the given
    // number of items and 1 as the initial value for the time steps. This is meant
    // to simulate the amount of time the RandomCustomer spends choosing a line. The
    // RandomCustomer spends one time-step choosing a line, so it needs to start its
    // counter at 1.
    public RandomCustomer(int num_items) {
        super(num_items,1);

    }

    // returns an integer randomly chosen from the range 0 (inclusive) to the lenght
    // of the list (exclusive).

    @Override
    public int chooseLine(ArrayList<CheckoutAgent> checkouts) {
        Random random = new Random();
        return random.nextInt(checkouts.size() - 0) + 0;

    }
}
