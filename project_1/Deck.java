import java.util.ArrayList;
import java.util.Random;

public class Deck {

    private ArrayList<Card> cards;

    public Deck() {
        // builds a deck of 52 cards, 4 each of cards with values 2-9 and 11, and 16
        // cards with the value 10. Note, you probably want the constructor to call the
        // build() method, below
        build();
    }

    public void build() {
        // builds a deck of 52 cards, 4 each of cards with values 2-9 and 11, and 16
        // cards with the value 10.
        // allocate memory for a list of cards.
        cards = new ArrayList<Card>();
        for (int i = 0; i < 4; i++) {
            for (int n = 2; n <= 9; n++) {
                // allocates memory for a Card.
                Card myCard = new Card(n);
                cards.add(myCard);
            }
            // allocates memory for a Card.
            Card myCard = new Card(11);
            cards.add(myCard);
        }
        for (int i = 0; i < 16; i++) {
            // allocates memory for a Card.
            Card myCard = new Card(10);
            cards.add(myCard);
        }
    }

    public int size() {
        // returns the number of cards in the deck.
        return cards.size();
    }

    public Card deal() {
        // returns the top card (position zero)
        return pick(0);
    }

    public Card pick(int i) {
        // (optional) returns the card at position i and removes it from the deck.
        return cards.remove(i);
    }

    public void shuffle() {
        // shuffles the deck. This method should put the deck in random order. One way
        // to do it is to build a fresh second deck and then make n random picks from
        // it, where n is the size of the deck at the time of the shuffle. Note that the
        // first pick should be in the range [0, n-1], the second pick should be in the
        // range [0, n-2] and so on. You may want to generate a seed for the Random
        // object by using the function System.currentTimeMillis() so that each run
        // of a game is different. You are expected to implement the shuffle method.
        // Please do not use Collection.shuffle()
        
        // allocates memory for Random. 
        Random ran = new Random();
        // allocates memory for ArrayList
        ArrayList<Card> shuffledCards = new ArrayList<>();
        while (cards.size() > 0) {

            int val = ran.nextInt(cards.size());
            Card removedCard = cards.remove(val);
            shuffledCards.add(removedCard);
            // System.out.println("item removed: " + removedCard +
            // " the remaining items in list:" + cards);
            // System.out.println("Shuffled List: " + shuffledCards);

        }
        //the last list stored in cards is lost as we assign
        cards = shuffledCards;
        //ran is lost as we exit the method shuffle()
    }

    public String toString() {
        // returns a String that has the contents of the deck "written" in a nice format
        // (so that you can see the ordering of the card values).

        String deckDisplay = "`Deck:";

        for (Card card : cards) {
            deckDisplay += "\n";
            deckDisplay += card.toString();
        }
        return deckDisplay;

    }

    public static void main(String[] args) {
        //memeory is allocated for deck
        Deck deck = new Deck();
        System.out.println("deck size: " + deck.size());
        System.out.println("the cards we have: " + deck.toString());
        deck.shuffle();
        System.out.println("the cards we have after shuffle " + deck.toString());
        System.out.println("we card that we dealt: " + deck.deal());
        System.out.println("after deal we have: " + deck.size() + " cards");

        // when we deal we will hand the card to the hand.
        //memory of deck is lost 

    }

}
