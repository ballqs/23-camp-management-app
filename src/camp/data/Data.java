package camp.data;

import camp.model.Student;
import camp.model.Subject;
import camp.model.Score;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Data {    // index 관리 필드

    public final static Scanner SC = new Scanner(System.in);
    public static int STUDENTINDEX;     // 학생 고유번호 INDEX
    public static int SUBJECTINDEX;     // 과목 고유번호 INDEX

    // 데이터 저장소
    public final static Map<String , Student> STUDENTSTORE = new HashMap<>(); // 학생 고유번호, 학생 객체
    public final static Map<String , Subject> SUBJECTSTORE = new HashMap<>(); // 과목 고유번호, 과목 객체
    public final static Map<String, Score> SCORESTORE = new HashMap<>(); // (학생 고유번호 + 과목 고유번호)
}
