package camp.model;

import java.util.*;

// 수강생
public class Student {
    private String studentId;   // 수강생 고유번호
    private String studentName; // 수강생 이름
    private List<String> subjectList;   // 과목리스트<과목 고유번호>

    public Student(String seq, String studentName, ArrayList<String> subjectList) {
        this.studentId = seq;
        this.studentName = studentName;
        this.subjectList = subjectList;
    }

    // Getter
    public String getStudentId() {
        return studentId;
    }

    public String getStudentName() {
        return studentName;
    }

}
