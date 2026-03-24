# 회원 관리 REST API

> C#/ASP.NET 백엔드 개발자의 Java/Spring 전환 역량 증명을 위한 포트폴리오 프로젝트입니다.

## 기술 스택

- Java 17
- Spring Boot 3.5
- Spring Data JPA
- H2 Database (인메모리)
- Lombok
- Gradle

## API 명세

| Method | URL | 설명 |
|--------|-----|------|
| GET | /api/members | 전체 회원 조회 |
| GET | /api/members/{id} | 단건 회원 조회 |
| POST | /api/members | 회원 등록 |
| PUT | /api/members/{id} | 회원 수정 |
| DELETE | /api/members/{id} | 회원 삭제 |

## 주요 구현 사항

- Controller / Service / Repository / DTO 계층 분리
- JPA Entity 설계 및 H2 인메모리 DB 연동
- 존재하지 않는 회원 요청 시 404 예외처리
- @NotBlank 기반 입력값 검증 (이름, 이메일 필수)

## 실행 방법
```bash
./gradlew bootRun
```

실행 후 아래 주소로 확인
- API: http://localhost:8080/api/members
- H2 콘솔: http://localhost:8080/h2-console
