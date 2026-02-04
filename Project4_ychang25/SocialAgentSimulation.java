
/**
 * SocialAgentSimulation.java
 * simulates social agents
 * Jade Chang
 * CS231 Lab B
 * Mar 18 2022
 */
import java.util.Random;

public class SocialAgentSimulation {
    public static void main(String[] args) throws InterruptedException {
        // EXTENTION: commandline arguments. The numbers are initilal values that is
        // given in
        // the original code. Can be modified to create different patterns.
        // (100,100)

        // allocates new memory for Landscape
        Landscape scape = new Landscape(500, 500);
        // Landscape scape = new Landscape(Integer.valueOf(args[1]),
        // Integer.valueOf(args[2]));

        // allocates new memeory for Random
        
        Random gen = new Random();
        //double density = Double.valueOf(200);
        
        // double density = Double.valueOf(args[0]); //0.3

     
        for (int i = 0; i < 200; i++){
            /*
            double x = ran.nextDouble()*scape.getWidth();
            double y = ran.nextDouble();
            */
            //Agent temp = new Agent (x,y);
            scape.addAgent( new SocialAgent( gen.nextDouble() * scape.getWidth(),gen.nextDouble() * scape.getHeight(),40) );
            
           // scape.addAgent(temp);
        }

        // alloctes new memory for LandscapeDisplay
        LandscapeDisplay display = new LandscapeDisplay(scape);
        Thread.sleep(250);

        for (int i = 0; i < 200; i++) {
            //scape.advance();
            scape.updateAgent();
            display.repaint();
            Thread.sleep(250);
            display.saveImage("data/life_frame_" + String.format("%03d", i) + ".png");
            //display.saveImage("data/life_frame_" + String.format("%03d", i) + ".png");
        }

    }
    // memory of Landscape, Random, LandscapeDisplay is lost when we exit the main
    // method.
}

/*
 * a main
 * method that
 * is similar
 * to the
 * main method
 * in landscapeDisplay.
 * java
 * 
 * similar
 * to simulation.
 * 
 * create a
 * landscape
 * landscape
 * landscapeDisplay
 * initlaise
 * the landsacpe
 * setting cells
 * loop and
 * advance then sleep,repaint
 * .
 */