import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        List<Person> people = getPeople();

        // Filter
        List<Person> females = people.stream()
                .filter(person -> person.getGender().equals(Gender.FEMALE))
                .collect(Collectors.toList());
        // filter people by Gender. Saves result to list.

        // Sort
        List<Person> sorted = people.stream()
                .sorted(Comparator.comparing(Person::getAge).thenComparing(Person::getGender).reversed())
                .collect(Collectors.toList());
        // firstly sorts persons by age, then by gender, then reverse sorted list. Saves it to list.

        // All match
        boolean allMatch = people.stream()
                .allMatch(person -> person.getAge() > 8);
        // returns false because there is person in the list under 8 years old

        // Any match
        boolean anyMatch = people.stream()
                .anyMatch(person -> person.getAge() > 8);
        // returns true because there are people in the list above 8 years old

        // None match
        boolean noneMatch = people.stream()
                .noneMatch(person -> person.getName().equals("Antonio"));
        // returns false, because there is person with name Antonio

        // Max
        Optional<Person> max = people.stream()
                .max(Comparator.comparing(Person::getAge));
        // prints Optional[Person{name='Zelda Brown', age=120, gender=FEMALE}]
        // result is saved in Optional, because it might not find max in the list

        people.stream()
                .max(Comparator.comparing(Person::getAge))
                .ifPresent(System.out::println);
        // prints same, as before

        // Min
        people.stream()
                .min(Comparator.comparing(Person::getName))
                .ifPresent(System.out::println);
        // prints Person{name='Alex Boz', age=14, gender=MALE}
        // compared in alphabetic order

        // Group
        Map<Gender, List<Person>> groupByGender = people.stream()
                .collect(Collectors.groupingBy(Person::getGender));
        // creates the map of people grouped by gender

        groupByGender.forEach((gender, people1) -> {
            System.out.println(gender);
            people1.forEach(System.out::println);
        });
        // prints map using lambda function and reference operator ::

        // Oldest female name
        Optional<String> oldestFemaleName = people.stream()
                .filter(person -> person.getGender().equals(Gender.FEMALE))
                .max(Comparator.comparing(Person::getAge))
                .map(Person::getName);
        // map function gets only one needed field from object

        oldestFemaleName.ifPresent(System.out::println);
        // prints oldestFemaleName using reference operator ::
    }

    private static List<Person> getPeople() {
        return List.of(
                new Person("Antonio", 20, Gender.MALE),
                new Person("Alina Smith", 33, Gender.FEMALE),
                new Person("Helen White", 57, Gender.FEMALE),
                new Person("Alex Boz", 14, Gender.MALE),
                new Person("Jamie Goa", 99, Gender.MALE),
                new Person("Anna Cook", 7, Gender.FEMALE),
                new Person("Zelda Brown", 120, Gender.FEMALE)
        );
    }

}
