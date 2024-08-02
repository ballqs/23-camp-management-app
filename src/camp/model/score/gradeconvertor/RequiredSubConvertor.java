package camp.model.score.gradeconvertor;

import java.util.Map;

import static camp.model.score.gradeconvertor.Grade.*;

public class RequiredSubConvertor implements GradeConvertor {

    @Override
    public Grade ScoreToGrade(Map<Integer, Integer> scoreMap) {
        Integer score = scoreMap.get(scoreMap.size());

        if (95 <= score && score <= 100) {
            return A;

        } else if (90 <= score) {
            return B;

        } else if (80 <= score) {
            return C;

        } else if (70 <= score) {
            return D;

        } else if (60 <= score) {
            return F;

        } else if (0 <= score) {
            return N;

        } else {
            System.out.println("등급을 산정할 수 없는 잘못된 점수입니다.");
            return null;
        }


    }
}
