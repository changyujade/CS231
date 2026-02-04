/*
***Jade Chang***

WordTrendsFinder.java
Fall 2020
CS 231 Project 8
finds the frequencies of specific words
*/

import java.util.ArrayList;
import java.util.Comparator;

public class WordTrendsFinder {

    private Comparator AscendingString;
    private ArrayList<String> words;
    private WordCounter2 hashmapCounter;
    private int beg_year;
    private int end_year;
    private String base_name;

    public WordTrendsFinder(String[] args) {
        // takes the command line arguments and takes the file and stores them in a list
        Hashmap<String, Integer> hashmap = new Hashmap<String, Integer>(new AscendingString());
        this.hashmapCounter = new WordCounter2(hashmap); // field
        words = new ArrayList<String>(); // field
        for (int k = 3; k < args.length; k++) {
            words.add(args[k]);
        }

        beg_year = Integer.parseInt(args[1]);
        end_year = Integer.parseInt(args[2]);
        this.base_name = args[0];

        // constructor: initialising the fields
        // have multiple parameters
        // basefile
        // basefile begin
        // arguments that are required to construct
        // analyse method to generate map etc.
    }

    public void analyse() {
        for (int i = beg_year; i < end_year+1; i++) {
            base_name +=  i + ".txt";
            // System.out.println("creating text file");
            hashmapCounter.readWordCount(base_name);
            System.out.println("year: " + i);
            for (String k : words) {
                // System.out.println("in loop");
                System.out.println(k + " " + hashmapCounter.getFrequency(k));
            }
            base_name = "count_reddit_comments_";
            
        }

    }


    public static void main(String[] args) {

        WordTrendsFinder wtf = new WordTrendsFinder(args);

        // "BaseComment: reddit_comments" "2008"-"2015"
        wtf.analyse();

        // create an array of words.
        // for each file
        // construct the file name
        // generate a map
        // then go through for each word in array of words, look it up.

        // Integer.parseInt(args[1])
        // Integer.parseInt(args[2])
        // args[0]

    }

    // checks the length and put it into command line arguments.
    // call wordCounter2 builder
    // get the map from word counter 2

    // then I can access the methods in the map

    // map.getWord
}
