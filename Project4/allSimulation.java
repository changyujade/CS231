import java.util.Random;

public class allSimulation {

    public static void main(String[] args) throws InterruptedException {

        Landscape scape = new Landscape(500, 500);

        Random gen = new Random();

        String userInput = args[0];
        String radius = args[1];
        System.out.println(userInput);
        int radiusInt = Integer.parseInt(radius);
        if (userInput.equals("c")) {
            System.out.println("creating 200 cats");
            for (int i = 0; i < 200; i++) {
                int cat = gen.nextInt(2);
                scape.addAgent(new CatSocialAgent(gen.nextDouble() * scape.getWidth(),
                        gen.nextDouble() * scape.getHeight(), radiusInt, cat));
            }
        }

        else if (userInput.equals("s")) {
            for (int i = 0; i < 200; i++) {
                scape.addAgent(new SocialAgent(gen.nextDouble() * scape.getWidth(),
                        gen.nextDouble() * scape.getHeight(), radiusInt));
            }
        }

        LandscapeDisplay display = new LandscapeDisplay(scape);
        Thread.sleep(250);

        for (int i = 0; i < 200; i++) {
            // scape.advance();
            scape.updateAgent();
            display.repaint();
            Thread.sleep(250);
            // display.saveImage("data/life_frame_" + String.format("%03d", i) + ".png");
        }

    }
    // memory of Landscape, Random, LandscapeDisplay is lost when we exit the main
    // method.
}