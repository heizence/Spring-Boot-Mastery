# Spring Boot Mastery: A Project-Based Roadmap ☕

이 저장소는 Spring Boot의 기본적인 기능부터 대규모 시스템 아키텍처까지, Spring Boot 백엔드 개발의 핵심 개념들을 실전 프로젝트를 통해 마스터하기 위한 학습 공간입니다.

하나의 거대한 프로젝트가 아니라, **작은 단위의 파트들이 연결된 로드맵 형태**로 구성되어 있습니다.
처음에는 간단한 CRUD API에서 출발하여, 이후에는 점차 난이도를 높여가며 보안, 메시징, 비동기 처리, 분산 환경, 클라우드 배포까지 확장됩니다.

이러한 구조를 택한 이유는 다음과 같습니다:

- **점진적 학습 곡선**: 초심자도 부담 없이 시작할 수 있도록 난이도를 조절했습니다.

- **실용적인 맥락**: 각 파트(프로젝트)는 실제 현업에서 자주 등장하는 문제 상황을 모델링했습니다.

- **재사용성과 독립성**: 각 프로젝트는 독립적으로 실행 가능하며, 필요한 부분만 선택적으로 학습할 수 있습니다.

## 🚀 프로젝트 목표

  - Spring Boot의 핵심 원리(DI, AOP)와 자동 설정에 대한 깊은 이해
  - 관계형 데이터베이스(JPA, Querydsl) 및 NoSQL(Redis, MongoDB) 활용 능력 습득
  - 대규모 트래픽 및 동시성 제어, 비동기 처리, 이벤트 기반 아키텍처 설계 경험
  - 클라우드 네이티브 환경(Docker, AWS, CI/CD)에서의 애플리케이션 배포 및 운영 능력 함양

## 🛠️ 기술 스택 (Tech Stack)

  - **Backend**: Java 17, Spring Boot 3.x, Spring Security, Spring Data (JPA, Redis, MongoDB, Elasticsearch)
  - **Database**: MySQL, H2, Redis, MongoDB, Elasticsearch, Neo4j
  - **Async & Messaging**: Kafka, RabbitMQ, WebSocket
  - **DevOps**: Docker, GitHub Actions, AWS (EC2, S3, RDS)
  - **Build Tool**: Gradle
  - **Client**: HTML, CSS, Vanilla JavaScript

## 🗺️ 프로젝트 로드맵 (Project Roadmap)

각 프로젝트의 상세한 구현 과정과 학습 내용은 [Wiki 문서](https://github.com/heizence/Spring-Boot-Mastery/wiki)를 통해 확인할 수 있으며, **소스코드 다운로드** 링크로 해당 파트의 코드만 개별적으로 받을 수 있습니다.

| Part | 프로젝트 | 핵심 기술 | Wiki 문서 | 소스코드 다운로드 |
| :--: | :--- | :--- | :--- | :--- |
| **1** | **투두리스트 API** | Spring MVC, JPA | `[작성 예정]` | `[링크 추가 예정]` |

## 💻 설치 및 실행 방법

이 저장소의 코드를 실행하기 위해 다음 환경이 필요합니다.

  - **Java 17** (혹은 그 이상)
  - **Gradle 8.x**
  - **IntelliJ IDEA** 또는 선호하는 IDE(IntelliJ 권장)
  - **Docker** (일부 프로젝트에서 DB, Redis 등을 실행하기 위해 필요)

프로젝트를 실행하는 방법은 크게 두 가지입니다. 전체 코드를 한 번에 받아보는 방법과, 원하는 프로젝트만 개별적으로 받는 방법 중 편한 것을 선택하세요.

어떤 방법을 선택하든, **각 파트 폴더 내에 있는 README.md 파일에 해당 프로젝트의 구체적인 실행 방법과 요구사항이 별도로 작성되어 있으니** 실행 전 반드시 먼저 확인해 주세요.

```bash
part-01-todo-api/
├── README.md  <-- 👈 이 파일을 먼저 확인하세요!
├── backend/
└── client/
```

### 방법 1: 전체 프로젝트 클론하여 실행하기

모든 프로젝트를 한 번에 내려받아 전체적인 구조를 파악하고 싶을 때 사용하는 방법입니다.

1.  터미널을 열고 다음 명령어를 입력하여 전체 저장소를 복제합니다.

    ```bash
    git clone https://github.com/heizence/Spring-Boot-Mastery.git
    ```

2.  원하는 파트의 폴더(예: part-01-todo-api)로 이동한 뒤, 해당 폴더의 README.md 파일에 따라 프로젝트를 실행합니다.

### 방법 2: 개별 프로젝트만 다운로드하여 실행하기

전체 저장소의 부담 없이 특정 프로젝트만 독립적으로 실행해보고 싶을 때 사용하는 방법입니다.

#### **▶️ 웹을 이용한 다운로드 (가장 간단하고 추천하는 방법)**

1.  위의 **프로젝트 로드맵** 표에서 원하는 파트의 **소스코드 다운로드** 링크를 클릭합니다.
2.  다운로드된 .zip 압축 파일을 해제합니다.
3.  폴더 내의 README.md 파일의 안내에 따라 프로젝트를 실행합니다.

#### **▶️ Command Line (고급 사용자용)**

git 의 sparse-checkout 기능을 사용하여 원하는 폴더만 가져올 수 있습니다.

1. 새 폴더를 만들고, Git을 초기화한 뒤 원하는 프로젝트 경로를 지정합니다.

    ```bash
    # 1. 새 폴더 생성 및 이동
    mkdir my-project
    cd my-project

    # 2. Git 초기화 및 원격 저장소 설정
    git init
    git remote add origin https://github.com/heizence/Spring-Boot-Mastery.git

    # 3. sparse-checkout 활성화 및 경로 지정 (예: part-01)
    git sparse-checkout init --cone
    git sparse-checkout set part-01-todo-api

    # 4. 원격 저장소에서 해당 폴더 내용만 가져오기
    git pull origin main
    ```
    
2. 다운로드된 폴더(part-01-todo-api)로 이동하여 README.md 파일을 확인하고 프로젝트를 실행합니다.
