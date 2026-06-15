# 🎫 QuanLyVoucher

Hệ thống **Quản Lý Voucher** được xây dựng bằng **Spring Boot 4.1.0**, áp dụng kiến trúc **Clean Architecture** (Domain-Driven Design) với 4 module độc lập: `Domain`, `Application`, `infrastructure`, `presentation`.

---

## 📋 Mục Lục

- [Yêu Cầu Hệ Thống](#-yêu-cầu-hệ-thống)
- [Kiến Trúc Dự Án](#-kiến-trúc-dự-án)
- [Cấu Trúc Thư Mục](#-cấu-trúc-thư-mục)
- [Cài Đặt & Chạy Dự Án](#-cài-đặt--chạy-dự-án)
- [Cấu Hình Database](#-cấu-hình-database)
- [Cấu Trúc Database](#-cấu-trúc-database)
- [API Endpoints](#-api-endpoints)
- [Mô Hình Dữ Liệu](#-mô-hình-dữ-liệu)
- [Công Nghệ Sử Dụng](#-công-nghệ-sử-dụng)

---

## 💻 Yêu Cầu Hệ Thống

| Thành phần | Phiên bản |
|---|---|
| Java (JDK) | 21 trở lên |
| Maven | 3.9.x (hoặc dùng Maven Wrapper đính kèm) |
| SQL Server | 2019+ (hoặc Azure SQL) |
| IDE | IntelliJ IDEA (khuyến nghị) |

---

## 🏛️ Kiến Trúc Dự Án

Dự án áp dụng **Clean Architecture** gồm 4 tầng tách biệt rõ ràng:

```
┌─────────────────────────────────────────┐
│         presentation (Web Layer)        │  ← REST Controllers, HTTP Request/Response
├─────────────────────────────────────────┤
│        infrastructure (Data Layer)      │  ← JPA Entities, Repositories, Mappers
├─────────────────────────────────────────┤
│         Application (Use Case Layer)    │  ← Services, DTOs, Business Logic
├─────────────────────────────────────────┤
│            Domain (Core Layer)          │  ← Entities, Repository Interfaces
└─────────────────────────────────────────┘
```

**Nguyên tắc phụ thuộc:** Tầng ngoài phụ thuộc vào tầng trong, tầng trong không biết gì về tầng ngoài.

```
presentation → infrastructure → Application → Domain
```

---

## 📁 Cấu Trúc Thư Mục

```
QuanLyVoucher/
├── pom.xml                          # Root POM (quản lý tất cả module)
│
├── Domain/                          # Tầng Domain (core)
│   └── src/main/java/com/example/domain/
│       ├── entity/
│       │   ├── user.java
│       │   ├── voucher.java
│       │   └── voucher_usage.java
│       └── repository/              # Interface repository (contracts)
│           ├── userRepo.java
│           ├── voucherRepo.java
│           └── voucher_usageRepo.java
│
├── Application/                     # Tầng Application (use cases)
│   └── src/main/java/com/example/application/
│       ├── common/
│       │   ├── ApiResponse.java     # Wrapper response chuẩn
│       │   └── PageResult.java      # Kết quả phân trang
│       ├── dto/
│       │   ├── UserDto.java / UserRequest.java
│       │   ├── VoucherDto.java / VoucherRequest.java
│       │   └── VoucherUsageDto.java / VoucherUsageRequest.java
│       └── service/
│           ├── UserService.java / UserServiceImpl.java
│           ├── VoucherService.java / VoucherServiceImpl.java
│           └── VoucherUsageService.java / VoucherUsageServiceImpl.java
│
├── infrastructure/                  # Tầng Infrastructure (data access)
│   └── src/main/java/com/example/infrastructure/
│       ├── jpa/                     # JPA Entities (ánh xạ vào database)
│       │   ├── UserJpaEntity.java
│       │   ├── VoucherJpaEntity.java
│       │   └── VoucherUsageJpaEntity.java
│       ├── Repositories/            # Spring Data JPA Repositories
│       │   ├── UserJpaRepository.java
│       │   ├── VoucherJpaRepository.java
│       │   └── VoucherUsageJpaRepository.java
│       ├── adapter/                 # Triển khai Domain Repository
│       │   ├── UserRepoJpaIml.java
│       │   ├── VoucherRepoJpaIml.java
│       │   └── VoucherUsageRepoJpaIml.java
│       └── mapper/                  # Chuyển đổi Domain ↔ JPA Entity
│           ├── UserMapper.java
│           ├── VoucherMapper.java
│           └── Voucher_UsageMapper.java
│
└── presentation/                    # Tầng Presentation (REST API)
    └── src/main/java/com/example/presentation/
        └── controller/
            ├── UserController.java
            ├── VoucherController.java
            └── VoucherUsageController.java
```

---

## 🚀 Cài Đặt & Chạy Dự Án

### Bước 1: Clone repository

```bash
git clone <repository-url>
cd QuanLyVoucher
```

### Bước 2: Cấu hình database

Mở file `presentation/src/main/resources/application.properties` và thêm cấu hình kết nối SQL Server:

```properties
spring.application.name=QuanLyVoucher

# SQL Server connection
spring.datasource.url=jdbc:sqlserver://localhost:1433;databaseName=QuanLyVoucher;encrypt=true;trustServerCertificate=true
spring.datasource.username=your_username
spring.datasource.password=your_password
spring.datasource.driver-class-name=com.microsoft.sqlserver.jdbc.SQLServerDriver

# JPA / Hibernate
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
spring.jpa.database-platform=org.hibernate.dialect.SQLServerDialect

# Server port
server.port=8080
```

### Bước 3: Build toàn bộ project

Sử dụng Maven Wrapper (không cần cài Maven riêng):

```bash
# Windows
.\mvnw.cmd clean install

# Linux / macOS
./mvnw clean install
```

Hoặc nếu đã cài Maven:

```bash
mvn clean install
```

### Bước 4: Chạy ứng dụng

```bash
# Chạy từ module presentation
cd presentation
..\mvnw.cmd spring-boot:run
```

Hoặc trong IntelliJ IDEA: chạy class `PresentationApplication.java` trong module `presentation`.

Ứng dụng khởi động tại: **http://localhost:8080**

---

## 🗄️ Cấu Hình Database

Tạo database `QuanLyVoucher` trên SQL Server, sau đó Hibernate sẽ tự tạo bảng khi `ddl-auto=update`. Nếu muốn tạo thủ công, dùng script sau:

```sql
CREATE DATABASE QuanLyVoucher;
GO

USE QuanLyVoucher;
GO

CREATE TABLE users (
    id          INT IDENTITY(1,1) PRIMARY KEY,
    full_name   NVARCHAR(255)  NOT NULL,
    email       NVARCHAR(255)  NOT NULL UNIQUE,
    phone       NVARCHAR(20),
    created_at  DATETIME2
);

CREATE TABLE vouchers (
    id               INT IDENTITY(1,1) PRIMARY KEY,
    code             NVARCHAR(50)  NOT NULL UNIQUE,
    discount_percent INT           NOT NULL,
    quantity         INT           NOT NULL,
    expired_date     DATE          NOT NULL,
    status           NVARCHAR(20)  NOT NULL,  -- 'ACTIVE' | 'INACTIVE'
    created_at       DATETIME2
);

CREATE TABLE user_vouchers (
    id          INT IDENTITY(1,1) PRIMARY KEY,
    user_id     INT       NOT NULL,
    voucher_id  INT       NOT NULL,
    used_at     DATETIME2,
    FOREIGN KEY (user_id)    REFERENCES users(id),
    FOREIGN KEY (voucher_id) REFERENCES vouchers(id)
);
```

---

## 📊 Cấu Trúc Database

### Bảng `users`
| Cột | Kiểu | Mô tả |
|---|---|---|
| id | INT (PK) | Khóa chính, tự tăng |
| full_name | NVARCHAR(255) | Họ tên đầy đủ |
| email | NVARCHAR(255) | Email (unique) |
| phone | NVARCHAR(20) | Số điện thoại |
| created_at | DATETIME2 | Thời gian tạo |

### Bảng `vouchers`
| Cột | Kiểu | Mô tả |
|---|---|---|
| id | INT (PK) | Khóa chính, tự tăng |
| code | NVARCHAR(50) | Mã voucher (unique) |
| discount_percent | INT | % giảm giá |
| quantity | INT | Số lượt còn lại |
| expired_date | DATE | Ngày hết hạn |
| status | NVARCHAR(20) | Trạng thái: `ACTIVE` / `INACTIVE` |
| created_at | DATETIME2 | Thời gian tạo |

### Bảng `user_vouchers`
| Cột | Kiểu | Mô tả |
|---|---|---|
| id | INT (PK) | Khóa chính, tự tăng |
| user_id | INT (FK) | Tham chiếu đến `users.id` |
| voucher_id | INT (FK) | Tham chiếu đến `vouchers.id` |
| used_at | DATETIME2 | Thời điểm sử dụng |

---

## 🔌 API Endpoints

Base URL: `http://localhost:8080`

Tất cả response đều có cấu trúc chuẩn:

```json
{
  "success": true,
  "message": "Mô tả kết quả",
  "data": { ... }
}
```

---

### 👤 User API — `/api/users`

#### Lấy danh sách users (có phân trang)
```
GET /api/users?page=0&size=10
```
Response:
```json
{
  "success": true,
  "message": "Lấy danh sách user thành công",
  "data": {
    "content": [ { "id": 1, "fullname": "Nguyễn Văn A", "email": "a@example.com", ... } ],
    "page": 0,
    "size": 10,
    "totalElements": 50,
    "totalPages": 5
  }
}
```

#### Lấy user theo ID
```
GET /api/users/{id}
```

#### Tạo user mới
```
POST /api/users
Content-Type: application/json

{
  "fullname": "Nguyễn Văn A",
  "email": "a@example.com",
  "phone": "0901234567"
}
```

---

### 🎫 Voucher API — `/api/vouchers`

#### Lấy danh sách vouchers (có phân trang)
```
GET /api/vouchers?page=0&size=10
```

#### Lấy voucher theo ID
```
GET /api/vouchers/{id}
```

#### Lấy voucher theo mã code
```
GET /api/vouchers/code/{code}
```

#### Tạo voucher mới
```
POST /api/vouchers
Content-Type: application/json

{
  "code": "SALE2025",
  "discountPercent": 20,
  "quantity": 100,
  "expiredDate": "2025-12-31",
  "status": "ACTIVE"
}
```

#### Cập nhật voucher
```
PUT /api/vouchers/{id}
Content-Type: application/json

{
  "code": "SALE2025",
  "discountPercent": 30,
  "quantity": 50,
  "expiredDate": "2025-12-31",
  "status": "ACTIVE"
}
```

#### Xóa voucher
```
DELETE /api/vouchers/{id}
```

---

### 📝 VoucherUsage API — `/api/voucher-usages`

#### Lấy danh sách lịch sử sử dụng (có phân trang)
```
GET /api/voucher-usages?page=0&size=10
```

#### Lấy lịch sử theo ID
```
GET /api/voucher-usages/{id}
```

#### Áp dụng voucher (sử dụng 1 lượt)
```
POST /api/voucher-usages
Content-Type: application/json

{
  "userId": 1,
  "voucherId": 5
}
```

> **Lưu ý:** Hệ thống sẽ tự động kiểm tra voucher còn hiệu lực, chưa hết hạn và còn số lượng. Nếu thành công, `quantity` của voucher sẽ giảm đi 1.

---

## 🧪 Kiểm Tra Nhanh Với curl

```bash
# Tạo user
curl -X POST http://localhost:8080/api/users \
  -H "Content-Type: application/json" \
  -d '{"fullname":"Nguyen Van A","email":"a@test.com","phone":"0901234567"}'

# Tạo voucher
curl -X POST http://localhost:8080/api/vouchers \
  -H "Content-Type: application/json" \
  -d '{"code":"SAVE50","discountPercent":50,"quantity":10,"expiredDate":"2025-12-31","status":"ACTIVE"}'

# Sử dụng voucher
curl -X POST http://localhost:8080/api/voucher-usages \
  -H "Content-Type: application/json" \
  -d '{"userId":1,"voucherId":1}'

# Xem danh sách vouchers
curl http://localhost:8080/api/vouchers
```

---

## 🛠️ Công Nghệ Sử Dụng

| Công nghệ | Phiên bản | Mục đích |
|---|---|---|
| Spring Boot | 4.1.0 | Framework chính |
| Spring Data JPA | 4.1.0 | ORM / Data access |
| Spring Web | 4.1.0 | REST API |
| Spring Validation | 4.1.0 | Validate request |
| Hibernate | 7.4.1 | JPA implementation |
| Lombok | 1.18.36 | Giảm boilerplate code |
| SQL Server JDBC | 12.8.1 | Driver kết nối database |
| Maven | 3.9.16 | Build tool |
| Java | 21 | Ngôn ngữ lập trình |

---

## 📝 Ghi Chú

- Project sử dụng **Clean Architecture** nên Domain và Application layer hoàn toàn độc lập với framework/database.
- File cấu hình `application.properties` chính nằm trong module `presentation/src/main/resources/`.
- Application chạy qua class `com.example.presentation.PresentationApplication`.
- Phân trang được xử lý thủ công trong Service layer (in-memory pagination). Với dataset lớn, nên chuyển sang dùng `Pageable` của Spring Data JPA.