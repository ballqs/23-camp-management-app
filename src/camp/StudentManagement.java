package camp;

import camp.model.Student;
import camp.model.Subject;

import java.util.ArrayList;
import java.util.List;

public class StudentManagement implements ManagementInterface<Student>{
    @Override
    public Student select(List<Student> list, String id) {
        for(Student student : list) {
            if(student.getStudentId().equals(id)) {
                return student;
            }
        }
        return null;
    }

    @Override
    public void selectAll(List<Student> list) {
        System.out.println("==================================");
        for(Student str : list){
            List<String> sub = new ArrayList<>();
            for(Subject subject : str.getSubjectList()) {
                sub.add(subject.getSubjectName());
            }
            System.out.println("수강생 고유번호 : " + str.getStudentId());
            System.out.println("수강생 이름 : " + str.getStudentName());
            System.out.println("수강생 과목 : " + String.join(",", sub));
            System.out.println("수강생 상태 : " + str.getStudentStatus());
            System.out.println("==================================");
        }
    }

    @Override
    public Student update() {
        return null;
    }

    @Override
    public Student delete() {
        return null;
    }

    @Override
    public Student insert() {
        return null;
    }
}
