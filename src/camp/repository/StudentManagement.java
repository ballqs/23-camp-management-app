package camp.repository;

import camp.enums.StudentStatusType;
import camp.interfaces.ManagementInterface;
import camp.model.Student;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StudentManagement implements ManagementInterface<Student> {

    public final static Map<String , Student> STUDENTSTORE = new HashMap<>(); // 학생 고유번호, 학생 객체
    private int studentIndex = 0;     // 학생 고유번호 INDEX

    public int getStudentIndex() {
        studentIndex++;
        return studentIndex;
    }

    @Override
    public Student getData(String key) {
        return STUDENTSTORE.get(key);
    }

    @Override
    public void print(Student info) {

    }

    @Override
    public void printAll() {
        System.out.println("==================================");
        for (String key : STUDENTSTORE.keySet()) {
            List<String> subjectList = new ArrayList<>();
            for(String subject : STUDENTSTORE.get(key).getSubjectList().keySet()) {
                subjectList.add(STUDENTSTORE.get(key).getSubjectList().get(subject).getSubjectName());
            }

            System.out.println("수강생 고유번호 : " + STUDENTSTORE.get(key).getStudentId());
            System.out.println("수강생 이름 : " + STUDENTSTORE.get(key).getStudentName());
            System.out.println("수강생 과목 : " + String.join(",", subjectList));
            System.out.println("수강생 상태 : " + STUDENTSTORE.get(key).getStudentStatus());
            System.out.println("==================================");
        }
    }

    public void update(String key , String fleid , String value) {
        Student student = STUDENTSTORE.get(key);
        switch (fleid) {
            case "studentName" :
                student.setStudentName(value);
                break;
            case "studentStatus" :
                student.setStudentStatus(value);
                break;
        }
        STUDENTSTORE.put(key , student);
    }

    public void delete(String key) {
        STUDENTSTORE.remove(key);
    }

    @Override
    public void insert(String key , Student student) {
        STUDENTSTORE.put(key , student);
    }

    public boolean lenCheck() {
        return STUDENTSTORE.size() > 0 ? true : false;
    }

    public void statusList() {
        StudentStatusType[] studentStatusType = StudentStatusType.values();
        for (int i = 0; i < studentStatusType.length; i++) {
            System.out.println((i + 1) + "." + studentStatusType[i]);
        }
    }

    // 상태별 수강생 리스트 조회
    public void selectStatusStudent(String status) {
        List<Student> list = new ArrayList<>();

        for (String key : STUDENTSTORE.keySet()) {
            Student student = STUDENTSTORE.get(key);
            if (student.getStudentStatus().equals(status)) {
                list.add(student);
            }
        }

        System.out.println("==================================");
        for (Student student : list) {
            List<String> subjectList = new ArrayList<>();
            for(String subject : student.getSubjectList().keySet()) {
                subjectList.add(student.getSubjectList().get(subject).getSubjectName());
            }
            System.out.println("수강생 고유번호 : " + student.getStudentId());
            System.out.println("수강생 이름 : " + student.getStudentName());
            System.out.println("수강생 과목 : " + String.join(",", subjectList));
            System.out.println("수강생 상태 : " + student.getStudentStatus());
            System.out.println("==================================");
        }
    }
}
