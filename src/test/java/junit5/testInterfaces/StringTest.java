package junit5.testInterfaces;

public class StringTest implements ComparableContract<String>, EqualsContract<String> {

    @Override
    public String createValue() {
        return "TestString";
    }

    @Override
    public String createSmallerValue() {
        return "Anapurna";
    }

    @Override
    public String createLargerValue() {
        return "Wakanda";
    }

    @Override
    public String createNotEqualValue() {
        return "CC";
    }
}
