package camp.model.score;

import java.util.Map;

public class Score {

    private final GradeConvertor gradeConvertor;
    private String subjectId;  // 과목 고유번호
    private String studentId;  // 수강생 고유번호
    private final Map<Integer, Integer> scoreMap;
    private final Map<Integer, Grade> gradeMap;

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

    public GradeConvertor getGradeConvertor() {
        return gradeConvertor;
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

    public Grade calculateGrade() {
        Grade grade = gradeConvertor.ScoreToGrade(scoreMap);
        gradeMap.put(gradeMap.size() + 1, grade);
        return grade;
    }

    @Override
    public String toString() {
        return "Score{" +
                ", 학생 고유번호:'" + studentId + '\'' +
                ", 과목 고유번호:'" + subjectId + '\'' +
                ", 회차, 점수: " + scoreMap +
                ", 회차, 등급: " + gradeMap +
                '}';
    }
}
