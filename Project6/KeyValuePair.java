/*
***Jade Chang***

KeyValuePair.java
Apr 10 2022
CS 231 Lab 2 Project 6
constructs the key and value for later use. Maps word and the frequency onto key and value. 
*/
public class KeyValuePair<Key, Value> {
    private Key key;
    private Value value;

    // the constructor initializing the key and value fields.
    public KeyValuePair(Key k, Value v) {
        this.key = k;
        this.value = v;
    }

    // returns the key.
    public Key getKey() {
        return key;

    }

    // returns the value.
    public Value getValue() {
        return value;

    }

    // sets the value.
    public void setValue(Value v) {
        this.value = v;

    }

    // returns a String containing both the key and value.
    public String toString() {
        String str = "";
        str = key + ": " + value + "\n";
        return str;
    }

    public static void main(String[] args) {
        KeyValuePair keyValuePair = new KeyValuePair(500, 300);
        System.out.println(keyValuePair.getKey());
        System.out.println(keyValuePair.getValue());
        keyValuePair.setValue(200);
        System.out.println(keyValuePair.getValue());
        System.out.println(keyValuePair.toString());

    }

}