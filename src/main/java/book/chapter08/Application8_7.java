package book.chapter08;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

public class Application8_7 {
    public static void main(String[] args) {
        List<Person> persons = getPersonList();

        int totalSalary = getTotalSalary(persons);

        int youngest = persons.isEmpty() ? Integer.MAX_VALUE : persons.get(0).getAge();
        for (Person person : persons) {
            if (person.getAge() < youngest) {
                youngest = person.getAge();
            }
        }

        System.out.println("youngest = " + youngest);
        System.out.println("totalSalary = " + totalSalary);
    }

    private static int getTotalSalary(List<Person> persons) {
        int totalSalary = 0;
        for (Person person : persons) {
            totalSalary += person.getSalary();
        }
        return totalSalary;
    }

    private static List<Person> getPersonList() {
        return List.of(new Person(20, 5000), new Person(30, 8000), new Person(50, 4000));
    }
}

@Getter
@AllArgsConstructor
@ToString
class Person {
    private int age;
    private int salary;
}
