/**
 * File: Shuffle.java
 * Author: Jade Chang
 * Date: 02/07/2022
 */

import java.util.ArrayList;
import java.util.Random;

public class Shuffle {

    public static void main(String[] args) {

        ArrayList<Integer> arr = new ArrayList<Integer>();
        ArrayList<Integer> newArr = new ArrayList<Integer>();
        ArrayList<Integer> oneToTen = new ArrayList<Integer>();


        Random ran = new Random();
        for (int i = 0; i < 10; i++) {
            /* loop block code here */
            
            int val = ran.nextInt(50);
            arr.add(val);
            Integer x = arr.get(i);
            System.out.println(x);
        }

        for (int i = 0; i < 10; i++) {

            int val = ran.nextInt(arr.size());
            int removedNum = arr.remove(val);
            newArr.add(removedNum);
            System.out.println("item removed: " + removedNum +
                    " the remaining items in list:" + arr);
            System.out.println("Shuffled List: " + newArr);

        }

        for (int i = 1; i <= 10; i++) {
            oneToTen.add(i);
            System.out.println(oneToTen);
        }
    }
}
