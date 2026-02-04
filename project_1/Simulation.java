public class Simulation {

    public static void main(String[] args) {
        // we create a place which allocates memory for blackjack.
        Blackjack blackjack = new Blackjack(26);
        int win = 0;
        int tie = 0;
        int lose = 0;

        for (int i = 0; i < 1000; i++) {
            int result = blackjack.game(false);
            if (result == -1) {
                lose += 1;
            } else if (result == 0) {
                tie += 1;
            } else {
                win += 1;
            }

        }
        System.out.println("lose: " + lose + "\n" + "tie: " + tie + "\n" + "win: " + win);
        double d = win;
        System.out.println("percentage: " + (d / 10));

        // after this line, there's no way to use the memory which blackjack allocated,
        // therefore, at this point, the memory is lost.
    }
}
