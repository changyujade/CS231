import java.util.ArrayList;

public class exampleOfCasting {
    public static void main(String[] args) {

        // object casting example
        String thisIsAString = "this is a string";

        thisIsAString.length();

        Object thisisAnObject = thisIsAString;

        String myString = (String) thisisAnObject;
        // primitive casting

        // primitives (int, double) do NOT extend from java.lang.Object.
        // however, Integer, Double, etc, do extend from java.lang.Object

        double value = 10.0;

        int halfValue = (int) value / 2;

    }
}
