public class Card {
    private int value;

    public Card(int v) {
        this.value = v;
    }

    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        //this allocates string memory
        String number = "`Card:" + String.valueOf(this.value)+"`";
        return number;
    }
    
    public static void main(String[] args) {
        /* allocate memory for myCard*/
        Card myCard = new Card(5);
        System.out.println("the value is:" + myCard.toString());
        System.out.println(myCard.getValue());
        // myCard is lost when we exit the function
    }
    
}
