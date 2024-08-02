package camp;

import camp.cont.IndexType;

public class Sequence {

    // index 자동 증가
    public String sequence(String type) {
        switch (type) {
            case "ST" -> {
                CampManagementApplication.studentIndex++;
                return IndexType.ST.name() + CampManagementApplication.studentIndex;
            }
            case "SU" -> {
                CampManagementApplication.subjectIndex++;
                return IndexType.SU.name() + CampManagementApplication.subjectIndex;
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
