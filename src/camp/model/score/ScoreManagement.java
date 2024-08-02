package camp.model.score;

import camp.ManagementInterface;

import java.util.List;

public class ScoreManagement implements ManagementInterface<Score> {

    private String studentId;

    public ScoreManagement(String studentId) {
        this.studentId = studentId;
    }

    @Override
    public Score select(List<Score> scoreStore, String id) {
        return null;
    }

    @Override
    public void selectAll(List<Score> scoreStore) {
        for (Score score : scoreStore) {
            if (score.getStudentId().equals(studentId)) {
                System.out.println(score);
            }
        }
    }

    @Override
    public Score update() {
        return null;
    }

    @Override
    public Score delete() {
        return null;
    }

    @Override
    public Score insert() {
        return null;
    }
}
