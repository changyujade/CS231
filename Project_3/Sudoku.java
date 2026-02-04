import java.util.Random;

public class Sudoku {
    private Board board;
    private LandscapeDisplay display;

    /*
    public void actionPerformed(ActionEvent event) {
        myButton.setText("Got Hit");
        myButton.Sudoku.sudoku();
    }
    */

    public Sudoku() {
        this.board = new Board();
        //this.display = new LandscapeDisplay(this.board, 30, this);
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

        // do we use the pre-generated board and then insert it in a random position
        Random random = new Random();
        // int N = random.nextInt(10 - 1) + 1;
        // System.out.println(N);
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
        int count = 0;
        while (count < N) {
            int ranRow = random.nextInt(10 - 1);
            int ranCol = random.nextInt(10 - 1);
            int ranValue = random.nextInt(10 - 1) + 1;
            /*
             * System.out.println("value of randomised row" + ranRow);
             * System.out.println("value of randomised col" + ranCol);
             * System.out.println("value of randomised value" + ranValue);
             */

            if ((board.value(ranRow, ranCol) != 0) || (board.validValue(ranRow, ranCol, ranValue) != false)) {
                // if it is not 0 or does not have an invalid value, assign it to the grid.
                // System.out.println("entered loop");
                board.set(ranRow, ranCol, ranValue, true);
                count += 1;
                // System.out.println("count: " + count);

            }
        }

        System.out.println("initial board");
        System.out.println(board.toString());
        this.display = new LandscapeDisplay(this.board, 30);

    }

    public boolean solve() {

        // Given: a stack, and the number of locked cells
        // ArrayStack arrayStack = new ArrayStack;
        int delay = 1;

        CellStack s = new CellStack();
        // System.out.println("1size of stack:" + s.size());
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

                // System.out.println("entereed loop");
                s.push(tempCell);
                board.set(tempCell.getRow(), tempCell.getCol(), tempCell.getValue());
                // System.out.println("size of stack:" + s.size());
            } else {
                // there is no cell availible, starts backtracking
                // System.out.println("size of stack:" + s.size());
                while (!s.empty()) {
                    Cell t = s.pop();
                    // System.out.println(t.toString());
                    int value = 0;
                    // System.out.println("value is:" + value);
                    for (int i = t.getValue() + 1; i < 10; i++) {
                        if (board.validValue(t.getRow(), t.getCol(), i)) {
                            value = i;
                            break;
                        }
                    }
                    if (value != 0) {
                        // set it to current value +1
                        // push the new value back to the stack
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
        Sudoku sudoku = new Sudoku(20);
        System.out.println(sudoku.toString());
        sudoku.solve();
        long endTime = System.nanoTime();
        System.out.println("Took " + (endTime - startTime) + " ns");

    }
}
