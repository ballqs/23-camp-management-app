package camp.model.score.gradeconvertor;

import java.util.Map;

public interface GradeConvertor {

    Grade ScoreToGrade(Map<Integer, Integer> scoreMap);
}
