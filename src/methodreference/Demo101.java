package methodreference;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.IntStream;

public class Demo101 {

    public static void main(String[] args) {
        Demo101 demo = new Demo101();

//        demo.ex1();
        demo.ex2();
    }

    public void ex1(){
        Function<String, Integer> convertToInt = Integer::parseInt;
        System.out.println(convertToInt.apply("1234523"));

        Consumer<String> printString = System.out::println;
        printString.accept("Hello Method Reference");

        Supplier<StringBuilder> stringBuilderLambda = () -> new StringBuilder();
        Supplier<StringBuilder> stringBuilderMethodRef = StringBuilder::new;

        StringBuilder sb = stringBuilderMethodRef.get();
        System.out.println(sb.append("Constructor Reference Example")); // Output: Constructor Reference Example

    }

    public void ex2(){

        Function<Double, Double> convertToSquareRoot = Math::sqrt;

        List<Integer> list = IntStream.range(0, 50).boxed().toList();

        for (int i : list){
            System.out.println(convertToSquareRoot.apply((double) i));
        }

    }

}
