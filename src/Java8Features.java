import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Java8Features {

    List<Dish> menu = Arrays.asList(
            new Dish("pork", false, 800, Dish.Type.MEAT),
            new Dish("beef", false, 700, Dish.Type.MEAT),
            new Dish("chicken", false, 400, Dish.Type.MEAT),
            new Dish("french fries", true, 530, Dish.Type.OTHER),
            new Dish("rice", true, 350, Dish.Type.OTHER),
            new Dish("season fruit", true, 120, Dish.Type.OTHER),
            new Dish("pizza", true, 550, Dish.Type.OTHER),
            new Dish("prawns", false, 300, Dish.Type.FISH),
            new Dish("salmon", false, 450, Dish.Type.FISH)
    );


    public static void main(String[] args) {

        Java8Features feature = new Java8Features();
//        feature.mapping();
//        feature.splitMethod();
//        feature.flatMapping();
//        feature.matches();
//        feature.countingDishes();
//        feature.reductionOperations();
//        feature.primitiveOperations();
//        feature.rangeOperations();
        feature.pythagorousTriplets();
    }

    public void mapping(){
        Function<Dish, String> dishMap = (d) -> d.getName();

        List<String> dishNames = menu.stream()
                .map(dishMap)
                .collect(Collectors.toList());

        List<Integer> dishNameLength = menu.stream()
                .map(dishMap)
                .map(s -> s.length())
                .collect(Collectors.toList());

        System.out.println(dishNames);

        System.out.println(dishNameLength);
    }

    public void splitMethod(){
        String s = "Hello world";
        String[] s1 = s.split(" ");
        System.out.println(s1[0]);
        System.out.println(s1[1]);
    }

    public void flatMapping(){
        List<String> words = Arrays.asList("Hello", "World");

        words
                .stream()
                .map(w -> w.split(""))
                .map(Arrays::stream)
                .collect(Collectors.toList());

        //Problem is line 61 returns a <Stream<Stream<String>>

//        Function<String[], String> flatMapFunction = s -> Arrays.stream(s);

        List<String> listOfWords = words.stream()
                .map(s -> s.split(""))
                .flatMap(Arrays::stream)
                .collect(Collectors.toList());

        System.out.println(listOfWords);
    }

    public void matches(){

        boolean b = menu.stream().noneMatch(d -> d.getCalories() == 0);
        System.out.println(b);

    }

    public void countingDishes(){
        long count = menu.stream().count();
        System.out.println(count);
        System.out.println(menu.size());

        Integer reduce = menu
                .stream()
                .map(d -> 1)
                .reduce(0, (a, b) -> a + b);

        System.out.println(reduce);
    }

    public void reductionOperations(){
        List<Integer> someNumbers = Arrays.asList(1, 2, 3, 4, 5);

        //sum
        Integer sum = someNumbers.stream().reduce(0, (a, b) -> a + b);
        System.out.println("The sum of numbers is " + sum);

        //multiply
        Integer multiply = someNumbers
                .stream()
                .reduce(1, (a, b) -> a * b);

        System.out.println("Multiplication is " + multiply);

        //minimum
        Optional<Integer> min = someNumbers
                .stream()
                .reduce((a, b) -> a < b ? a : b);

        System.out.println("Minimum is " + min.get());

        Optional<Integer> max = someNumbers
                .stream()
                .reduce((a, b) -> a > b ? a : b);

        System.out.println("Maximum is " + max.get());

    }

    public void primitiveOperations(){
        int sum = menu.stream()
                .mapToInt(d -> d.getCalories())
                .sum();

        OptionalInt max = menu.stream()
                .mapToInt(Dish::getCalories)
                .max();

        System.out.println(sum);

        System.out.println(max.getAsInt());
    }

    public void rangeOperations(){

        long count = IntStream
                .range(0, 100)
                .filter(a -> a % 3 == 0)
                .sum();

        System.out.println(count);

    }

    public void pythagorousTriplets(){

        List<int[]> triplets = IntStream.range(1, 100)
                .boxed()
                .flatMap(a -> IntStream.range(a, 100)
                        .filter(b -> Math.sqrt(a * a + b * b) % 1 == 0)
                        .mapToObj(b -> new int[]{a, b, (int) Math.sqrt(a * a + b * b)})
                )
                .collect(Collectors.toList());

        System.out.println(triplets.size());

        for (int[] triple : triplets){
            System.out.println(Arrays.toString(triple));
        }
    }
}