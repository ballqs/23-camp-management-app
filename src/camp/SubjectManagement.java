package camp;

import camp.data.Data;
import camp.model.Subject;

import java.util.List;

public class SubjectManagement implements ManagementInterface<Subject>{


    public Subject getData(String value) {
        return Data.subjectStore.get(value);
    }

    @Override
    public Subject select(List<Subject> list, String id) {
        return null;
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
    public Subject update(String id , String fleid , String value) {
        return null;
    }

    @Override
    public Subject delete() {
        return null;
    }

    @Override
    public Subject insert() {
        return null;
    }
}
