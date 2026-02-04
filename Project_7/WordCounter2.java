/*
***Jade Chang***

WordCounter2.java
Apr 17 2022
CS 231 Lab 2 Project 7
contains methods that reads and writes the file, and build the data on to a map. 
*/

import java.util.ArrayList;
import java.util.Collections;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.io.FileWriter;

public class WordCounter2 {
    // - constructor, where data_structure is either "bst" or "hashmap". It should
    // create the appropriate data structure and store it in the map field.

    private int totalWordCount;
    private int totalUniqueWords;

    private MapSet<String, Integer> mapSet;

    public WordCounter2(MapSet<String, Integer> mapSet) {
        this.mapSet = mapSet;
    }

    public WordCounter2(String data_structure) {
        if (data_structure.equals("BST")) {
            mapSet = new BSTMap<String, Integer>(new AscendingString());
        } else if (data_structure.equals("Hashmap")) {
            mapSet = new Hashmap<String, Integer>(new AscendingString());
        }
    }

    // - given the filename of a text file, read the text file and return an
    // ArrayList list of all the words in the file.
    public ArrayList<String> readWords(String filename) {
        ArrayList<String> list_of_words = new ArrayList<>();

        try {
            FileReader file = new FileReader(filename);
            BufferedReader buffer = new BufferedReader(file);

            String line = buffer.readLine();
            int counter = 0;
            // start a while loop that loops while line isn't null
            while (line != null) {
                line = line.strip();

                if (line.length() != 0) {
                    String[] words = line.split("[^a-zA-Z0-9']");
                    for (int i = 0; i < words.length; i++) {
                        String word = words[i].trim().toLowerCase();
                        if (!word.isEmpty()) {
                            list_of_words.add(word);
                            totalWordCount += 1;
                        }
                    }
                }
                line = buffer.readLine();

            }
            buffer.close();
            // System.out.println(list_of_words);
            return list_of_words;
        }

        catch (FileNotFoundException ex) {
            System.out.println("Board.read():: unable to open file " + filename);
        } catch (IOException ex) {
            System.out.println("Board.read():: error reading file " + filename);
        }
        return list_of_words;
    }

    // - given an ArrayList of words, put the words into the map data structure.
    // Return the time taken in ms. Record the time using System.nanoTime().
    public double buildMap(ArrayList<String> words) {
        long start = System.nanoTime();
        // System.out.println(words);

        for (String e : words) {
            // why is it not getting the list?
            // System.out.println("string: " + e);
            // System.out.println(mapSet);
            if (mapSet.get(e) == null) {
                mapSet.put(e, 1);
            }
            mapSet.put(e, mapSet.get(e) + 1);
        }
        // System.out.println(mapSet);
        // how to put it in the map data structure.
        long end = System.nanoTime();
        long time = end - start;
        return time;

    }

    // - clear the map data structure.
    public void clearMap() {
        this.mapSet.clear();
    }

    public Mapset getMap(){
        return mapSet;
    }
    // - return the total word count from the last time readWords was called.
    public int totalWordCount() {
        return totalWordCount;

    }

    // - return the unique word count, which should be the size of the map data
    // structure.
    public int uniqueWordCount() {
        int totalUniqueWords = mapSet.entrySet().size();
        return totalUniqueWords;

    }

    // - return the number of times the word occurred in the list of words. Query
    // the data structure to get the information. Return 0 if the word does not
    // exist in the data structure.
    public int getCount(String word) {
        if (mapSet.get(word) == null) {
            return 0;
        } else {
            return (int) mapSet.get(word);
        }

    }

    // - return the frequency of the word in the list of words. Query the data
    // structure to get the word count and then divide by the total number of words
    // to get the frequency. Return 0 if the word does not exist in the data
    // structure.
    public double getFrequency(String word) {
        if ((Integer) mapSet.get(word) == null) {
            return 0;
        } else {
            return (double) getCount(word) / totalWordCount;
        }
    }

    // - write a word count file given the current set of words in the data
    // structure. The first line of the file should contain the total number of
    // words. Each subsequent line should contain a word and its frequency.
    public boolean writeWordCount(String filename) {
        // int totalWordCount = 0;

        try (// write a loop that goes through each entry in entry set and create a string
             // File file = new File(filename);
                FileWriter fileWriter = new FileWriter(filename)) {
            fileWriter.write("total word count: " + totalWordCount + "\n");
            ArrayList<KeyValuePair<String, Integer>> entrySet = mapSet.entrySet();
            for (int i = 0; i < entrySet.size(); i++) {
                String keyString = entrySet.get(i).getKey() + " "
                        + Double.parseDouble(String.format("%.16f", getFrequency(entrySet.get(i).getKey()))) + "\n";
                // String keyString = entrySet.get(i).getKey() + " " +
                // (entrySet.get(i).getValue()) + "\n";
                fileWriter.write(keyString);
                // Printing and display the elements in ArrayList
            }
            return true;

        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

    }

    // - read a word count file given the filename. The function should clear the
    // map and then put all of the words, with their counts, into the map data
    // structure.
    public boolean readWordCount(String filename) {

        try {
            mapSet.clear();
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
            totalWordCount = Integer.parseInt(wordCountLine[1].trim());

            // move into the body
            line = buffer.readLine();

            // start a while loop that loops while line isn't null
            while (line != null) {
                // assign to an array of type String the result of calling split on the line
                // with the argument "[ ]+"

                String[] words = line.split("\s");
                line = line.replaceAll("\\s+", "");

                if (words.length == 2) {
                    int wordsCount = (int) (Double.parseDouble(words[1]) * totalWordCount);
                    mapSet.put(words[0], wordsCount);
                }

                line = buffer.readLine();

            }
            // call the close method of the BufferedReader
            // return true

            buffer.close();
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
    private static Double averageRunTime(String fileName, WordCounter2 counter) {
        // calculates average time to build a map
        ArrayList<Double> times = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            counter.clearMap();
            times.add(counter.buildMap(counter.readWords(fileName)));
        }
        // System.out.println(times);
        Collections.sort(times);
        times.remove(0); // removes the highest and lowest value
        times.remove(times.size()-1);

        Double average = times.stream().mapToDouble(val -> val).average().orElse(0.0);
        return average;

    }

    public static void main(String[] argv) {

        BSTMap<String, Integer> bstmap = new BSTMap<String, Integer>(new AscendingString());
        Hashmap<String, Integer> hashmap = new Hashmap<String, Integer>(new AscendingString());

        for (String fileName : argv) {
            hashmap.clear();
            bstmap.clear();
            WordCounter2 hashmapCounter = new WordCounter2(hashmap);
            // WordCounter2 bstCounter = new WordCounter2(bstmap);

            // // WordCounter2 wordCount2 = new WordCounter2(map);

            System.out.println("FileName: " + fileName);

            // // BST Map Part
            // System.out.println("BST running");
            // //System.out.println("run time: " + bstCounter.buildMap(bstCounter.readWords(fileName)));
            // System.out.println("average runtime: "+averageRunTime(fileName, bstCounter));
            // System.out.println("Total Word Count: " + bstCounter.totalWordCount());
            // System.out.println("Unique Words: " + bstCounter.uniqueWordCount());
            // System.out.println("depth: " + bstmap.getDepth());

            // // Hashmap File
            // System.out.println("hashmap running");
            System.out.println("run time: " + hashmapCounter.buildMap(hashmapCounter.readWords(fileName)));
            // System.out.println("average runtime: "+averageRunTime(fileName, hashmapCounter));
            
            // System.out.println("Total Word Count: " + hashmapCounter.totalWordCount());
            // System.out.println("Unique Words: " + hashmapCounter.uniqueWordCount());
            // System.out.println("Num of Collisions: " + hashmap.getNumber_collisions());

            hashmapCounter.writeWordCount(fileName+ "_count" + ".txt");

        }

        // wordCounter2.readWordCount("hi.txt");

        // * for (String fileName : argv) {
        // * // long start = System.currentTimeMillis();
        // * ArrayList<String> string = wordCounter2.readWords(fileName);
        // * System.out.println("Filename: " + fileName);
        // * System.out.println("Total Word Count: " + wordCounter2.totalWordCount());
        // * System.out.println("Unique Words: " + wordCounter2.uniqueWordCount());
        // * // System.err.println("Currently on Filename: " + fileName);
        // * wordCounter2.writeWordCount(fileName + "_count" + ".txt");
        // * // long end = System.currentTimeMillis();
        // * // long time = end - start;
        // * // System.out.println("Run time " + time + "ms");
        // * }

        // System.out.println(wordCounter2.buildMap(str));

        // wordCounter2.buildMap(str);
        // wordCounter2.clearMap();
        // System.out.println("Total Word Count: " + wordCounter2.getTotalWordCount());
        // System.out.println("Unique Words: " + wordCounter.getUniqueWordCount());
        // System.err.println("Currently on Filename: " + fileName);
        // wordCounter2.writeWordCount("hi_out.txt");

        // The WordCounter2 main function should first read the words from the file into
        // an ArrayList using the readWords method. Then it should calculate a robust
        // average of the time it takes to build the map. For example, loop five times,
        // each time through the loop clearing the map and then building the map,
        // storing the run times. Then calculate a robust average by dropping the low
        // and high values and computing the average of the remaining times.

        // why would the running time be different if the file is the same?

        // wordCounter2.writeWordCount(fileName + "_count" + ".txt");
        // wordCounter2.writeWordCount("hi" + "_count" + ".txt");
        // System.out.println("Run time " + time + "ms");

    }


}
