package CollectorsPractiseQs.beginner;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;


public class Questions {

    public static void main(String[] args) {

        Questions obj = new Questions();
        Data data = new Data();

        obj.intermediate(obj, data);
    }

    public void beginner(Questions obj, Data data){

        System.out.println("Question 1: Convert a list of strings to a comma-separated string");
        obj.testQuestion1(data.getFruits());

        System.out.println("\nQuestion 2: Group Person objects by age");
        obj.testQuestion2(data.getPeople());

        System.out.println("\nQuestion 3: Remove duplicates from a list of integers");
        obj.testQuestion3(data.getNumbers());

        System.out.println("\nQuestion 4: Find the sum of all integers in a list");
        obj.testQuestion4(data.getNumbers());

//        System.out.println("\nQuestion 5: Count elements meeting a condition");
//        obj.testQuestion5(obj.numbers);

        System.out.println("\nQuestion 6: Partition numbers into even and odd groups");
        obj.testQuestion6(data.getNumbers());

    }

    public void intermediate(Questions obj, Data data){

//        obj.testQuestion7(data.getNumbers());

        obj.testQuestion8(data.getEmployees());

        obj.testQuestion9(data.getPeople());

    }

    public void testQuestion1(List<String> fruits){

        System.out.println(String.join(", ", fruits));
        System.out.println(fruits.stream().collect(Collectors.joining(", ")));
    }

    public void testQuestion2(List<Person> people){

        Map<Integer, List<Person>> result = people.stream().collect(Collectors.groupingBy(Person::getAge));
        System.out.println(result);
    }

    public void testQuestion3(List<Integer> numbers){
        Set<Integer> collect = numbers.stream().collect(Collectors.toSet());
        System.out.println(collect);
    }

    public void testQuestion4(List<Integer> numbers){
        Integer collect = numbers.stream().collect(Collectors.summingInt(number -> number.intValue()));
        System.out.println(collect);

    }

    public void testQuestion6(List<Integer> numbers){
        Map<Boolean, List<Integer>> collect = numbers.stream().collect(Collectors.partitioningBy(number -> number % 2 == 0));
        System.out.println(collect);
    }

    public void testQuestion7(List<Integer> numbers){

        Map<String, ? extends Number> collect = numbers.stream()
                .collect(Collectors.teeing(
                        Collectors.summingInt(Integer::intValue),
                        Collectors.averagingDouble(Integer::doubleValue),
                        (a, b) -> Map.of("sum", a, "average", b)
                ));

        System.out.println(collect);

    }

    public void testQuestion8(List<Employee> employees){

        Map<String, Double> collect = employees.stream()
                .collect(Collectors.groupingBy(Employee::getDepartment,
                        Collectors.averagingDouble(Employee::getSalary)));

        System.out.println(collect);
    }

    public void testQuestion9(List<Person> people) {
        System.out.println("Original list: " + people);

        // Group by age, but collect only the names instead of Person objects
        Map<Integer, List<String>> namesByAge = people.stream()
                .collect(Collectors.groupingBy(
                        Person::getAge,
                        Collectors.mapping(
                                Person::getName,  // Map Person to name
                                Collectors.toList() // Collect names into a List
                        )
                ));

        System.out.println("\nNames grouped by age: " + namesByAge);

        // Example 2: Group by age and join names in each group
        Map<Integer, String> joinedNamesByAge = people.stream()
                .collect(Collectors.groupingBy(
                        Person::getAge,
                        Collectors.mapping(
                                Person::getName,
                                Collectors.joining(", ")
                        )
                ));

        System.out.println("\nJoined names by age: " + joinedNamesByAge);

        // Example 3: Collect to a Set to remove duplicates after mapping
        List<Person> peopleWithDuplicateNames = Arrays.asList(
                new Person("Alice", 25),
                new Person("Bob", 30),
                new Person("Alice", 35),  // Same name, different age
                new Person("Charlie", 25)
        );

        Set<String> uniqueNames = peopleWithDuplicateNames.stream()
                .collect(Collectors.mapping(
                        Person::getName,
                        Collectors.toSet()
                ));

        System.out.println("\nUnique names: " + uniqueNames);
    }
}
