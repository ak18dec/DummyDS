package streams;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

class Person {
    private  String name;
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    public String toString() {
        return this.name;
    }
}

public class StreamMain {
    public static void main(String[] args) {
        List<Person> orl = new ArrayList<>();

        orl.add(new Person("ankit", 10));
        orl.add(new Person("anhya", 20));
        orl.add(new Person("anjghkfhya", 20));

        System.out.println(orl.stream().collect(Collectors.groupingBy(Person::getAge)));

    }
}
