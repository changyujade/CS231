import java.util.ArrayList;

public class Hand {
    private ArrayList<Card> handList;

    public Hand() {
        //allocate memeory for handList
        handList = new ArrayList<Card>();
    }

    public void reset() {
        // reset the hand to empty.
        handList.clear();
    }

    public void add(Card card) {
        // add the card object to the hand.
        handList.add(card);
    }

    public int size() {
        // returns the number of cards in the hand.
        return handList.size();
    }

    public Card getCard(int i) {
        // returns the card with index i. Cast as appropriate.
        return handList.get(i);
    }

    public int getTotalValue() {
        // returns the sum of the values of the cards in the hand.

        int sum = 0;
        for (int i = 0; i < handList.size(); i++) {
            // get the current card object in the list
            Card currentCard = handList.get(i);
            // get the numerical value of the card object and then adding them all together
            sum += currentCard.getValue();
        }

        return sum;
    }

    public String toString() {
        // returns a String that has the contents of the hand presented in a nice
        // format. Use the Card toString method (implicitly or explicitly) to accomplish
        // this.
        // FIXME

        // for all the java syntax you know:
        // there are two steps
        // declaration:
        // declaration is the process of defining a TYPE for a given "binding" (which we
        // call a variable name)
        // String myString;
        // int myInt;
        // definition:
        // definition is taking a "binding" (a variable name) and assigning a value.
        // myString = "this string here"
        // myInt = 3
        // but we often combine these two syntaxes (declaring and definition) into one
        // line:
        // String myString = "this string here"

        // there is some rules.
        // for a given variable name in a given scope, we can only declare it only ONCE.
        // however, for a given variable in a given scope, we can define it (assign a
        // value to it) as many times as we want.

        String handDescription = "`Hand:";


        // // legal java syntax, because it's just a definition which we can do many
        // times
        // handDescription = handDescription + "nonsense";
        // // however, this is NOT legal, because you can't DECLARE a second time.
        // String handDescription = handDescription + "nonsense";

        // here's we'll fill in all the cards.
        for (int i = 0; i < handList.size(); i++) {
            // get the current card object in the list
            Card currentCard = handList.get(i);
            // get the toString representation of the Card object and then adding them all together
            handDescription += currentCard.toString();
        }

        handDescription = handDescription + "`";
        return handDescription;
    }

    public static void main(String[] args) {
        //allocate memory for hand 
        Hand hand = new Hand();
        for (int i = 1; i <= 11; i++) {
            //allocate new memory for card
            Card myCard = new Card(i);
            hand.add(myCard);
            System.out.println("new card added: " + hand.getCard(hand.size()-1));
            System.out.println("the sum of the hand" + hand.getTotalValue());

        }
        System.out.println("the cards we have:" + hand.toString());
        hand.reset();
        System.out.println("after we reset, we have " + hand.getTotalValue() + " cards");
        

        //memory of hand as well as all the cards associated with it is lost when we exit the main function
    }
}
