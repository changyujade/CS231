
/*
Jade Chang
CS 231 Lab B
WordCounter.java
Apr 10

Counts the number of total word counts and unique word counts in a file and calculates the run time of each file
*/
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.io.FileWriter;

public class WordCounter {
    private BSTMap<String, Integer> tree;
    private int wordCount;

    // - constructor that makes an empty BSTMap and sets the total word count to
    // zero.
    public WordCounter() {
        this.tree = new BSTMap<>(new AscendingString());
        this.wordCount = 0;
        // this.tree.put("a", 3);
        // System.out.println(this.tree.get("a"));

    }

    // - generates the word counts from a file of words. This method should use the
    // BufferedReader to read in the file one line at a time. It should also use the
    // String's split method to separate each line into words. Your code will be
    // similar to the Board class file reader in the Sudoku project, including the
    // need to use a try-catch structure to enclose your code. See lab 3 for the
    // code template or look at your own code.
    public void analyze(String filename) {
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

            int counter = 0;
            // start a while loop that loops while line isn't null
            while (line != null) {
                line = line.strip(); // assign to an array of type String the result of calling split on the line
                // with the argument "[ ]+"

                // String[] stringArray = line.split("[ ]+");
                // System.out.println(Arrays.toString(stringArray));
                // System.exit(0);

                String[] words = line.split("[^a-zA-Z0-9']");
                // line = line.replaceAll("\\s+", " ");
                // System.out.println("WordCounter.analyze()");
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
                    for (int i = 0; i < words.length; i++) {
                        String word = words[i].trim().toLowerCase();
                        // Might want to check for a word of length 0 and not process it
                        // Write code to update the map
                        // put into the tree is it's not in there

                        if (word.length() == 0) {
                            continue;
                        }
                        // if contains key then update the value by 1
                        // if not in the tree, create a new pair with word and frquency 1
                        if (tree.containsKey(word)) {
                            // FIXME: how to access the words of a specific number
                            tree.put(word, tree.get(word) + 1);
                        } else {
                            tree.put(word, 1);
                        }
                        wordCount += 1;
                    }
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
        }

        catch (FileNotFoundException ex) {
            System.out.println("Board.read():: unable to open file " + filename);
        } catch (IOException ex) {
            System.out.println("Board.read():: error reading file " + filename);
        }
    }

    // - returns the total word count (note that this is not the number of unique
    // words, which would be the size of the BSTMap - it is the total number of
    // words in the document that was originally read in).
    public int getTotalWordCount() {
        return wordCount;

    }

    // - returns the number of unique words, which should be the size of the BSTMap.
    public int getUniqueWordCount() {
        return tree.size();

    }

    // - returns the frequency value associated with this word.
    public int getCount(String word) {
        return this.tree.get(word);
    }

    // - returns the value associated with this word divided by the total word
    // count. Use a cast to ensure this is a floating point calculation.
    public double getFrequency(String word) {
        return (double) (getCount(word)) / (wordCount);
    }

    public void writeWordCountFile(String filename) {
        int totalWordCount = wordCount;
        // System.out.println("total word count: " + totalWordCount);
        // System.out.println(tree.entrySet());

        // System.out.println("WordCounter.writeWordCountFile()");
        try (// write a loop that goes through each entry in entry set and create a string
             // File file = new File(filename);
                FileWriter fileWriter = new FileWriter(filename)) {
            fileWriter.write("total word count: " + totalWordCount + "\n");
            ArrayList<KeyValuePair<String, Integer>> entrySet = tree.entrySet();
            for (int i = 0; i < entrySet.size(); i++) {
                String keyString = entrySet.get(i).getKey() + " " + entrySet.get(i).getValue() + "\n";
                fileWriter.write(keyString);
                // Printing and display the elements in ArrayList
            }

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public void readWordCountFile(String filename) {

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
            line = buffer.readLine();

            // start a while loop that loops while line isn't null
            while (line != null) {
                // assign to an array of type String the result of calling split on the line
                // with the argument "[ ]+"

                // String[] stringArray = line.split("[ ]+");
                // System.out.println(Arrays.toString(stringArray));
                // System.exit(0);

                String[] words = line.split("[^a-zA-Z0-9']");
                line = line.replaceAll("\\s+", "");

                if (words.length == 2) {
                    tree.put(words[0], Integer.parseInt(words[1]));
                }

                line = buffer.readLine();

            }

            // call the close method of the BufferedReader
            // return true
            buffer.close();
        }

        catch (FileNotFoundException ex) {
            System.out.println("Board.read():: unable to open file " + filename);
        } catch (IOException ex) {
            System.out.println("Board.read():: error reading file " + filename);
        }

        // assign to a variable of type BufferedReader a new BufferedReader, passing the
        // FileReader variable to the constructor
    }

    public static void main(String[] argv) {

        WordCounter wordCounter = new WordCounter();

        /*
        for (String fileName : argv) {
            long start = System.currentTimeMillis();
            wordCounter.analyze(fileName);
            System.out.println("Filename: " + fileName);
            System.out.println("Total Word Count: " + wordCounter.getTotalWordCount());
            System.out.println("Unique Words: " + wordCounter.getUniqueWordCount());
            // System.err.println("Currently on Filename: " + fileName);
            // wordCounter.writeWordCountFile("testFile.txt");
            wordCounter.writeWordCountFile(fileName + "_count" + ".txt");
            long end = System.currentTimeMillis();
            long time = end - start;
            System.out.println("Run time " + time + "ms");
        }

        */

        System.out.println(wordCounter.tree.toString() + "\n");

        wordCounter.analyze("counttest.txt");
        //wordCounter.analyze("reddit_comments_2008.txt");
        wordCounter.writeWordCountFile("testFile.txt");
        wordCounter.writeWordCountFile("counts_ct.txt");
        wordCounter.readWordCountFile("counts_ct.txt");
        wordCounter.writeWordCountFile("counts_ct_v2.txt.");

        System.out.println(wordCounter.tree.entrySet());
        System.out.println(wordCounter.tree.toString());

    }

}
