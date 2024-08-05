package camp.interfaces;

import java.util.List;

public interface ManagementInterface<T> {

    // 1개 가지고 오는 용도
    T getData(String str);

    void select(T info);

    // 전체 조회
    void selectAll();

    // 수정
    void update(String id , String fleid , String value);

    // 삭제
    void delete(String id);

    // 등록
    void insert(String key , T info);
}
