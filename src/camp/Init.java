package camp;

import camp.enums.IndexType;
import camp.enums.SubjectType;
import camp.model.Subject;

import java.util.List;

public class Init {

    // 과목 초기 데이터 생성
    public List<Subject> setInitData() {
        Sequence sequence = new Sequence();
        List<Subject> subjectStore = List.of(
                new Subject(
                        sequence.sequence(IndexType.SU.name()),
                        "Java",
                        SubjectType.MANDATORY.name()
                ),
                new Subject(
                        sequence.sequence(IndexType.SU.name()),
                        "객체지향",
                        SubjectType.MANDATORY.name()
                ),
                new Subject(
                        sequence.sequence(IndexType.SU.name()),
                        "Spring",
                        SubjectType.MANDATORY.name()
                ),
                new Subject(
                        sequence.sequence(IndexType.SU.name()),
                        "JPA",
                        SubjectType.MANDATORY.name()
                ),
                new Subject(
                        sequence.sequence(IndexType.SU.name()),
                        "MySQL",
                        SubjectType.MANDATORY.name()
                ),
                new Subject(
                        sequence.sequence(IndexType.SU.name()),
                        "디자인 패턴",
                        SubjectType.CHOICE.name()
                ),
                new Subject(
                        sequence.sequence(IndexType.SU.name()),
                        "Spring Security",
                        SubjectType.CHOICE.name()
                ),
                new Subject(
                        sequence.sequence(IndexType.SU.name()),
                        "Redis",
                        SubjectType.CHOICE.name()
                ),
                new Subject(
                        sequence.sequence(IndexType.SU.name()),
                        "MongoDB",
                        SubjectType.CHOICE.name()
                )
        );
        return subjectStore;
    }
}
