package camp.model;

import java.util.List;

// 점수
public class Score {

    private String scoreSubjectId;  // 과목 고유번호
    private String scoreStudentId;  // 수강생 고유번호
    private int times;           // 회차 ※변수명 설정해주세요.
    private int score;           // 점수 ※변수명 설정해주세요.
    private char grade;
    private List<Score> repository;// 등급(A,B,C,D,E,F,N) ※변수명 설정해주세요.

    public Score(String scoreSubjectId, String scoreStudentId, int score) {
        this.scoreSubjectId = scoreSubjectId;
        this.scoreStudentId = scoreStudentId;
        this.score = score;
        this.times++;
    }

    public String getScoreSubjectId() {
        return scoreSubjectId;
    }

    public String getScoreStudentId() {
        return scoreStudentId;
    }

    public int getTimes() {
        return times;
    }

    public int getScore() {
        return score;
    }

    public char getGrade() {
        return grade;
    }
}
