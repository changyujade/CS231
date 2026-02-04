/**
 * Graph.java
 * create a graph
 * Jade Chang
 * CS231 Lab B
 * May 8 2022
 */

import java.util.ArrayList;
import java.util.PriorityQueue;

public class Graph {
    private ArrayList<Vertex> verticies;

    public Graph() {
        verticies = new ArrayList<>();
    }

    // : returns the number of vertices in the graph.
    public int vertexCount() {
        return verticies.size();
    }

    // : return true if the query Vertex is in the graph's vertex list.
    public boolean inGraph(Vertex query) {
        for (Vertex e : verticies) {
            if (e == query) {
                return true;
            }
        }
        return false;

    }

    // : adds v1 and v2 to the graph (if necessary) and adds an edge connecting v1
    // to v2, creating a uni-directional link.
    public void addUniEdge(Vertex v1, Vertex v2) {
        if (!inGraph(v1)) {
            verticies.add(v1);
        }
        if (!inGraph(v2)) {
            verticies.add(v2);
        }
        v1.connect(v2);

    }

    // : adds v1 and v2 to the graph (if necessary), adds an edge connecting v1 to
    // v2, and adds a second edge connecting v2 to v1, creating a bi-directional
    // link.
    public void addBiEdge(Vertex v1, Vertex v2) {
        if (!inGraph(v1)) {
            verticies.add(v1);
        }
        if (!inGraph(v2)) {
            verticies.add(v2);
        }
        v1.connect(v2);
        v2.connect(v1);
    }

    // : implements a single-source shortest-path algorithm for the Graph,
    // Dijkstra's algorithm.
    public void shortestPath(Vertex v0) {
        for (Vertex e : verticies) {
            e.setCost(Double.MAX_VALUE);
            e.setParent(null);
            e.setVisited(false);
        }
        // q = list of all nodes in graph and their cost (cost of start node is 0, all
        // other nodes infinite)
        // Initialize all vertices in G to be unmarked, have a large cost, and a null
        // parent (A large cost can be 1e+7)
        PriorityQueue<Vertex> pq = new PriorityQueue<>();
        v0.setCost(0);
        pq.add(v0);

        while (!(pq.isEmpty())) {
            // remove v from pq where v is the vertex with lowest cost
            Vertex v = pq.remove();
            if (v.isVisited()) {
                continue;
            }

            v.setVisited(true);

            for (Vertex w : v.getAdjacencyList()) {
                double distance = v.distance(w);
                if (w.isVisited() == false && (v.getCost() + distance < w.getCost())) {
                    w.setCost(v.getCost() + distance);
                    w.setParent(v);
                    pq.add(w);
                }
            }

        }

    }

    public ArrayList<Vertex> getVertices() {
        return verticies;
    }

    public static void main(String[] args) {
        // Test 1
        // make the vertices
        Vertex v00 = new Vertex(0, 0, false);
        Vertex v01 = new Vertex(0, 1, false);
        Vertex v02 = new Vertex(0, 2, false);
        Vertex v03 = new Vertex(0, 3, false);
        Vertex v10 = new Vertex(1, 0, false);
        Vertex v11 = new Vertex(1, 1, false);
        Vertex v12 = new Vertex(1, 2, false);
        Vertex v13 = new Vertex(1, 3, false);
        Vertex v20 = new Vertex(2, 0, false);
        Vertex v21 = new Vertex(2, 1, false);
        Vertex v22 = new Vertex(2, 2, false);
        Vertex v23 = new Vertex(2, 3, false);
        Vertex v30 = new Vertex(3, 0, false);
        Vertex v31 = new Vertex(3, 1, false);
        Vertex v32 = new Vertex(3, 2, false);
        Vertex v33 = new Vertex(3, 3, false);

        // build the graph
        Graph g = new Graph();

        g.addBiEdge(v00, v10);
        g.addBiEdge(v10, v20);
        g.addBiEdge(v20, v30);
        g.addBiEdge(v30, v31);
        g.addBiEdge(v31, v32);
        g.addBiEdge(v32, v33);
        g.addBiEdge(v33, v23);
        g.addBiEdge(v23, v13);
        g.addBiEdge(v13, v03);
        g.addBiEdge(v03, v02);
        g.addBiEdge(v02, v01);
        g.addBiEdge(v00, v01);

        // print out before shortestPath
        ArrayList<Vertex> set = g.getVertices();

        System.out.println("Before shortestPath");
        for (Vertex v : set) {
            System.out.println(v);
        }

        // run shortest path from node (0, 0)
        g.shortestPath(v00);

        // print out after shortest path
        System.out.println("\nAfter shortestPath");
        for (Vertex v : set) {
            System.out.println(v);
        }

        // Test 2

        int Nx = 4, Ny = 4;
        Vertex grid[][] = new Vertex[Ny][Nx];
        for (int i = 0; i < Ny; i++) {
            for (int j = 0; j < Nx; j++) {
                grid[i][j] = new Vertex(i, j, false);
            }
        }

        g = new Graph();
        g.addBiEdge(grid[0][0], grid[1][0]);
        g.addBiEdge(grid[1][0], grid[2][0]);
        g.addBiEdge(grid[2][0], grid[3][0]);
        g.addBiEdge(grid[3][0], grid[3][1]);
        g.addBiEdge(grid[3][1], grid[3][2]);
        g.addBiEdge(grid[3][2], grid[3][3]);
        g.addBiEdge(grid[3][3], grid[2][3]);
        g.addBiEdge(grid[2][3], grid[1][3]);
        g.addBiEdge(grid[1][3], grid[0][3]);
        g.addBiEdge(grid[0][3], grid[0][2]);
        g.addBiEdge(grid[0][2], grid[0][1]);
        g.addBiEdge(grid[0][0], grid[0][1]);

        g.shortestPath(grid[1][0]);

        System.out.println("\nTest 2:  root = (1, 1)\n");
        String s = "";
        for (int i = 0; i < Ny; i++) {
            String t = "";
            for (int j = 0; j < Nx; j++) {
                s += grid[i][j].getCost();
                if (j < Nx - 1) {
                    s += " -- ";
                }
                t += " |     ";
            }
            s += "\n";
            if (i < Ny - 1) {
                s += t + "\n";
            }
        }
        System.out.println(s);
    }
}
