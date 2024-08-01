package camp.model.score;

public class Score {

    private GradeConvertor gradeConvertor;
    private String SubjectId;  // 과목 고유번호
    private String StudentId;  // 수강생 고유번호
    private int times;           // 회차 ※변수명 설정해주세요.
    private int score;           // 점수 ※변수명 설정해주세요.
    private Grade grade;

    public Score(GradeConvertor gradeConvertor, String SubjectId, String StudentId, int score) {

        /**
         * 순서대로 보자.
         * gradeConvertor -> 점수를 등급으로 산정해주는 클래스 -> 다형성 활용 -> 필수 | 선택 과목에 따라서 생성자로 받는다.
         * SubjectId -> 과목 고유번호 -> 고유번호는 문자 + 숫자 -> String
         * StudentId -> 학생 고유번호 -> 고유번호는 문자 + 숫자 -> String
         * score -> 점수
         * times++; -> 회차 -> Score를 생성하면 회차가 1회차로 등록된다.
         */
        this.gradeConvertor = gradeConvertor;
        this.SubjectId = SubjectId;
        this.StudentId = StudentId;
        this.score = score;
        this.times++;
    }


    public String getSubjectId() {
        return SubjectId;
    }

    public String getStudentId() {
        return StudentId;
    }

    public int getTimes() {
        return times;
    }

    public int getScore() {
        return score;
    }

    public Grade calculateGrade() {
        grade = gradeConvertor.ScoreToGrade(score);
        return grade;
    }
}
