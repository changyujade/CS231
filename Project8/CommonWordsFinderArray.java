/*
***Jade Chang***

CommonWordsFinderArray.java
Fall 2020
CS 231 Project 8
finds the N frequent words with array (EXTENSION)
*/

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class CommonWordsFinderArray {

    private ArrayList<KeyValuePair<String, Double>> alist;


    public CommonWordsFinderArray() {
        this.alist = new ArrayList<KeyValuePair<String, Double>>();
    }
    
    class MapComparator implements Comparator<KeyValuePair<String, Double>>
{
    private final String key;

    public MapComparator(String key)
    {
        this.key = key;
    }


    @Override
    public int compare(KeyValuePair<String, Double> o1, KeyValuePair<String, Double> o2) {
        Double firstValue = o1.getValue();
        Double secondValue = o2.getValue();
        return firstValue.compareTo(secondValue);
    }
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

                alist.add(new KeyValuePair<String, Double>(words[0], Double.parseDouble(words[1])));


                line = buffer.readLine();

            }
            // call the close method of the BufferedReader
            // return true
            //System.out.println(PQHeap.toString());

            //System.out.println("before sorting");
            //System.out.println(alist);
            Collections.sort(alist, new KVTestComparator());
            System.out.println("aftet sorting");
            //System.out.println(alist);

            for (int i = alist.size(); i-- > alist.size()-10; ) {
                System.out.println(alist.get(i));
            }

            buffer.close();
            long time_end = System.currentTimeMillis();
            System.out.println("time run: "+ (time_end-time_start)+"ms");

            //System.out.println(PQHeap.toString());
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
        CommonWordsFinderArray cwf = new CommonWordsFinderArray();

        for (String fileName:args){
            System.out.println("Year: "+fileName);
            cwf.readWordCount(fileName);// use the count_reddit_comments_20* in command line to read all the files
        }
        
    }
}
