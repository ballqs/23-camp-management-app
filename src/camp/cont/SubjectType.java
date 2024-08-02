package camp.cont;

public enum SubjectType {
    // 필수 , 선택
    MANDATORY("필수") , CHOICE("선택");

    private String name;

    SubjectType(String name) {
        this.name = name;
    }
}
