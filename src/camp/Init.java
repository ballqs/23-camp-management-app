package camp;

import camp.enums.IndexType;
import camp.enums.SubjectType;
import camp.model.Subject;

import java.util.List;

import static camp.cont.SubjectType.*;

public class Init {

    // 과목 초기 데이터 생성
    public List<Subject> setInitData() {
        Sequence sequence = new Sequence();
        List<Subject> subjectStore = List.of(
                new Subject(
                        sequence.sequence(IndexType.SU.name()),
                        "Java",
                        MANDATORY.name()
                ),
                new Subject(
                        sequence.sequence(IndexType.SU.name()),
                        "객체지향",
                        MANDATORY.name()
                ),
                new Subject(
                        sequence.sequence(IndexType.SU.name()),
                        "Spring",
                        MANDATORY.name()
                ),
                new Subject(
                        sequence.sequence(IndexType.SU.name()),
                        "JPA",
                        MANDATORY.name()
                ),
                new Subject(
                        sequence.sequence(IndexType.SU.name()),
                        "MySQL",
                        MANDATORY.name()
                ),
                new Subject(
                        sequence.sequence(IndexType.SU.name()),
                        "디자인 패턴",
                        CHOICE.name()
                ),
                new Subject(
                        sequence.sequence(IndexType.SU.name()),
                        "Spring Security",
                        CHOICE.name()
                ),
                new Subject(
                        sequence.sequence(IndexType.SU.name()),
                        "Redis",
                        CHOICE.name()
                ),
                new Subject(
                        sequence.sequence(IndexType.SU.name()),
                        "MongoDB",
                        CHOICE.name()
                )
        );
        return subjectStore;
    }
}
