package camp.data;

import camp.model.Student;
import camp.model.Subject;
import camp.model.score.Score;

import java.util.Map;

public class Data {    // index 관리 필드
    public static int studentIndex;     // 학생 고유번호 INDEX
    public static int subjectIndex;     // 과목 고유번호 INDEX

    // 데이터 저장소
    public static Map<String , Student> studentStore;
    public static Map<String , Subject> subjectStore;
    public static Map<String, Score> scoreStore;
}
