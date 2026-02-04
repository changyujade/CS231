/**
 * Die.java
 * Represents a 6 sided die
 * Jade Chang
 * CS231 S22
 * Lecture 2
 */

public class Die {
    // a field that specifies the number of surfaces on a die.
    private int faces;

    // a non-argument constructor that specifies the default number of surfaces
    public Die() {
        faces = 6;
    }

    // a accessor method that returns the information of an object
    public int getFaces() {
        return faces;
    }

    // a mutator method that changes the field's value
    public void setFaces(int numFaces) {
        faces = numFaces;
    }

    /*
     * clone method
     * this method is accessible outside of this class
     * no parameters
     * returns a new Die with the same number of faces as this die
     */

    public Die clone() {
        // create a new instance of die
        Die clonedDie = new Die();
        // get the number of faces of the current instance
        // assigning the number of faces to the new (cloned) instance.
        clonedDie.setFaces(getFaces());

        // return a new cloned die.
        return clonedDie;
    }

    /*
     * equals method
     * this method is accessible outside of this class
     * input parameter is a Die
     * returns a boolean
     * check to see if this die has the same number of faces as the input die
     */

     /**
      * check to see if this die has the same number of faces as the input die
      * @param otherDie the die to compare with 
      * @return true if the die has the same number of faces of the other die
      */
    public boolean equals(Die otherDie) {
        return getFaces() == otherDie.getFaces();
    }

    /*
     * roll method
     * this method is accessible outside of this class
     * no parameters
     * returns an integer (result of die roll)
     * roll the die using the random function from the math package
     * Math package documentation
     * https://docs.oracle.com/javase/8/docs/api/java/lang/Math.html
     */

    public int roll() {
        return generateRandomInt(1, getFaces() + 1);
    }

    /**
     * Create a random integer between [min, max).
     * 
     * Note: whatever we pass for max, will never be generated.
     * @param min the minimum possible value
     * @param max the exclusive maximum possible value.
     * @return an integer between [min, max)
     */
    public static int generateRandomInt(int min, int max) {
        // if we python translation
        // random.randint(1, 7) // that gives us [1, 6] or [1, 7)
        // randomNumber * (7 - 1) // that gives us [0, 6). [0, 5.9999999999999]
        // if we add one:
        // randomNumber * (7 - 1) + 1 // that gives us [1, 7), [1.0, 6.99999999999]
        // and then if we truncate the decimal (round towards -infinity)
        // (int) (randomNumber * (7 - 1) + 1) which gives us [1, 6]
        // what we want to do first is change the range from [0, 1) to [0, max - min)

        // and then, we'll want to move the number on the number line such that
        // we have [0, max-min) -> [min, max)
        // to do that, all we need to do is add the min.

        return (int) (Math.random() * (max - min) + min);
    }

    /*
     * main method
     * tests all die methods
     */

    public static void main(String[] args) {
        Die myDie = new Die();
        Die newDie = myDie.clone();

        System.out.println("myDie == newDie: " + myDie.equals(newDie));

        // we should see 6 faces for both
        System.out.println("the original die has " + myDie.getFaces() + " faces");
        System.out.println("the new (cloned) die has " + newDie.getFaces() + " faces");

        // set the faces of only the clone
        newDie.setFaces(8);

        System.out.println("myDie == newDie: " + myDie.equals(newDie));

        // we should see 6 and 8 faces.
        System.out.println("the original die has " + myDie.getFaces() + " faces");
        System.out.println("the new (cloned after setting number of faces) die has " + newDie.getFaces() + " faces");

        for (int i = 0; i < 50; i++) {
            System.out.println(newDie.roll());
        }
    }

}