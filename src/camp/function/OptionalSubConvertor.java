package camp.function;

import camp.enums.Grade;
import camp.interfaces.GradeConvertor;

import static camp.enums.Grade.*;

public class OptionalSubConvertor implements GradeConvertor {

    @Override
    public Grade ScoreToGrade(int score) {

        if (90 <= score && score <= 100) {
            return A;

        } else if (80 <= score) {
            return B;

        } else if (70 <= score) {
            return C;

        } else if (60 <= score) {
            return D;

        } else if (50 <= score) {
            return F;

        } else if (0 <= score) {
            return N;

        } else {
            System.out.println("등급을 산정할 수 없는 잘못된 점수입니다.");
            return null;
        }

    }
}
