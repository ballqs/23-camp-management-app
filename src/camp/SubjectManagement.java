package camp;

import camp.model.Subject;

import java.util.List;

public class SubjectManagement implements ManagementInterface<Subject>{
    @Override
    public Subject select(List<Subject> subjectStore , String subjectId) {
        for (Subject subject : subjectStore) {
            if (subject.getSubjectId().equals(subjectId)) {
                return subject;
            }
        }
        return null;
    }

    @Override
    public void selectAll(List<Subject> subjectStore) {
        System.out.println("==================================");
        for (Subject list : subjectStore) {
            System.out.println("과목 고유번호 : " + list.getSubjectId());
            System.out.println("과목 이름 : " + list.getSubjectName());
            System.out.println("과목 타입 : " + list.getSubjectType());
            System.out.println("==================================");
        }
    }

    @Override
    public Subject update() {
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
