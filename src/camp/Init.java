package camp;

import camp.data.Data;
import camp.enums.IndexType;
import camp.enums.SubjectList;
import camp.model.Subject;
import camp.repository.SubjectManagement;

import java.util.ArrayList;
import java.util.HashMap;

public class Init {

    // 초기 설정
    public static void setInitData() {
        Data.studentStore = new HashMap<>();
        Data.subjectStore = new HashMap<>();
        Data.scoreStore = new ArrayList<>();

        Sequence sequence = new Sequence();
        SubjectManagement subjectManagement = new SubjectManagement();

        String id = "";
        for (int i = 0; i < SubjectList.values().length; i++) {
            id = sequence.sequence(IndexType.SU.name());
            subjectManagement.insert(id , new Subject(id , SubjectList.values()[i].getName() , SubjectList.values()[i].getType().name()));
        }
    }
}
