/*
***Jade Chang***

CommonWordsFinderDouble.java
Fall 2020
CS 231 Project 8
Finds the most frequent words. 
*/

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Comparator;

public class CommonWordsFinderDouble {

    private PQHeap<KeyValuePair<String, Double>> PQHeap;

    public CommonWordsFinderDouble() {
        this.PQHeap = new PQHeap<KeyValuePair<String, Double>>(new KVTestComparator());
    }


    class KVTestComparator implements Comparator<KeyValuePair<String, Double>> {
        @Override
        public int compare(KeyValuePair<String, Double> o1, KeyValuePair<String, Double> o2) {
            // int diff = o1.getValue() - o2.getValue();
            return o1.getValue().compareTo(o2.getValue());
            // returns negative if o1 is smaller than o2
        }

    }

    // - read a word count file given the filename. The function should clear the
    // map and then put all of the words, with their counts, into the map data
    // structure.
    public boolean readWordCount(String filename) {

        long time_start = System.currentTimeMillis();

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
            String[] wordCountLine = line.split(":");
            int totalWordCount = Integer.parseInt(wordCountLine[1].trim());

            // move into the body
            line = buffer.readLine();

            // start a while loop that loops while line isn't null
            while (line != null) {
                // assign to an array of type String the result of calling split on the line
                // with the argument "[ ]+"

                String[] words = line.split("[ ]+");
                // line = line.replaceAll("\\s+", "");
                //System.out.println(line);

                PQHeap.add(new KeyValuePair<String, Double>(words[0], Double.parseDouble(words[1])));


                line = buffer.readLine();

            }
            // call the close method of the BufferedReader
            // return true
            //System.out.println(PQHeap.toString());

            StringBuilder sb = new StringBuilder();
            sb.append("[");
            for (int i = 0; i < 10; i++) {
                sb.append((PQHeap.remove()));
                sb.append(",");
            }
            

            System.out.println(sb);
            buffer.close();

            long time_end = System.currentTimeMillis();
            System.out.println("runtime: "+ (time_end-time_start));

            return true;
        }

        catch (FileNotFoundException ex) {
            System.out.println("Board.read():: unable to open file " + filename);
            return false;
        } catch (IOException ex) {
            System.out.println("Board.read():: error reading file " + filename);
            return false;
        }
        // assign to a variable of type BufferedReader a new BufferedReader, passing the
        // FileReader variable to the constructor
    }


    public static void main(String[] args) {
        CommonWordsFinderDouble cwf = new CommonWordsFinderDouble();

        for (String fileName:args){
            System.out.println("Year: "+fileName);
            cwf.readWordCount(fileName);// use the count_reddit_comments_20* in command line to read all the files
        }
        
    }
}
