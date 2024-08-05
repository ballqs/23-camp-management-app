package camp.repository;

import camp.data.Data;
import camp.interfaces.ManagementInterface;
import camp.model.Subject;

public class SubjectManagement implements ManagementInterface<Subject> {
    @Override
    public Subject getData(String value) {
        return Data.SUBJECTSTORE.get(value);
    }

    @Override
    public void print(Subject info) {
        System.out.println("과목 고유번호 : " + info.getSubjectId());
        System.out.println("과목 이름 : " + info.getSubjectName());
        System.out.println("과목 타입 : " + info.getSubjectType());
        System.out.println("==================================");
    }

    @Override
    public void printAll() {
        System.out.println("==================================");
        for (String str : Data.SUBJECTSTORE.keySet()) {
            System.out.println("과목 고유번호 : " + Data.SUBJECTSTORE.get(str).getSubjectId());
            System.out.println("과목 이름 : " + Data.SUBJECTSTORE.get(str).getSubjectName());
            System.out.println("과목 타입 : " + Data.SUBJECTSTORE.get(str).getSubjectType());
            System.out.println("==================================");
        }
    }

    @Override
    public void insert(String key , Subject subject) {
        Data.SUBJECTSTORE.put(key , subject);
    }
}
