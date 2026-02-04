/**
 * Lifesimulation.java
 * combines all the files and runs the simulation of multiple life cycles
 * Jade Chang
 * CS231 Lab B
 * Feb 20 2022
 */
import java.util.Random;

public class Lifesimulation {
    public static void main(String[] args) throws InterruptedException {
        // EXTENTION: commandline arguments. The numbers are initilal values that is given in 
        //the original code. Can be modified to create different patterns. 
        //(100,100) 

        //allocates new memory for Landscape
        Landscape scape = new Landscape(Integer.valueOf(args[1]), Integer.valueOf(args[2]));
        //allocates new memeory for Random 
        Random gen = new Random();
        double density = Double.valueOf(args[0]); //0.3

        // initialize the grid to be 30% full

        for (int i = 0; i < scape.getRows(); i++) {
            for (int j = 0; j < scape.getCols(); j++) {
                scape.getCell(i, j).setAlive(gen.nextDouble() <= density);
            }
        }

        //alloctes new memory for LandscapeDisplay
        LandscapeDisplay display = new LandscapeDisplay(scape, 8);
        Thread.sleep(250);
        for (int i = 0; i < Integer.valueOf(args[3]); i++) {
            scape.advance();
            display.repaint();
            Thread.sleep(250);
            display.saveImage("data/life_frame_" + String.format("%03d", i) + ".png");
        }

    }
    //memory of Landscape, Random, LandscapeDisplay is lost when we exit the main method. 
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