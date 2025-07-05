package collectors_practise;

import lombok.Getter;

import java.util.Arrays;
import java.util.List;

@Getter
public class Data {

    private final List<String> fruits = Arrays.asList("Apple", "Banana", "Orange", "Mango", "Pineapple");
    private final List<Person> people = Arrays.asList(
//            new Person("Alice", 25),
//            new Person("Bob", 30),
//            new Person("Charlie", 25),
//            new Person("David", 40),
//            new Person("Eve", 30)
            new Person("Aman", 21, "Delhi"),
            new Person("Priya", 17, "Mumbai"),
            new Person("Ravi", 25, "Delhi"),
            new Person("Sneha", 19, "Bangalore"),
            new Person("Ankit", 30, "Delhi"),
            new Person("Meera", 16, "Mumbai")
    );
    private final List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 5, 6, 7);
    private final List<Employee> employees = List.of(
            new Employee("Alice", "HR", 50000, 28),
            new Employee("Bob", "IT", 60000, 32),
            new Employee("Charlie", "HR", 55000, 45),
            new Employee("David", "Finance", 65000, 29),
            new Employee("Eve", "IT", 70000, 35),
            new Employee("Frank", "Finance", 72000, 41),
            new Employee("Grace", "IT", 58000, 26),
            new Employee("Heidi", "HR", 52000, 38),
            new Employee("Ivan", "Finance", 80000, 50),
            new Employee("Judy", "IT", 62000, 30)
    );
}
