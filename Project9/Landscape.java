/**
 * Landscape.java
 * create an landscape
 * Jade Chang
 * CS231 Lab B
 * Apr 4 2022
 */

import java.util.ArrayList;
import java.awt.Graphics;

public class Landscape {
    private int w;
    private int h;
    // constructor. The list of finished customers should be initialized to an empty
    // list.
    private Hunter hunter;
    private ArrayList<VertexProject> verticies;
    private Wumpus wumpus;

    // Question: where do we initialis our array
    public Landscape(int w, int h) {
        this.w = w;
        this.h = h;
        this.verticies = new ArrayList<VertexProject>();
        this.wumpus = wumpus;
        this.hunter = hunter;
        // finishedCustomer = new LinkedList<Customer>();
    }

    // return the height of the Landscape.
    public int getHeight() {
        return h;

    }

    // return the width of the Landscape.
    public int getWidth() {
        return w;

    }

    // return a string indicating how many checkouts and finished customers are in
    // the landscape.
    public String toString() {
        String str = "";
        return str;
        // int size = listCheckoutAgent.size();
        // int num_finishedCustomer = finishedCustomer.findlength();
        // String str = "number of checkout agents:" + size + "\nnumber of finished customoers: " + num_finishedCustomer;
        // return str;
    }

    public void draw(Graphics g, int scale) {
        for (VertexProject item : verticies) {
            item.draw(g,64);
        }
    }

    public void addBackgroundAgent(VertexProject v1) {
        verticies.add(v1);
    }
}
