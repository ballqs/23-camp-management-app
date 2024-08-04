package camp.model.score.service;

import camp.StudentManagement;
import camp.model.Subject;
import camp.model.score.Score;
import camp.model.score.controller.ScoreController;

import java.util.Map;
import java.util.Scanner;

import static camp.data.Data.*;

public class ScoreService {

    private final ScoreController scoreController = new ScoreController();
    private final StudentManagement studentManagement = new StudentManagement();
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
            Subject subject = subjectStore.get(subjectId.toString());
            score = scoreController.createScore(studentId, subject); // new Score(); //subject는 ID와 Type이 필요.
        }

        // Score의 회차가 10회차 이상이면 안된다.
        if (score.getScoreMap().size() >= 10) {
            throw new IllegalStateException("최대 10회차까지만 등록이 가능합니다.");
        }

        int option;
        do {
            int times = score.getScoreMap().size();
            String subjectName = subjectStore.get(score.getSubjectId()).getSubjectName();

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

        scoreController.register(subjectScore, score);

        // 결과 출력
        System.out.println("=====등록 결과 확인 시작=====");
        CompletePrinter(score);
        System.out.println("=====등록 결과 확인 종료=====\n");

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
        System.out.println(subjectStore.get(score.getSubjectId()).getSubjectName() + "과목의 회차별 점수 출력");
        for (int i = 0; i < score.getScoreMap().size(); i++) {
            System.out.println("*" + (i + 1) + "회차 " + score.getScoreMap().get(i + 1) + "점");
        }

        // 수정할 회차 입력받기
        int times = designateTimes(score);

        // 변경할 점수 입력받기
        System.out.print("변경할 점수를 입력해주세요: ");
        int scoreToChange = inputScore();


        // 업데이트 컨트롤러 호출 -> 수정할 score 객체, 회차, 변경할 점수 넘겨줘야함.
        scoreController.update(score, times, scoreToChange);

        // 결과 출력
        System.out.println("=====수정 결과 확인 시작=====");
        CompletePrinter(score, times);
        System.out.println("=====수정 결과 확인 종료=====\n");
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

        // score가 null이면 조회할 점수가 없다는 뜻이므로 돌아간다.
        if (score == null) {
            throw new IllegalStateException("***조회할 점수가 등록되지 않은 과목입니다.***\n");
        }

        // 몇 회차까지 등록되어 있는지 출력
        System.out.println(
                subjectStore.get(score.getSubjectId()).getSubjectName() + "과목은 " +
                        score.getScoreMap().size() + "회차까지 등록되어있습니다."
        );

        // 조회할 회차 입력 받기
        int times = designateTimes(score);

        // 5.결과 보여주기
        System.out.println("=====조회 결과 확인 시작=====");
        CompletePrinter(score, times);
        System.out.println("=====조회 결과 확인 종료=====\n");


    }


    // PRIVATE ZONE=====================================================================================================

    /**
     * target 학생 id 입력받는 메서드
     */
    private String designateStudentId() {
        studentManagement.selectAll(); // 학생 목록 조회
        System.out.print("\n관리할 수강생의 번호를 입력하시오...");
        return scanner.nextLine();
    }

    /**
     * 메서드 오버로딩
     * target 학생의 등록된 점수 가져오기 -> updateRoundScoreBySubject(), inquireRoundGradeBySubject()에서 사용
     */
    private Score getSpecificScore(String studentId) {
        Map<String, Subject> subjectMap = studentManagement.getData(studentId).getSubjectList();// 학생의 수강과목들
        for (String key : subjectMap.keySet()) { // 수강 과목 정보 출력
            System.out.println(subjectMap.get(key).toString());
        }

        System.out.print("과목 번호를 입력하세요: ");
        String subjectId = scanner.nextLine(); // 작업할 과목 번호 받기

        while (!subjectMap.containsKey(subjectId)) {
            System.out.print("학생이 수강하지 않는 과목입니다.\n다시 입력해주세요: ");
            subjectId = scanner.nextLine(); // 작업할 과목 번호 받기
        }

        // 저장소에 있으면 등록되어있는 Score반환 아닐 시, null 반환
        return scoreController.findScoreById(studentId, subjectId);
    }

    /**
     * 메서드 오버로딩
     * target 학생의 등록된 점수 가져오기 -> createScore()에서 사용 -> null일 경우 subjectId가 필요함
     */
    private Score getSpecificScore(String studentId, StringBuilder sbSubjectId) {
        Map<String, Subject> subjectMap = studentManagement.getData(studentId).getSubjectList();// 학생의 수강과목들
        for (String key : subjectMap.keySet()) { // 수강 과목 정보 출력
            System.out.println(subjectMap.get(key).toString());
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
        return scoreController.findScoreById(studentId, subjectId);
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
     * 작업을 완료후 확인차 출력해주는 프린터 -> 조회, 수정에서 사용
     */
    private void CompletePrinter(Score score, int times) {
        System.out.println("전체 회차: " + score.getScoreMap().size());
        System.out.println("학생 번호: " + score.getStudentId());
        System.out.println("과목 번호: " + score.getSubjectId());
        System.out.println("작업 회차: " + times);
        System.out.println("점수: " + score.getScoreMap().get(times));
        System.out.println("등급: " + score.getGradeMap().get(times));
    }

    /**
     * 작업을 완료후 확인차 출력해주는 프린터 -> 등록에서 사용
     */
    private void CompletePrinter(Score score) {
        System.out.println("학생 번호: " + score.getStudentId());
        System.out.println("과목 번호: " + score.getSubjectId());
        System.out.println("작업 회차: " + score.getScoreMap().size());
        System.out.println("점수: " + score.getScoreMap().get(score.getScoreMap().size()));
        System.out.println("등급: " + score.getGradeMap().get(score.getScoreMap().size()));
    }

}