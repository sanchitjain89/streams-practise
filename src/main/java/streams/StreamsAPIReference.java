package streams;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;
import java.util.*;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Comprehensive Java Streams API Reference
 * Covers: Core Concepts, Stream Creation, Intermediate Operations, Terminal Operations
 */
public class StreamsAPIReference {

    // Helper classes for examples
    @AllArgsConstructor
    @Getter
    static class Person {
        private String name;
        private int age;
        private String city;
        private String department;

        // Constructor for Person with city (department will be null)
        public Person(String name, int age, String city) {
            this.name = name;
            this.age = age;
            this.city = city;
            this.department = null;
        }

        @Override
        public String toString() {
            return String.format("Person{name='%s', age=%d, city='%s', dept='%s'}",
                    name, age, city, department);
        }
    }

    @AllArgsConstructor
    @Getter
    static class Product {
        private String name;
        private String category;
        private double price;

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (!(obj instanceof Product)) return false;
            Product product = (Product) obj;
            return Objects.equals(name, product.name) &&
                    Objects.equals(category, product.category);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name, category);
        }

        @Override
        public String toString() {
            return String.format("Product{name='%s', category='%s', price=%.2f}",
                    name, category, price);
        }
    }

    @AllArgsConstructor
    @Getter
    static class Order {
        private String customerId;
        private List<String> items;
        private double totalAmount;
        private String status;
        private LocalDate orderDate;

        @Override
        public String toString() {
            return String.format("Order{customerId='%s', items=%s, amount=%.2f, status='%s', date=%s}",
                    customerId, items, totalAmount, status, orderDate);
        }
    }

    public static void main(String[] args) {
        System.out.println("=== JAVA STREAMS API COMPREHENSIVE REFERENCE ===\n");

        // Initialize sample data
        List<String> sampleWords = Arrays.asList("apple", "banana", "cherry", "date", "elderberry");
        List<Integer> sampleNumbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        List<Person> samplePeople = Arrays.asList(
                new Person("Alice", 25, null, "Engineering"),
                new Person("Bob", 30, null, "Marketing"),
                new Person("Charlie", 35, null, "Engineering"),
                new Person("Diana", 28, null, "HR"),
                new Person("Eve", 32, null, "Marketing")
        );

        // ===== CORE CONCEPTS =====
        demonstrateCoreConceptsAndCreation();

        // ===== INTERMEDIATE OPERATIONS =====
        demonstrateIntermediateOperations(sampleWords, sampleNumbers, samplePeople);

        // ===== TERMINAL OPERATIONS =====
        demonstrateTerminalOperations(sampleWords, sampleNumbers, samplePeople);

        // ===== REAL-WORLD EXAMPLES =====
        demonstrateRealWorldExamples();
    }

    // ===== CORE CONCEPTS & STREAM CREATION =====
    private static void demonstrateCoreConceptsAndCreation() {
        System.out.println("1. CORE CONCEPTS & STREAM CREATION");
        System.out.println("=====================================");

        // Stream vs Collection difference
        List<String> names = Arrays.asList("Alice", "Bob", "Charlie");
        System.out.println("Original list: " + names);

        // Functional approach - original unchanged
        List<String> upperNames = names.stream()
                .map(String::toUpperCase)
                .collect(Collectors.toList());
        System.out.println("After stream processing - Original: " + names);
        System.out.println("New list: " + upperNames);

        // Lazy evaluation demonstration
        System.out.println("\nLazy Evaluation Demo:");
        Stream<String> lazyStream = names.stream()
                .filter(name -> {
                    System.out.println("Filtering: " + name);
                    return name.startsWith("A");
                })
                .map(name -> {
                    System.out.println("Mapping: " + name);
                    return name.toUpperCase();
                });
        System.out.println("Stream created, no operations executed yet...");
        List<String> result = lazyStream.collect(Collectors.toList());
        System.out.println("Result: " + result);

        // Different ways to create streams
        System.out.println("\nStream Creation Methods:");

        // From Collections
        Stream<String> fromList = Arrays.asList("a", "b", "c").stream();
        Stream<String> parallelStream = Arrays.asList("a", "b", "c").parallelStream();

        // From Arrays
        String[] array = {"x", "y", "z"};
        Stream<String> fromArray = Arrays.stream(array);

        // Static factory methods
        Stream<String> fromOf = Stream.of("1", "2", "3");
        Stream<String> empty = Stream.empty();

        // Infinite streams (limited for demo)
        List<Double> randomNumbers = Stream.generate(Math::random)
                .limit(5)
                .collect(Collectors.toList());
        System.out.println("5 Random numbers: " + randomNumbers);

        List<Integer> evenNumbers = Stream.iterate(0, n -> n + 2)
                .limit(10)
                .collect(Collectors.toList());
        System.out.println("First 10 even numbers: " + evenNumbers);

        // Primitive streams
        IntStream intRange = IntStream.rangeClosed(1, 5);
        System.out.println("Int range 1-5: " + intRange.boxed().collect(Collectors.toList()));

        System.out.println();
    }

    // ===== INTERMEDIATE OPERATIONS =====
    private static void demonstrateIntermediateOperations(List<String> words, List<Integer> numbers, List<Person> people) {
        System.out.println("2. INTERMEDIATE OPERATIONS");
        System.out.println("==========================");

        // Filter
        System.out.println("FILTER Examples:");
        List<Integer> evenNums = numbers.stream()
                .filter(n -> n % 2 == 0)
                .collect(Collectors.toList());
        System.out.println("Even numbers: " + evenNums);

        List<String> longWords = words.stream()
                .filter(word -> word.length() > 5)
                .collect(Collectors.toList());
        System.out.println("Long words (>5 chars): " + longWords);

        // Map
        System.out.println("\nMAP Examples:");
        List<String> upperWords = words.stream()
                .map(String::toUpperCase)
                .collect(Collectors.toList());
        System.out.println("Uppercase words: " + upperWords);

        List<Integer> wordLengths = words.stream()
                .map(String::length)
                .collect(Collectors.toList());
        System.out.println("Word lengths: " + wordLengths);

        List<Integer> squares = numbers.stream()
                .map(n -> n * n)
                .collect(Collectors.toList());
        System.out.println("Squares: " + squares);

        // FlatMap
        System.out.println("\nFLATMAP Examples:");
        List<List<String>> listOfLists = Arrays.asList(
                Arrays.asList("a", "b"),
                Arrays.asList("c", "d", "e"),
                Arrays.asList("f")
        );

        List<String> flattened = listOfLists.stream()
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
        System.out.println("Flattened lists: " + flattened);

        List<String> sentences = Arrays.asList("Hello world", "Java streams", "are powerful");
        List<String> allWords = sentences.stream()
                .flatMap(sentence -> Arrays.stream(sentence.split(" ")))
                .collect(Collectors.toList());
        System.out.println("All words from sentences: " + allWords);

        // Sorted
        System.out.println("\nSORTED Examples:");
        List<String> sortedWords = words.stream()
                .sorted()
                .collect(Collectors.toList());
        System.out.println("Sorted words: " + sortedWords);

        List<String> sortedByLength = words.stream()
                .sorted(Comparator.comparing(String::length))
                .collect(Collectors.toList());
        System.out.println("Sorted by length: " + sortedByLength);

        List<Person> sortedPeople = people.stream()
                .sorted(Comparator.comparing(Person::getAge))
                .collect(Collectors.toList());
        System.out.println("People sorted by age: " + sortedPeople);

        // Distinct
        System.out.println("\nDISTINCT Examples:");
        List<Integer> numbersWithDups = Arrays.asList(1, 2, 2, 3, 3, 3, 4, 5, 5);
        List<Integer> uniqueNumbers = numbersWithDups.stream()
                .distinct()
                .collect(Collectors.toList());
        System.out.println("Unique numbers: " + uniqueNumbers);

        // Limit and Skip
        System.out.println("\nLIMIT & SKIP Examples:");
        List<Integer> first5 = numbers.stream()
                .limit(5)
                .collect(Collectors.toList());
        System.out.println("First 5 numbers: " + first5);

        List<Integer> skip3 = numbers.stream()
                .skip(3)
                .collect(Collectors.toList());
        System.out.println("Skip first 3: " + skip3);

        List<Integer> pagination = numbers.stream()
                .skip(3)
                .limit(4)
                .collect(Collectors.toList());
        System.out.println("Skip 3, take 4: " + pagination);

        // Peek (for debugging)
        System.out.println("\nPEEK Example (debugging):");
        List<String> debugResult = words.stream()
                .peek(word -> System.out.println("  Processing: " + word))
                .filter(word -> word.length() > 5)
                .peek(word -> System.out.println("  After filter: " + word))
                .map(String::toUpperCase)
                .collect(Collectors.toList());
        System.out.println("Final result: " + debugResult);

        // Chaining operations
        System.out.println("\nCHAINING Multiple Operations:");
        List<String> complexResult = words.stream()
                .filter(word -> word.length() > 4)
                .map(String::toUpperCase)
                .sorted()
                .limit(3)
                .collect(Collectors.toList());
        System.out.println("Complex chain result: " + complexResult);

        System.out.println();
    }

    // ===== TERMINAL OPERATIONS =====
    private static void demonstrateTerminalOperations(List<String> words, List<Integer> numbers, List<Person> people) {
        System.out.println("3. TERMINAL OPERATIONS");
        System.out.println("======================");

        // Collection operations
        System.out.println("COLLECTION Operations:");

        // Collect to different types
        List<String> wordList = words.stream()
                .filter(word -> word.length() > 4)
                .collect(Collectors.toList());
        System.out.println("Filtered to List: " + wordList);

        Set<String> wordSet = words.stream()
                .collect(Collectors.toSet());
        System.out.println("To Set: " + wordSet);

        // Collect to Map
        Map<String, Integer> wordToLength = words.stream()
                .collect(Collectors.toMap(word -> word, String::length));
        System.out.println("Word to Length map: " + wordToLength);

        // toArray
        String[] wordArray = words.stream()
                .filter(word -> word.length() > 5)
                .toArray(String[]::new);
        System.out.println("To Array: " + Arrays.toString(wordArray));

        // Reduction operations
        System.out.println("\nREDUCTION Operations:");

        // reduce
        Optional<Integer> sum = numbers.stream()
                .reduce((a, b) -> a + b);
        System.out.println("Sum using reduce: " + sum.orElse(0));

        Integer sumWithIdentity = numbers.stream()
                .reduce(0, Integer::sum);
        System.out.println("Sum with identity: " + sumWithIdentity);

        Optional<Integer> max = numbers.stream()
                .reduce(Integer::max);
        System.out.println("Max: " + max.orElse(0));

        String concatenated = words.stream()
                .reduce("", (a, b) -> a + " " + b);
        System.out.println("Concatenated: " + concatenated.trim());

        // Aggregation functions
        System.out.println("\nAGGREGATION Functions:");

        long count = words.stream()
                .filter(word -> word.startsWith("a"))
                .count();
        System.out.println("Words starting with 'a': " + count);

        Optional<String> minWord = words.stream()
                .min(Comparator.comparing(String::length));
        System.out.println("Shortest word: " + minWord.orElse("none"));

        Optional<String> maxWord = words.stream()
                .max(Comparator.comparing(String::length));
        System.out.println("Longest word: " + maxWord.orElse("none"));

        // Specialized numeric operations
        int numSum = numbers.stream()
                .mapToInt(Integer::intValue)
                .sum();
        System.out.println("Sum (IntStream): " + numSum);

        OptionalDouble average = numbers.stream()
                .mapToInt(Integer::intValue)
                .average();
        System.out.println("Average: " + average.orElse(0.0));

        IntSummaryStatistics stats = numbers.stream()
                .mapToInt(Integer::intValue)
                .summaryStatistics();
        System.out.println("Statistics: " + stats);

        // Finding operations
        System.out.println("\nFINDING Operations:");

        Optional<String> firstMatch = words.stream()
                .filter(word -> word.startsWith("a"))
                .findFirst();
        System.out.println("First word starting with 'a': " + firstMatch.orElse("none"));

        Optional<String> anyMatch = words.stream()
                .filter(word -> word.length() > 5)
                .findAny();
        System.out.println("Any long word: " + anyMatch.orElse("none"));

        // Matching operations
        System.out.println("\nMATCHING Operations:");

        boolean hasShortWord = words.stream()
                .anyMatch(word -> word.length() < 5);
        System.out.println("Has short word (<5 chars): " + hasShortWord);

        boolean allLongerThan2 = words.stream()
                .allMatch(word -> word.length() > 2);
        System.out.println("All words longer than 2 chars: " + allLongerThan2);

        boolean noneStartWithZ = words.stream()
                .noneMatch(word -> word.startsWith("z"));
        System.out.println("No words start with 'z': " + noneStartWithZ);

        // forEach
        System.out.println("\nFOREACH Operations:");
        System.out.print("Words in uppercase: ");
        words.stream()
                .map(String::toUpperCase)
                .forEach(word -> System.out.print(word + " "));
        System.out.println();

        // Advanced collectors
        System.out.println("\nADVANCED COLLECTORS:");

        // Grouping
        Map<String, List<Person>> byDepartment = people.stream()
                .collect(Collectors.groupingBy(Person::getDepartment));
        System.out.println("Grouped by department: " + byDepartment);

        Map<String, Long> countByDept = people.stream()
                .collect(Collectors.groupingBy(Person::getDepartment, Collectors.counting()));
        System.out.println("Count by department: " + countByDept);

        // Partitioning
        Map<Boolean, List<Person>> partitioned = people.stream()
                .collect(Collectors.partitioningBy(person -> person.getAge() >= 30));
        System.out.println("Partitioned by age >=30: " + partitioned);

        // Joining
        String joinedNames = people.stream()
                .map(Person::getName)
                .collect(Collectors.joining(", "));
        System.out.println("Joined names: " + joinedNames);

        String formattedNames = people.stream()
                .map(Person::getName)
                .collect(Collectors.joining(", ", "[", "]"));
        System.out.println("Formatted names: " + formattedNames);

        System.out.println();
    }

    // ===== REAL-WORLD EXAMPLES =====
    private static void demonstrateRealWorldExamples() {
        System.out.println("4. REAL-WORLD EXAMPLES");
        System.out.println("=======================");

        // E-commerce scenario
        List<Product> products = Arrays.asList(
                new Product("iPhone", "Electronics", 999.99),
                new Product("MacBook", "Electronics", 1999.99),
                new Product("T-Shirt", "Clothing", 29.99),
                new Product("Jeans", "Clothing", 79.99),
                new Product("Book", "Education", 19.99),
                new Product("iPad", "Electronics", 599.99)
        );

        System.out.println("E-COMMERCE Analysis:");

        // Most expensive product in each category
        Map<String, Optional<Product>> mostExpensiveByCategory = products.stream()
                .collect(Collectors.groupingBy(
                        Product::getCategory,
                        Collectors.maxBy(Comparator.comparing(Product::getPrice))
                ));
        System.out.println("Most expensive by category: " + mostExpensiveByCategory);

        // Average price by category
        Map<String, Double> avgPriceByCategory = products.stream()
                .collect(Collectors.groupingBy(
                        Product::getCategory,
                        Collectors.averagingDouble(Product::getPrice)
                ));
        System.out.println("Average price by category: " + avgPriceByCategory);

        // Products over $100, sorted by price descending
        List<Product> expensiveProducts = products.stream()
                .filter(product -> product.getPrice() > 100)
                .sorted(Comparator.comparing(Product::getPrice).reversed())
                .collect(Collectors.toList());
        System.out.println("Expensive products (>$100): " + expensiveProducts);

        // Order processing example with proper LocalDate values
        List<Order> orders = Arrays.asList(
                new Order("C001", Arrays.asList("iPhone", "Case"), 1050.0, "completed", LocalDate.of(2024, 1, 15)),
                new Order("C002", Arrays.asList("Book", "Pen"), 25.0, "pending", LocalDate.of(2024, 1, 20)),
                new Order("C001", Arrays.asList("MacBook"), 1999.99, "completed", LocalDate.of(2024, 2, 5)),
                new Order("C003", Arrays.asList("T-Shirt", "Jeans"), 109.98, "completed", LocalDate.of(2024, 2, 10))
        );

        System.out.println("\nORDER Processing:");

        // Total revenue from completed orders
        double totalRevenue = orders.stream()
                .filter(order -> "completed".equals(order.getStatus()))
                .mapToDouble(Order::getTotalAmount)
                .sum();
        System.out.println("Total revenue from completed orders: $" + totalRevenue);

        // Customer spending analysis
        Map<String, Double> customerSpending = orders.stream()
                .filter(order -> "completed".equals(order.getStatus()))
                .collect(Collectors.groupingBy(
                        Order::getCustomerId,
                        Collectors.summingDouble(Order::getTotalAmount)
                ));
        System.out.println("Customer spending: " + customerSpending);

        // All items from completed orders
        List<String> allItems = orders.stream()
                .filter(order -> "completed".equals(order.getStatus()))
                .flatMap(order -> order.getItems().stream())
                .distinct()
                .sorted()
                .collect(Collectors.toList());
        System.out.println("All items from completed orders: " + allItems);

        // Orders by month
        Map<Integer, List<Order>> ordersByMonth = orders.stream()
                .collect(Collectors.groupingBy(order -> order.getOrderDate().getMonthValue()));
        System.out.println("Orders by month: " + ordersByMonth);

        // Data validation example
        System.out.println("\nDATA Validation:");

        List<String> emails = Arrays.asList(
                "user@example.com",
                "invalid-email",
                "another@domain.org",
                "bad@",
                "good@test.co.uk"
        );

        List<String> validEmails = emails.stream()
                .filter(email -> email.contains("@") && email.contains("."))
                .filter(email -> !email.endsWith("@"))
                .collect(Collectors.toList());
        System.out.println("Valid emails: " + validEmails);

        boolean allEmailsValid = emails.stream()
                .allMatch(email -> email.contains("@") && email.contains(".") && !email.endsWith("@"));
        System.out.println("All emails valid: " + allEmailsValid);

        // Performance comparison
        System.out.println("\nPERFORMACE Comparison (Sequential vs Parallel):");

        List<Integer> largeList = Stream.iterate(1, n -> n + 1)
                .limit(1000000)
                .collect(Collectors.toList());

        // Sequential processing
        long startTime = System.currentTimeMillis();
        long sequentialSum = largeList.stream()
                .filter(n -> n % 2 == 0)
                .mapToLong(n -> n * n)
                .sum();
        long sequentialTime = System.currentTimeMillis() - startTime;

        // Parallel processing
        startTime = System.currentTimeMillis();
        long parallelSum = largeList.parallelStream()
                .filter(n -> n % 2 == 0)
                .mapToLong(n -> n * n)
                .sum();
        long parallelTime = System.currentTimeMillis() - startTime;

        System.out.println("Sequential result: " + sequentialSum + " (Time: " + sequentialTime + "ms)");
        System.out.println("Parallel result: " + parallelSum + " (Time: " + parallelTime + "ms)");
        System.out.println("Results match: " + (sequentialSum == parallelSum));

        // UnaryOperator example
        System.out.println("\nUNARYOPERATOR Examples:");

        UnaryOperator<String> addPrefix = s -> "Hello, " + s;
        UnaryOperator<String> addSuffix = s -> s + "!";
        UnaryOperator<String> toUpper = String::toUpperCase;

        UnaryOperator<String> combined = (UnaryOperator<String>) addPrefix
                .andThen(addSuffix)
                .andThen(toUpper);

        List<String> names = Arrays.asList("Alice", "Bob", "Charlie");
        List<String> transformed = names.stream()
                .map(combined)
                .collect(Collectors.toList());
        System.out.println("Transformed names: " + transformed);

        // Clean up example with multiple UnaryOperators
        UnaryOperator<String> trim = String::trim;
        UnaryOperator<String> removeSpecialChars = s -> s.replaceAll("[^a-zA-Z0-9\\s]", "");
        UnaryOperator<String> normalizeSpaces = s -> s.replaceAll("\\s+", " ");
        UnaryOperator<String> toLowerCase = String::toLowerCase;

        UnaryOperator<String> dataCleanup = (UnaryOperator<String>) trim
                .andThen(removeSpecialChars)
                .andThen(normalizeSpaces)
                .andThen(toLowerCase);

        List<String> dirtyData = Arrays.asList("  Hello@#$   World!!!   ", "Java***Streams   ", "  Clean##Data  ");
        List<String> cleanData = dirtyData.stream()
                .map(dataCleanup)
                .collect(Collectors.toList());
        System.out.println("Cleaned data: " + cleanData);

        System.out.println("\n=== END OF STREAMS API REFERENCE ===");
    }
}