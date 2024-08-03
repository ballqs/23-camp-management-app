package camp;

import camp.data.Data;
import camp.model.Student;

import java.util.ArrayList;
import java.util.List;

public class StudentManagement implements ManagementInterface<Student>{
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
    public void delete() {

    }

    @Override
    public void insert(String key , Student student) {
        Data.studentStore.put(key , student);
    }

    public boolean lenCheck() {
        return Data.studentStore.size() > 0 ? true : false;
    }
}
