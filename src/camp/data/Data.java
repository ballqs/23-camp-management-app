package camp.data;

import camp.model.Student;
import camp.model.Subject;
import camp.model.score.Score;

import java.util.Map;

public class Data {    // index 관리 필드
    public static int studentIndex;     // 학생 고유번호 INDEX
    public static int subjectIndex;     // 과목 고유번호 INDEX

    // 데이터 저장소
    public static Map<String , Student> studentStore; // 학생 고유번호, 학생 객체
    public static Map<String , Subject> subjectStore; // 과목 고유번호, 과목 객체
    public static Map<String, Score> scoreStore; // (학생 고유번호 + 과목 고유번호)
}
