package camp.service;

import camp.repository.StudentManagement;
import camp.repository.SubjectManagement;
import camp.model.Student;
import camp.model.Subject;
import camp.model.Score;
import camp.repository.ScoreManagement;
import camp.enums.Grade;
import camp.interfaces.GradeConvertor;
import camp.function.RequiredSubConvertor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import static camp.data.Data.*;
import static camp.enums.SubjectType.*;

public class ScoreService {

    private final ScoreManagement scoreManagement = new ScoreManagement();
    private final StudentManagement studentManagement = new StudentManagement();
    private final SubjectManagement subjectManagement = new SubjectManagement();
    private final Scanner scanner;

    public ScoreService(Scanner scanner) {
        this.scanner = scanner;
    }

    /**
     * 점수 등록 서비스
     */
    public void createScore() {

        String studentId = designateStudentId(); // 점수를 등록할 수강생 ID
        StringBuilder subjectId = new StringBuilder();
        Score score = getSpecificScore(studentId, subjectId); // score를 얻어오는 로직

        if (score == null) {
            System.out.print("***해당 과목에 등록된 점수가 없습니다.***\n해당 과목의 점수를 등록합니다.\n");
            Subject subject = SUBJECTSTORE.get(subjectId.toString());
            score = scoreManagement.createScore(studentId, subject); // new Score(); //subject는 ID와 Type이 필요.
        }


        // Score의 회차가 10회차 이상이면 안된다.
        if (score.getScoreMap().size() >= 10) {
            throw new IllegalStateException("최대 10회차까지만 등록이 가능합니다.");
        }

        int option;
        do {
            int times = score.getScoreMap().size();
            String subjectName = SUBJECTSTORE.get(score.getSubjectId()).getSubjectName();

            System.out.print(subjectName + "과목의 " + (times + 1) + "회차에 점수를 등록하시겠습니까? ");
            System.out.print("(1. 예 | 2. 아니오)\n입력한 옵션: ");
            option = scanner.nextInt();

            switch (option) {
                case 1:
                    break;

                case 2:
                    throw new IllegalStateException(subjectName + "과목의 점수를 등록하지 않습니다."); // 예외 발생!!

                default:
                    System.out.println("옵션을 잘못 입력하셨습니다.\n");
            }
        } while (option != 1);

        System.out.print("등록할 점수를 입력해주세요: ");
        int subjectScore = inputScore();

        scoreManagement.register(subjectScore, score);

        // 결과 출력
        System.out.println("*****등록 결과 확인 시작*****");
        completePrinter(score, score.getScoreMap().size());
        System.out.println("*****등록 결과 확인 종료*****\n");

    }


    /**
     * 점수 수정 서비스
     */
    public void updateRoundScoreBySubject() {
        String studentId = designateStudentId(); // 수정할 학생
        Score score = getSpecificScore(studentId);

        // 수정할 점수 존재하는지 검증 로직
        if (score == null) {
            throw new IllegalStateException("***수정할 점수가 등록되지 않은 과목입니다.***\n");
        }

        // 수정할 과목의 각 회차별 점수를 출력
        System.out.println(SUBJECTSTORE.get(score.getSubjectId()).getSubjectName() + "과목의 회차별 점수 출력");
        for (int i = 0; i < score.getScoreMap().size(); i++) {
            System.out.println("-" + (i + 1) + "회차 " + score.getScoreMap().get(i + 1) + "점");
        }

        // 수정할 회차 입력받기
        int times = designateTimes(score);

        // 변경할 점수 입력받기
        System.out.print("변경할 점수를 입력해주세요: ");
        int scoreToChange = inputScore();


        // 업데이트 컨트롤러 호출 -> 수정할 score 객체, 회차, 변경할 점수 넘겨줘야함.
        scoreManagement.update(score, times, scoreToChange);

        // 결과 출력
        System.out.println("*****수정 결과 확인 시작*****");
        completePrinter(score, times);
        System.out.println("*****수정 결과 확인 종료*****\n");
    }

    private int inputScore() {
        int inputScore = scanner.nextInt();
        while (0 > inputScore || inputScore > 100) {
            System.out.print("잘못된 점수를 입력하였습니다.\n0~100 사이의 점수를 다시 입력해주세요: ");
            inputScore = scanner.nextInt();
        }
        return inputScore;
    }

    /**
     * 등급 조회 서비스
     */
    public void inquireRoundGradeBySubject() {
        String studentId = designateStudentId(); // 조회할 학생 ID
        Score score = getSpecificScore(studentId); // 학생 ID에 일치하는 등록된 Score -> scoreController.findScoreInStore()

        System.out.print("1. 회차별 등급 조회 | 2. 특정 과목 평균등급 조회 | 3. 필수과목 평균등급 조회\n선택한 옵션: ");
        int option = scanner.nextInt();

        switch (option) {
            case 1 -> {
                for (int i = 0; i < score.getScoreMap().size(); i++) {
                    System.out.println("*****수정 결과 확인 시작*****");
                    completePrinter(score, (i + 1));
                    System.out.println("*****수정 결과 확인 종료*****");

                }

                /* 특정 과목, 특정 회차 조회 로직
                System.out.println(
                        SUBJECTSTORE.get("===" + score.getSubjectId()).getSubjectName() + "과목의 회차별 등급 조회==="
                );
                for (int i = 0; i < score.getScoreMap().size(); i++) {
                    System.out.println(
                            "*" + (i + 1) + "회차 " +
                                    score.getScoreMap().get(i + 1) + "점 " +
                                    "등급: " + score.getGradeMap().get(i + 1));
                }*/
            }

            case 2 -> {
                // 과목의 모든 회차의 점수를 담는다.
                Map<Integer, Integer> scoreMap = score.getScoreMap();

                int sumScore = 0;
                for (int i = 0; i < scoreMap.size(); i++) {
                    sumScore += scoreMap.get(i + 1);
                }

                //점수의 평균값
                int averageScore = sumScore / scoreMap.size();

                Grade grade = score.calculateGrade(averageScore);
                System.out.println("=====평균등급 조회 결과 확인 시작=====");
                System.out.println(
                        "학생 ID: " + score.getSubjectId() +
                                "\n과목명: " + SUBJECTSTORE.get(score.getSubjectId()).getSubjectName() +
                                "\n평균 점수: " + averageScore +
                                "\n평균 등급: " + grade
                );
                System.out.println("=====평균등급 조회 결과 확인 종료=====");
            }

            case 3 -> {
                // 학생의 등록된 점수 다 가져오기
                List<Score> scores = scoreManagement.findAllScoreByStudentId(studentId);

                // 필수확인 && 필수과목 회차별 점수 합 담기
                ArrayList<Integer> mandatoryScoreNum = new ArrayList<>();
                scores.stream()
                        .filter(scored ->
                            SUBJECTSTORE.get(scored.getSubjectId()).getSubjectType().equals(MANDATORY.name()))
                        .forEach(scored -> {
                            Map<Integer, Integer> scoreMap = scored.getScoreMap();
                            for (Integer key : scoreMap.keySet()) {
                                mandatoryScoreNum.add(scoreMap.get(key));
                            }
                        });

                int sumScored = 0;
                for (Integer scoreNum : mandatoryScoreNum) {
                    sumScored += scoreNum;
                }

                GradeConvertor requiredGradeConvertor = new RequiredSubConvertor();
                int averageMandatory = sumScored / mandatoryScoreNum.size();
                System.out.println(
                        "학생 ID: " + studentId+
                                "\n필수 과목 평균 점수: " + averageMandatory +
                                "\n필수 과목 평균 등급: " + requiredGradeConvertor.ScoreToGrade(averageMandatory)
                );
            }

            default -> throw new IllegalStateException("잘못된 옵션을 선택했습니다.");
        }
    }

    public void delete(String studentId) {
        scoreManagement.delete(studentId);
    }

    // PRIVATE ZONE=====================================================================================================

    /**
     * target 학생 id 입력받는 메서드
     */
    private String designateStudentId() {
        studentManagement.printAll(); // 학생 목록 조회
        System.out.print("\n관리할 수강생의 번호를 입력하시오...");
        String studentId = scanner.nextLine();

        if (studentManagement.getData(studentId) == null) {
            throw new IllegalStateException("등록되지 않은 학생 정보입니다.");
        }

        return studentId;
    }

    /**
     * 메서드 오버로딩
     * target 학생의 등록된 점수 가져오기 -> updateRoundScoreBySubject(), inquireRoundGradeBySubject()에서 사용
     */
    private Score getSpecificScore(String studentId) {

        Map<String, Subject> subjectMap = studentManagement.getData(studentId).getSubjectList();// 학생의 수강과목들
        System.out.println("==================================");
        for (String key : subjectMap.keySet()) { // 수강 과목 정보 출력
            subjectManagement.print(subjectMap.get(key));
        }

        System.out.print("과목 번호를 입력하세요: ");
        String subjectId = scanner.nextLine(); // 작업할 과목 번호 받기

        while (!subjectMap.containsKey(subjectId)) {
            System.out.print("학생이 수강하지 않는 과목입니다.\n다시 입력해주세요: ");
            subjectId = scanner.nextLine(); // 작업할 과목 번호 받기
        }

        // 저장소에 있으면 등록되어있는 Score반환 아닐 시, null 반환
        return scoreManagement.findScoreById(studentId, subjectId);
    }

    /**
     * 메서드 오버로딩
     * target 학생의 등록된 점수 가져오기 -> createScore()에서 사용 -> null일 경우 subjectId가 필요함
     */
    private Score getSpecificScore(String studentId, StringBuilder sbSubjectId) {
        Student student = studentManagement.getData(studentId);

        if (student == null) {
            throw new IllegalStateException("등록되지 않은 학생 정보입니다.");
        }

        Map<String, Subject> subjectMap = student.getSubjectList();// 학생의 수강과목들
        System.out.println("==================================");
        for (String key : subjectMap.keySet()) { // 수강 과목 정보 출력
            subjectManagement.print(subjectMap.get(key));
        }

        System.out.print("과목 번호를 입력하세요: ");
        sbSubjectId.append(scanner.nextLine());// 작업할 과목 번호 받기

        while (!subjectMap.containsKey(sbSubjectId.toString())) {
            System.out.print("학생이 수강하지 않는 과목입니다.\n다시 입력해주세요: ");
            sbSubjectId.setLength(0);
            sbSubjectId.append(scanner.nextLine());// 작업할 과목 번호 받기
        }

        String subjectId = sbSubjectId.toString();

        // 저장소에 있으면 등록되어있는 Score반환 아닐 시, null 반환
        return scoreManagement.findScoreById(studentId, subjectId);
    }

    /**
     * 수정, 조회의 회차를 입력받음
     */
    private int designateTimes(Score score) {
        System.out.print("회차를 입력해주세요: ");
        int times = scanner.nextInt();

        // 입력 받은 회차가 0이하 OR 회차보다 큰 수를 입력 받으면 다시.
        while (score.getScoreMap().size() < times || times <= 0) {
            System.out.print("존재하지 않는 회차입니다.\n다시 입력해주세요: ");
            times = scanner.nextInt();
        }
        return times;
    }

    /**
     * 작업을 완료후 확인차 출력해주는 프린터
     */
    private void completePrinter(Score score, int times) {
        System.out.println("전체 회차: " + score.getScoreMap().size());
        System.out.println("학생명: " + studentManagement.getData(score.getStudentId()).getStudentName());
        System.out.println("과목명: " + SUBJECTSTORE.get(score.getSubjectId()).getSubjectName());
        System.out.println("회차: " + times);
        System.out.println("점수: " + score.getScoreMap().get(times));
        System.out.println("등급: " + score.getGradeMap().get(times));
    }
}