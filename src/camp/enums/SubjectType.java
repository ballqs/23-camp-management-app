package camp.enums;

public enum SubjectType {
    MANDATORY("필수"),
    CHOICE("선택");

    private String name;

    SubjectType(String name) {
        this.name = name;
    }
}
