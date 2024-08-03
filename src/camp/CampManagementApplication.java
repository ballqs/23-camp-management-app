package camp;

import camp.data.Data;
import camp.enums.IndexType;
import camp.enums.StudentStatusType;
import camp.enums.SubjectType;
import camp.model.Score;
import camp.model.Student;
import camp.model.Subject;

import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;
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
    public Sequence sequence = new Sequence();

//    private static int scoreIndex;                                // 사용안함
//    private static final String INDEX_TYPE_SCORE = "SC";          // 사용안함

    // 스캐너
    public Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        Data.studentStore = new HashMap<>();
        Data.scoreStore = new ArrayList<>();
        Data.subjectStore = new Init().setInitData();

        CampManagementApplication campManagementApplication = new CampManagementApplication();
        try {
            campManagementApplication.displayMainView();
        } catch (IllegalArgumentException e) {
            System.out.println(e);
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
            int input = sc.nextInt();

            switch (input) {
                case 1 -> displayStudentView(); // 수강생 관리
                case 2 -> displayScoreView(); // 점수 관리
                case 3 -> flag = false; // 프로그램 종료
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
            int input = sc.nextInt();

            switch (input) {
                case 1 -> createStudent(); // 수강생 등록
                case 2 -> inquireStudent(); // 수강생 목록 조회
                case 3 -> updateStudent(); // 수강생 정보 수정
                case 4 -> selectStatus(); // 상태별 수강생 목록 조회
                case 5 -> deleteStudent(); // 수강생 삭제
                case 6 -> flag = false; // 메인 화면 이동
                default -> {
                    System.out.println("잘못된 입력입니다.\n메인 화면 이동...");
                    flag = false;
                }
            }
        }
    }

    // 수강생 등록
    public void createStudent() {
        System.out.println("\n수강생을 등록합니다...");

        // 1.수강생 이름 저장
        System.out.print("수강생 이름 입력: ");
        String studentName = sc.next(); // 수강생 이름

        // 2.과목리스트 조회(Subject 에서 가져와서 뿌리기) 필수:3 , 선택:2 검증 필요!!
        SubjectManagement subjectManagement = new SubjectManagement();
        subjectManagement.selectAll();

        // 3.과목 담기
        int required = 0;   // 필수
        int choice = 0;     // 선택
        Map<String , Subject> subjectList = new HashMap<>();
        while (true) {
            System.out.print("과목 고유번호 입력: ");
            String subjectId = sc.next(); // 과목 고유번호

            // null이 리턴될 경우 어떻게 처리할지
            Subject subject = subjectManagement.getData(subjectId);

            // null 검증
            if (Objects.isNull(subject)) {
                System.out.println("과목 고유번호를 제대로 입력해주세요.");
                continue;
            }

            // 중복으로 등록된건지 검증 (anyMatch : 일치하는게 있는지 검증[return boolean])
            // subject 이게 명칭 중복 등 문제가 있으면 다른 명칭으로 대체
            if (subjectList.containsKey(subject.getSubjectId())) {
                System.out.println("이미 등록된 과목이 있습니다.");
                continue;
            }
            subjectList.put(subject.getSubjectId() , subject);

            if (subject.getSubjectType().equals(SubjectType.MANDATORY.name())) {
                required++;     // 필수 증가
            } else {
                choice++;       // 선택 증가
            }

            if (required < 3 || choice < 2) {
                System.out.println("등록된 과목은 현재 필수 : " + required + " , 선택 : " + choice);
                System.out.println("과목을 더 등록해주세요.");
            } else {
                System.out.println("과목을 더 등록하시겠습니까?");
                String result = sc.next(); // 과목 고유번호
                if (!result.equals("예")) {
                    System.out.println("과목 등록을 마쳤습니다.");
                    break;
                }
            }
        }

        String id = sequence.sequence(IndexType.ST.name());
        // 4.studentStore 에 저장
        Student student = new Student(id, studentName , subjectList ,StudentStatusType.Green.name()); // 수강생 인스턴스 생성 예시 코드
        StudentManagement studentManagement = new StudentManagement();
        studentManagement.insert(id , student);
        // 기능 구현
        System.out.println("수강생 등록 성공!\n");
    }

    // 수강생 목록 조회
    public void inquireStudent() {
        System.out.println("\n수강생 목록을 조회합니다...");
        // 1.전체 조회 기반(studentStore 에서 가져오기) Student에서 조회해서 뿌리는 것 구현해서 이용해도 댐
        StudentManagement studentManagement = new StudentManagement();
        studentManagement.selectAll();
        System.out.println("\n수강생 목록 조회 성공!");
    }

    // 수강생 정보 수정
    public void updateStudent() {
        StudentManagement studentManagement = new StudentManagement();

        if (studentManagement.lenCheck()) {
            inquireStudent();
            String studentId = getStudentId("\n수정할 수강생의 번호를 입력하시오..."); //수강생 고유번호

            Student student = studentManagement.getData(studentId);

            System.out.println("수정하려는 수강생 정보는 아래와 같습니다.");
            studentManagement.select(student);

            System.out.println("==================================");
            System.out.println("1.이름");
            System.out.println("2.상태");
            System.out.print("변경할 항목을 선택하세요...");
            int input = sc.nextInt(); // 상태 번호

            switch (input) {
                case 1 -> changeStudentName(student.getStudentId());
                case 2 -> changeStudentStatus(student.getStudentId());
                default -> {
                    System.out.println("잘못된 입력입니다.\n이전 화면 이동...");
                }
            }
        } else {
            System.out.println("==================================");
            System.out.println("등록된 수강생이 없습니다. 등록 후 진행해주세요.");
        }
    }

    // 수강생 이름 변경
    public void changeStudentName(String studentId) {
        System.out.println("==================================");
        System.out.print("변경하실 수강생 이름을 입력해주세요...");
        String subjectName = sc.next(); // 수강생 이름

        StudentManagement studentManagement = new StudentManagement();
        studentManagement.update(studentId , "studentName" , subjectName);
        System.out.println("\n수강생 이름 변경 성공!");
    }

    // 수강생 상태 변경
    public void  changeStudentStatus(String studentId) {
        StudentManagement studentManagement = new StudentManagement();

        String status = "";
        while (true) {
            System.out.println("==================================");
            System.out.println("수강생 상태 종류");
            studentManagement.statusList();
            System.out.print("변경할 항목을 선택하세요...");
            int input = sc.nextInt(); // 상태 번호

            if (input < 1 || input > StudentStatusType.values().length) {
                System.out.println("잘못된 입력입니다.");
            } else {
                status = StudentStatusType.getStatus(input).name();
                break;
            }
        }

        studentManagement.update(studentId , "studentStatus" , status);
        System.out.println("\n수강생 상태 변경 성공!");
    }

    // 상태별 수강생 목록을 조회
    public void selectStatus() {
        StudentManagement studentManagement = new StudentManagement();

        String status = "";
        while (true) {
            System.out.println("==================================");
            System.out.println("수강생 상태 종류");
            studentManagement.statusList();
            System.out.print("조회할 항목을 선택하세요...");
            int input = sc.nextInt();

            if (input < 1 || input > StudentStatusType.values().length) {
                System.out.println("잘못된 입력입니다.");
            } else {
                status = StudentStatusType.getStatus(input).name();
                break;
            }
        }
        studentManagement.selectStatusStudent(status);
    }
    
    // 수강생 삭제
    public void deleteStudent() {
        StudentManagement studentManagement = new StudentManagement();
        inquireStudent();
        String studentId = getStudentId("\n삭제할 수강생의 번호를 입력하시오..."); //수강생 고유번호

        studentManagement.delete(studentId);
        System.out.println("\n수강생 정보 삭제 완료!");
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
            int input = sc.nextInt();

            switch (input) {
                case 1 -> createScore(); // 수강생의 과목별 시험 회차 및 점수 등록
                case 2 -> updateRoundScoreBySubject(); // 수강생의 과목별 회차 점수 수정
                case 3 -> inquireRoundGradeBySubject(); // 수강생의 특정 과목 회차별 등급 조회
                case 4 -> flag = false; // 메인 화면 이동
                default -> {
                    System.out.println("잘못된 입력입니다.\n메인 화면 이동...");
                    flag = false;
                }
            }
        }
    }

    public String getStudentId(String msg) {
        System.out.print(msg);
        return sc.next();
    }

    // 수강생의 과목별 시험 회차 및 점수 등록
    public void createScore() {
        // 1.inquireStudent 함수 이용
        // 2.getStudentId 함수 이용
        String studentId = getStudentId(""); // 관리할 수강생 고유 번호

        // 3.과목 리스트(해당 수강생이 등록한 과목 리스트)

        // 4.회차(1 ~ 10) -> 해당 회차가 등록되어있는지 검증! 안되어 있는 것만 등록

        // 5.점수 등록
        System.out.println("시험 점수를 등록합니다...");

        // 6.등급 매기기

        // 7.등록
        System.out.println("\n점수 등록 성공!");
    }

    // 수강생의 과목별 회차 점수 수정
    public void updateRoundScoreBySubject() {
        // 1.inquireStudent 함수 이용
        // 2.getStudentId 함수 이용
        String studentId = getStudentId(""); // 관리할 수강생 고유 번호

        // 3.과목 리스트(해당 수강생이 등록한 과목 리스트)

        // 4.회차(1 ~ 10)

        // 5.점수 수정
        System.out.println("시험 점수를 수정합니다...");

        // 6.등급 매기기

        // 7.수정
        System.out.println("\n점수 수정 성공!");
    }

    // 수강생의 특정 과목 회차별 등급 조회
    public void inquireRoundGradeBySubject() {
        // 1.inquireStudent 함수 이용
        // 2.getStudentId 함수 이용
        String studentId = getStudentId(""); // 관리할 수강생 고유 번호

        // 3.과목 리스트(해당 수강생이 등록한 과목 리스트)

        // 4.회차(1 ~ 10)
        System.out.println("회차별 등급을 조회합니다...");

        // 5.결과 보여주기
        System.out.println("\n등급 조회 성공!");
    }

}
