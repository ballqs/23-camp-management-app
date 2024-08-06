package camp.function;

import camp.enums.IndexType;

public class Sequence {

    // index 자동 증가
    public String create(IndexType type , int index) {
        return type.name() + index;
    }
}
