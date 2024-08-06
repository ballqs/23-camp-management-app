package camp.service;

import camp.CampManagementApplication;
import camp.enums.IndexType;
import camp.enums.StudentStatusType;
import camp.enums.SubjectType;
import camp.function.Sequence;
import camp.model.Student;
import camp.model.Subject;
import camp.repository.StudentManagement;
import camp.repository.SubjectManagement;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class StudentAndSubjectService {
    public Sequence sequence = new Sequence();

    public SubjectManagement subjectManagement = new SubjectManagement();
    public StudentManagement studentManagement = new StudentManagement();

    // 수강생 등록
    public void createStudent() {
        System.out.println("\n수강생을 등록합니다...");

        // 1.수강생 이름 저장
        System.out.print("수강생 이름 입력: ");
        String studentName = CampManagementApplication.SC.next(); // 수강생 이름

        // 2.과목리스트 조회(Subject 에서 가져와서 뿌리기) 필수:3 , 선택:2 검증 필요!!
        subjectManagement.printAll();

        // 3.과목 담기
        int required = 0;   // 필수
        int choice = 0;     // 선택
        Map<String , Subject> subjectList = new HashMap<>();
        while (true) {
            System.out.print("과목 고유번호 입력: ");
            String subjectId = CampManagementApplication.SC.next(); // 과목 고유번호

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

            System.out.println("등록된 과목은 현재 필수 : " + required + " , 선택 : " + choice);
            if (required < 3 || choice < 2) {
                System.out.println("과목을 더 등록해주세요.");
            } else {
                System.out.println("과목을 더 등록하시겠습니까?");
                String result = CampManagementApplication.SC.next(); // 과목 고유번호
                if (!result.equals("예")) {
                    System.out.println("과목 등록을 마쳤습니다.");
                    break;
                }
            }
        }

        String id = sequence.create(IndexType.ST , studentManagement.getStudentIndex());
        // 4.STUDENTSTORE 에 저장
        Student student = new Student(id, studentName , subjectList , StudentStatusType.GREEN.name()); // 수강생 인스턴스 생성 예시 코드
        studentManagement.insert(id , student);
        // 기능 구현
        System.out.println("수강생 등록 성공!\n");
    }

}
