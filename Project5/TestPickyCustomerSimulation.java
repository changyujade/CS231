/*
    Stephanie Taylor (Spring 2019)
    TestPickyCustomerSimulation.java
    Purpose: to test the line-choosing strategy of the PickyCustomer by visualizing
    the lines as customers join and leave them.
*/
import java.util.Random;
import java.util.ArrayList;

public class TestPickyCustomerSimulation {
    // test function that creates a new LandscapeDisplay and populates it with 5 checkouts and 99 customers.
    public static void main(String[] args) throws InterruptedException {
        Random gen = new Random();
        ArrayList<CheckoutAgent> checkouts = new ArrayList<CheckoutAgent>(5);

        for(int i=0;i<5;i++) {
            CheckoutAgent checkout = new CheckoutAgent( i*100+50, 480 );
            checkouts.add( checkout );
        }
        Landscape scape = new Landscape(500,500, checkouts);
        LandscapeDisplay display = new LandscapeDisplay(scape);
        int printcounter = 0;
        for (int j = 0; j < 1000; j++) {
            
            Customer cust = new PickyCustomer(1+gen.nextInt(7), checkouts.size());
            scape.updateCheckouts();
            int choice = cust.chooseLine( checkouts );
            checkouts.get(choice).addCustomerToQueue( cust );
            
            if (printcounter == 100){
                scape.printFinishedCustomerStatistics();
                printcounter = 0;
            }
            printcounter += 1;
            display.repaint();
            //Thread.sleep( 250 );
        }

    }

}