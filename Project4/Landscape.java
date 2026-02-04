import java.util.ArrayList;
import java.lang.Math;
import java.awt.Graphics;

public class Landscape {

    private int width;
    private int height;
    private LinkedList<Agent> linkedList;

    // a constructor that sets the width and height fields, and initializes the
    // agent list.
    public Landscape(int w, int h) {
        width = w;
        height = h;
        this.linkedList = new LinkedList<Agent>();
    }

    // returns the height.
    public int getHeight() {
        return height;

    }

    // returns the width.
    public int getWidth() {
        return width;

    }

    // inserts an agent at the beginning of its list of agents.
    public void addAgent(Agent a) {
        linkedList.addFirst(a);

    }

    // returns a String representing the Landscape. It can be as simple as
    // indicating the number of Agents on the Landscape.
    public String toString() {
        String str = "";
        String number = str + linkedList.findlength();
        return number;

    }

    // returns a list of the Agents within radius distance of the location x0, y0.
    public ArrayList<Agent> getNeighbors(double x0, double y0, double radius) {

        ArrayList<Agent> neighbors = new ArrayList<>();

        for (Agent item : linkedList) {
            // System.out.printf("thing %d\n", item);
            // need to not
            // FIXME: potential logic error?
            //
            if (Math.hypot(item.getX() - x0, item.getY() - y0) <= radius && item.getX() != x0 && item.getY() != y0) {
                neighbors.add(item);
            }
            

            //System.out.println("x:" + item.getX() + "y:" + item.getY());
        }
        return neighbors;

    }

    // Calls the draw method of all the agents on the Landscape.
    public void draw(Graphics g) {
        for (Agent item : linkedList) {
            item.draw(g);
        }
    }

    public void updateAgent() {

        ArrayList<Agent> l = linkedList.toShuffledList();
        
        for (Agent item : l) {
            item.updateState(this);
        }
    }

    public static void main(String[] args) {

    }

}
