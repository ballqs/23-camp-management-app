package camp.enums;

// 수강생 상태값
public enum StudentStatusType {
    Green(1) , Red(2) , Yellow(3);

    int index = 0;
    StudentStatusType(int n) {
        this.index = n;
    }

    public static String getStatus(int n) {
        return StudentStatusType.values()[n-1].name(); // less safe
    }
}
