package practise_problems;

import java.util.Comparator;
import java.util.List;

public class Questions {

    public static void main(String[] args) {

        Questions questions = new Questions();
//        questions.set1();
        questions.set2();
    }

    public void set1() {

        // 1.Filter by Age: Get a list of all people older than 30.
        List<Person> peopleOlderThan30 =  DataProvider.getPeople()
                .stream()
                .filter(person -> person.getAge() > 30)
                .toList();

        System.out.println(peopleOlderThan30);

        // 2.Filter by City: Find all people who live in "New York".
        List<Person> peopleLivingInNY = DataProvider.getPeople()
                .stream()
                .filter(person -> person.getCity().equals("New York"))
                .toList();
        System.out.println(peopleLivingInNY);

        // 3.Map to Names: Get a list of just the names of all people.
        List<String> names = DataProvider.getPeople()
                .stream()
                .map(Person::getName)
                .toList();
        System.out.println(names);

        // 4.Filter and Map: Get the names of all "Engineers" who earn more than $70,000.
        List<String> engineerNames =  DataProvider.getPeople()
                .stream()
                .filter(person -> person.getOccupation().equals("Engineer") && person.getSalary() > 70000)
                .map(Person::getName)
                .toList();
        System.out.println(engineerNames);

        // 5.Iterate and Print: Print the name and occupation of each person.
        DataProvider.getPeople()
                .forEach(person ->
                        System.out.println("Name: " + person.getName() + "\tOccupation: " + person.getOccupation()));


        // 6.Limit Results: Get the first 5 people from the list.
        List<Person> firstFivePeople =  DataProvider.getPeople()
                .stream()
                .limit(5)
                .toList();
        System.out.println(firstFivePeople);

        // 7.Skip Results: Get all people except the first 3.
        List<Person> peopleExceptFirstThree =  DataProvider.getPeople()
                .stream()
                .skip(3)
                .toList();
        System.out.println(peopleExceptFirstThree);

        // 8.Combined Limit & Skip: Get the 4th, 5th, and 6th people from the list.
        List<Person> fourthToSixthPeople =  DataProvider.getPeople()
                .stream()
                .skip(3)
                .limit(3)
                .toList();
        System.out.println(fourthToSixthPeople);


        // 9.Count by Occupation: Count how many "Doctors" are in the list.
        long doctorCount =  DataProvider.getPeople()
                .stream()
                .filter(person -> person.getOccupation().equalsIgnoreCase("Doctor"))
                .count();
        System.out.println("Number of Doctors: " + doctorCount);

        // 10.Distinct Occupations: Get a list of all unique occupations present in the people list.
        List<String> uniqueOccupations =  DataProvider.getPeople()
                .stream()
                .map(Person::getOccupation)
                .distinct()
                .toList();
        System.out.println(uniqueOccupations);

        // 11.Filter and Print: Print the name and price of all "Electronics" products.
        DataProvider.getProducts()
                .stream()
                .filter(product -> product.getCategory().equalsIgnoreCase("Electronics"))
                .forEach(product -> System.out.println("Name " +product.getName() + " Price " + product.getPrice()));

        // 12.Count by Category: Count how many "Apparel" products there are.
        long apparelCount = DataProvider.getProducts()
                .stream()
                .filter(product -> product.getCategory().equalsIgnoreCase("Apparel"))
                .count();
        System.out.println("Number of Apparel products: " + apparelCount);

        // 13.Distinct Words: Get a list of all unique words.
        List<String> uniqueWords =  DataProvider.getWords()
                .stream()
                .distinct()
                .toList();
        System.out.println(uniqueWords);

        // 14.Filter, Map, and Print: Find all words that start with 'a', convert them to uppercase, and print them.
        List<String> wordsStartingWithA = DataProvider.getWords()
                .stream()
                .filter(word -> word.startsWith("a"))
                .map(String::toUpperCase)
                .toList();
        System.out.println(wordsStartingWithA);
    }

    public void set2() {

        // 1.Sorted by Age (Ascending): Get a list of all people sorted by age in ascending order
        List<Person> sortedByAgeAsc = DataProvider.getPeople()
                .stream()
                .sorted(Comparator.comparingInt(Person::getAge))
                .toList();
        System.out.println("Sorted by Age Ascending: " + sortedByAgeAsc);

        // 2.Sorted by Salary (Descending): Get a list of all people sorted by salary in descending order
        List<Person> sortedBySalaryDesc = DataProvider.getPeople()
                .stream()
                .sorted(Comparator.comparingDouble(Person::getSalary).reversed())
                .toList();
        System.out.println("Sorted by Salary Descending: " + sortedBySalaryDesc);

        // 3.Sorted by City then Name: Get a list of people sorted first by city (ascending), then by name (ascending).
        List<Person> sortedByCityThenName =  DataProvider.getPeople()
                .stream()
                .sorted(Comparator.comparing(Person::getCity).thenComparing(Person::getName))
                .toList();
        System.out.println("Sorted by City then Name: " + sortedByCityThenName);

        // 4.Find the Youngest Person: Find the person with the minimum age. Handle the Optional.
        DataProvider.getPeople()
                .stream()
                .min(Comparator.comparingInt(Person::getAge))
                .ifPresent(person -> System.out.println("Youngest Person: " + person));

        // 5.Find the Highest Earner: Find the person with the maximum salary. Handle the Optional.
        DataProvider.getPeople()
                .stream()
                .max(Comparator.comparingDouble(Person::getSalary))
                .ifPresent(person -> System.out.println("Highest Salary Person: " + person));

        // 6.Check All Match (Age): Check if all people are older than 20.
        boolean allOlderThan20 = DataProvider.getPeople()
                .stream()
                .allMatch(person -> person.getAge() > 20);
        System.out.println("All older than 20: " + allOlderThan20);

        // 7.Check Any Match (Occupation): Check if any person is an "Artist".
        boolean anyArtist =  DataProvider.getPeople()
                .stream()
                .anyMatch(person -> person.getOccupation().equalsIgnoreCase("Artist"));
        System.out.println("Any Artist: " + anyArtist);

        // 8.Check None Match (City): Check if none of the people live in "Dallas".
        boolean noneInDallas =  DataProvider.getPeople()
                .stream()
                .noneMatch(person -> person.getCity().equalsIgnoreCase("Dallas"));
        System.out.println("None in Dallas: " + noneInDallas);

        // 9.peek() for Debugging: Print the name of each person as they are processed, then collect them into a list. (Use peek() for this intermediate print).
        List<Person> peopleWithDebug =  DataProvider.getPeople()
                .stream()
                .peek(person -> System.out.println("Processing: " + person.getName()))
                .toList();
        System.out.println("People with Debug: " + peopleWithDebug);

        // 10.Find First Engineer: Find the first person whose occupation is "Engineer". Handle the Optional.
        DataProvider.getPeople()
                .stream()
                .filter(person -> person.getOccupation().equalsIgnoreCase("Engineer"))
                .findFirst()
                .ifPresent(person -> System.out.println("Found Engineer: " + person));

        // 11.Sorted by Category then Price: Get a list of products sorted first by category, then by price (both ascending).
        List<Product> sortedByCategoryThenPrice = DataProvider.getProducts()
                .stream()
                .sorted(Comparator.comparing(Product::getCategory).thenComparingDouble(Product::getPrice))
                .toList();
        System.out.println("Sorted by Category then Price: " + sortedByCategoryThenPrice);

        // 12.Check Any Match (Quantity): Check if any product has a quantity less than 5.
        boolean anyProductLowQuantity = DataProvider.getProducts()
                .stream()
                .anyMatch(product -> product.getQuantity() < 5);
        System.out.println("Any product with low quantity: " + anyProductLowQuantity);

        // 13.peek() for price check: Print the price of each "Apparel" product as it's processed, then collect them into a list.
        List<Product> apparelProductsWithDebug =  DataProvider.getProducts()
                .stream()
                .filter(product -> product.getCategory().equalsIgnoreCase("Apparel"))
                .peek(product -> System.out.println("Price: " + product.getPrice()))
                .toList();

        // 14.peek() for price check: Print the price of each "Apparel" product as it's processed, then collect them into a list.
        List<Product> apparelProducts = DataProvider.getProducts()
                .stream()
                .filter(product -> product.getCategory().equalsIgnoreCase("Apparel"))
                .peek(product -> System.out.println("Price: " + product.getPrice()))
                .toList();
        System.out.println("Apparel Products with Debug: " + apparelProductsWithDebug);

        // 15.flatMap() to Flatten: Flatten the listOfLists into a single stream of integers.
        List<Integer> flattenedList = DataProvider.getListOfLists()
                .stream()
                .flatMap(List::stream)
                .toList();
        System.out.println("Flattened List: " + flattenedList);

        // 16.flatMap() and Sum: Flatten the listOfLists into a single stream of integers and then calculate their sum.
        int sumOfFlattenedList = DataProvider.getListOfLists()
                .stream()
                .flatMap(List::stream)
                .mapToInt(Integer::intValue)
                .sum();
        System.out.println("Sum of Flattened List: " + sumOfFlattenedList);

        // 17.flatMap() and Distinct: Flatten the listOfLists into a single stream, then find all distinct numbers.
        List<Integer> distinctNumbers = DataProvider.getListOfLists()
                .stream()
                .flatMap(List::stream)
                .distinct()
                .toList();
        System.out.println("Distinct Numbers: " + distinctNumbers);
    }
}
