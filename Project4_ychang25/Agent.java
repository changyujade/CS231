/**
 * Agent.java
 * create an agent objects
 * Jade Chang
 * CS231 Lab B
 * Mar 18 2022
 */
import java.awt.Graphics;

public class Agent {

    private double pos_x;
    private double pos_y;

    // a constructor that sets the position.
    public Agent(double x0, double y0) {
        pos_x = x0;
        pos_y = y0;
    }

    // returns the x position.
    public double getX() {
        return pos_x;
    }

    // returns the y position.
    public double getY() {
        return pos_y;
    }

    // sets the x position.
    public void setX(double newX) {
        pos_x = newX;

    }

    // sets the y position.
    public void setY(double newY) {
        pos_y = newY;

    }

    // returns a String containing the x and y positions, e.g. "(3.024, 4.245)".
    public String toString() {

        return (pos_x + "," + pos_y);
    }

    
    // does nothing, for now.
    public void updateState(Landscape scape) {

    }

    // does nothing, for now.
    public void draw(Graphics g) {
    }
    

    public static void main(String[] args) {
        Agent agent = new Agent(0.5, 3.5);
        System.out.println("x pos of agent: " + agent.getX());
        System.out.println("y pos of agent: " + agent.getY());

        agent.setX(5.3);
        agent.setY(9.7);
        System.out.println("after resetting, the position becomes" + agent.toString());
    }

}
