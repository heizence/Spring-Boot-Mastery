# Part 1: 투두리스트 API

## 1. 프로젝트 소개

Spring Boot를 사용하여 기본적인 CRUD 기능을 갖춘 RESTful API를 구축하는 프로젝트입니다. 
이 프로젝트는 JPA를 이용한 데이터베이스 연동, 계층형 아키텍처(Controller, Service, Repository), DTO를 통한 데이터 전송, 그리고 전역 예외 처리 등 백엔드 개발의 핵심 기초를 학습하는 것을 목표로 합니다.

## 2. 주요 기능

-   할 일 추가 (Create)
-   할 일 목록 전체 조회 (Read)
-   할 일 내용 수정 (Update)
-   할 일 완료 상태 변경 (Toggle)
-   할 일 삭제 (Delete)

## 3. 기술 스택 (Tech Stack)

- **Server**: Java 17, Spring Boot 3.5.5, Spring Data JPA, SpringDoc OpenAPI
- **Database**: H2 Database
- **Utilities**: Lombok
- **Frontend**: HTML, CSS, Javascript

## 4. 시작하기

이 프로젝트의 코드를 실행하기 위해 다음 환경이 필요합니다.

- **Java 17** (혹은 그 이상)
- **Gradle 8.x**
- **IntelliJ IDEA** 또는 선호하는 IDE(IntelliJ 권장)

### 소스코드 다운로드

아래 링크를 통해 이 프로젝트의 소스코드를 `.zip` 파일로 즉시 다운로드할 수 있습니다. 

**➡️ [Part 1 소스코드 다운로드](https://downgit.github.io/#/home?url=https://github.com/heizence/Spring-Boot-Mastery/new/main/part01-todo-api)**

전체 저장소를 `git clone` 하는 방법은 [메인 README](https://github.com/heizence/Spring-Boot-Mastery/tree/main?tab=readme-ov-file#%EB%B0%A9%EB%B2%95-1-%EC%A0%84%EC%B2%B4-%ED%94%84%EB%A1%9C%EC%A0%9D%ED%8A%B8-%ED%81%B4%EB%A1%A0%ED%95%98%EC%97%AC-%EC%8B%A4%ED%96%89%ED%95%98%EA%B8%B0) 를 참고하세요.


### 백엔드 실행

프로젝트 root 디렉토리에서 다음 명령어를 통해 백엔드 애플리케이션을 실행합니다.

**1. 백엔드 디렉토리로 이동**

```bash
cd part-01-todo-api/backend
```

**2. Gradle Wrapper를 사용하여 애플리케이션 실행**

> **Info:** Gradle Wrapper (`gradlew`)를 사용하면 로컬에 Gradle을 별도로 설치할 필요 없이 프로젝트를 실행할 수 있습니다.

```bash
# For macOS/Linux
./gradlew bootRun

# For Windows
.\gradlew.bat bootRun
```

**3. 서버 확인**

서버를 실행하면 [http://localhost:8081](http://localhost:8081) 에서 서버가 실행됩니다.


### 클라이언트 실행

클라이언트는 정적(static) HTML, CSS, JS 파일로 구성되어 있으므로, 웹 서버를 통해 실행해야 합니다. 단순히 `index.html` 파일을 브라우저에서 열면 CORS 정책으로 인해 API 요청이 실패할 수 있습니다.

**1. 클라이언트 디렉토리로 이동**

```bash
cd part-01-todo-api/client
```

**2. 로컬 웹 서버 실행**
  
VS Code의 `Live Server` 확장 프로그램을 사용하면 매우 편리합니다. 

Live Server 를 이용하여 index.html 파일을 웹 브라우저에서 열면 웹 애플리케이션을 확인할 수 있습니다.

## 5. API 문서 및 테스트 (Swagger UI)

서버 실행 후 아래 주소로 접속하세요.

- **Swagger UI Link**: [**http://localhost:8081/swagger-ui.html**](http://localhost:8081/swagger-ui.html)

## 부록: 더 알아보기

프로젝트 [Wiki 문서]()에 들어가면 프로젝트의 구조, 적용된 핵심 개념 등 더 많은 내용을 볼 수 있습니다.

**1. Spring 핵심 원리**

- `IoC/DI`: 생성자 주입의 장점
- `AOP`: 프로젝트 속 AOP 활용 사례
- `Lombok`: 코드 자동 생성 원리

**2. 아키텍처와 설계 원칙**

- `계층형 아키텍처`: Controller, Service, Repository 의 책임 분리
- `DTO 설계`: Entity를 외부에 노출하지 않는 이유
- `RESTful API` 설계 원칙

**3. 데이터베이스와 영속성 관리**

- `JPA` 엔티티 매핑과 `JpaRepository`
- `영속성 컨텍스트`와 변경 감지(Dirty Checking)
- `H2 데이터베이스`를 개발 단계에서 사용하는 이유

**4. API 설계와 문서화**

- HTTP Method와 의미 (GET, POST, PUT, DELETE)
- Status Code 설계
- `Swagger(SpringDoc)`를 이용한 API 문서 자동화
- `@RestControllerAdvice`를 활용한 전역 예외 처리
  
**5. 전역 예외 처리**

- 예외를 중앙에서 처리하는 방식
- @ExceptionHandler 비교
- 커스텀 예외 설계

**6. Gradle 과 빌드 관리**

- Gradle 기본 개념과 Wrapper
- build.gradle 구조
- 의존성 관리
- 빌드 & 실행 과정
- 자주 사용하는 Gradle 명령어
