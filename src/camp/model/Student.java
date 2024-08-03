package camp.model;

import java.util.*;

// 수강생
public class Student {
    private String studentId;                  // 수강생 고유번호
    private String studentName;           // 수강생 이름
    private Map<String , Subject> subjectList;  // 과목리스트<과목 고유번호>
    private String studentStatus;               // 수강생 상태

    public Student(String seq, String studentName, Map<String , Subject> subjectList, String studentStatus) {
        this.studentId = seq;
        this.studentName = studentName;
        this.subjectList = subjectList;
        this.studentStatus = studentStatus;
    }

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

    public Map<String , Subject> getSubjectList() {
        return subjectList;
    }

    public void setSubjectList(Map<String , Subject> subjectList) {
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
