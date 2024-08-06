![header](https://capsule-render.vercel.app/api?text=23조%20수강생%20관리%20시스템&animation=fadeIn&type=Venom&fontColor=ffffff)

## 프로젝트 개요

이 프로젝트는 Java 강의를 통해 배운 내용을 바탕으로 개발된 간단한 **수강생 관리 시스템**입니다.

이 시스템은 Java의 기본 입력 방법인 `Scanner` 클래스를 활용하여 사용자로부터 데이터를 입력받고, 이를 바탕으로 수강생의 등록 및 점수 관리를 수행합니다.

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

- **수강생**

| 수강생    | 타입                  |
| --------- | --------------------- |
| 고유 번호 | String                |
| 이름      | String                |
| 과목 목록 | Map<String , Subject> |

- **점수**

| 점수                       | 타입   |
| -------------------------- | ------ |
| 과목 고유 번호             | String |
| 수강생 고유 번호           | String |
| 회차                       | int    |
| 점수                       | int    |
| 등급 (A, B, C, D, E, F, N) | char   |

- **과목**

| 과목                   | 타입   |
| ---------------------- | ------ |
| 과목 고유 번호         | String |
| 과목 이름              | String |
| 과목 타입(필수 , 선택) | String |

- **주어진 과목 목록**

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
