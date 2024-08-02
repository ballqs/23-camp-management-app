package camp.model;

import java.util.*;

// 수강생
public class Student {
    private String studentId;   // 수강생 고유번호
    private String studentName; // 수강생 이름
    private List<Subject> subjectList;   // 과목리스트<과목 고유번호>
    private String studentStatus;

    public String getStudentStatus() {
        return studentStatus;
    }

    public void setStudentStatus(String studentStatus) {
        this.studentStatus = studentStatus;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public List<Subject> getSubjectList() {
        return subjectList;
    }

    public void setSubjectList(List<Subject> subjectList) {
        this.subjectList = subjectList;
    }

    public Student(String seq, String studentName, ArrayList<Subject> subjectList, String studentStatus) {
        this.studentId = seq;
        this.studentName = studentName;
        this.subjectList = subjectList;
        this.studentStatus = studentStatus;
    }

    // Getter
    public String getStudentId() {
        return studentId;
    }

    public String getStudentName() {
        return studentName;
    }

}
