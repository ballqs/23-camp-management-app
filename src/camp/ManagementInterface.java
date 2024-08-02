package camp;

import java.util.List;

public interface ManagementInterface<T> {

    // 1개 가지고 오는 용도
    T getData(String str);

    // 전체 조회
    void selectAll();

    // 수정
    void update(String id , String fleid , String value);

    // 삭제
    void delete();

    // 등록
    void insert();
}
