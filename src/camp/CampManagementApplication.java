package camp;

import camp.model.score.Grade;
import camp.model.score.RequiredSubConvertor;
import camp.model.score.Score;
import camp.model.Student;
import camp.model.Subject;

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

// 김준형
public class CampManagementApplication {
    // 데이터 저장소
    public List<Student> studentStore;
    public List<Subject> subjectStore;
    public List<Score> scoreStore;

    public Sequence sequence = new Sequence();

    // 과목 타입
    public String SUBJECT_TYPE_MANDATORY = "MANDATORY";     // 필수
    public String SUBJECT_TYPE_CHOICE = "CHOICE";           // 선택

    // index 관리 필드
    public static int studentIndex;
    public final static String INDEX_TYPE_STUDENT = "ST";          // ST + 숫자 => 수강생 고유번호
    public static int subjectIndex;
    public final static String INDEX_TYPE_SUBJECT = "SU";          // SU + 숫자 => 과목 고유번호
//    private static int scoreIndex;                                // 사용안함
//    private static final String INDEX_TYPE_SCORE = "SC";          // 사용안함

    // 스캐너
    public Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        CampManagementApplication campManagementApplication = new CampManagementApplication();
        try {
            campManagementApplication.displayMainView();
        } catch (Exception e) {
            System.out.println("\n오류 발생!\n프로그램을 종료합니다.");
        }
    }

    public void displayMainView() throws InterruptedException {
        studentStore = new ArrayList<>();
        scoreStore = new ArrayList<>();
        subjectStore = new Init().setInitData();

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
            System.out.println("3. 메인 화면 이동");
            System.out.print("관리 항목을 선택하세요...");
            int input = sc.nextInt();

            switch (input) {
                case 1 -> createStudent(); // 수강생 등록
                case 2 -> inquireStudent(); // 수강생 목록 조회
                case 3 -> flag = false; // 메인 화면 이동
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
        subjectManagement.selectAll(subjectStore);

        // 3.과목 담기
        int required = 0;
        int choice = 0;
        List<Subject> subjectList = new ArrayList<>();
        while (true) {
            System.out.print("과목 고유번호 입력: ");
            String subjectId = sc.next(); // 과목 고유번호

            // null이 리턴될 경우 어떻게 처리할지
            Subject subject = subjectManagement.select(subjectStore , subjectId);

            // null 검증
            if (Objects.isNull(subject)) {
                System.out.println("과목 고유번호를 제대로 입력해주세요.");
                continue;
            }

            // 중복으로 등록된건지 검증
            if (subjectList.stream().anyMatch(it -> it.getSubjectId().equals(subject.getSubjectId()))) {
                System.out.println("이미 등록된 과목이 있습니다.");
                continue;
            }
            subjectList.add(subject);

            if (subject.getSubjectType().equals("MANDATORY")) {
                required++;
            } else {
                choice++;
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

        // 4.studentStore 에 저장
        Student student = new Student(sequence.sequence(INDEX_TYPE_STUDENT), studentName , (ArrayList) subjectList); // 수강생 인스턴스 생성 예시 코드
        // 기능 구현
        System.out.println("수강생 등록 성공!\n");
    }

    // 수강생 목록 조회
    public void inquireStudent() {
        System.out.println("\n수강생 목록을 조회합니다...");

        // 1.전체 조회 기반(studentStore 에서 가져오기) Student에서 조회해서 뿌리는 것 구현해서 이용해도 댐

        System.out.println("\n수강생 목록 조회 성공!");
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

    public String getStudentId() {
        System.out.print("\n관리할 수강생의 번호를 입력하시오...");
        return sc.next();
    }

    /**
     * ``팀원들의 코드의 이해를 돕기위한 로직 수행 순서와 이유``
     * <p>
     * 수강생의 과목별 시험 회차, 점수를 최초로 점수 저장소에 등록한다.
     * <p>
     * 1. inquireStudent();를 이용해서 수강생을 조회해서 전부 콘솔에 출력한다.
     * <p>
     * 2. getStudentId();를 이용해서 관리할 수강생의 번호를 받는다. -> 콘솔에 출력된 내용을 보면 학생의 고유번호를 확인할 수 있다.
     * <p>
     * 3. findStudentSubjects(studentId);를 이용해서 관리할 학생의 수강 과목을 찾아낸다.
     * -> studentId는 고유하기 때문에 학생을 특정 가능
     * <p>
     * 4. studentId가 잘못돼서 관리할 학생의 수강과목이 없을 수 있다. -> studentSubjects에 null이 담긴다.
     * -> null일 때는 없는 학생이므로 조건문을 이용해서 없는 학생인지 아닌지를 검증을 한다.
     * ============================================================================================================
     * 현재까지 알아낸 사항을 확인해보자. -> 학생의 고유번호, 특정 학생의 수강 과목들.
     * 남은 로직은 점수에 따른 grade로 변환해서 점수 저장소에 저장하는 일
     * ============================================================================================================
     * 5. registerScore(studentSubjects, studentId);를 이용해서 학생의 점수를 저장한다.
     * <p>
     * 5-1. registerScore();를 살펴보자.
     * -> 이중 for문은 점수 저장소에 있는 학생, 과목이 일치하는지를 확인하기 위해 돌리는 거다.
     * -> 조건문은 이중 for문으로 돌아가면서, 나오는
     * 점수 저장소의 요소 == 학생 고유번호, 과목 고유번호를 만족하는 게 있는지 없는지 확인.
     * 우리는 현재 점수 저장소에 '저장되어있지 않은 것'을 찾는중이다!
     * -> isNotInScoreRepository((studentId, score, studentSubject);를 이용해서,
     * 점수 저장소에 등록된 score 객체의 학생 고유번호를 꺼냄. -> 우리가 특정한 학생의 고유번호와 비교
     * -> 비교 결과, 점수 저장소에 등록된 요소가 우리가 특정한 학생의 고유번호와 일치한다면 이제는 과목을 확인.
     * 점수 저장소에 등록된 score 객체의 과목 고유번호를 꺼냄.
     * -> 우리가 특정한 학생이 수강하는 과목의 고유번호와 일치하는지 확인. -> 일치한다면 `!`를 이용해서 반전.
     * 반전하는 이유는 우리는 현재 점수 저장소에 등록되어있지 않은 것을 찾는중이다.
     * => 즉, 우리가 특정한 학생의 수강 과목중 점수 저장소에 등록되어있지 않는 조건을 확인하는 메서드이다.
     * 조건 1. 우리가 특정한 학생이어야한다.
     * 조건 2. 특정한 학생이 수강하는 과목이 점수 저장소에 등록되어있지 않아야 한다.
     * <p>
     * 5-2. 위 조건문을 통과했다면 이제 Score 인스턴스를 생성해서 등록할 준비를 하자.
     * <p>
     * a. new Score(new RequiredSubConvertor(), studentSubject, studentId, sc.nextInt());로 객체를 생성.
     * -> Score는 생성자로 점수를 등급으로 변환할 컨버터, 저장할 과목의 고유번호, 저장할 학생의 고유번호, 점수를 받는다.
     * -> 등록할 점수는 scanner로 받는다.
     * <p>
     * b. calculateGrade();를 이용해서 점수 -> 등급으로 변환하고 score에 저장한다.
     * -> 만들어진 score 객체 내부에서 로직 수행.
     * -> 생성자로 점수를 받았고, 등급으로의 변환은 점수만 있으면 되기에,
     * score 내부에서 작업하고 자신의 필드에 직접 넣는 게 맞다고 생각했다.
     * -> calculateGrade()는 GradeConvertor()를 호출한다.
     * -> 다형성을 이용해서 선택과목의 경우 선택과목 컨버터를 구현해서 생성자로 넣어주면 된다.
     * -> 우선은 모두가 필수과목이라고 가정했다. -> 선택과목은 기능추가 선택사항이기 때문에...
     * <p>
     * c. 점수 -> 등급 변환을 마치면, score 객체에는 학생 고유번호, 과목 고유번호, 점수, 등급이 채워져있다.
     * -> 그럼 회차는?
     * -> 회차는 Score 객체를 생성할 때, 생성자에서 times++;로 생성과 동시에 1회차가 적용되도록 했다.
     * <p>
     * d. 이제 남은 건 scoreStore에 Score 객체를 저장하는 것 뿐이다.
     */
    public void createScore() {
        // 1.
        inquireStudent();

        // 2.
        String studentId = getStudentId(); // 관리할 수강생 고유 번호

        // 3.
        List<String> studentSubjects = findStudentSubjects(studentId); // 관리할 학생의 수강 과목

        // 4.
        if (studentSubjects == null) {
            return; // try catch 생각
        }

        // 5.
        registerScore(studentSubjects, studentId);
    }


    // 5-1.
    private void registerScore(List<String> studentSubjects, String studentId) { // 더 좋게 코드를 변경할 수 있을 거 같은데...
        for (Score score : scoreStore) { // 점수 저장소
            for (String studentSubject : studentSubjects) { // 과목 고유번호
                if (isNotInScoreRepository(studentId, score, studentSubject)) {
                    System.out.print(studentSubject + "의 점수를 저장합니다.\n점수를 입력해주세요: ");
                    // 5-2.
                    int studentScore = sc.nextInt();
                    Score scoreObj = new Score(new RequiredSubConvertor(), studentSubject, studentId, studentScore);
                    Grade grade = scoreObj.calculateGrade(); // 점수 -> 등급
                    System.out.println(
                            "학생 고유번호: " + studentId +
                                    ", 과목: " + studentSubject +
                                    ", 점수: " + studentScore +
                                    ", 등급: " + grade);

                    scoreStore.add(scoreObj);
                    System.out.println("\n점수 등록 성공!");
                }
            }
        }
    }

    private static boolean isNotInScoreRepository(String studentId, Score score, String studentSubject) {
        return score.getStudentId().equals(studentId) && !(score.getSubjectId().equals(studentSubject));
    }

    /**
     * findStudentSubjects는 학생 저장소를 하나씩 꺼내서 원하는 학생 고유번호랑 일치하는지 확인하고,
     * 일치하면 그 학생의 수강 과목을 리스트로 반환한다.
     * 아니면 null을 반환한다.
     *
     * @param studentId
     * @return
     */
    private List<String> findStudentSubjects(String studentId) { //학생의 전체 수강과목을 알고싶다.
        for (Student student : studentStore) {
            if (student.getStudentId().equals(studentId)) {
                List<String> subjectList = student.getSubjectList();
                System.out.println("학생이 등록한 과목은 " + subjectList);
                return subjectList;
            }
        }
        System.out.println("등록한 과목 리스트가 없습니다...");
        return null;
    }

    // 수강생의 과목별 회차 점수 수정
    // 학생 고유 번호, 수강하는 과목 고유 번호
    public void updateRoundScoreBySubject() {
        // 1.inquireStudent 함수 이용
        inquireStudent();

        // 2.getStudentId 함수 이용
        String studentId = getStudentId(); // 관리할 수강생 고유 번호

        // 3.과목 리스트(해당 수강생이 등록한 과목 리스트)
        // 학생 고유번호를 통해 학생을 특정 -> 반환

        // 4.회차(1 ~ 10)
        // 점수 저장소 안에 학생이 수강한 과목의 점수가 등록되어있어야한다. (조건문) -> 반환 받을 수 있다. -> 수학 score.getTimes() -> 회차 반환
        // 학생 고유번호를 통해 얻어낸 학생의 회차를 확인

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
        String studentId = getStudentId(); // 관리할 수강생 고유 번호

        // 3.과목 리스트(해당 수강생이 등록한 과목 리스트)

        // 4.회차(1 ~ 10)
        System.out.println("회차별 등급을 조회합니다...");

        // 5.결과 보여주기
        System.out.println("\n등급 조회 성공!");
    }

}
