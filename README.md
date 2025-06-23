# 📦 POS Order Service

Spring Boot 기반의 주문 처리 마이크로서비스입니다.  
재고 확인 및 주문 생성/조회 기능을 제공하며, 분리된 `pos-common` 공통 모듈을 참조합니다.

---

## ✅ Features

- 주문 등록 및 조회 API
- 재고 서비스 연동 (`pos-inventory-service`)
- 공통 API 응답 구조 적용 (`ApiResponse`)
- Swagger 기반 REST API 문서 제공
- Gradle 기반 빌드 및 Docker 배포 지원
- MSA 아키텍처 구성

---

## 🧱 Tech Stack

- Java 17
- Spring Boot 3.3.x
- Gradle 8.x
- Spring Web, OpenFeign
- Springdoc OpenAPI (Swagger UI)
- Docker
- GitHub Actions (예정)

---

## 📁 프로젝트 구조

```
pos-order-service/
├── src/
│   ├── main/
│   │   ├── java/com/example/orderservice
│   │   │   ├── controller
│   │   │   ├── service
│   │   │   ├── model
│   │   │   └── client
│   ├── test/
├── build.gradle
├── settings.gradle
└── README.md
```

---

## 🚀 실행 방법

### 1. 사전 준비

- Java 17 이상
- Gradle 설치 (또는 Gradle Wrapper 사용)
- 로컬 Maven 저장소에 `pos-common` 사전 배포 필요:

```bash
cd ~/projects/pos-common
./gradlew clean publishToMavenLocal
```

### 2. 프로젝트 실행

```bash
cd ~/projects/pos-order-service
./gradlew clean build
./gradlew bootRun
```

> 기본 포트는 `8081` (설정 확인 필요)

---

## 📡 API 사용 예시

### ✅ 서버 상태 확인

```
GET /api/orders/test
```

응답:

```json
{
  "data": "Order service is running!",
  "code": "order-ok"
}
```

---

### 📦 주문 목록 조회

```
GET /api/orders
```

---

### 📦 주문 생성

```
POST /api/orders
Content-Type: application/json

{
  "itemName": "bolt",
  "quantity": 10
}
```

---

## 📚 Swagger 문서

실행 후 아래 경로에서 확인:

```
http://localhost:8081/swagger-ui/index.html
```

---

## 🔗 관련 프로젝트

| 서비스                                                                   | 설명                          |
| ------------------------------------------------------------------------ | ----------------------------- |
| [pos-common](https://github.com/grshin/pos-common)                       | 공통 DTO 및 응답 포맷 제공    |
| [pos-inventory-service](https://github.com/grshin/pos-inventory-service) | 재고 연동 마이크로서비스      |
| [pos-api-gateway](https://github.com/grshin/pos-api-gateway)             | API 통합 및 라우팅 게이트웨이 |

---

## 🛠️ 향후 확장 예정

- Spring Cloud Eureka 연동 (서비스 디스커버리)
- Spring Cloud Config (설정 분리)
- JWT 기반 인증/인가 적용
- GitHub Actions 기반 CI/CD 파이프라인

---

## 👤 Author

- **grshin**
- [GitHub](https://github.com/grshin)
