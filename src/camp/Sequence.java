package camp;

public class Sequence {

    // index 자동 증가
    public String sequence(String type) {
        switch (type) {
            case CampManagementApplication.INDEX_TYPE_STUDENT -> {
                CampManagementApplication.studentIndex++;
                return CampManagementApplication.INDEX_TYPE_STUDENT + CampManagementApplication.studentIndex;
            }
            case CampManagementApplication.INDEX_TYPE_SUBJECT -> {
                CampManagementApplication.subjectIndex++;
                return CampManagementApplication.INDEX_TYPE_SUBJECT + CampManagementApplication.subjectIndex;
            }
            default -> {
//                scoreIndex++;
//                return INDEX_TYPE_SCORE + scoreIndex;
                // try ~ catch 사용해보자....
                return "";
            }
        }
    }
}
