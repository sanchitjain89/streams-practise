package collectors_practise;

import java.util.*;
import java.util.stream.Collectors;

public class Questions {

    public static void main(String[] args) {
        Questions questions = new Questions();
        Data data = new Data();

//        questions.runBeginnerQuestions(data);
//        questions.groupByPlayground(data);
//        questions.groupByQuestions(data);
//        questions.partitionByQuestions(data);
//        questions.summaryStatsQuestions(data);
        questions.countingQuestions(data);
//        questions.joiningQuestions(data);
    }

    private void runBeginnerQuestions(Data data) {
        System.out.println("Question 1: Convert a list of strings to a comma-separated string");
        System.out.println(String.join(", ", data.getFruits()));
        System.out.println(data.getFruits().stream().collect(Collectors.joining(", ", "[", "]")));

        System.out.println("\nQuestion 3: Remove duplicates from a list of integers");
        removeDuplicates(data.getNumbers());

        System.out.println("\nQuestion 4: Find the sum of all integers in a list");
        sumOfIntegers(data.getNumbers());
    }

    private void removeDuplicates(List<Integer> numbers) {
        // Simple way of doing it
        // Set<Integer> uniqueNumbers = new HashSet<>(numbers);

        Set<Integer> uniqueNumbers = numbers.stream().collect(Collectors.toSet());
        System.out.println(uniqueNumbers);
    }

    private void sumOfIntegers(List<Integer> numbers) {
        // Using reduce
        // int sum_mapToInt = numbers.stream().mapToInt(Integer::intValue).sum();
        Integer sum = numbers.stream().collect(Collectors.summingInt(Integer::intValue));
        System.out.println(sum);
    }


    private void groupByPlayground(Data data){
        Map<Integer, Long> collect = data.getPeople().stream().collect(Collectors.groupingBy(Person::getAge, Collectors.counting()));
        System.out.println(collect);
    }

    private void groupByQuestions(Data data){
        //Group people by age.
        System.out.println("1. Group people by age.");
        Map<Integer, List<Person>> peopleByAge = data.getPeople().stream().collect(Collectors.groupingBy(Person::getAge));
        System.out.println(peopleByAge);

        //Group employees by department.
        System.out.println("2. Group employees by department.");
        Map<String, List<Employee>> employeesByDepartment = data.getEmployees().stream().collect(Collectors.groupingBy(Employee::getDepartment));
        System.out.println(employeesByDepartment);

        //Group fruits by their length.
        System.out.println("3. Group fruits by their length.");
        Map<Integer, List<String>> fruitsByLength = data.getFruits().stream().collect(Collectors.groupingBy(String::length));
        System.out.println(fruitsByLength);

        System.out.println("\n4: Group names by age");
        System.out.println("Original list: " + data.getPeople());

        // Group by age, but collect only the names instead of Person objects
        Map<Integer, List<String>> namesByAge = data.getPeople().stream()
                .collect(Collectors.groupingBy(
                        Person::getAge,
                        Collectors.mapping(Person::getName, Collectors.toList())
                ));
        System.out.println("\nNames grouped by age: " + namesByAge);

        // Group by age and join names in each group
        Map<Integer, String> joinedNamesByAge = data.getPeople().stream()
                .collect(Collectors.groupingBy(
                        Person::getAge,
                        Collectors.mapping(Person::getName, Collectors.joining(", "))
                ));
        System.out.println("\nJoined names by age: " + joinedNamesByAge);

        // Intermediate

        // 4.Count how many people are in each age group.
        System.out.println("4. Count how many people are in each age group.");
        Map<Integer, Long> peopleCountByAge = data.getPeople().stream().collect(Collectors.groupingBy(Person::getAge, Collectors.counting()));
        System.out.println(peopleCountByAge);

        // 5.Get a map of department → average salary.
        System.out.println("5. Get a map of department → average salary.");
        Map<String, Double> departmentAverageSalary = data.getEmployees().stream().collect(Collectors.groupingBy(Employee::getDepartment, Collectors.averagingDouble(Employee::getSalary)));
        System.out.println(departmentAverageSalary);

        // 6.Group employees by department and collect their names as a list.
        System.out.println("6. Group employees by department and collect their names as a list.");
        Map<String, List<String>> employeeNamesByDepartment = data.getEmployees().stream().collect(Collectors.groupingBy(Employee::getDepartment, Collectors.mapping(Employee::getName, Collectors.toList())));
        System.out.println(employeeNamesByDepartment);

        // 7.Group employees by department and collect names into a comma-separated string.
        System.out.println("7. Group employees by department and collect names into a comma-separated string.");
        Map<String, String> employeeNamesByDepartmentString = data.getEmployees().stream().collect(Collectors.groupingBy(Employee::getDepartment, Collectors.mapping(Employee::getName, Collectors.joining(", "))));
        System.out.println(employeeNamesByDepartmentString);

        // Advanced

        // 8.Group employees by department using a TreeMap
        System.out.println("8. Group employees by department using a TreeMap.");
        Map<String, List<Employee>> employeesByDepartmentTreeMap = data.getEmployees().stream().collect(Collectors.groupingBy(Employee::getDepartment, TreeMap::new, Collectors.toList()));
        System.out.println(employeesByDepartmentTreeMap);

        // 9.Group people by age and collect the names into a Set.
        System.out.println("9. Group people by age and collect the names into a Set.");
        Map<Integer, Set<String>> peopleNamesByAgeSet = data.getPeople().stream().collect(Collectors.groupingBy(Person::getAge, Collectors.mapping(Person::getName, Collectors.toSet())));
        System.out.println(peopleNamesByAgeSet);

        // 10.Group numbers by even/odd, and count the number of occurrences in each group.
        System.out.println("10. Group numbers by even/odd, and count the number of occurrences in each group.");
        //Using partitioningBy
//        Map<Boolean, Long> evenOddCount = data.getNumbers().stream().collect(Collectors.partitioningBy(n -> n % 2 == 0, Collectors.counting()));

        //Using groupingBy
        Map<Boolean, Long> evenOddCount = data.getNumbers().stream().collect(Collectors.groupingBy(n -> n%2 == 0, Collectors.counting()));
        System.out.println(evenOddCount);

        // 11.Group people by age, and for each group, find the person with the max name length.
        System.out.println("11. Group people by age, and for each group, find the person with the max name length.");
        Map<Integer, Person> personWithLongestNameByAge = data.getPeople().stream()
                .collect(Collectors.groupingBy(
                        Person::getAge,
                        Collectors.collectingAndThen(
                                Collectors.maxBy(Comparator.comparingInt(p -> p.getName().length())),
                                optional -> optional.orElse(null)
                        )
                ));
        System.out.println(personWithLongestNameByAge);



        // 12.Group employees by department and get the total salary per department.
        System.out.println("12. Group employees by department and get the total salary per department.");
        // Using Collectors.summingInt
        Map<String, Double> totalSalaryByDepartment = data.getEmployees().stream()
                .collect(Collectors.groupingBy(Employee::getDepartment, Collectors.summingDouble(Employee::getSalary)));
        System.out.println(totalSalaryByDepartment);
    }

    private void partitionByQuestions(Data data){
        // 1.Partition numbers into even and odd groups.
        System.out.println("1. Partition numbers into even and odd groups.");
        Map<Boolean, List<Integer>> partitionedNumbers = data.getNumbers().stream().collect(Collectors.partitioningBy(n -> n%2 == 0));
        System.out.println(partitionedNumbers);

        //2.Partition a list of strings into those longer than 5 characters and the rest.
        System.out.println("2. Partition a list of strings into those longer than 5 characters and the rest.");
        Map<Boolean, List<String>> partitionedFruits =
                data.getFruits().stream().collect(Collectors.partitioningBy(fruit -> fruit.length() > 5));
        System.out.println(partitionedFruits);

        //3.Partition people into adults (age ≥ 18) and minors.
        System.out.println("3. Partition people into adults (age ≥ 18) and minors.");
        Map<Boolean, List<Person>> partitionedPeople = data.getPeople().stream().collect(Collectors.partitioningBy(person -> person.getAge() >= 18));
        System.out.println(partitionedPeople);

        //4.Partition people into adults (age ≥ 18) and minors.
        System.out.println("4. Partition people by whether their name starts with \"A\".");
        Map<Boolean, List<Person>> partitionedPeopleByName = data.getPeople().stream().collect(Collectors.partitioningBy(person -> person.getName().startsWith("A")));
        System.out.println(partitionedPeopleByName);

        //5.Partition numbers into even/odd and count how many are in each group.
        System.out.println("5. Partition numbers into even/odd and count how many are in each group.");
        Map<Boolean, Long> partitionedCount = data.getNumbers().stream().collect(Collectors.partitioningBy(n -> n%2 == 0, Collectors.counting()));
        System.out.println(partitionedCount);

        //6.Partition employees into high-salary (≥100k) vs others, and collect names in each group.
        System.out.println("6. Partition employees into high-salary (≥60k) vs others, and collect names in each group.");
        Map<Boolean, List<String>> partitionedEmployees = data.getEmployees().stream().collect(Collectors.partitioningBy(employee -> employee.getSalary() > 60000, Collectors.mapping(Employee::getName, Collectors.toList())));
        System.out.println(partitionedEmployees);

        //7.Partition people into adults/minors and collect the average age of each group.
        System.out.println("7. Partition people into adults/minors and collect the average age of each group.");
        Map<Boolean, Double> averageAgeByPartition = data.getPeople().stream().collect(Collectors.partitioningBy(person -> person.getAge() >= 18, Collectors.averagingInt(Person::getAge)));
        System.out.println(averageAgeByPartition);


        //8.Partition people into those who live in "Delhi" and those who don't; for each group, get a list of names sorted alphabetically.
        System.out.println("8. Partition people into those who live in \"Delhi\" and those who don't; for each group, get a list of names sorted alphabetically.");
        Map<Boolean, List<String>> partitionedPeopleByCity = data.getPeople().stream()
                .collect(Collectors.partitioningBy(
                        person -> person.getCity().equalsIgnoreCase("DELHI"),
                        Collectors.collectingAndThen(Collectors.mapping(
                                        Person::getName,
                                        Collectors.toCollection(TreeSet::new)
                                ),
                                ArrayList::new
                        )));
        System.out.println(partitionedPeopleByCity);

    }

    private void summaryStatsQuestions(Data data){

        // 1.Given a list of integers, get the sum and average.

        // Alternative way using reduce
        // int sum = data.getNumbers().stream().mapToInt(Integer::intValue).sum();

        int sum = data.getNumbers().stream().collect(Collectors.summingInt(Integer::intValue));
        double average = data.getNumbers().stream().collect(Collectors.averagingInt(Integer::intValue));
        System.out.println("Sum: " + sum + ", Average: " + average);

        // Using Collectors.summarizingInt
        IntSummaryStatistics summary = data.getNumbers().stream().collect(Collectors.summarizingInt(Integer::intValue));
        System.out.println("Summary: " + summary);

        // 2.Find the number of employees older than 30.
        // Using filter and count
        long count = data.getEmployees().stream().filter(employee -> employee.getAge() > 30).count();
        System.out.println("Employees with age > 30 " + count);

        // 3.Get the name of the youngest employee.
        
        // Employee youngestEmployee = data.getEmployees().stream().min(Comparator.comparingInt(Employee::getAge)).orElse(null);

        // Using collect and Collectors.minBy
        Employee youngestEmployee = data.getEmployees().stream().collect(Collectors.minBy(Comparator.comparingInt(Employee::getAge))).get();
        System.out.println("Youngest Employee: " + youngestEmployee);

        // 4.Find the max salary from a list of employees.
        // Using max and mapToDouble
        double maxSalary = data.getEmployees().stream().mapToDouble(Employee::getSalary).max().orElse(0);
        System.out.println("Max Salary: " + maxSalary);

        // 5.For a list of employees, get a DoubleSummaryStatistics of their salary.
        DoubleSummaryStatistics salaryStats = data.getEmployees().stream().collect(Collectors.summarizingDouble(Employee::getSalary));
        System.out.println("Salary Stats: " + salaryStats);

        // 6.Group employees by department and get the average age per department.
        Map<String, Double> averageAgeByDepartment = data.getEmployees().stream().collect(Collectors.groupingBy(Employee::getDepartment, Collectors.averagingInt(Employee::getAge)));
        System.out.println("Average Age by Department: " + averageAgeByDepartment);

        //7.Given a list of strings, get the length of the longest string.
        // String longestString = data.getFruits().stream().max(Comparator.comparingInt(String::length)).orElse(null);

        // Using Collectors.maxBy
        String longestString = data.getFruits().stream().collect(Collectors.maxBy(Comparator.comparingInt(String::length))).get();
        System.out.println("Longest String: " + longestString + ", Length: " + longestString.length());



    }

    private void countingQuestions(Data data){
        // 1.Count the number of employees in each department.
        Map<String, Long> employeeCountByDepartment = data.getEmployees().stream().collect(Collectors.groupingBy(Employee::getDepartment, Collectors.counting()));
        System.out.println("Employee Count by Department: " + employeeCountByDepartment);

        // 2.Count the number of unique fruits.
        long uniqueFruitCount = data.getFruits().stream().distinct().count();
        System.out.println("Unique Fruit Count: " + uniqueFruitCount);

        // 3.Count the number of people in each city.
        Map<String, Long> peopleCountByCity = data.getPeople().stream().collect(Collectors.groupingBy(Person::getCity, Collectors.counting()));
        System.out.println("People Count by City: " + peopleCountByCity);

        // 4.Count total number of employees.
        long totalEmployees = data.getEmployees().stream().collect(Collectors.counting());
        // Alternative way using count()
        // long totalEmployees = data.getEmployees().stream().count();
        System.out.println("Total Employees: " + totalEmployees);

        // 5.Count the number of employees with a salary greater than 60,000, by department.
        Map<String, Long> highSalaryCountByDepartment = data.getEmployees().stream().filter(employee -> employee.getSalary() > 60000)
                .collect(Collectors.groupingBy(Employee::getDepartment, Collectors.counting()));
        System.out.println("Employees with Salary > 60,000: " + highSalaryCountByDepartment);

        // 6.Find the department with the most employees.
        // Come back to this later
        Map.Entry<String, Long> departmentWithMostEmployees = data.getEmployees().stream()
                .collect(Collectors.groupingBy(Employee::getDepartment, Collectors.counting()))
                .entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .orElse(null);
        System.out.println("Department with Most Employees: " + departmentWithMostEmployees);
    }

    private void joiningQuestions(Data data){

        // 1.Dept wise names
        Map<String, String> deptWiseNames = data.getEmployees().stream()
               .collect(Collectors.groupingBy(Employee::getDepartment, Collectors.mapping(Employee::getName, Collectors.joining(", "))));
        System.out.println(deptWiseNames);

        // 2.Create a comma-separated list of all employee names.
        String allEmployeeNames = data.getEmployees().stream()
                .map(Employee::getName)
                .collect(Collectors.joining(", "));
        System.out.println("All Employee Names: " + allEmployeeNames);

        // 3.Create a comma-separated list of all employee names, enclosed in square brackets.
        String allEmployeeNamesWithBrackets = data.getEmployees().stream()
                .map(Employee::getName)
                .collect(Collectors.joining(", ", "[", "]"));

        // 4.Create a string of names of employees earning more than ₹60,000.
        String highSalaryEmployeeNames = data.getEmployees().stream()
                .filter(employee -> employee.getSalary() > 60000)
                .map(Employee::getName)
                .collect(Collectors.joining(", ", "High Salary Employees: ", ""));
        System.out.println(highSalaryEmployeeNames);

    }
}
