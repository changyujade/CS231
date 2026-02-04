import java.awt.Graphics;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;

public class CatSocialAgent2 extends SocialAgent {

    private int category;
    private boolean moved;

    public CatSocialAgent2(double x0, double y0, int radius, int cat) {
        // FIXME: do we need a parameter of radius in the constructor?
        super(x0, y0, radius);
        category = cat;
    }

    // returns the category value.
    public int getCategory() {
        return category;

    }

    // returns a single character string indicating the category.
    public String toString() {
        String s = "" + category;
        return s;

    }

    // draws a circle of radius 5 (i.e. it fits in a 10x10 box) at the Agent's
    // location. Different categories should have different colors, and each
    // category will need a darker color (didn't move) and a lighter color (moved).
    public void draw(Graphics g) {
        int x = (int) getX();
        int y = (int) getY();
        if (moved) {
            g.setColor(Color.MAGENTA);
            g.fillOval(x, y, 5, 5);
        } else {
            g.setColor(Color.RED);
            g.fillOval(x, y, 5, 5);
        }
    }

    // implements the following rule.
    public void updateState(Landscape scape) {
        Random rand = new Random();
        int number = scape.getNeighbors(this.getX(), this.getY(), (double) this.getRadius()).size();
        ArrayList<Agent> neighbours = scape.getNeighbors(this.getX(), this.getY(), (double) this.getRadius());
        double start = -10;
        double end = 10;
        double x = start + (rand.nextDouble() * (end - start));
        double y = start + (rand.nextDouble() * (end - start));
        // System.out.println(result);
        // FIXME: more of them has the same category.
        // how many of the cells in the same radius have the same category. 
        // get the agents by looping through the list. 

        int count=0;
        for (Agent item : neighbours ) {
            if (this.getCategory() == ((CatSocialAgent2) item).getCategory()){
                count +=1;
                moved = true;
                this.setX(item.getX());
                this.setY(item.getY());
            }

        }
        if (number >= 2 && count>(number/2)) {
            // var d = Math.random();
            int chance = rand.nextInt(100);
            if (chance == 1) {
                moved = true;
                this.setX(getX() + x);
                this.setY(getY() + y);
            } else {
                moved = false;
            }

        } else {
            moved = true;
            this.setX(getX() + x);
            this.setY(getY() + y);
        }

    }

}
