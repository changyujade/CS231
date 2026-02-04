
/*
***Jade Chang***

Ascending String.java
Fall 2020
CS 231 Project 6
returns the result of the comparison between objects. 
*/
import java.util.Comparator;

public class AscendingString implements Comparator<String> {

    @Override
    public int compare(String o1, String o2) {
        return o1.compareTo(o2);
    }

    public static void main(String[] args) {
        AscendingString compare = new AscendingString();
        System.out.println("a is smaller than b " + compare.compare("a", "b"));
        System.out.println("b is bigger than a " + compare.compare("b", "a"));
        System.out.println("b is the same as b " + compare.compare("b", "b"));

    }

}
