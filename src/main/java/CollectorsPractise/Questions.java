package CollectorsPractise;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class Questions {

    public static void main(String[] args) {
        Questions questions = new Questions();
        Data data = new Data();

        questions.runBeginnerQuestions(data);
//        questions.runIntermediateQuestions(data);
    }

    private void runBeginnerQuestions(Data data) {
        System.out.println("Question 1: Convert a list of strings to a comma-separated string");
        convertListToCommaSeparatedString(data.getFruits());

        System.out.println("\nQuestion 2: Group Person objects by age");
        groupPersonsByAge(data.getPeople());

        System.out.println("\nQuestion 3: Remove duplicates from a list of integers");
        removeDuplicates(data.getNumbers());

        System.out.println("\nQuestion 4: Find the sum of all integers in a list");
        sumOfIntegers(data.getNumbers());

        System.out.println("\nQuestion 5: Partition numbers into even and odd groups");
        partitionNumbersByParity(data.getNumbers());
    }

    private void runIntermediateQuestions(Data data) {
        System.out.println("\nIntermediate Question 1: Calculate average salary by department");
        averageSalaryByDepartment(data.getEmployees());

        System.out.println("\nIntermediate Question 2: Group names by age");
        groupNamesByAge(data.getPeople());
    }

    private void convertListToCommaSeparatedString(List<String> fruits) {
        System.out.println(String.join(", ", fruits));
        System.out.println(fruits.stream().collect(Collectors.joining(", ")));
    }

    private void groupPersonsByAge(List<Person> people) {
        Map<Integer, List<Person>> groupedByAge = people.stream().collect(Collectors.groupingBy(Person::getAge));
        System.out.println(groupedByAge);
    }

    private void removeDuplicates(List<Integer> numbers) {
        // Simple way of doing it
        // Set<Integer> uniqueNumbers = new HashSet<>(numbers);

        Set<Integer> uniqueNumbers = numbers.stream().collect(Collectors.toSet());
        System.out.println(uniqueNumbers);
    }

    private void sumOfIntegers(List<Integer> numbers) {
        Integer sum = numbers.stream().collect(Collectors.summingInt(Integer::intValue));
        System.out.println(sum);
    }

    private void partitionNumbersByParity(List<Integer> numbers) {
        Map<Boolean, List<Integer>> partitioned = numbers.stream().collect(Collectors.partitioningBy(number -> number % 2 == 0));
        System.out.println(partitioned);
    }

    private void averageSalaryByDepartment(List<Employee> employees) {
        Map<String, Double> averageSalary = employees.stream()
                .collect(Collectors.groupingBy(Employee::getDepartment, Collectors.averagingDouble(Employee::getSalary)));
        System.out.println(averageSalary);
    }

    private void groupNamesByAge(List<Person> people) {
        System.out.println("Original list: " + people);

        // Group by age, but collect only the names instead of Person objects
        Map<Integer, List<String>> namesByAge = people.stream()
                .collect(Collectors.groupingBy(
                        Person::getAge,
                        Collectors.mapping(Person::getName, Collectors.toList())
                ));
        System.out.println("\nNames grouped by age: " + namesByAge);

        // Group by age and join names in each group
        Map<Integer, String> joinedNamesByAge = people.stream()
                .collect(Collectors.groupingBy(
                        Person::getAge,
                        Collectors.mapping(Person::getName, Collectors.joining(", "))
                ));
        System.out.println("\nJoined names by age: " + joinedNamesByAge);

        // Collect to a Set to remove duplicates after mapping
        List<Person> peopleWithDuplicateNames = List.of(
                new Person("Alice", 25),
                new Person("Bob", 30),
                new Person("Alice", 35),
                new Person("Charlie", 25)
        );

        Set<String> uniqueNames = peopleWithDuplicateNames.stream()
                .collect(Collectors.mapping(Person::getName, Collectors.toSet()));
        System.out.println("\nUnique names: " + uniqueNames);
    }
}
