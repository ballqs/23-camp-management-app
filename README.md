![header](https://capsule-render.vercel.app/api?text=23조%20수강생%20관리%20시스템&animation=fadeIn&type=venom&color=FFA7A7&fontColor=F15F5F)

## 프로젝트 개요

이 프로젝트는 Java 강의를 통해 배운 내용을 바탕으로 개발된 간단한 **수강생 관리 시스템**입니다.

이 시스템은 Java의 기본 입력 방법인 `Scanner` 클래스를 활용하여 사용자로부터 데이터를 입력받고, 이를 바탕으로 수강생의 등록 및 점수 관리를 수행합니다.

---

### 👨‍💻팀 소개

- 팀명 : **폭주 기관차🚂**
- 팀 소개 :  `java`에 대한 열정을 가지고 폭주 기관차처럼 성장하는 팀입니다!!
- 팀원 소개 및 역할  분담

| 이름   | 구현                           | 역할     |
| :----- | :----------------------------- | :------- |
| 김준형 | 메인 파트구현                  | 팀장     |
| 황호진 | 전체적인 기획 및 과목파트 구현 | 부팀장   |
| 권정익 | 점수 파트 구현                 | 영상촬영 |
| 이유진 | 점수 파트 구현                 | PPT 제작 |
| 위종혁 | 수강생 파트 구현               | 발표     |

---



### 💡<span style="color:blue">기능 명세서</span>

#### 필수 요구사항

- **수강생 관리**
  - 수강생 정보 등록
  - 수강생 목록 조회
- **점수 관리**
  - 수강생의 과목별 시험 회차 및 점수 등록
  - 수강생의 과목별 시험 회차 및 점수 수정
  - 수강생의 특정 과목 회차별 등급 조회

#### 추가 요구사항

- **수강생 관리**
  - 수강생 상태 관리
  - 수강생 정보 조회
  - 수강생 정보 수정
  - 상태별 수강생 목록 조회
  - 수강생 삭제
- **점수 관리**
  - 수강생의 과목별 평균 등급 조회
  - 특정 상태 수강생들의 필수 과목 평균 등급을 조회

---



### 🏷️ <span style="color:blue">모델 정보</span>

> **수강생**

| 수강생    | 타입                  |
| --------- | --------------------- |
| 고유 번호 | String                |
| 이름      | String                |
| 과목 목록 | Map<String , Subject> |

> 점수

| 점수                       | 타입   |
| -------------------------- | ------ |
| 과목 고유 번호             | String |
| 수강생 고유 번호           | String |
| 회차                       | int    |
| 점수                       | int    |
| 등급 (A, B, C, D, E, F, N) | char   |

> 과목

| 과목                   | 타입   |
| ---------------------- | ------ |
| 과목 고유 번호         | String |
| 과목 이름              | String |
| 과목 타입(필수 , 선택) | String |

> 주어진 과목 목록

| 과목            | 타입 |
| --------------- | ---- |
| Java            | 필수 |
| 객체지향        | 필수 |
| Spring          | 필수 |
| JPA             | 필수 |
| MySQL           | 필수 |
| 디자인 패턴     | 선택 |
| Spring Security | 선택 |
| Redis           | 선택 |
| MongoDB         | 선택 |

***



### 💿 <span style="color:blue">파일 및 디렉토리</span>

```java
src/
└── camp/
    ├── enums/
    │   ├── Grade.java                # 학점 등급을 정의하는 열거형
    │   ├── IndexType.java            # 인덱스 타입을 정의하는 열거형
    │   ├── StudentStatusType.java    # 학생 상태 유형을 정의하는 열거형
    │   ├── SubjectList.java          # 과목 목록을 정의하는 열거형
    │   └── SubjectType.java          # 과목 유형을 정의하는 열거형
    ├── function/
    │   ├── Init.java                 # 설정 관련 초기화 함수
    │   ├── OptionalSubConverter.java # 선택 과목 변환기
    │   ├── RequiredSubConverter.java # 필수 과목 변환기
    │   └── Sequence.java             # 일련번호 생성 및 관리
    ├── interfaces/
    │   ├── GradeConvertor.java       # 성적 변환 인터페이스
    │   └── ManagementInterface.java  # 관리 기능을 위한 인터페이스
    ├── model/
    │   ├── Score.java                # 성적 모델
    │   ├── Student.java              # 학생 모델
    │   └── Subject.java              # 과목 모델
    ├── repository/
    │   ├── ScoreManagement.java      # 성적 관리 저장소
    │   ├── StudentManagement.java    # 학생 관리 저장소
    │   └── SubjectManagement.java    # 과목 관리 저장소
    ├── service/
    │   ├── ScoreService.java         # 성적 관리 서비스
    │   ├── StudentAndScoreService.java # 학생 및 성적 관리 서비스
    │   ├── StudentAndSubjectService.java # 학생 및 과목 관리 서비스
    │   └── StudentService.java       # 학생 관리 서비스
    └── CampManagementApplication.java # 메인 애플리케이션 실행 클래스
```

