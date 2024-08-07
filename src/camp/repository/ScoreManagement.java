package camp.repository;

import camp.function.OptionalSubConvertor;
import camp.model.Subject;
import camp.enums.Grade;
import camp.interfaces.GradeConvertor;
import camp.function.RequiredSubConvertor;
import camp.model.Score;

import java.util.*;

public class ScoreManagement {


    public final static Map<String, Score> SCORESTORE = new HashMap<>(); // (학생 고유번호 + 과목 고유번호)

    /**
     * A학생의 B과목을 찾아야함 -> 학생 ID, 과목 ID 둘다 필요
     * 저장소에 있는 특정 Score를 찾음 -> 없으면 null 반환
     */
    public Score findScoreById(String studentId, String subjectId) {
        return SCORESTORE.getOrDefault(makeKey(studentId, subjectId), null);
    }

    /**
     * 학생의 등록된 Score 모두 반환 -> A학생의 등록된 점수 모두를 가져오면 됨 -> 학생 ID만 필요
     */
    public List<Score> findAllScoreByStudentId(String studentId) {
        List<Score> scores = new ArrayList<>();
        for (Score score : SCORESTORE.values()) {
            if (score.getStudentId().equals(studentId)) {
                scores.add(score);
            }
        }
        return scores;
    }

    /**
     * 저장할 Score 객체를 생성
     */
    public Score createScore(String studentId, Subject subject) {
        return new Score(getNewGradeConvertor(subject), subject.getSubjectId(), studentId);
    }

    /**
     * 점수를 등록
     */
    public void register(int subjectScore, Score score) {
        Map<Integer, Integer> scoreMap = score.getScoreMap();
        Map<Integer, Grade> gradeMap = score.getGradeMap();

        int times = score.getScoreMap().size(); // 현재 저장되어있는 회차(진행중인 작업 아직 등록 전)
        scoreMap.put(score.getScoreMap().size() + 1, subjectScore); // 회차에 따른 점수 저장

        Grade grade = score.calculateGrade(subjectScore);// 점수에 따른 등급 저장
        gradeMap.put(times + 1, grade);

        //최초 등록되는 Score는 저장소에 등록
        if (scoreMap.size() == 1) {
            String storeKey = makeKey(score.getStudentId(), score.getSubjectId()); // 점수 저장소의 key값 생성
            SCORESTORE.put(storeKey, score);
        }
    }

    /**
     * 점수 업데이트
     */
    public void update(Score score, int times, int scoreToChange) {
        score.getScoreMap().replace(times, scoreToChange);
        Grade grade = score.calculateGrade(scoreToChange);
        score.getGradeMap().replace(times, grade);
    }

    public void delete(String studentId) {
        Map<String , Score> map = SCORESTORE;
        for (String str : Set.copyOf(map.keySet())) {
            Score score = map.get(str);
            if (score.getStudentId().equals(studentId)) {
                map.remove(str);
            }
        }
    }


//  PRIVATE ZONE==================================================================================================


    /**
     * 점수를 등록할 때 사용할 키를 만들어줌.
     */
    private String makeKey(String studentId, String subjectId) {
        return studentId + subjectId;
    }

    /**
     * 점수 -> 등급 변환 컨버터
     */
    private GradeConvertor getNewGradeConvertor(Subject subject) {

        if (subject.getSubjectType().equals("MANDATORY")) {
            return new RequiredSubConvertor();

        } else if (subject.getSubjectType().equals("CHOICE")) {
            return new OptionalSubConvertor();

        } else {
            throw new IllegalStateException("과목의 타입이 분류 제대로 되어있지 않습니다. 과목 타입: " + subject.getSubjectType());
        }
    }
}
