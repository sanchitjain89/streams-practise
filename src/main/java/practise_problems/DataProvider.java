package practise_problems;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class DataProvider {
    public static List<Person> getPeople() {
        return Arrays.asList(
                new Person("Alice", 30, "New York", "Engineer", 75000.0, LocalDate.of(1994, 5, 15)),
                new Person("Bob", 24, "Los Angeles", "Designer", 60000.0, LocalDate.of(2000, 1, 20)),
                new Person("Charlie", 35, "New York", "Doctor", 120000.0, LocalDate.of(1989, 11, 3)),
                new Person("David", 22, "Chicago", "Engineer", 70000.0, LocalDate.of(2002, 7, 28)),
                new Person("Eve", 29, "Los Angeles", "Artist", 55000.0, LocalDate.of(1995, 3, 10)),
                new Person("Frank", 40, "New York", "Manager", 90000.0, LocalDate.of(1984, 9, 2)),
                new Person("Grace", 28, "Chicago", "Doctor", 110000.0, LocalDate.of(1996, 6, 25)),
                new Person("Heidi", 22, "Los Angeles", "Engineer", 72000.0, LocalDate.of(2002, 2, 18)),
                new Person("Ivan", 33, "New York", "Artist", 58000.0, LocalDate.of(1991, 12, 5)),
                new Person("Judy", 27, "Chicago", "Designer", 62000.0, LocalDate.of(1997, 8, 1)),
                new Person("Kelly", 30, "Houston", "Engineer", 78000.0, LocalDate.of(1994, 4, 22)),
                new Person("Liam", 25, "New York", "Designer", 63000.0, LocalDate.of(1999, 10, 10)),
                new Person("Mia", 38, "Chicago", "Manager", 95000.0, LocalDate.of(1986, 1, 30)),
                new Person("Noah", 26, "Houston", "Doctor", 105000.0, LocalDate.of(1998, 7, 12)),
                new Person("Olivia", 31, "New York", "Engineer", 80000.0, LocalDate.of(1993, 9, 8))
        );
    }

    public static List<Product> getProducts() {
        return Arrays.asList(
                new Product("Laptop", "Electronics", 1200.0, 5),
                new Product("Mouse", "Electronics", 25.0, 20),
                new Product("Keyboard", "Electronics", 75.0, 10),
                new Product("T-Shirt", "Apparel", 20.0, 50),
                new Product("Jeans", "Apparel", 50.0, 30),
                new Product("Socks", "Apparel", 5.0, 100),
                new Product("Coffee Maker", "Home Appliances", 90.0, 8),
                new Product("Toaster", "Home Appliances", 40.0, 15),
                new Product("Book - Java Streams", "Books", 35.0, 12),
                new Product("Notebook", "Stationery", 10.0, 40),
                new Product("Pen Set", "Stationery", 15.0, 25),
                new Product("Laptop Stand", "Accessories", 45.0, 7),
                new Product("Desk Lamp", "Home Appliances", 60.0, 10),
                new Product("Headphones", "Electronics", 150.0, 6),
                new Product("Running Shoes", "Apparel", 80.0, 20),
                new Product("Art Set", "Crafts", 30.0, 15)
        );
    }

    public static List<String> getWords() {
        return Arrays.asList(
                "apple", "banana", "cherry", "date", "elderberry", "fig", "grape",
                "honeydew", "kiwi", "lemon", "mango", "nectarine", "orange",
                "pear", "quince", "raspberry", "strawberry", "tangerine", "ugli",
                "vanilla", "watermelon", "xigua", "yam", "zucchini", "apple", "banana", "date"
        );
    }

    public static List<List<Integer>> getListOfLists() {
        return Arrays.asList(
                Arrays.asList(1, 2, 3),
                Arrays.asList(4, 5),
                Arrays.asList(6, 7, 8, 9),
                Arrays.asList(10)
        );
    }
}