package camp.function;

import camp.data.Data;
import camp.enums.IndexType;

public class Sequence {

    // index 자동 증가
    public String sequence(String type) {
        switch (type) {
            case "ST" -> {
                Data.studentIndex++;
                return IndexType.ST.name() + Data.studentIndex;
            }
            case "SU" -> {
                Data.subjectIndex++;
                return IndexType.SU.name() + Data.subjectIndex;
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
