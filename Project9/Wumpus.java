/**
 * Wumpus.java
 * create a wumpus
 * Jade Chang
 * CS231 Lab B
 * May 8 2022
 */

public class Wumpus {
    private VertexProject home;
    private boolean visible;

    public Wumpus(VertexProject home) {
        // Define a Wumpus class
        // Create a Wumpus class. It represents the Wumpus; in the default game the
        // Wumpus does not move. The Wumpus should also have a reference to its home
        // vertex. Unlike the Hunter, the Wumpus is visible only if it is killed by the
        // Hunter or it eats the Hunter. Until then, it should not be drawn. Somehow,
        // you need to pass in the visibility information to the Wumpus, including
        // whether it should adopt a victorious pose or play dead
        this.home = home;
        this.visible = false;
    }

    public VertexProject getPos(){
        return this.home;
    }

}
