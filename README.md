# 📱 mobile-test-automation-framework

This repository contains a **structured mobile automation framework** for Android and iOS applications using **Appium** and **TestNG**. 

The framework is designed for **E2E, Unit, and DB testing**, with **fixture-driven data, step-wise logging**, and **Allure reporting** support.

# ✨ Features

1. Cross-Platform Support: Android & iOS
2. Test Types:
    - Unit Tests (pages & DB negative scenarios)
    - E2E Flows
3. Fixture-Driven Test Data:
    - All test data is maintained outside tests using POJOs and Fixture templates.
4. Database Helpers:
    - DB cleanup and setup for test scenarios.
5. Page Object Model:
    - Each screen represented as a page class.
    - Built-in logging and screenshot capture for Allure.
6. Utilities:
    - LoggerUtils with Allure reporting support
    - WaitUtils for robust element handling
    - MobileActions for common gestures
7. Reporting:
    - Allure TestNG reporting with step-wise logs and screenshots.

```
src
├── main
│   ├── java
│   │   ├── base             # BaseTest, BasePage, FixtureLoader
│   │   ├── core
│   │   │   ├── db           # GarageDbHelper, DbUtils, GarageDbConstants
│   │   │   ├── driver       # DriverFactory, DriverManager
│   │   │   └── utils        # LoggerUtils, WaitUtils, MobileActions
│   │   ├── dataTemplates    # Fixture templates for test data
│   │   └── dataTemplatesPojo # POJOs for test data
│
└── test
    └── java
        ├── unit
        │   ├── pages           # Unit tests for page classes
        │   └── db              # Unit tests for DB helpers
        └── E2ETest              # End-to-end flows

```
# Test Execution

## 🔧 Prerequisites
- Java 11+
- Maven
- Appium server
- Emulator or real device connected

## 🚀 Run E2E Tests

Android:
```mvn clean test -DsuiteXmlFile=src/testng/android-e2e.xml```

iOS:
```mvn clean test -DsuiteXmlFile=src/testng/ios-e2e.xml```

## 🧪 Run Unit Tests

Android:
```mvn clean test -DsuiteXmlFile=src/testng/android-unit.xml```

iOS:
```mvn clean test -DsuiteXmlFile=src/testng/ios-unit.xml```

## 🧩 Fixtures and Test Data
  - All test data is defined in POJOs (dataTemplatesPojo) and Fixture templates (dataTemplates).
  - Examples:
    - LoginDetailsTemplate → valid/invalid login data
    - VehicleDetailsTemplate → valid/invalid vehicle data

## 📝 Logging & Reporting
- LoggerUtils provides step-wise logs with timestamps.
- Allure Reporting captures:
    - Step-wise logs
    - Screenshots for critical actions
    - Test status (PASS/FAIL)
- Screenshots and logs are automatically attached during test execution.

## 💾 Database Support
- GarageDbHelper and DbUtils allow:
  - Cleaning up user data before tests
  - Setting up vehicle/owner details
  - Querying DB for validation
- Supports MySQL for Android/iOS test scenarios.

## 📄 Page Objects
- Page Object Model is used for all screens.Pages include:
  - LoginPage
  - HomePage
  - GarageHomePage
- Built-in LoggerUtils and MobileActions for:
  - Clicks, sendKeys, scroll, and waits
  - Screenshot capture for reporting

## 🔄 Sample E2E Flow
1. Clean user DB (GarageDbHelper.resetUserDataForAddingVehicle)
2. Setup vehicle/owner details in DB
3. Login using mobile number
4. Navigate to Garage
5. Add vehicle
6. Validate vehicle addition
7. Logs and screenshots captured in Allure

## 📊 Allure Reporting
1. Install Allure CLI: https://docs.qameta.io/allure/
2. Generate report:
```allure serve target/allure-results```
3. Report includes step logs, screenshots, and test status.


