
/**
 * Lifesimulation.java
 * combines all the files and runs the simulation of multiple life cycles
 * Jade Chang
 * CS231 Lab B
 * Feb 20 2022
 */
import java.util.Random;

public class CatSocialAgentSimulation {
    public static void main(String[] args) throws InterruptedException {

        // allocates new memory for Landscape
        Landscape scape = new Landscape(500, 500);
        // Landscape scape = new Landscape(Integer.valueOf(args[1]),
        // Integer.valueOf(args[2]));

        // allocates new memeory for Random

        Random gen = new Random();
        // double density = Double.valueOf(200);

        // double density = Double.valueOf(args[0]); //0.3

        for (int i = 0; i < 200; i++) {
            /*
             * double x = ran.nextDouble()*scape.getWidth();
             * double y = ran.nextDouble();
             */
            // Agent temp = new Agent (x,y);
            Random ran = new Random();
            int cat = ran.nextInt(2);
            scape.addAgent(new CatSocialAgent(gen.nextDouble() * scape.getWidth(), gen.nextDouble() * scape.getHeight(),20, cat));
            // scape.addAgent(temp);
        }

        // alloctes new memory for LandscapeDisplay
        LandscapeDisplay display = new LandscapeDisplay(scape);
        Thread.sleep(250);

        for (int i = 0; i < 200; i++) {
            // scape.advance();
            scape.updateAgent();
            display.repaint();
            Thread.sleep(250);
            display.saveImage("data/life_frame_" + String.format("%03d", i) + ".png");
        }

    }
    // memory of Landscape, Random, LandscapeDisplay is lost when we exit the main
    // method.
}

/*
RESULTS:
sensitivity 
(radius of 20):
    -degree of clusters: 18
(radius of 40)
    - more sparse

*/