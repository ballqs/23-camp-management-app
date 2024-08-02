package camp;

import camp.enums.IndexType;
import camp.enums.SubjectType;
import camp.model.Subject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Init {

    // 과목 초기 데이터 생성
    public Map<String , Subject> setInitData() {
        Sequence sequence = new Sequence();
        Map<String , Subject> subjectStore = new HashMap<>();
        String id = "";

        id = sequence.sequence(IndexType.SU.name());
        subjectStore.put(id ,
                new Subject(
                    id,
                    "Java",
                    SubjectType.MANDATORY.name()
        ));

        id = sequence.sequence(IndexType.SU.name());
        subjectStore.put(id ,
                new Subject(
                    id,
                    "객체지향",
                    SubjectType.MANDATORY.name()
                ));

        id = sequence.sequence(IndexType.SU.name());
        subjectStore.put(id ,
                new Subject(
                    id,
                    "Spring",
                    SubjectType.MANDATORY.name()
                ));

        id = sequence.sequence(IndexType.SU.name());
        subjectStore.put(id ,
                new Subject(
                    id,
                    "JPA",
                    SubjectType.MANDATORY.name()
                ));

        id = sequence.sequence(IndexType.SU.name());
        subjectStore.put(id ,
                new Subject(
                    id,
                    "MySQL",
                    SubjectType.MANDATORY.name()
                ));

        id = sequence.sequence(IndexType.SU.name());
        subjectStore.put(id ,
                new Subject(
                    id,
                    "디자인 패턴",
                    SubjectType.CHOICE.name()
                ));

        id = sequence.sequence(IndexType.SU.name());
        subjectStore.put(id ,
                new Subject(
                    id,
                    "Spring Security",
                    SubjectType.CHOICE.name()
                ));

        id = sequence.sequence(IndexType.SU.name());
        subjectStore.put(id ,
                new Subject(
                    id,
                    "Redis",
                    SubjectType.CHOICE.name()
                ));

        id = sequence.sequence(IndexType.SU.name());
        subjectStore.put(id ,
                new Subject(
                    id,
                    "MongoDB",
                    SubjectType.CHOICE.name()
                ));
        return subjectStore;
    }
}
