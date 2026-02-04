/**
 * Coin.java
 * Represents a coin toss
 * Author: Lewis/Loftus, Adapted by Hannen Wolfe
 * CS231 S22
 */
 
import java.util.Random;

public class Coin  {
   private int face;

   public Coin ()  {
      flip();
   }

   public void flip ()  {
      face = (int) (Math.random() * 2);
   }

   public boolean isHeads ()  {
      return (face == 0);
   }

   public String toString()  {
      String faceName;

      if (face == 0)
         faceName = "Heads";
      else
         faceName = "Tails";

      return faceName;
   }

   public static void main(String[] args) {
       Coin c = new Coin();
       System.out.println("if face=true then it is a head; if face=false it is a head, and our result is:"+ c.isHeads());
       System.out.println("so it is a:"+c.toString());

    
}
   
}
 
/*

Question 1:
On what line is an example of a constructor method header?
line 13
Question 2:
On what line is an example of an accessor method header?
line 21

Question 3:
On what line is an example of an mutator method header?
line 25

Question 4:
On what line is a field?
line 11

Question 5:
Write a main method for the Coin class and test all of the classes' methods.

*/


