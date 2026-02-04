/**
 * VertexProject.java
 * create verticies for the HuntTheWumpus Game (different from the vertex from the lab)
 * Jade Chang
 * CS231 Lab B
 * May 8 2022
 */

import java.util.ArrayList;
import java.awt.Graphics;
import java.awt.Color;

public class VertexProject implements Comparable<VertexProject> {

    private VertexProject[] neighbors;
    private ArrayList<VertexProject> adjacencyList;
    private int x_pos;
    private int y_pos;
    private boolean visible;
    private double cost;
    private double distance;
    private boolean visited;
    private VertexProject parent;

    public VertexProject() {
        this.adjacencyList = new ArrayList<>();
        this.neighbors = new VertexProject[4];
        this.x_pos = -1;
        this.y_pos = -1;
        this.visible = true; // change back to false later
        this.cost = Double.MAX_VALUE;
        this.visited = false;
        this.parent = null;
    }

    public VertexProject(int i, int j, boolean b) {
        this.adjacencyList = new ArrayList<>();
        this.neighbors = new VertexProject[4];
        x_pos = i;
        y_pos = j;
        visited = b;
        this.parent = null;
    }

    public VertexProject(int i, int j) {
        this.adjacencyList = new ArrayList<>();
        x_pos = i;
        y_pos = j;
        this.parent = null;
        this.neighbors = new VertexProject[4];
        this.visited = false;
        this.cost = Double.MAX_VALUE;

    }

    // returns true if the x and y positions of the two vertices match.
    public static boolean matchPosition(VertexProject a, VertexProject b) {
        return a.getX_pos() == b.getX_pos() && a.getY_pos() == b.getY_pos();
    }

    // : returns the Euclidean distance between this vertex and the other vertex
    // based on their x and y positions.
    public double distance(VertexProject other) {
        double ac = Math.abs(other.getY_pos() - this.getY_pos());
        double cb = Math.abs(other.getX_pos() - this.getX_pos());

        return Math.hypot(ac, cb);
    }

    // : updates this vertex' adjacency list/map so that it connects with the other
    // Vertex. This is a uni-directional link.
    public void connect(VertexProject other, Direction d) {
        neighbors[d.ordinal()] = other;
    }

    // : returns the Vertex at position (x, y) if the Vertex is in the adjacency
    // list, otherwise null.
    public VertexProject[] getNeighbor(int x, int y) {
        return neighbors;
    }

    public VertexProject getNeighbor(int z) {
        return neighbors[z];
    }

    public VertexProject getNorth() {
        return neighbors[0];
    }

    public VertexProject getSouth() {
        return neighbors[1];
    }

    public VertexProject getEast() {
        return neighbors[2];
    }

    public VertexProject getWest() {
        return neighbors[3];
    }

    // : returns an ArrayList of type Vertex which contains all of this Vertex'
    // neighbors.
    public ArrayList<VertexProject> getNeighbors() {
        return adjacencyList;

    }

    // : returns the number of connected vertices.
    public int numNeighbors() {
        return adjacencyList.size();

    }

    // returns a String containing (at least) the number of neighbors, this Vertex'
    // cost, and the marked flag.
    public String toString() {
        return "x_pos"+ this.getX_pos()+"\n"+
        "y_pos"+ this.getY_pos()+"\n"+
        "number of neighbors: " + numNeighbors() + "\n"
                + "cost: " + cost + "\n"
                + "marked flag: " + this.visited + "\n";
    }

    @Override
    public int compareTo(VertexProject other) {
        // returns a value < 0 if this vertex comes before other, 0 if this is equal to
        // other, and a value > 0 if this comes after other.
        double diff = this.getCost() - other.getCost();
        return (int) Math.signum(diff);
    }

    public ArrayList<VertexProject> getAdjacencyList() {
        return adjacencyList;
    }

    public void setAdjacencyList(ArrayList<VertexProject> adjacencyList) {
        this.adjacencyList = adjacencyList;
    }

    public int getX_pos() {
        return x_pos;
    }

    public VertexProject setX_pos(int x_pos) {
        this.x_pos = x_pos;
        return this;
    }

    public int getY_pos() {
        return y_pos;
    }

    public VertexProject setY_pos(int y_pos) {
        this.y_pos = y_pos;
        return this;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public VertexProject getParent() {
        return parent;
    }

    public void setParent(VertexProject parent) {
        this.parent = parent;
    }

    public void draw(Graphics g, int scale) {
        if (!this.visible)
            return;
        int xpos = (int) this.getX_pos() * scale;
        int ypos = (int) this.getY_pos() * scale;
        int border = 2;
        int half = scale / 2;
        int eighth = scale / 8;
        int sixteenth = scale / 16;

        // draw rectangle for the walls of the room
        if (this.getCost() <= 2)
            // wumpus is nearby
            g.setColor(Color.red);
        else
            // wumpus is not nearby
            g.setColor(Color.black);

        g.drawRect(xpos + border, ypos + border, scale - 2 * border, scale - 2 * border);

        // draw doorways as boxes
        g.setColor(Color.black);
        if (this.getNorth() != null) {// North
            //System.out.println("North if");
            g.fillRect(xpos + half - sixteenth, ypos, eighth, eighth + sixteenth);
        }

        if (this.getSouth()!=null) {//South
            g.fillRect(xpos + half - sixteenth, ypos + scale - (eighth + sixteenth),eighth, eighth + sixteenth);
            //System.out.println("South");
        }
        if (this.getWest() != null) // West
        
            g.fillRect(xpos, ypos + half - sixteenth, eighth + sixteenth, eighth);
        if (this.getEast() != null) // East
            g.fillRect(xpos + scale - (eighth + sixteenth), ypos + half - sixteenth,eighth + sixteenth, eighth);
    }

    public static void main(String[] args) {
        VertexProject vertex = new VertexProject();
    }

}