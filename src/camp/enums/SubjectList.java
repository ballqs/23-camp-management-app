package camp.enums;

public enum SubjectList {
    JAVA("JAVA" , SubjectType.MANDATORY),
    OOP("객체지향" , SubjectType.MANDATORY),
    SPRING("Spring" , SubjectType.MANDATORY),
    JPA("JPA" , SubjectType.MANDATORY),
    MYSQL("MySQL" , SubjectType.MANDATORY),
    DESIGN_PATTERN("디자인 패턴" , SubjectType.CHOICE),
    SPRING_SECURITY("Spring Security" , SubjectType.CHOICE),
    REDIS("Redis" , SubjectType.CHOICE),
    MONGODB("MongoDB" , SubjectType.CHOICE);

    private final String name;
    private final SubjectType type;

    SubjectList(String name , SubjectType type) {
        this.name = name;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public SubjectType getType() {
        return type;
    }
}
