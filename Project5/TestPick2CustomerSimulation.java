
/*
    Stephanie Taylor (Spring 2019)
    TestPick2CustomerSimulation.java
    Purpose: to test the line-choosing strategy of the Pick2Customer by visualizing
    the lines as customers join and leaves the queue. Customers leave.
*/
import java.util.Random;
import java.util.ArrayList;

public class TestPick2CustomerSimulation {
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
            Customer cust = new Pick2Customer(1 + gen.nextInt(7));
            int choice = cust.chooseLine(checkouts);
            checkouts.get(choice).addCustomerToQueue(cust);
            
            if (printcounter == 100) {
                scape.printFinishedCustomerStatistics();
                printcounter = 0;
            }
            printcounter += 1;
            scape.updateCheckouts();
            display.repaint();
            //Thread.sleep(250);
        }

    }

}