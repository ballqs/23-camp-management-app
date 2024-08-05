package camp.function;

import camp.data.Data;
import camp.enums.IndexType;

public class Sequence {

    // index 자동 증가
    public String create(String type) {
        if (type.equals("ST")) {
            Data.STUDENTINDEX++;
            return IndexType.ST.name() + Data.STUDENTINDEX;
        } else {
            Data.SUBJECTINDEX++;
            return IndexType.SU.name() + Data.SUBJECTINDEX;
        }
    }
}
