package camp;

import camp.function.Init;
import camp.service.*;

import java.util.Objects;
import java.util.Scanner;

/**
 * Notification
 * Java, 객체지향이 아직 익숙하지 않은 분들은 위한 소스코드 틀입니다.
 * main 메서드를 실행하면 프로그램이 실행됩니다.
 * model 의 클래스들과 아래 (// 기능 구현...) 주석 부분을 완성해주세요!
 * 프로젝트 구조를 변경하거나 기능을 추가해도 괜찮습니다!
 * 구현에 도움을 주기위한 Base 프로젝트입니다. 자유롭게 이용해주세요!
 */

public class CampManagementApplication {

//    private static int scoreIndex;                                // 사용안함
//    private static final String INDEX_TYPE_SCORE = "SC";          // 사용안함

    // 스캐너
    public final static Scanner SC = new Scanner(System.in);

    private StudentAndSubjectService studentAndSubjectService = new StudentAndSubjectService();
    private StudentService studentService = new StudentService();
    private StudentAndScoreService studentAndScoreService = new StudentAndScoreService();
    private ScoreService scoreService = new ScoreService();

    public static void main(String[] args) {
        Init.setInitData(); // 초기 설정

        CampManagementApplication campManagementApplication = new CampManagementApplication();
        try {
            campManagementApplication.displayMainView();
        } catch (Exception e) {
            System.out.println("\n오류 발생!\n프로그램을 종료합니다.");
        }
    }

    public void displayMainView() throws InterruptedException {
        boolean flag = true;

        while (flag) {
            System.out.println("\n==================================");
            System.out.println("내일배움캠프 수강생 관리 프로그램 실행 중...");
            System.out.println("1. 수강생 관리");
            System.out.println("2. 점수 관리");
            System.out.println("3. 프로그램 종료");
            System.out.print("관리 항목을 선택하세요...");
            String input = SC.next();

            switch (input) {
                case "1" -> displayStudentView(); // 수강생 관리
                case "2" -> displayScoreView(); // 점수 관리
                case "3" -> flag = false; // 프로그램 종료
                default -> {
                    System.out.println("잘못된 입력입니다.\n되돌아갑니다!");
                    Thread.sleep(2000);
                }
            }
        }
        System.out.println("프로그램을 종료합니다.");
    }

    public void displayStudentView() {
        boolean flag = true;

        while (flag) {
            System.out.println("==================================");
            System.out.println("수강생 관리 실행 중...");
            System.out.println("1. 수강생 등록");
            System.out.println("2. 수강생 목록 조회");
            System.out.println("3. 수강생 정보 수정");
            System.out.println("4. 상태별 수강생 목록 조회");
            System.out.println("5. 수강생 삭제");
            System.out.println("6. 메인 화면 이동");
            System.out.print("관리 항목을 선택하세요...");
            String input = SC.next();

            switch (input) {
                case "1" -> studentAndSubjectService.createStudent(); // 수강생 등록
                case "2" -> studentService.printAllStudent(); // 수강생 목록 조회
                case "3" -> studentService.updateStudent(); // 수강생 정보 수정
                case "4" -> studentService.selectStatus(); // 상태별 수강생 목록 조회
                case "5" -> studentAndScoreService.deleteStudent(); // 수강생 삭제
                case "6" -> flag = false; // 메인 화면 이동
                default -> {
                    System.out.println("잘못된 입력입니다.\n메인 화면 이동...");
                    flag = false;
                }
            }
        }
    }

    public void displayScoreView() {
        boolean flag = true;

        while (flag) {
            System.out.println("==================================");
            System.out.println("점수 관리 실행 중...");
            System.out.println("1. 수강생의 과목별 시험 회차 및 점수 등록");
            System.out.println("2. 수강생의 과목별 회차 점수 수정");
            System.out.println("3. 수강생의 특정 과목 회차별 등급 조회");
            System.out.println("4. 메인 화면 이동");
            System.out.print("관리 항목을 선택하세요...");
            String input = SC.next();
            SC.nextLine();

            try {
                switch (input) {
                    case "1" -> scoreService.createScore(); // 수강생의 과목별 시험 회차 및 점수 등록
                    case "2" -> scoreService.updateRoundScoreBySubject();// 수강생의 과목별 회차 점수 수정
                    case "3" -> scoreService.inquireRoundGradeBySubject(); // 수강생의 특정 과목 회차별 등급 조회
                    case "4" -> flag = false; // 메인 화면 이동
                    default -> {
                        System.out.println("잘못된 입력입니다.\n메인 화면 이동...");
                        flag = false;
                    }
                }
            } catch (RuntimeException e) {
                System.out.println("오류 메시지: " + e.getMessage() + "\n점수 관리 메인화면으로 돌아갑니다.");
            }
        }
    }
}
