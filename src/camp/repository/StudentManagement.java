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
        return Data.studentStore.get(key);
    }

    @Override
    public void select(Student info) {

    }

    @Override
    public void selectAll() {
        System.out.println("==================================");
        for (String str : Data.studentStore.keySet()) {
            List<String> sub = new ArrayList<>();
            for(String subject : Data.studentStore.get(str).getSubjectList().keySet()) {
                sub.add(Data.studentStore.get(str).getSubjectList().get(subject).getSubjectName());
            }
            System.out.println("수강생 고유번호 : " + Data.studentStore.get(str).getStudentId());
            System.out.println("수강생 이름 : " + Data.studentStore.get(str).getStudentName());
            System.out.println("수강생 과목 : " + String.join(",", sub));
            System.out.println("수강생 상태 : " + Data.studentStore.get(str).getStudentStatus());
            System.out.println("==================================");
        }
    }


    @Override
    public void update(String id , String fleid , String value) {
        Student student = Data.studentStore.get(id);
        switch (fleid) {
            case "studentName" :
                student.setStudentName(value);
                break;
            case "studentStatus" :
                student.setStudentStatus(value);
                break;
        }
        Data.studentStore.put(id , student);
    }

    @Override
    public void delete(String id) {
        Data.studentStore.remove(id);
    }

    @Override
    public void insert(String key , Student student) {
        Data.studentStore.put(key , student);
    }

    public boolean lenCheck() {
        return Data.studentStore.size() > 0 ? true : false;
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
        for (String str : Data.studentStore.keySet()) {
            Student student = Data.studentStore.get(str);
            if (student.getStudentStatus().equals(status)) {
                list.add(student);
            }
        }

        System.out.println("==================================");
        for (Student student : list) {
            List<String> sub = new ArrayList<>();
            for(String subject : student.getSubjectList().keySet()) {
                sub.add(student.getSubjectList().get(subject).getSubjectName());
            }
            System.out.println("수강생 고유번호 : " + student.getStudentId());
            System.out.println("수강생 이름 : " + student.getStudentName());
            System.out.println("수강생 과목 : " + String.join(",", sub));
            System.out.println("수강생 상태 : " + student.getStudentStatus());
            System.out.println("==================================");
        }
    }
}
