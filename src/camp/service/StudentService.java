package camp.service;

import camp.CampManagementApplication;
import camp.enums.StudentStatusType;
import camp.model.Student;
import camp.repository.StudentManagement;

import java.util.Objects;

public class StudentService {
    public StudentManagement studentManagement = new StudentManagement();

    public String getStudentId(String msg) {
        System.out.print(msg);
        return CampManagementApplication.SC.next();
    }

    public void printAllStudent() {
        System.out.println("\n수강생 목록을 조회합니다...");
        // 1.전체 조회 기반(STUDENTSTORE 에서 가져오기) Student에서 조회해서 뿌리는 것 구현해서 이용해도 댐
        studentManagement.printAll();
        System.out.println("\n수강생 목록 조회 성공!");
    }

    // 수강생 정보 수정
    public void updateStudent() {
        if (studentManagement.lenCheck()) {
            printAllStudent();
            String studentId = getStudentId("\n수정할 수강생의 번호를 입력하시오..."); //수강생 고유번호

            Student student = studentManagement.getData(studentId);
            if (Objects.nonNull(student)) {
                System.out.println("수정하려는 수강생 정보는 아래와 같습니다.");
                studentManagement.print(student);

                System.out.println("==================================");
                System.out.println("1.이름");
                System.out.println("2.상태");
                System.out.print("변경할 항목을 선택하세요...");
                String input = CampManagementApplication.SC.next(); // 상태 번호

                switch (input) {
                    case "1" -> changeStudentName(student.getStudentId());
                    case "2" -> changeStudentStatus(student.getStudentId());
                    default -> {
                        System.out.println("잘못된 입력입니다.\n이전 화면 이동...");
                    }
                }
            } else {
                System.out.println("==================================");
                System.out.println("입력하신 수강생 고유번호는 존재하지 않습니다.\n이전 화면 이동...");
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
        String studentName = CampManagementApplication.SC.next(); // 수강생 이름

        StudentManagement studentManagement = new StudentManagement();
        studentManagement.update(studentId , "studentName" , studentName);
        System.out.println("\n수강생 이름 변경 성공!");
    }

    // 수강생 상태 변경
    public void  changeStudentStatus(String studentId) {
        String status = "";
        while (true) {
            System.out.println("==================================");
            System.out.println("수강생 상태 종류");
            studentManagement.statusList();
            System.out.print("변경할 항목을 선택하세요...");

            String input = CampManagementApplication.SC.next();

            if(!input.matches("[+-]?\\d*(\\.\\d+)?")) {
                System.out.println("잘못된 입력입니다.");
                continue;
            }

            if (Integer.parseInt(input) < 1 || Integer.parseInt(input) > StudentStatusType.values().length) {
                System.out.println("잘못된 입력입니다.");
            } else {
                status = StudentStatusType.getStatus(Integer.parseInt(input));
                break;
            }
        }

        studentManagement.update(studentId , "studentStatus" , status);
        System.out.println("\n수강생 상태 변경 성공!");
    }

    // 상태별 수강생 목록을 조회
    public void selectStatus() {
        String status = "";
        while (true) {
            System.out.println("==================================");
            System.out.println("수강생 상태 종류");
            studentManagement.statusList();
            System.out.print("조회할 항목을 선택하세요...");

            String input = CampManagementApplication.SC.next();

            if(!input.matches("[+-]?\\d*(\\.\\d+)?")) {
                System.out.println("잘못된 입력입니다.");
                continue;
            }

            if (Integer.parseInt(input) < 1 || Integer.parseInt(input) > StudentStatusType.values().length) {
                System.out.println("잘못된 입력입니다.");
            } else {
                status = StudentStatusType.getStatus(Integer.parseInt(input));
                break;
            }
        }
        studentManagement.selectStatusStudent(status);
    }
}
