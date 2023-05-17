package enums;

public enum Condition {
    NEW(3),
    VERY_GOOD(2),
    GOOD(1),
    DEFECT(0);
    private final int value;
    Condition(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
