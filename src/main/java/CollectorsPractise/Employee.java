package CollectorsPractise;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
class Employee {
    private String name;
    private String department;
    private double salary;

    @Override
    public String toString() {
        return name + " (" + department + ", $" + salary + ")";
    }
}
