package camp.model;

// 과목
public class Subject {
    private String subjectId;   // 과목 고유번호
    private String subjectName; // 과목 이름
    private String subjectType; // 과목 타입(

    // 수정
    public Subject(String seq, String subjectName, String subjectType) {
        this.subjectId = seq;
        this.subjectName = subjectName;
        this.subjectType = subjectType;
    }

    // Getter
    public String getSubjectId() {
        return subjectId;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public String getSubjectType() {
        return subjectType;
    }

}
