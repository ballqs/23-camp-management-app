package camp.repository;

import camp.interfaces.ManagementInterface;
import camp.model.Subject;

import java.util.HashMap;
import java.util.Map;

public class SubjectManagement implements ManagementInterface<Subject> {

    public final static Map<String , Subject> SUBJECTSTORE = new HashMap<>(); // 과목 고유번호, 과목 객체
    private int subjectIndex = 0;     // 과목 고유번호 INDEX

    public int getSubjectIndex() {
        subjectIndex++;
        return subjectIndex;
    }

    @Override
    public Subject getData(String value) {
        return SUBJECTSTORE.get(value);
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
        for (String str : SUBJECTSTORE.keySet()) {
            System.out.println("과목 고유번호 : " + SUBJECTSTORE.get(str).getSubjectId());
            System.out.println("과목 이름 : " + SUBJECTSTORE.get(str).getSubjectName());
            System.out.println("과목 타입 : " + SUBJECTSTORE.get(str).getSubjectType());
            System.out.println("==================================");
        }
    }

    @Override
    public void insert(String key , Subject subject) {
        SUBJECTSTORE.put(key , subject);
    }
}
