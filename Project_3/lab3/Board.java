import java.io.*;

public class Board {
    public boolean read(String filename) {
        try {
            // assign to a variable of type FileReader a new FileReader object, passing
            // filename to the constructor
            FileReader variable = new FileReader(filename);

            // assign to a variable of type BufferedReader a new BufferedReader, passing the
            // FileReader variable to the constructor
            BufferedReader variable2 = new BufferedReader(variable);
            // assign to a variable of type String line the result of calling the readLine
            // method of your BufferedReader object.
            String line = variable2.readLine();

            // start a while loop that loops while line isn't null
            while (line != null) {
                    // assign to an array of type String the result of calling split on the line
                    // with the argument "[ ]+"
                String[] stringArray = line.split("[ ]+");
            
                    // print the String (line)
                System.out.println(line);
            
                    // print the size of the String array (you can use .length)
                System.out.println(stringArray.length);
                
                    // assign to line the result of calling the readLine method of your
                    // BufferedReader object.
                line = variable2.readLine();
            }
            
            variable2.close();
            return true;
            
            // call the close method of the BufferedReader
            // return true
        } catch (FileNotFoundException ex) {
            System.out.println("Board.read():: unable to open file " + filename);
        } catch (IOException ex) {
            System.out.println("Board.read():: error reading file " + filename);
        }

        return false;
    }

    public static void main(String[] args) {
        Board board = new Board();
        board.read(args[0]);
    }
}
