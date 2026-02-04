/**
 * Vertex.java
 * create a vertex for the graph
 * Jade Chang
 * CS231 Lab B
 * May 8 2022
 */

import java.util.ArrayList;
import java.awt.Graphics;
import java.awt.Color;


public class Vertex implements Comparable<Vertex> {

    public static final String Direction = null;
    private Vertex[] neighbors;
    private ArrayList<Vertex> adjacencyList;
    private int x_pos;
    private int y_pos;
    private boolean visible;
    private double cost;
    private double distance;
    private boolean visited;
    private Vertex parent;

    public enum Direction {
        NORTH, SOUTH, EAST, WEST
    }

    public Vertex() {
        this.adjacencyList = new ArrayList<>();
        this.x_pos = -1;
        this.y_pos = -1;
        this.visible = true;
        this.cost = Double.MAX_VALUE;
        this.visited = false;
        this.parent = null;
    }

    public Vertex(int i, int j, boolean b) {
        this.adjacencyList = new ArrayList<>();
        this.neighbors = new Vertex[4];
        x_pos = i;
        y_pos = j;
        visited = b;
        this.parent = null;
    }



    public Vertex(int i, int j) {
        this.adjacencyList = new ArrayList<>();
        x_pos = i;
        y_pos = j;
        this.parent = null;

    }

    // returns true if the x and y positions of the two vertices match.
    public static boolean matchPosition(Vertex a, Vertex b) {
        return a.getX_pos() == b.getX_pos() && a.getY_pos() == b.getY_pos();
    }

    // : returns the Euclidean distance between this vertex and the other vertex
    // based on their x and y positions.
    public double distance(Vertex other) {
        double ac = Math.abs(other.getY_pos() - this.getY_pos());
        double cb = Math.abs(other.getX_pos() - this.getX_pos());

        return Math.hypot(ac, cb);
    }

    // : updates this vertex' adjacency list/map so that it connects with the other
    // Vertex. This is a uni-directional link.
    public void connect(Vertex other) {
        adjacencyList.add(other);

    }

    // : returns the Vertex at position (x, y) if the Vertex is in the adjacency
    // list, otherwise null.
    public Vertex getNeighbor(int x, int y) {
        for (Vertex v : adjacencyList) {
            if (v.getX_pos() == x && v.getY_pos() == y) {
                return v;
            }
        }
        return null;

    }

    // : returns an ArrayList of type Vertex which contains all of this Vertex'
    // neighbors.
    public ArrayList<Vertex> getNeighbors() {
        return adjacencyList;

    }

    // : returns the number of connected vertices.
    public int numNeighbors() {
        return adjacencyList.size();

    }

    // returns a String containing (at least) the number of neighbors, this Vertex'
    // cost, and the marked flag.
    public String toString() {
        return "vertex:"+ x_pos+ ","+y_pos+ "\n"+
        "number of neighbors: " + numNeighbors() + "\n"
                + "cost: " + cost + "\n"
                + "marked flag: " + this.visited + "\n";
    }

    @Override
    public int compareTo(Vertex other) {
        // returns a value < 0 if this vertex comes before other, 0 if this is equal to
        // other, and a value > 0 if this comes after other.
        double diff = this.getCost() - other.getCost();
        return (int) Math.signum(diff);
    }

    public ArrayList<Vertex> getAdjacencyList() {
        return adjacencyList;
    }

    public void setAdjacencyList(ArrayList<Vertex> adjacencyList) {
        this.adjacencyList = adjacencyList;
    }

    public int getX_pos() {
        return x_pos;
    }

    public Vertex setX_pos(int x_pos) {
        this.x_pos = x_pos;
        return this;
    }

    public int getY_pos() {
        return y_pos;
    }

    public Vertex setY_pos(int y_pos) {
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

    public Vertex getParent() {
        return parent;
    }

    public void setParent(Vertex parent) {
        this.parent = parent;
    }

    public static void main(String[] args) {
        Vertex vertex = new Vertex();
        vertex.connect(new Vertex().setX_pos(-3).setY_pos(-3));
        vertex.connect(new Vertex().setX_pos(-2).setY_pos(-3));
        vertex.connect(new Vertex().setX_pos(-1).setY_pos(-1));

        System.out.println(vertex.getNeighbor(-1, -1));
    }

}