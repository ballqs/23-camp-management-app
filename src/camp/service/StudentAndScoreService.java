package camp.service;

import camp.repository.StudentManagement;

import java.util.Objects;
import java.util.Scanner;

public class StudentAndScoreService {
    public Scanner sc;
    public StudentManagement studentManagement = new StudentManagement();

    public StudentAndScoreService(Scanner sc) {
        this.sc = sc;
    }

    // 수강생 삭제
    public void deleteStudent() {
        StudentService studentService = new StudentService(sc);
        studentService.inquireStudent();
        String studentId = studentService.getStudentId("\n삭제할 수강생의 번호를 입력하시오..."); //수강생 고유번호
        if (!Objects.isNull(studentManagement.getData(studentId))) {
            // 수강생 정보 삭제
            studentManagement.delete(studentId);
            // 점수 정보 삭제
            ScoreService scoreService = new ScoreService(sc);
            scoreService.delete(studentId);
            System.out.println("\n수강생 정보 삭제 완료!");
        } else {
            System.out.println("\n잘못된 수강생 번호입니다. 이전화면으로 이동...");
        }
    }
}
