/*
  Originally written by Bruce A. Maxwell a long time ago.
  Updated by Brian Eastwood and Stephanie Taylor more recently

  Creates a window using the JFrame class.

  Creates a drawable area in the window using the JPanel class.

  The JPanel calls the Landscape's draw method to fill in content, so the
  Landscape class needs a draw method.
*/

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * Displays a Landscape graphically using Swing. In this version, we do not
 * assume
 * the Landscape is a grid.
 */
public class LandscapeDisplay {
    protected JFrame win;
    protected Landscape scape;
    private LandscapePanel canvas;

    /**
     * Initializes a display window for a Landscape.
     * 
     * @param scape the Landscape to display
     */
    public LandscapeDisplay(Landscape scape) {
        // setup the window
        this.win = new JFrame("Agents");
        this.win.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.scape = scape;

        // create a panel in which to display the Landscape
        this.canvas = new LandscapePanel(this.scape.getWidth(),
                this.scape.getHeight());

        // add the panel to the window, layout, and display
        this.win.add(this.canvas, BorderLayout.CENTER);
        this.win.pack();
        this.win.setVisible(true);
    }

    /**
     * Saves an image of the display contents to a file. The supplied
     * filename should have an extension supported by javax.imageio, e.g.
     * "png" or "jpg".
     *
     * @param filename the name of the file to save
     */
    public void saveImage(String filename) {
        // get the file extension from the filename
        String ext = filename.substring(filename.lastIndexOf('.') + 1, filename.length());

        // create an image buffer to save this component
        Component tosave = this.win.getRootPane();
        BufferedImage image = new BufferedImage(tosave.getWidth(), tosave.getHeight(),
                BufferedImage.TYPE_INT_RGB);

        // paint the component to the image buffer
        Graphics g = image.createGraphics();
        tosave.paint(g);
        g.dispose();

        // save the image
        try {
            ImageIO.write(image, ext, new File(filename));
        } catch (IOException ioe) {
            System.out.println(ioe.getMessage());
        }
    }

    /**
     * This inner class provides the panel on which Landscape elements
     * are drawn.
     */
    private class LandscapePanel extends JPanel {
        /**
         * Creates the panel.
         * 
         * @param width  the width of the panel in pixels
         * @param height the height of the panel in pixels
         */
        public LandscapePanel(int width, int height) {
            super();
            this.setPreferredSize(new Dimension(width, height));
            this.setBackground(Color.white);
        }

        /**
         * Method overridden from JComponent that is responsible for
         * drawing components on the screen. The supplied Graphics
         * object is used to draw.
         * 
         * @param g the Graphics object used for drawing
         */
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            scape.draw(g);
        } // end paintComponent

    } // end LandscapePanel

    public void repaint() {
        this.win.repaint();
    }

    // test function that creates a new LandscapeDisplay and populates it with 200
    // agents.
    public static void main(String[] args) throws InterruptedException {
        Landscape scape = new Landscape(500, 500);
        Random gen = new Random();

        /*
         * for(int i=0;i<200;i++) {
         * scape.addAgent( new SocialAgent( gen.nextDouble() * scape.getWidth(),
         * gen.nextDouble() * scape.getHeight(),15) );
         * }
         */

        SocialAgent sa5 = new SocialAgent(5, 5, 15);

        scape.addAgent(sa5);
        scape.addAgent(new SocialAgent(10, 10, 15));
        scape.addAgent(new SocialAgent(15, 15, 15));
        scape.addAgent(new SocialAgent(20, 20, 15));

        System.out.println("" + sa5.getX() + sa5.getY());
        int number = scape.getNeighbors(sa5.getX(), sa5.getY(), 25).size();
        sa5.updateState(scape);
        System.out.println("x and y coordinates of social agent 5: " + sa5.getX() + " " + sa5.getY());
        // int number = scape.getNeighbors(sa5.getX(), sa5.getY(), 10).size();

        // FIXME: why would the number of neighbours be a specific number if it moves
        // randomly?
        // FIXME: not printing correctly onto the screen!!
        System.out.println("number of neighbours:" + number);

        // sa5.setX(5);
        // sa5.setY(5);
        // linkedlist.clear();

        LandscapeDisplay display = new LandscapeDisplay(scape);

        display.repaint();
        
    }
}
