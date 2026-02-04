import java.awt.Graphics;
import java.awt.Color;
import java.util.Random;
import java.lang.Math;

public class SocialAgent extends Agent {

    // a field that specifies the radius R within which it considers another agent a
    // neighbor.
    // FIXME: should the fields be private or public?
    private int radius;
    private boolean moved;

    public SocialAgent(double x0, double y0, int radiusR) {
        super(x0, y0);
        this.radius = radiusR;
    }

    // sets the cell's radius of sensitivity to the value of radius.
    public void setRadius(int radius) {
        this.radius = radius;
    }

    // returns the cell's radius of sensitivity.
    public int getRadius() {
        return this.radius;

    }

    // draws a circle of radius 5 (i.e. it fits in a 10x10 box) at the Agent's
    // location.
    // Pick two colors for the agent, a darker and a lighter version of the same hue
    // (e.g. dark blue and light blue). If the agent moved during the
    // last updateState, it should be drawn with the lighter color, otherwise the
    // darker color.
    public void draw(Graphics g) {
        int x = (int) getX();
        int y = (int) getY();
        if (moved) {
            g.setColor(Color.cyan);
            g.fillOval(x, y, 5, 5);
        } else {
            g.setColor(Color.blue);
            g.fillOval(x, y, 5, 5);
        }
        //g.fillOval(x, y, 5, 5);
    }

    public void updateState(Landscape scape) {
        // the number of agents
        //FIXME: potential logical error?
        Random rand = new Random();
        int number = scape.getNeighbors(this.getX(), this.getY(), (double) this.radius).size();
        double start = -10;
        double end = 10;
        double x = start + (rand.nextDouble() * (end - start));
        double y = start + (rand.nextDouble() * (end - start));
     
        if (number > 3) {
            //var d = Math.random();
            int chance = rand.nextInt(100);
            if (chance == 1) {
                moved = true;
                this.setX(getX() + x);
                this.setY(getY() + y);
            }
            else{
                moved = false;
            }

        } else {
            moved = true;
            this.setX(getX() + x);
            this.setY(getY() + y);
        }

    }

    public static void main(String[] args) {
        /*
         * //SocialAgent sa = new SocialAgent(5, 5.3, 6.5);
         * sa.setRadius(6);
         * System.out.println("new radius:" + sa.getRadius());
         */

    }

}
