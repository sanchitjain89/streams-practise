package j8ia;

import java.util.Comparator;

public class JavaAPIMethods {

    public void ex1(){

        Comparator<Apple> c = Comparator.comparing(a -> a.getWeight());


    }
}
