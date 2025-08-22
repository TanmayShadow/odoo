# Oddo Hackathon Backend 🚀

Spring Boot backend for the **Oddo Hackathon** project. It exposes RESTful APIs, implements the core business logic, and follows a clean, layered architecture (controller → service → repository). Built with Java and Maven.

---

## 📂 Project Structure

```
odoo/
├─ src/
│  ├─ main/
│  │  ├─ java/com/example/odoo/
│  │  │  ├─ config/        # SecurityConfig and other configuration
│  │  │  ├─ controller/    # REST controllers (API endpoints)
│  │  │  ├─ dto/           # Request/Response DTOs
│  │  │  ├─ model/         # JPA entities / domain models
│  │  │  ├─ repository/    # Spring Data JPA repositories
│  │  │  └─ service/       # Business logic layer
│  │  └─ resources/
│  │     └─ application.properties
│  └─ test/                # Unit / integration tests (if any)
├─ pom.xml
└─ OdooApplication.java    # Spring Boot entry point
```

---

## ✨ Features

- 🔐 **Authentication & Security** (via Spring Security in `config/`)
- 📡 **RESTful APIs** for core Oddo features
- 🗄️ **Persistence** with Spring Data JPA (repositories, entities)
- 🧱 **Layered architecture** with DTOs and services
- ✅ **Validation & error handling** (extensible)
- 🧪 Ready for **unit/integration testing**

---

## 🛠 Tech Stack

- **Language:** Java (17)
- **Framework:** Spring Boot
- **Build Tool:** Maven
- **Persistence:** Spring Data JPA
- **Security:** Spring Security
- **Database:** MySQL

---

## ✅ Prerequisites

- Java 8 or newer
- Maven installed
- A running database (if the project uses one)

---

## 🚀 Getting Started

### 1) Clone
```bash
git clone https://github.com/TanmayShadow/odoo.git
cd odoo
```

### 2) Build
```bash
./mvnw clean install
```

### 3) Run
```bash
./mvnw spring-boot:run
```
Or run the generated JAR:
```bash
java -jar target/*.jar
```

> The server typically starts on `http://localhost:8080` (unless overridden).

---

## ⚙️ Configuration

All configuration lives in `src/main/resources/application.properties`.  
Create a local override file (e.g., `application-local.properties`) or export environment variables to avoid hardcoding secrets.

**Example (`application.properties`):**
```properties
# Server
server.port=8080

# Database (example: PostgreSQL)
spring.datasource.url=jdbc:postgresql://localhost:5432/odoo
spring.datasource.username=${DB_USER}
spring.datasource.password=${DB_PASSWORD}
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=false

# Security (example)
app.jwt.secret=${JWT_SECRET}
```

> ✅ Put real secrets in environment variables (e.g., `DB_USER`, `DB_PASSWORD`, `JWT_SECRET`) and **do not commit** them to Git.

---

## 🤝 Contributing

Contributions, issues, and feature requests are welcome!
1. Fork the repo
2. Create a feature branch: `git checkout -b feat/your-feature`
3. Commit your changes: `git commit -m "feat: add your feature"`
4. Push to the branch: `git push origin feat/your-feature`
5. Open a Pull Request

---

## 💻 Frontend

**https://github.com/MrudulAhirrao/skill-swap.git**

---

## 👤 Author

**Tanmay Shindkar** — [@TanmayShadow](https://github.com/TanmayShadow)
