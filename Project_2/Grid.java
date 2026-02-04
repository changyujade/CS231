/**
 * Grid.java
 * lab assignment
 * Jade Chang
 * CS231 Lab B
 * Feb 20 2022
 */

import java.util.Random;

public class Grid {
    public static void main(String[] args) {
        if (args.length <= 0) {
            System.out.println("please enter at least 1 item");
            return;
        }
        for (int i = 0; i < args.length; i++) {
            System.out.println(args[i]);
        }

        // convert strings to integers
        int yogi = Integer.parseInt(args[0]);
        int booboo = Integer.parseInt(args[1]);
        //System.out.println(yogi + booboo);

        String[][] ranger;
        ranger = new String[yogi][booboo];

        int asciiValue = 97;

        Random ran = new Random();

        for (int i = 0; i < ranger.length; i++) {
            for (int j = 0; j < ranger[0].length; j++) {
                /* access the elements of the array using i and j as index variables */
                asciiValue += ran.nextInt(26);
                Character convertedChar = (char) asciiValue;
                //System.out.println(convertedChar);
                String convertedString = convertedChar.toString(); 
                ranger[i][j] = (convertedString);
                System.out.print(ranger[i][j]);
                asciiValue = 97;
            }
            System.out.println();
        }

        

    }
}