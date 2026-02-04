/**
 * Cell.java
 * Creates individual cells 
 * Jade Chang
 * CS231 Lab B
 * Feb 20 2022
 */


import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

public class Cell {
    private boolean state;
    public Object getNeighbors;

    public Cell(boolean alive) {
        /*
         * constructor method that specifies the Cell's state.
         * A True argument means the Cell is alive, a False argument means the Cell is
         * dead.
         */
        state = alive;

    }

    public boolean getAlive() {
        // returns whether the Cell is alive.
        return state;
    }

    public void reset() {
        // sets the Cell state to its default value (not alive).
        state = false;
    }

    public void setAlive(boolean alive) {
        // sets the Cell's state.
        state = alive;
    }

    public String toString() {
        // returns a string that indicates the alive state of the Cell
        // as a one-character string. For example, you could use a "0" for alive
        // and " " for dead. This will override the default toString method in the
        // Object class
        if (state) {
            return "0";
        } else {
            return " ";
        }
    }

    public void draw(Graphics g, int x, int y, int scale) {
        if (getAlive()) {
            g.setColor(Color.ORANGE);
            g.drawOval(x, y, scale, scale);
            g.fillOval(x, y, scale, scale);
        }

    }

    public void updateState(ArrayList<Cell> neighbors) {
        // if it is dead cell. then check the number of neighbors. set true if 3
        // neighbors,
        // and return (quit the conditional) if they don't have 3 neighbors.

        // then if the cell is alive, check if they either have 2 or 3 neighbors,
        // otherwise they all necome dead

        // arrays create a memory of a specific size. dont have methods associated with
        // it.
        // array list. has list functionality.
        int count = 0;
        for (int i = 0; i < neighbors.size(); i++) {
            if (neighbors.get(i).getAlive()) {
                count += 1;
            }

        }
        if (getAlive() == false) {
            if (count == 3) {
                setAlive(true);
                //System.out.println("cell born!");
            }
        } else {
            if (!(count == 2 || count == 3)) {
                setAlive(false);
                //System.out.println("cell died!");
            }
        }
    }

    public static void main(String[] args) {
        //allocates memory for Cell
        Cell cell = new Cell(false);
        /*
         * System.out.println(cell.getAlive());
         * 
         * cell.reset();
         * cell.setAlive(true);
         * System.out.println(cell.toString());
         */

        
        //allocates memory for an ArrayList of cell. 
        ArrayList<Cell> test = new ArrayList<Cell>();
        for (int i = 0; i < 5; i++) {
            test.add(new Cell(true));
        }
        for (int i = 0; i < 3; i++) {
            test.add(new Cell(false));
        }
        System.out.println(cell.getAlive());
        cell.updateState(test);
        System.out.println(cell.getAlive());
        // memory for an Cell and ArrayList of cell is lost when we exit the main method. 

    }

}
