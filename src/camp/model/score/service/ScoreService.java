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
    private Scanner scanner = new Scanner(System.in);

    /**
     * 점수 등록 서비스
     */
    public void createScore() {
        Score score;

        String studentId = getSpecificStudentId(); // 점수를 등록할 수강생 ID
        score = getSpecificScore(studentId); // subjectId와 score를 얻어오는 로직

        if (score == null) {
            System.out.print("해당 과목의 등록된 점수가 없습니다.\n 점수를 등록할 과목 번호를 입력해주세요: ");
            Subject subject = subjectStore.get(scanner.nextLine());
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
                    System.out.print("등록할 점수를 입력해주세요: ");
                    break;

                case 2:
                    throw new IllegalStateException(subjectName + "과목의 점수를 등록하지 않습니다."); // 예외 발생!!

                default:
                    System.out.println("옵션을 잘못 입력하셨습니다.\n");
            }
        } while (option != 1);

        int subjectScore = scanner.nextInt();
        while (0 > subjectScore || subjectScore > 100) {
            System.out.println("잘못된 점수를 입력하였습니다.\n0~100 사이의 점수를 다시 입력해주세요: ");
            subjectScore = scanner.nextInt();
        }

        scoreController.register(subjectScore, score);
    }


    /**
     * 점수 수정 서비스
     */
    public void updateRoundScoreBySubject() {
        String studentId = getSpecificStudentId(); // 수정할 학생
        Score score = getSpecificScore(studentId);

        // 수정할 점수 존재하는지 검증 로직
        if (score == null) {
            System.out.println("***점수가 등록되지 않은 과목입니다.***\n");
            return;
        }

        // 수정할 과목의 각 회차 점수를 출력
        System.out.println(subjectStore.get(score.getSubjectId()).getSubjectName() + "과목의 회차별 점수 출력");
        for (int i = 0; i < score.getScoreMap().size(); i++) {
            System.out.println("*" + (i + 1) + "회차 " + score.getScoreMap().get(i + 1) + "점");
        }

        // 수정할 회차 입력받기
        System.out.print("회차를 입력하세요: ");
        int times = scanner.nextInt();

        // 입력 받은 회차가 0이하 OR 회차보다 큰 수를 입력 받으면 다시.
        while (score.getScoreMap().size() < times || times <= 0) {
            System.out.print("존재하지 않는 회차입니다.\n다시 입력해주세요: ");
            times = scanner.nextInt();
        }

        // 변경할 점수 입력받기
        System.out.print("변경할 점수를 입력해주세요: ");
        int scoreToChange = scanner.nextInt();

        // 업데이트 컨트롤러 호출 -> 수정할 score 객체, 회차, 변경할 점수 넘겨줘야함.
        scoreController.update(score, times, scoreToChange);
    }


    // PRIVATE ZONE=====================================================================================================

    /**
     * target 학생 id 입력받는 메서드
     */
    private String getSpecificStudentId() {
        studentManagement.selectAll(); // 학생 목록 조회
        System.out.print("\n관리할 수강생의 번호를 입력하시오...");
        return scanner.nextLine();
    }

    /**
     * target 학생의 등록된 점수 가져오기
     */
    private Score getSpecificScore(String studentId) {
        Map<String, Subject> subjectMap = studentManagement.getData(studentId).getSubjectList();// 학생의 수강과목들
        for (String key : subjectMap.keySet()) { // 수강 과목 정보 출력
            System.out.println(subjectMap.get(key).toString());
        }
        System.out.print("과목 번호를 입력하세요: ");
        String subjectId = scanner.nextLine(); // 작업할 과목 번호 받기
        return scoreController.findScoreInStore(studentId, subjectId);
    }
}
