package camp.repository;

import camp.data.Data;
import camp.enums.StudentStatusType;
import camp.interfaces.ManagementInterface;
import camp.model.Student;

import java.util.ArrayList;
import java.util.List;

public class StudentManagement implements ManagementInterface<Student> {
    @Override
    public Student getData(String key) {
        return Data.STUDENTSTORE.get(key);
    }

    @Override
    public void print(Student info) {

    }

    @Override
    public void printAll() {
        System.out.println("==================================");
        for (String key : Data.STUDENTSTORE.keySet()) {
            List<String> subjectList = new ArrayList<>();
            for(String subject : Data.STUDENTSTORE.get(key).getSubjectList().keySet()) {
                subjectList.add(Data.STUDENTSTORE.get(key).getSubjectList().get(subject).getSubjectName());
            }

            System.out.println("수강생 고유번호 : " + Data.STUDENTSTORE.get(key).getStudentId());
            System.out.println("수강생 이름 : " + Data.STUDENTSTORE.get(key).getStudentName());
            System.out.println("수강생 과목 : " + String.join(",", subjectList));
            System.out.println("수강생 상태 : " + Data.STUDENTSTORE.get(key).getStudentStatus());
            System.out.println("==================================");
        }
    }

    public void update(String key , String fleid , String value) {
        Student student = Data.STUDENTSTORE.get(key);
        switch (fleid) {
            case "studentName" :
                student.setStudentName(value);
                break;
            case "studentStatus" :
                student.setStudentStatus(value);
                break;
        }
        Data.STUDENTSTORE.put(key , student);
    }

    public void delete(String key) {
        Data.STUDENTSTORE.remove(key);
    }

    @Override
    public void insert(String key , Student student) {
        Data.STUDENTSTORE.put(key , student);
    }

    public boolean lenCheck() {
        return Data.STUDENTSTORE.size() > 0 ? true : false;
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

        for (String key : Data.STUDENTSTORE.keySet()) {
            Student student = Data.STUDENTSTORE.get(key);
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
