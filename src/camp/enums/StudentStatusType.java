package camp.enums;

public enum StudentStatusType {
    GREEN(1),
    RED(2),
    YELLOW(3);

    int index = 0;

    StudentStatusType(int n) {
        this.index = n;
    }

    public static String getStatus(int n) {
        return StudentStatusType.values()[n-1].name(); // less safe
    }
}
