package j8ia;

import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

public class ConstructorReference {

    public static void main(String[] args) {
        ConstructorReference constructorReference = new ConstructorReference();

//        constructorReference.ex1();
//        constructorReference.ex2();
        constructorReference.ex3();
    }

    public void ex1(){

        //No args constructor
        Supplier<Apple> s1 = () -> new Apple();
        Supplier<Apple> s2 = Apple::new;

        Apple a1 = s1.get();
        Apple a2 = s2.get();

        System.out.println(a1 == a2);
        System.out.println(a1.getWeight() == a2.getWeight());
    }

    public void ex2(){

        Function<Integer, Apple> f1 = weight -> new Apple(weight);
        Function<Integer, Apple> f2 = Apple::new;

        Apple a1 = f1.apply(10);
        Apple a2 = f2.apply(10);

        System.out.println(a1.getWeight() == a2.getWeight());
    }

    public void ex3(){

        BiFunction<String, Integer, Apple> bf1 = (color, weight) -> new Apple(color, weight);
        BiFunction<String , Integer, Apple> bf2 = Apple::new;

        Apple a1 = bf1.apply("red", 150);
        Apple a2 = bf1.apply("red", 500);

        System.out.println(a1.getColor().equals(a2.getColor()));
    }

}
