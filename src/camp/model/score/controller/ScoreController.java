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

//    private final ManagementInterface<Score> management = new ScoreManagement();


    /**
     * registerScore는 점수를 등록하는 메서드이다.
     *
     * 순서에 따라 각 로직에 가면 간단한 설명 주석이 있다.
     *
     * 각 로직 순서
     * 1 -> 3 -> 4-1 ~ 4-4
     *
     * 2 -> 2-1
     * 저장소에 등록 되어있을 경우: -> 4-1 ~ 4-4
     * 저장소에 등록 되어있지 않을 경우: -> 3 -> 4-1 ~ 4-4
     */

    public void registerScore(String studentId, int subjectScore, Subject subject) {
        Score score = null;

        // 1. 저장소가 비어있으면 수강 과목을 바로 등록한다.
            // 2. 저장소가 비어있지 않으면, 과목이 등록되어있는지 확인한다.
            for (String key : scoreStore.keySet()) {
                // 2-1. 등록되어있으면, 등록되어있는 Score를 꺼내고 for문 탈출.
                if (key.equals(makeKey(studentId, subject))) {
                    score = scoreStore.get(key);
                    break;
                }
            }

        // 3. 저장소가 비어있거나 || 저장소에 등록된 점수가 없을 때 이곳으로 들어온다.
        if (score == null) {
            GradeConvertor gradeConvertor = getNewGradeConvertor(subject);

            if (gradeConvertor == null) {
                System.out.println("잘못 분류된 과목입니다. 분류:" + subject.getSubjectType());
                return;
            }

            score = new Score(gradeConvertor, subject.getSubjectId(), studentId, new HashMap<>(), new HashMap<>());
        }

        // 4-1. 회차는 0~9 사이의 값이어야 한다.
        Map<Integer, Integer> scoreMap = score.getScoreMap();
        int times = scoreMap.size();

        if (times >= 10) {
            System.out.println("더 이상 시험 점수를 등록할 수 없습니다.\n현재까지 등록된 회차: " + times + "\n");
            return;
        }

        // 4-2. 회차, 점수를 보관
        scoreMap.put(++times, subjectScore);

        // 4-3. 회차, 등급을 보관
        Grade grade = score.calculateGrade();

        // 4-4. 점수 저장소에 등록
        String storeKey = makeKey(studentId, subject);
        scoreStore.put(storeKey, score);

        // 4-5. 등록한 정보를 출력
        Score registrationScore = scoreStore.get(storeKey);
        System.out.println("===등록된 정보를 확인합니다.===");
        System.out.println("학생 번호: " + registrationScore.getStudentId());
        System.out.println("과목 번호: " + registrationScore.getSubjectId());
        System.out.println("회차: " + registrationScore.getScoreMap().size());
        System.out.println("점수: " + registrationScore.getScoreMap().get(times));
        System.out.println("등급: " + registrationScore.getGradeMap().get(times));
        System.out.println("===점수 등록이 완료됐습니다.===");
        System.out.println();
    }

    // scoreStore에 새로 저장할 key값을 생성한다.(Unique)
    private static String makeKey(String studentId, Subject subject) {
        return studentId + subject.getSubjectId();
    }

    // 새로운 Score를 생성할 때 필요한 gradeConvertor를 반환해준다.
    private static GradeConvertor getNewGradeConvertor(Subject subject) {

        if (subject.getSubjectType().equals("MANDATORY")) {
            return new RequiredSubConvertor();

        } else if (subject.getSubjectType().equals("CHOICE")) {
            return new RequiredSubConvertor();

        } else {
            return null;
        }
    }
}
