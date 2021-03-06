package junit5;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.junit.platform.commons.util.Preconditions;

import java.util.Objects;

public class Person {
    private String firstName;
    private String lastName;
    private int age;

    public Person (String inputToParse) {
        String[] parsedInput = inputToParse.split(" ");

        Objects.requireNonNull(parsedInput);
        Preconditions.condition(3 == parsedInput.length, "It has to be 3 params :(");

        this.firstName = parsedInput[0];
        this.lastName = parsedInput[1];
        this.age = Integer.parseInt(parsedInput[2]);
    }

    public Person(String firstName, String lastName, int age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }

    public static Person generateRandomPerson() {
        return new Person(RandomStringUtils.random(5), RandomStringUtils.random(7), RandomUtils.nextInt(0, 100));
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
