package collectors_practise;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@ToString
class Employee {
    private String name;
    private String department;
    private double salary;
    private int age;
}
