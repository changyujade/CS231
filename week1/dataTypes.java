public class dataTypes {

public static void main(String[] args) {

    //String is a Java Class
    String name = "Jade";
    System.out.println(name);

    //These are the primitive types in Java
    byte small = 1;
    short little = 1;
    int bigger = 1;
    long longer = 1L;
    float decimal = 3.1f;
    double biggerDec = 3.1;
    char character = 'a';
    boolean isOn = true;

    if (isOn){
        System.err.println("The light is on");

    }

    while (isOn){
        System.err.println("The light is on");
        if(isOn){
            isOn = false;
        }
    }

    for (int i =0; i< 10; i++){
        System.out.println("The light is on");
    }
}

}

//read boolean operators on w3schools, relational operators, 