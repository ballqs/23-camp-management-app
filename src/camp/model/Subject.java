package camp.model;

import java.util.List;

// 과목
public class Subject {
    private String subjectId;   // 과목 고유번호
    private String subjectName; // 과목 이름
    private String subjectType; // 과목 타입(필수 , 선택)

    public Subject() {
    }

    // 수정
    public Subject(String seq, String subjectName, String subjectType) {
        this.subjectId = seq;
        this.subjectName = subjectName;
        this.subjectType = subjectType;
    }

    public void setSubjectId(String subjectId) {
        this.subjectId = subjectId;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public void setSubjectType(String subjectType) {
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
