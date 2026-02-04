public class CellStack {

    private Cell[] cellArray;
    private int nextFreeLocation = 0;

    // - initialize the stack to a default size.
    public CellStack() {

        cellArray = new Cell[10];
    }

    // - initialize the stack to hold up to max elements.
    public CellStack(int max) {
        cellArray = new Cell[max];

    }

    // - if there is space{}, push c onto the stack.
    public void push(Cell c) {

        if (cellArray.length == nextFreeLocation) {
            // throw new IllegalStateException("Stack is full");
            Cell[] cellArray2 = new Cell[cellArray.length * 2];

            for (int i = 0; i < cellArray.length; i++) {
                cellArray2[i] = cellArray[i];
            }
            // System.arraycopy(cellArray, 0, cellArray2, 0, cellArray.length);
            cellArray = cellArray2;
        }

        // nextFreeLocation++;
        cellArray[nextFreeLocation++] = c;
    }

    // - remove and return the top element from the stack; return null if the stack
    // is empty.
    public Cell pop() {
        if (empty()) {
            return null;
        } else {
            Integer r = nextFreeLocation - 1;
            Cell object = cellArray[r];
            cellArray[r] = null;
            nextFreeLocation--;
            return object;
        }
    }

    // - return the number of selements on the stack.
    public int size() {
        return nextFreeLocation;
    }

    // - return true if the stack is empty.
    public boolean empty() {
        return nextFreeLocation == 0;
    }

}
