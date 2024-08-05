package camp.function;

import camp.enums.IndexType;
import camp.enums.SubjectList;
import camp.model.Subject;
import camp.repository.SubjectManagement;

public class Init {

    // 초기 설정
    public static void setInitData() {
        Sequence sequence = new Sequence();
        SubjectManagement subjectManagement = new SubjectManagement();

        String id = "";
        for (int i = 0; i < SubjectList.values().length; i++) {
            id = sequence.create(IndexType.SU.name());
            subjectManagement.insert(id , new Subject(id , SubjectList.values()[i].getName() , SubjectList.values()[i].getType().name()));
        }
    }
}
