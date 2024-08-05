package camp.interfaces;

import java.util.List;

public interface ManagementInterface<T> {

    // 1개 가지고 오는 용도
    T getData(String str);

    void print(T info);

    // 전체 조회
    void printAll();

    // 등록
    void insert(String key , T info);
}
