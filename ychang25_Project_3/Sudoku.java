/**
 * Sudoku.java
 * Main function that executes the sudoku game
 * Jade Chang
 * CS231 Lab B
 * Mar 7 2022
 */

import java.util.Random;

public class Sudoku {
    private Board board;
    private LandscapeDisplay display;
    private Random ran;
    private int n;

    public Sudoku() {
        this.board = new Board();
        //this.display = new LandscapeDisplay(this.board, 30);
    }

    public Sudoku(int N) {
        // To construct a board with N valid, but randomly selected values, you will
        // need a random number generator and a loop.
        // Each time through the loop generate a random row, random column, and random
        // value. If the board already has a non-zero value there, try again.
        // If the location does not yet have a value, then test if the value is valid in
        // that location using your board's validValue method. If it is valid, then
        // insert the value into the board at that location and specify it as locked. If
        // it is not valid, then try again.
        // Note that the loop will likely need to execute more than N times to generate
        // N valid values.

        n = N;
        // do we use the pre-generated board and then insert it in a random position
        ran = new Random();
        // int N = random.nextInt(10 - 1) + 1;
        //System.out.println(N);
        // board = new Cell[SIZE][SIZE];
        // Board board = new Board();
        this.board = new Board();
        /*
         * for (int i = 0; i < SIZE; i++) {
         * for (int j = 0; j < SIZE; j++) {
         * Cell cell = new Cell(i,j,N);
         * board[i][j] = cell;
         * }
         * }
         */

        this.board.randomInitializeBoard(N, ran);
        System.out.println("initial board");
        System.out.println(board.toString());
        //FIXME: how do we feed the function to Landscape Display
        this.display = new LandscapeDisplay(this.board, 30, this);

    }
    public void reset() {
        this.board = new Board();
        this.board.randomInitializeBoard(n,ran);
        this.display.setScape(this.board);
    }

    public boolean solve() {
        // Given: a stack, and the number of locked cells

        //change the delay to change the display speed of the screen
        int delay = 1;

        CellStack s = new CellStack();
        //System.out.println("1size of stack:" + s.size());
        // while the stack size is less than the number of unspecified cells
        // --> number of cells that aren't locked

        while (s.size() < (81 - this.board.numLocked())) {
            
            
            if (delay > 0) {
                try {
                    Thread.sleep(delay);
                } catch (InterruptedException ex) {
                    System.out.println("Interrupted");
                }
                display.repaint();
            }
            
            
            

            // select the next cell to check (this is where you add smarts)
            Cell tempCell = board.findBestCell();
            
            if (tempCell != null) {

                //System.out.println("entereed loop");
                s.push(tempCell);
                board.set(tempCell.getRow(), tempCell.getCol(), tempCell.getValue());
                //System.out.println("size of stack:" + s.size());
            } else {
                //System.out.println("size of stack:" + s.size());
                while (!s.empty()) {
                    Cell t = s.pop();
                    //System.out.println(t.toString());
                    int value = 0;
                    //System.out.println("value is:" + value);
                    for (int i = t.getValue() + 1; i < 10; i++) {
                        if (board.validValue(t.getRow(), t.getCol(), i)) {
                            value = i;
                            break;
                        }
                    }
                    if (value != 0) {
                        // set it to current value +1
                        t.setValue(value);
                        s.push(t);
                        board.set(t.getRow(), t.getCol(), t.getValue());
                        break;
                    } else {
                        t.setValue(0);
                        board.set(t.getRow(), t.getCol(), t.getValue());
                    }
                }
                if (s.empty()) {
                    System.out.println("there is no solution");
                    return false;
                }
            }
        }
        System.out.println("the board contains the solution");
        display.repaint();
        System.out.println(board.toString());
        
        return true;
    }

    public static void main(String[] args) {
        
        
        long startTime = System.nanoTime();

        // code

        Sudoku sudoku = new Sudoku(5);
        System.out.println(sudoku);
        sudoku.solve();
        long endTime = System.nanoTime();
        System.out.println("Took "+(endTime - startTime) + " ns");  
        
        
    }
}
