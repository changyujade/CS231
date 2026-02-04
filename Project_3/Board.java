import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.awt.Graphics;

public class Board {

    public static final int SIZE = 9;
    private Cell[][] board;

    public Board() {
        board = new Cell[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                Cell cell = new Cell();
                board[i][j] = cell;
            }
        }
    }

    public String toString() {
        String text = "";
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                /* access the elements of the array using i and j as index variables */
                // the inner loop creates rows
                // FIXME: how to get only the value.
                Integer element = value(i, j);
                text += element.toString();
                text += " ";
            }
            // when we exit, we add another line
            // FIXME: Question: how do we make a 3x3 grid
            text += '\n';
        }
        return text;
    }

    public boolean read(String filename) {
        try {
            // assign to a variable of type FileReader a new FileReader object, passing
            // filename to the constructor
            FileReader file = new FileReader(filename);

            // assign to a variable of type BufferedReader a new BufferedReader, passing the
            // FileReader variable to the constructor
            BufferedReader buffer = new BufferedReader(file);
            // assign to a variable of type String line the result of calling the readLine
            // method of your BufferedReader object.
            String line = buffer.readLine();
            line = line.strip();

            // start a while loop that loops while line isn't null
            int lineNum = 0;

            while (line != null) {
                // assign to an array of type String the result of calling split on the line
                // with the argument "[ ]+"

                // String[] stringArray = line.split("[ ]+");
                // System.out.println(Arrays.toString(stringArray));
                // System.exit(0);

                line = line.replaceAll("\\s+", "");

                // print the String (line)
                // System.out.println(line);

                // go through each item and add it to the associated spot on the board.
                // scanner = new Scanner (filename)
                // for loop with 81 times
                // scanner.next

                // while loop that runs 9 times
                // have a for loop that runs
                // counter varilable outside the while loop
                // counter increments every time the for loop

                if (line.length() != 0) {
                    // System.out.println("empty");
                    for (int k = 0; k < 9; k++) {
                        // turn ascii value back to an int
                        // converting
                        // Integer.parseInt((charAt9k)+"")
                        // take a string
                        board[lineNum][k].setValue(Integer.parseInt(line.charAt(k) + ""));

                    }
                    lineNum += 1;
                }
                // print the size of the String array (you can use .length)
                // System.out.println(stringArray.length);

                // assign to line the result of calling the readLine method of your
                // BufferedReader object.
                line = buffer.readLine();

            }

            // call the close method of the BufferedReader
            // return true
            buffer.close();
            return true;
        }

        catch (FileNotFoundException ex) {
            System.out.println("Board.read():: unable to open file " + filename);
        } catch (IOException ex) {
            System.out.println("Board.read():: error reading file " + filename);
        }
        return false;
    }

    // - returns the number of columns
    public int getCols() {
        // FIXME: isn't the length the same as always?
        return board.length;

    }

    // - returns the Cell at location r, c.
    public Cell get(int r, int c) {
        return board[r][c];

    }

    // - returns whether the Cell at r, c, is locked.
    public boolean isLocked(int r, int c) {
        return board[r][c].isLocked();
    }

    // - returns the number of locked Cells on the board.
    public int numLocked() {
        int count = 0;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j].isLocked()) {
                    count += 1;
                }
            }
        }
        return count;

    }

    // - returns the value at Cell r, c.
    public int value(int r, int c) {
        return board[r][c].getValue();

    }

    // - sets the value of the Cell at r, c.
    public void set(int r, int c, int value) {
        board[r][c].setValue(value);

    }

    // - sets the value and locked fields of the Cell at r, c.
    public void set(int r, int c, int value, boolean locked) {
        board[r][c].setValue(value);
        board[r][c].setLocked(locked);
    }

    /// solve a sudoku
    //
    // esssentially the fundamental recursive backtracking algorithm. 
    // - Andy Day
    // 
    // Unless I've made a logical bug (which might be likely), this framework should be 
    // trivially generifiable to work for any recursive backtracking problem
    // returns true is encounters valid sudoku state (no conflicts of rows and columns or 3x3's)
    // else false
    // bool solve(Stack (stores rows and cols of previous moves) stack, int currentRow, int currentVal)

    //   if there are no remaining squares
    //       return true if the sudoku is correctly solved else false
    //   l 
    //   for next place in list of possible place
    //        push current location onto move stack and place that number on the board (new Location(next place))
    //        ensure that the current board is valid after the push and if it's not valid skip this iteration.
    //        display the board here if you need to.
    //        if (solve(stack, next place))
    //             return true
    //        pop current location from move stack and remove that number from the board -> board[next place.row][loc.col] = 0
    //
    //        
    //   return false

    // f(row, col) = row * 9 + col: [0, 81)
    // f_row(m) =  row
    // f_col(m) = col
    //
    // f(f_row(m), f_col(m)) = m

    // to call solve, you pick a location (assumedly randomly, and pass a new stack to it, but keep reference to the stack!)
    // if the solve returns true then the positions stored in the stack are the positions need to play to win the game
    // otherwise the stack *should* be empty.

    // - returns the number of rows
    public int getRows() {
        return board.length;
    }

    // tests if the given value is a valid value at the given row and column of the
    // board.
    // It should make sure the value is unique in its row, in its column, and in its
    // local 3x3 square.
    // You can figure out which local 3x3 square it is in by using integer division.
    // (Note, you probably want to test that the value is in the range [1,9] before
    // doing anything else.)

    /*

    private class Location {
        private final int row;
        private final int col;
        
        public Location(int row, int col) {
            this.row = row;
            this.col = col;
        }

        public int getRow() {
            return row;
        }

        public int getCol() {
            return col;
        }
    }
    */


    // /**
    //  * Given a location, get a legal cell that can fill that location.
    //  * @param location
    //  * @return
    //  */
    // public Cell getLegalCell(Location location) { 
    //     for (int cellValue = 1; cellValue < 10; cellValue++) {
    //         if (validValue(location.getRow(), location.getCol(), cellValue)) {
    //             return new Cell(cellValue);
    //         } 
    //     }
    // }

    // public Location getBestLocation() {
    //     for (int i = 0; i < getRows(); i++) {
    //         for (int j = 0; j < getCols(); j++) {
    //             if (board[i][j].getValue() == 0) {
    //                 return new Location(i, j);
    //             }
    //         }
    //     }
    //     return null;
    // }
 
    
    public Cell findBestCell() {
        for (int i = 0; i < getRows(); i++) {
            for (int j = 0; j < getCols(); j++) {
                if (board[i][j].getValue() == 0) {
                    for (int k = 1; k < 10;k++) {
                        if (validValue(i, j, k)) {
                            Cell cell = new Cell(i, j, k);
                            return cell;
                        } 
                    }
                    return null;
                    
                }
            }
        }
        return null;
    }

    public boolean validValue(int row, int col, int value) {
        // checks if it is in the range
        //System.out.println("board value: " + value);
        if (!(value > 0 && value < 10)) {
            //System.out.println("not in range 1-9");
            return false;
        }
        // FIXME: how to let it skip to the end?

        // determine which block it is currently at.
        // floor division to determine which square I am in.
        int intDivRow = row / 3;
        int intDivCol = col / 3;
        //System.out.println("block x" + intDivRow);
        //System.out.println("block y" + intDivCol);

        for (int i = intDivRow * 3; i < (intDivRow * 3) + 3; i++) {
            for (int j = intDivCol * 3; j < (intDivCol * 3) + 3; j++) {
                if (board[i][j].getValue() == value && (i != row || j != col)) {
                    //System.out.println("same number exists in the same block");
                    return false;
                }
            }
        }
        // check the values in the row
        // checks the column
        for (int k = 0; k < getCols(); k++) {
            if (board[row][k].getValue() == value && k != col) {
                //System.out.println("same number exists in column");
                return false;
            }
            if (board[k][col].getValue() == value && k != row) {
                //System.out.println("same number exists in row");
                return false;
            }
        }
        return true;
    }

    public boolean validSolution() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                int boardValue = board[i][j].getValue();
        
                if (boardValue == 0 || !(validValue(i, j, boardValue))) {
                    //System.out.println("number we are at: " + i + "," + j + board[i][j].getValue());
                    //System.out.println("not solved");
                    return false;
                }
            }
        }
        System.out.println("solved");
        return true;

    }

    public void draw(Graphics g, int scale){
        for (int i = 0; i < getRows(); i++) {
            for (int j = 0; j < getCols(); j++) {
                this.get(i, j).draw(g, i*(scale)+20, j*(scale)+20, scale);
            }
        }
    }

    public static void main(String[] args) {
        Board board = new Board();
        board.read(args[0]);
        System.out.println(board.toString());
        System.out.println(board.validValue(3, 8, 10));
        System.out.println(board.validSolution());

        /*
         * board.set(0, 0, 1);
         * System.out.println(board.toString());
         * 
         * // test lock function
         * board.set(0, 2, 2, true);
         * System.out.println(board.toString());
         * System.out.println(board.isLocked(0, 0));
         * System.out.println("number of locks: " + board.numLocked());
         * System.out.println("value: " + board.value(0, 2));
         * 
         * // return number of rows and colomns
         * System.out.println(board.getRows());
         * System.out.println(board.getCols());
         * System.err.println(board.get(0,0));
         * 
         */

    }
}
