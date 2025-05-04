# EGTproject

Automation test framework for UI and API validation built with Java, TestNG, RestAssured, Selenium, and Spring Boot.

## Project Purpose

This project provides a modular, maintainable, and scalable automation testing framework for validating both Web UI components and RESTful APIs.
It includes advanced features like Allure reporting, Spring Boot configuration management, Page Object Model structure, and data-driven test capabilities.

---

## 🚀 Technologies Used

- Java 20
- Maven
- TestNG
- Selenium WebDriver
- RestAssured
- Spring Boot
- Allure Report
- Lombok
- AssertJ
- Logback & SLF4J
- JavaFaker

---

## ⚙️ How to Run Tests

### Run All Tests

### Run UI or API Specific Suite
```bash
mvn clean test -DsuiteXmlFile=FullUiTestSuite.xml
mvn clean test -DsuiteXmlFile=FullApiTestSuite.xml
```

---

## 📊 Generate Allure Report

### 1. After running the tests:
```bash
mvn allure:serve
```

This command will start a local server and open the Allure report in your browser.

---

## ✅ Useful Maven Profiles

```bash
mvn test -Pdev     # Run tests with dev profile
mvn test -Ptest    # Run tests with test profile
```

---

## ✍️ Author

Tihomir Dimitrov  
Test Automation Engineer

