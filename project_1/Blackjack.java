public class Blackjack {
    private Deck deck = new Deck();
    private int reshuffleCutOff;
    private Hand playerHand = new Hand();
    private Hand dealerHand = new Hand();

    public void reset() {
        /// should reset the game. Both the player Hand and dealer Hand should
        // start with no cards. If the number of cards in the deck is less than
        // the reshuffle cutoff, then the method should create a fresh
        // (complete), shuffled deck. Otherwise, it should not modify the deck,
        // just clear the player and dealer hands.

        playerHand.reset();
        dealerHand.reset();
        // deck = new Deck();

        // System.out.println(deck);
        if (deck.size() < reshuffleCutOff) {

            // allocates some new memory for deck.
            // but it also does something else:
            // which means we lose the old deck.
            deck = new Deck();
            // System.out.println("checks the deck value");
            // System.out.println("deck Value:" + deck);
            deck.shuffle();
        }

        /*
         * System.out.println("after reshuffle");
         * deck.shuffle();
         * System.out.println(deck);
         * System.exit(0);
         */
    }

    public Blackjack(int reshuffleCutoff) {
        // the constructor should store the reshuffleCutoff and set up a game.
        // You can avoid duplicate code by calling the reset() method.
        this.reshuffleCutOff = reshuffleCutoff;
        reset();
        deck.shuffle();

    }

    public boolean playerTurn() {
        // have the player draw cards until the total value of the player's hand is
        // equal to or above 16.
        // The method should return false if the player goes over 21 (bust).
        while (playerHand.getTotalValue() <= 16) {
            playerHand.add(deck.deal());
        }
        //System.out.println("playerTurnBoolean: " + (playerHand.getTotalValue() <= 21));
        
        return playerHand.getTotalValue() <= 21;

    }

    public boolean dealerTurn() {
        // have the dealer draw cards until the total of the dealer's hand is equal to
        // or above 17.
        // The method should return false if the dealer goes over 21.
        while (dealerHand.getTotalValue() <= 17) {
            dealerHand.add(deck.deal());
        }
        //System.out.println("dealerTurnBoolean: " + (dealerHand.getTotalValue() <= 21));
        return (dealerHand.getTotalValue() <= 21);
        // will return true if it is smaller than 21, false (bust) if larger than 21
    }

    public void setReshuffleCutoff(int cutoff) {
        // should assign the new cutoff value to the internal reshuffle cutoff field.
        reshuffleCutOff = cutoff;
    }

    public int getReshuffleCutoff() {
        // returns the current value of the reshuffle cutoff field.
        return reshuffleCutOff;
    }

    public String toString() {
        // returns a String that has represents the state of the game.
        // It may be helpful to show the player and dealer hands as well as their
        // current total value.
        String playerValue = "Value on player's hands";
        String dealerValue = "Value on dealer's hands";

        // FIXME: how to loop thorugh the list of cards in hands of both the dealer and
        // the player
        playerValue += "\n";
        playerValue += playerHand.toString();
        dealerValue += "\n";
        dealerValue += dealerHand.toString();

        System.out.println(playerValue);
        System.out.println(dealerValue);

        playerValue += ("total value of player's hand: " + playerHand.getTotalValue());
        dealerValue += "total value of dealer's hand: " + dealerHand.getTotalValue();

        String endValue = playerValue + dealerValue;

        return endValue;
    }

    public void deal() {
        // should deal out two cards to both players from the Deck.

        for (int i = 0; i < 2; i++) {
            playerHand.add(deck.deal());
            dealerHand.add(deck.deal());
        }

    }
    
    /**
     * Print verbose info.
     * 
     * If verbose is true, print out some extra information.
     * Otherwise, outputs nothing. 
     * 
     * The winner value can have three (legal) values, it's one
     * if the player wins, -1 if the dealers wins, and 0 for a draw.
     * 
     * @param winnerValue the value that represents the winner
     * @param verbose whether or not to output
     */
    public void printVerboseInfo(int winnerValue, boolean verbose) { 
        if (!verbose) return;

        // implement if condition.
        if (winnerValue == 1){
            System.out.println("player wins");
            
        }
        else if (winnerValue == -1){
            System.out.println("player loses");
        }
        else{
            System.out.println("it's a tie");

        }

    }

    public int game(boolean verbose) {

        // verbose = telling them results in string
        // if verbose is true;
        // if false then just print the result.

        // the game method should play a single game of Blackjack following the
        // procedure outlined above.
        // The game method should call the reset method at the start of each game.
        // The game method should return a -1 if the dealer wins, 0 in case of a push
        // (tie), and a 1 if the player wins.
        // If the parameter verbose is true, then the game method should print out the
        // initial and final hands of the
        // game and a statement about the result (dealer/push/player).

        reset();
        deal();

        if (verbose){
            System.out.println("initial hand of the player: "+ playerHand.toString());
            System.out.println("initial hand of the dealer: "+ dealerHand.toString());
        }
        

        boolean playerReturn = playerTurn();
        boolean dealerReturn = dealerTurn();


        if (verbose){
            System.out.println("final hand of the player: "+ playerHand.toString()+ ": " + playerHand.getTotalValue());
            System.out.println("final hand of the dealer: "+ dealerHand.toString() + ": " + dealerHand.getTotalValue());
        }
        // int dealerValue = dealerHand.getTotalValue();

        // did the player bust?
        // if they did, then return -1 now.

        if (!playerReturn) {
            printVerboseInfo(-1, verbose);
            return -1;

        }
        // otherwise, have the dealer do his turn.
        // check if the dealer busted. if he busted, return 1 now.



        if (!dealerReturn) {

            printVerboseInfo(1, verbose);
            // the player wins
            return 1;

        }
        // then if neither bust.
        // then compare hands

        int playerValue = playerHand.getTotalValue();
        int dealerValue = dealerHand.getTotalValue();
        int winningValue;

        if (playerValue > dealerValue) {
            winningValue = 1;

        } 
        else if (playerValue < dealerValue) {
            winningValue = -1;
        }
        // it is a tie because the values are the same
        else{
            winningValue = 0;
        }
        printVerboseInfo(winningValue, verbose);

        return winningValue;

    }

    public static void main(String[] args) {

        // create a blackjack class within the loop
        // create a new game

        Blackjack blackjack = new Blackjack(26);
        blackjack.game(true);
        //System.out.println();
        // System.out.println(blackjack.toString());

        // System.out.println(blackjack.game());
        // System.out.println(blackjack.toString());

    }

}
