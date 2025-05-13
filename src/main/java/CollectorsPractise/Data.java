package CollectorsPractise;

import lombok.Getter;

import java.util.Arrays;
import java.util.List;

@Getter
public class Data {

    private final List<String> fruits = Arrays.asList("Apple", "Banana", "Orange", "Mango", "Pineapple");
    private final List<Person> people = Arrays.asList(
            new Person("Alice", 25),
            new Person("Bob", 30),
            new Person("Charlie", 25),
            new Person("David", 40),
            new Person("Eve", 30)
    );
    private final List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 5, 6, 7);
    private final List<Employee> employees = Arrays.asList(
            new Employee("Alice", "HR", 50000),
            new Employee("Bob", "IT", 60000),
            new Employee("Charlie", "HR", 55000),
            new Employee("David", "Finance", 65000),
            new Employee("Eve", "IT", 70000)
    );
}
