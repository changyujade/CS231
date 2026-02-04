/**
 * Hunter.java
 * create a hunter
 * Jade Chang
 * CS231 Lab B
 * May 8 2022
 */

import java.awt.Graphics;

public class Hunter {

    // Create a Hunter class. It represents the player moving around the cave. The
    // Hunter should store a reference to its current location, which should be a
    // Vertex object. The Hunter's position comes from the Vertex it is on. When the
    // Hunter's current vertex changes, it should update this field. The Hunter is
    // always visible on the map. In the Hunter's draw method, use the position
    // associated with its current Vertex.
    private VertexProject current_location;
    private boolean visible;
    private boolean hasArrow; // if he still has the arrow
    private boolean hitArrow; // if the arrow has hit the arrow
    private boolean isEaten;

    public Hunter(VertexProject current_location) {
        this.current_location = current_location;
        this.visible = true;
        hasArrow = true;
        hitArrow = false;
        isEaten = false;
    }

    public void setPos (VertexProject pos){
        this.current_location = pos;
    }
    public VertexProject getPos(){
        return this.current_location;
    }

    public boolean checkRoom (VertexProject wompusLocation){
        // if current_pos = wompusLocation, print you are eaten
        // set play state = stop
        if (current_location == wompusLocation){
            isEaten=true;
            System.out.println("Game Over");
            return true;
        }
        return false;
    }

    public boolean shoot(VertexProject cur,Wumpus wumpus){
        // check if the arrow is in the same room as the wumpus. 
        // if wumpus.getPos = arrow
        // then hit arrow = true
        // return true, otherwise false
        return false;
    }

    public void draw(Graphics g, int scale) {
        g.drawOval(current_location.getX_pos(), current_location.getY_pos(), 10, 10);
    }
}
