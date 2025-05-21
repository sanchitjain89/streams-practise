package practise_problems;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class Person {
    private String name;
    private int age;
    private String city;
    private String occupation;
    private double salary;
    private LocalDate dateOfBirth; // Added for more complex scenarios


    // Optional: hashCode and equals if you plan to use distinct() on Person objects
    @Override
    public int hashCode() {
        return java.util.Objects.hash(name, age, city, occupation, salary, dateOfBirth);
    }

}
