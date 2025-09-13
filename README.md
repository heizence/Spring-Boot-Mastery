# Spring Boot Mastery: A Project-Based Roadmap ☕

이 저장소는 Spring Boot의 기본적인 기능부터 대규모 시스템 아키텍처까지, 여러 개의 실전 프로젝트를 통해 마스터하는 개인 학습 로드맵입니다. 각 파트는 현업에서 마주할 수 있는 실용적인 문제 해결에 초점을 맞추고 있습니다.

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

각 프로젝트의 상세한 구현 과정과 학습 내용은 **WIKI** 링크를 통해 확인할 수 있으며, **소스코드 다운로드** 링크로 해당 파트의 코드만 개별적으로 받을 수 있습니다.

| Part | 프로젝트 | 핵심 기술 | WIKI (학습 내용) | 소스코드 다운로드 |
| :--: | :--- | :--- | :--- | :--- |
| **1** | **투두리스트 API** | Spring MVC, JPA | `[작성 예정]` | `[링크 추가 예정]` |

## 💻 설치 및 실행 방법

이 저장소의 코드를 실행하기 위해 다음 환경이 필요합니다.

  - **Java 17** (혹은 그 이상)
  - **Gradle 8.x**
  - **IntelliJ IDEA** 또는 선호하는 IDE(IntelliJ 권장)
  - **Docker** (일부 프로젝트에서 DB, Redis 등을 실행하기 위해 필요)

#### 전체 프로젝트 클론

```bash
git clone https://github.com/heizence/Spring-Boot-Mastery.git
cd Spring-Boot-Mastery
```

#### 개별 프로젝트 실행

각 파트의 `backend` 디렉토리로 이동하여 IDE로 프로젝트를 열고 실행합니다.

```bash
# 예시: Part 1 프로젝트 실행
cd part-01-todo-api/backend
```
