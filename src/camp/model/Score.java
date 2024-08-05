package camp.model;

import camp.enums.Grade;
import camp.interfaces.GradeConvertor;

import java.util.Map;

public class Score {

    private final GradeConvertor gradeConvertor;
    private String subjectId;  // 어떤 과목의 점수
    private String studentId;  // 어떤 학생의 점수
    private final Map<Integer, Integer> scoreMap; // 회차에 따른 점수
    private final Map<Integer, Grade> gradeMap; // 회차에 따른 등급

    public Score(GradeConvertor gradeConvertor,
                 String subjectId,
                 String studentId,
                 Map<Integer, Integer> scoreMap,
                 Map<Integer, Grade> gradeMap
    ) {
        this.gradeConvertor = gradeConvertor;
        this.subjectId = subjectId;
        this.studentId = studentId;
        this.scoreMap = scoreMap;
        this.gradeMap = gradeMap;
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

    public Grade calculateGrade(int score) {
        return gradeConvertor.ScoreToGrade(score);
    }

    @Override
    public String toString() {
        return "학생 고유번호:'" + studentId + "\n" +
                ", 과목 고유번호:'" + subjectId + "\n" +
                ", 최신 회차, 최신 점수: " + scoreMap.size() + +scoreMap.get(scoreMap.size()) + "\n" +
                ", 최신 회차, 최신 등급: " + gradeMap + scoreMap.size() + +scoreMap.get(scoreMap.size());
    }
}
