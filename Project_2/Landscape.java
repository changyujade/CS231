/**
 * Landscape.java
 * displays the status of the cells
 * Jade Chang
 * CS231 Lab B
 * Feb 20 2022
 */
import java.util.Random;

import java.util.ArrayList;
import java.awt.Graphics;

public class Landscape {

    private Cell[][] grid;
    private int rows;
    private int cols;

    public Landscape(int rows, int cols) {
        /*
         * sets the number of rows and columns to the specified values and allocates the
         * grid of Cell references.
         * Then it should allocate a Cell for each location in the Grid.
         */
        this.rows = rows;
        this.cols = cols;
        //allocates memory for a 2D array of Cells and asign it to name grid.
        grid = new Cell[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                Cell cell = new Cell(false);
                grid[i][j] = cell;
            }
        }

    }

    public void reset() {
        // calls the reset method for each cell.
        this.reset();
    }

    public int getRows() {
        // returns the number of rows in the Landscape.
        return grid.length;
    }

    public int getCols() {
        // returns the number of columns in the Landscape.
        return grid[0].length;
    }

    public Cell getCell(int row, int col) {
        // returns a reference to the Cell located at position (row, col).
        return grid[row][col];
    }

    public String toString() {
        // converts the Landscape into a text-based string representation.
        // At the end of each row, put a carriage return ("\n").
        String text = "";
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                /* access the elements of the array using i and j as index variables */
                Cell element = grid[i][j];

                text += element.toString();

            }
            text += '\n';
        }

        return text;

    }

    public ArrayList<Cell> getNeighbors(int row, int col) {
        // returns a list of references to the neighbors of the Cell at location (row,
        // col).
        // Pay attention to the boundaries of the Landscape when writing this function.
        // The returned list should not contain the Cell at (row, col)


        //allocates memory for an ArrayList of cell. 
        ArrayList<Cell> getNeighbors = new ArrayList<Cell>();
        // do some conditionals to consider the borders.

        for (int i = row - 1; i <= row + 1; i++) {
            for (int j = col - 1; j <= col + 1; j++) {
                if (!(i < 0 || i > rows - 1 || j < 0 || j > cols - 1) && (i != row || j != col)){
                    // make sure it is not outside the bounds
                    // not sure if the logic of the this condition is correct
                    getNeighbors.add(grid[i][j]);
                }

            }
        }

        return getNeighbors;
    }

    public void draw(Graphics g, int gridScale) {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                getCell(i, j).draw(g, i * gridScale, j * gridScale, gridScale);

            }
        }
    }

    public void advance() {
        // 

        //allocates memory for an 2D Array of cell. 
        Cell[][] tempGrid = new Cell[rows][cols];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                tempGrid[i][j] = new Cell(grid[i][j].getAlive());
            }
        }
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                tempGrid[i][j].updateState(getNeighbors(i, j));
            }
        }

        grid = tempGrid;
        //the memory in grid is lost when it is reassigned with the data in tempGrid. 
        
    }

    public static void main(String[] args) {
        Landscape scape = new Landscape(100, 100);

        Random gen = new Random();
        double density = Double.valueOf(0.3); 

        // initialize the grid to be 30% full

        for (int i = 0; i < scape.getRows(); i++) {
            for (int j = 0; j < scape.getCols(); j++) {
                scape.getCell(i, j).setAlive(gen.nextDouble() <= density);
            }
        }
        scape.advance();
        System.out.println(scape.toString());

    }

}
