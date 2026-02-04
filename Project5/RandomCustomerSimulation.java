
/*
    Stephanie Taylor (Spring 2019)
    RandomCustomerSimulation.java
    Purpose: simulate the random customer strategy
*/
import java.util.Random;
import java.util.ArrayList;

public class RandomCustomerSimulation {
    // test function that creates a new LandscapeDisplay and populates it with 5
    // checkouts and 99 customers.
    public static void main(String[] args) throws InterruptedException {
        Random gen = new Random();
        ArrayList<CheckoutAgent> checkouts = new ArrayList<CheckoutAgent>(5);

        for (int i = 0; i < 5; i++) {
            CheckoutAgent checkout = new CheckoutAgent(i * 100 + 50, 480);
            checkouts.add(checkout);
        }
        Landscape scape = new Landscape(500, 500, checkouts);
        LandscapeDisplay display = new LandscapeDisplay(scape);
        int printcounter = 0;
        for (int j = 0; j < 1000; j++) {
            Customer cust = new RandomCustomer(1 + gen.nextInt(7));
            scape.updateCheckouts();
            int choice = cust.chooseLine(checkouts);
            // how to print stats periodically
            if (printcounter == 100){
                scape.printFinishedCustomerStatistics();
                printcounter = 0;
            }
            printcounter += 1;
            checkouts.get(choice).addCustomerToQueue(cust);
            display.repaint();
            //Thread.sleep(250);
        }

    }

}