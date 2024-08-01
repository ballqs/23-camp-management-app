package camp;

import java.util.List;

public interface ManagementInterface<T> {

    // 1개 조회용
    T select(List<T> list , String id);

    // 전체 조회
    void selectAll(List<T> list);

    // 수정
    T update();

    // 삭제
    T delete();

    // 등록
    T insert();
}
