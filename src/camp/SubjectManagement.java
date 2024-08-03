package camp;

import camp.data.Data;
import camp.model.Subject;

import java.util.List;

public class SubjectManagement implements ManagementInterface<Subject>{
    @Override
    public Subject getData(String value) {
        return Data.subjectStore.get(value);
    }

    @Override
    public void select(Subject info) {

    }

    @Override
    public void selectAll() {
        System.out.println("==================================");
        for (String str : Data.subjectStore.keySet()) {
            System.out.println("과목 고유번호 : " + Data.subjectStore.get(str).getSubjectId());
            System.out.println("과목 이름 : " + Data.subjectStore.get(str).getSubjectName());
            System.out.println("과목 타입 : " + Data.subjectStore.get(str).getSubjectType());
            System.out.println("==================================");
        }
    }

    @Override
    public void update(String id , String fleid , String value) {

    }

    @Override
    public void delete() {

    }

    @Override
    public void insert(String key , Subject subject) {

    }
}
