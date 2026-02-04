/**
 * Customer.java
 * create an picky customers who choose the shorteset lines
 * Jade Chang
 * CS231 Lab B
 * Apr 4 2022
 */

import java.util.ArrayList;

public class PickyCustomer extends Customer {

    // constructor. This should call the super class's constructor with the given
    // number of items and num_lines as the initial value for the time steps. The
    // PickyCustomer examines the lengths of all the lines before choosing one, so
    // its initial time needs to reflect that.
    public PickyCustomer( int num_items, int num_lines ){
        super(num_items,num_lines);
    }

    @Override
    //returns the index of the CheckoutAgent with the shortest line.
    public int chooseLine(ArrayList<CheckoutAgent> checkouts) {
        int shortest=999;
        int index=0;
        for (CheckoutAgent item : checkouts) {
            item.getNumInQueue();
            if (item.getNumInQueue()<shortest){
                shortest = item.getNumInQueue();
            }
        }

        for (CheckoutAgent item : checkouts) {
            if (shortest==item.getNumInQueue()){
                break;
            }
            index+=1;
        }
        return index;
    }

}
