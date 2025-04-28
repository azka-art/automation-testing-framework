# Automation Testing Framework

This is a comprehensive automation testing framework for both API and UI Web testing, built with Java.

## Features

- API Testing with REST Assured
- UI Testing with Selenium WebDriver
- Modular structure
- Configuration management
- Logging with Log4j2
- Reporting with Allure
- Test management with TestNG

## Project Structure

```
automation-testing-framework/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── azka/
│   │   │           ├── api/       # API Testing helpers
│   │   │           ├── ui/        # UI Testing helpers
│   │   │           └── util/      # Utility classes
│   │   └── resources/            # Configuration files
│   └── test/
│       ├── java/
│       │   └── com/
│       │       └── azka/
│       │           ├── api/      # API Tests
│       │           └── ui/       # UI Tests
│       └── resources/           # Test resources
└── .github/
    └── workflows/               # CI/CD pipelines
```

## Getting Started

1. Install dependencies: mvn clean install  
2. Run tests: mvn test  
3. Generate Allure report: mvn allure:report  

## CI/CD Integration

Workflow ada di .github/workflows/main.yml.

## Requirements

- Java 11+  
- Maven 3.6.3+  
- Chrome/Firefox/Edge (for UI tests)
