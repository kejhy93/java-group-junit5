package junit5.testInterfaces;

import junit5.Person;

public class PersonTest implements EqualsContract<Person> {
    @Override
    public Person createNotEqualValue() {
        return new Person("Petra", "Novakova", 53);
    }

    @Override
    public Person createValue() {
        return new Person("Petr", "Novak", 35);
    }
}
