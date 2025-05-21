package practise_problems;

import java.util.List;

public class Questions {

    public static void main(String[] args) {

        Questions questions = new Questions();
        questions.set1();

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
}
