package camp;

import camp.model.Subject;

import java.util.ArrayList;
import java.util.List;

public class Init {
    // 과목 타입
    public String SUBJECT_TYPE_MANDATORY = "MANDATORY";     // 필수
    public String SUBJECT_TYPE_CHOICE = "CHOICE";           // 선택

    // 과목 초기 데이터 생성
    public List<Subject> setInitData() {
        Sequence sequence = new Sequence();
        List<Subject> subjectStore = List.of(
                new Subject(
                        sequence.sequence(CampManagementApplication.INDEX_TYPE_SUBJECT),
                        "Java",
                        SUBJECT_TYPE_MANDATORY
                ),
                new Subject(
                        sequence.sequence(CampManagementApplication.INDEX_TYPE_SUBJECT),
                        "객체지향",
                        SUBJECT_TYPE_MANDATORY
                ),
                new Subject(
                        sequence.sequence(CampManagementApplication.INDEX_TYPE_SUBJECT),
                        "Spring",
                        SUBJECT_TYPE_MANDATORY
                ),
                new Subject(
                        sequence.sequence(CampManagementApplication.INDEX_TYPE_SUBJECT),
                        "JPA",
                        SUBJECT_TYPE_MANDATORY
                ),
                new Subject(
                        sequence.sequence(CampManagementApplication.INDEX_TYPE_SUBJECT),
                        "MySQL",
                        SUBJECT_TYPE_MANDATORY
                ),
                new Subject(
                        sequence.sequence(CampManagementApplication.INDEX_TYPE_SUBJECT),
                        "디자인 패턴",
                        SUBJECT_TYPE_CHOICE
                ),
                new Subject(
                        sequence.sequence(CampManagementApplication.INDEX_TYPE_SUBJECT),
                        "Spring Security",
                        SUBJECT_TYPE_CHOICE
                ),
                new Subject(
                        sequence.sequence(CampManagementApplication.INDEX_TYPE_SUBJECT),
                        "Redis",
                        SUBJECT_TYPE_CHOICE
                ),
                new Subject(
                        sequence.sequence(CampManagementApplication.INDEX_TYPE_SUBJECT),
                        "MongoDB",
                        SUBJECT_TYPE_CHOICE
                )
        );
        return subjectStore;
    }
}
