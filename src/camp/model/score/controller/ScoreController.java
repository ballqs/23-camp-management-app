package camp.model.score.controller;

import camp.model.Subject;
import camp.model.score.gradeconvertor.Grade;
import camp.model.score.gradeconvertor.GradeConvertor;
import camp.model.score.gradeconvertor.RequiredSubConvertor;
import camp.model.score.Score;

import java.util.HashMap;
import java.util.Map;

import static camp.data.Data.scoreStore;

public class ScoreController {

    /**
     * Score 객체를 생성
     */
    public Score createScore(String studentId, Subject subject) {
        return new Score(getNewGradeConvertor(subject), subject.getSubjectId(), studentId, new HashMap<>(), new HashMap<>());
    }


    /**
     * registerScore 점수를 등록
     */
    public void registerScore(int subjectScore, Score score) {
        Map<Integer, Integer> scoreMap = score.getScoreMap();

        int times = score.getScoreMap().size();
        scoreMap.put(++times, subjectScore);
        score.calculateGrade();

        String storeKey = makeKey(score.getStudentId(), score.getSubjectId());
        scoreStore.put(storeKey, score);

        Score registrationScore = scoreStore.get(storeKey);
        System.out.println("===등록된 정보를 확인합니다.===");
        System.out.println("학생 번호: " + registrationScore.getStudentId());
        System.out.println("과목 번호: " + registrationScore.getSubjectId());
        System.out.println("회차: " + registrationScore.getScoreMap().size());
        System.out.println("점수: " + registrationScore.getScoreMap().get(times));
        System.out.println("등급: " + registrationScore.getGradeMap().get(times));
        System.out.println("===점수 등록이 완료됐습니다.===\n");
    }

    /**
     * 저장소에 있는 특정 Score를 찾음 -> 없으면 null 반환
     */
    public Score findScoreInStore(String studentId, Subject subject) {
        return scoreStore.getOrDefault(makeKey(studentId, subject.getSubjectId()), null);
    }

    /**
     * 점수를 등록할 때 사용할 키를 만들어줌.
     */
    public String makeKey(String studentId, String subjectId) {
        return studentId + subjectId;
    }

    /**
     * 점수 -> 등급 변환 컨버터
     */
    private GradeConvertor getNewGradeConvertor(Subject subject) {

        if (subject.getSubjectType().equals("MANDATORY")) {
            return new RequiredSubConvertor();

        } else if (subject.getSubjectType().equals("CHOICE")) {
            return new RequiredSubConvertor();

        } else {
            throw new IllegalStateException("과목의 타입이 분류 제대로 되어있지 않습니다. 과목 타입: " + subject.getSubjectType());
        }
    }
}
