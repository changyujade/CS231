import java.awt.Graphics;

public class Cell {
    // invariants - things that must always be true for the program work (but you must uphold them as the programmer)
    // board[row][col].rowIndex = row
    // board[row][col].colIndex = col
    private int rowIndex;
    private int colIndex;
    private int valueOf;
    private boolean lock;

    // intialise all values to false
    public Cell() {
        rowIndex = 0;
        colIndex = 0;
        valueOf = 0;
        lock = false;
    }

    public Cell(int row, int col, int value) {
        this.rowIndex = row;
        this.colIndex = col;
        this.valueOf = value;
    }

    public Cell(int row, int col, int value, boolean locked) {
        this.rowIndex = row;
        this.colIndex = col;
        this.valueOf = value;
        this.lock = locked;
    }

    // - return the Cell's row index.
    public int getRow() {
        return rowIndex;
    }

    // - return the Cell's column index.
    public int getCol() {
        return colIndex;

    }

    // - return the Cell's value.
    public int getValue() {
        return valueOf;

    }

    // - set the Cell's value.
    public void setValue(int newval) {
        valueOf = newval;
    }

    // - return the value of the locked field.
    public boolean isLocked() {
        return lock;
    }

    // - set the Cell's locked field to the new value.
    public void setLocked(boolean lock) {
        this.lock = lock;
    }

    public Cell clone() {
        Cell newCell = new Cell(this.rowIndex, this.colIndex, this.valueOf, this.lock);
        return newCell;
    }

    public String toString() {
        return "position: " + String.valueOf(rowIndex) + ", " + String.valueOf(colIndex) + " value: "
                + String.valueOf(valueOf) + " locked: " + lock;
    }

    public void draw(Graphics g, int x0, int y0, int scale){
        char character = (char)('0' + this.valueOf);
        char charArray [] = new char[1];
        charArray [0] = character;
        g.drawChars(charArray, 0, 1, x0, y0);
    }

}
