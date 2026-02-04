/**
 * Customer.java
 * create an customer objects
 * Jade Chang
 * CS231 Lab B
 * Apr 4 2022
 */

import java.util.ArrayList;

public abstract class Customer {
    private int num_items;
    private int time_steps=0;
    // constructor method. (By default, the number of time steps is zero.)
    public Customer(int num_items) {
        this.num_items = num_items;

    }
    //constructor method.
    public Customer(int num_items, int time_steps) {
        this.num_items = num_items;
        this.time_steps = time_steps;

    }
    //increments the number of time steps.
    public void incrementTime(){
        time_steps+=1;

    } 
    //returns the number of time steps
    public int getTime() {
        return time_steps;

    }
    //decrements the number of items (indicating another item has been paid for).
    public void giveUpItem(){
        num_items-=1;

    } 
    //returns the number of items.
    public int getNumItems() {
        return num_items;

    }
    //since this is an abstract method, there is no body.
    public abstract int chooseLine(ArrayList<CheckoutAgent> checkouts);
    
}
