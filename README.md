# Practo App

## Project Overview
Practo App is a healthcare management system developed using Spring Boot. The application allows patients to search doctors, book appointments, make payments, upload medical reports, and communicate with doctors securely.

---

## Features

- Doctor Registration & Login
- Patient Registration & Login
- JWT Authentication & Authorization
- Role-Based Access Control
- Doctor Search & Filtering
- Appointment Booking System
- Online Payment Integration
- Email Notification
- SMS Notification using Twilio
- Medical Report Upload
- Swagger API Documentation
- Global Exception Handling
- RESTful APIs
- Jenkins CI/CD Integration

---

## Technologies Used

### Backend
- Java 17
- Spring Boot
- Spring Security
- Hibernate
- JPA
- JWT Authentication
- Maven

### Database
- PostGreSQL

### Tools & DevOps
- Git
- GitHub
- Jenkins
- Docker
- Swagger UI
- Postman

### Cloud & APIs
- AWS S3
- Twilio API
- SendGrid API

---

## Project Architecture

```text
Controller → Service → Repository → Database
```

---

## API Documentation

Swagger URL:

```text
http://localhost:8080/swagger-ui/index.html
```

---

## Setup Steps

### Clone Repository

```bash
git clone https://github.com/nitesh2027/practo-app.git
```

### Open Project

```bash
cd practo-app
```

### Configure Database

Update `application.yml`:

```yaml
spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/practo_db
    username: root
    password: root
```

### Run Application

```bash
mvn spring-boot:run
```

---

## Future Enhancements

- AI Chatbot
- Video Consultation
- Kafka Notification Service
- Microservices Architecture
- Kubernetes Deployment

---

## Author

Nitesh Kumar

Java Developer
