package camp.model;

import camp.enums.Grade;
import camp.interfaces.GradeConvertor;

import java.util.HashMap;
import java.util.Map;

public class Score {

    private String subjectId;  // 과목의 점수
    private String studentId;  // 학생의 점수
    private final GradeConvertor gradeConvertor; // 점수 -> 등급 변환기
    private final Map<Integer, Integer> scoreMap; // 회차에 따른 점수
    private final Map<Integer, Grade> gradeMap; // 회차에 따른 등급

    public Score(GradeConvertor gradeConvertor,
                 String subjectId,
                 String studentId
    ) {
        this.gradeConvertor = gradeConvertor;
        this.subjectId = subjectId;
        this.studentId = studentId;
        this.scoreMap = new HashMap<>();
        this.gradeMap = new HashMap<>();
    }

    public Grade calculateGrade(int score) {
        return gradeConvertor.ScoreToGrade(score);
    }

    public String getSubjectId() {
        return subjectId;
    }

    public String getStudentId() {
        return studentId;
    }

    public Map<Integer, Integer> getScoreMap() {
        return scoreMap;
    }

    public Map<Integer, Grade> getGradeMap() {
        return gradeMap;
    }
}
