package camp.model.score;

import java.util.Map;

public interface GradeConvertor {

    Grade ScoreToGrade(Map<Integer, Integer> scoreMap);
}
