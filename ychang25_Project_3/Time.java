/**
 * Time.java
 * Calculating the number of successful solves
 * Jade Chang
 * CS231 Lab B
 * Mar 7 2022
 */

public class Time {
    public static void main(String[] args) {
        int numSol=0;
        
        for (int i=0;i<50;i++){
            
            long startTime = System.nanoTime();
            Sudoku sudoku = new Sudoku(5);
        
            //System.out.println(sudoku.toString());
        
            if (sudoku.solve()){
                numSol+=1;
                long endTime = System.nanoTime();
                System.out.println("Took "+(endTime - startTime) + " ns"); 
            }

        }
        System.out.println("Number of successful Solves: " + numSol);
       

    }
  

    
}
