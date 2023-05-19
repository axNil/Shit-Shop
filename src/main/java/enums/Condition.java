package enums;

public enum Condition {
    NEW(3, "NEW"),
    VERY_GOOD(2, "VERY GOOD"),
    GOOD(1, "GOOD"),
    DEFECT(0, "DEFECT");
    private final int value;
    private final String name;
    Condition(int value, String name) {
        this.value = value;
        this.name = name;
    }

    public int getValue() {
        return value;
    }

    public String toString() {
        return name;
    }
}
