# Taskly

A simple and efficient task management application written in Java/JavaScript using Spring Boot and React.

## Description

Taskly helps you organize and manage your daily tasks with ease. Keep track of your to-dos, deadlines, and progress all in one place. This project is currently in development.

## Requirements

- Backend:
  - Java 17 or higher
  - Maven 3.8+
  - Spring Boot 3.x
- Frontend:
  - Node.js 18+
  - npm 9+ or yarn 1.22+

## Installation

1. Clone the repository
   ```bash
   git clone https://github.com/MicheleGrieco/taskly.git
   ```

2. Navigate to project directory
   ```bash
   cd taskly
   ```

3. Build and run the backend
   ```bash
   mvn clean install
   mvn spring-boot:run
   ```

4. In a new terminal, start the frontend development server
   ```bash
   cd taskly-frontend
   npm start
   ```

The application will be available at:
- Backend: http://localhost:8080
- Frontend: http://localhost:3000

## Current Features

- Basic task management with:
  - Title and description for each task
  - Completion status tracking
  - Creation timestamp
  - Task ID for unique identification

## Features (Planned)

- User interface for task management
- Deadline tracking
- Priority levels
- Progress monitoring
- Task categorization
- User authentication
- Data persistence with database storage

## Technical Stack

Backend:
- Java
- Spring Boot
- JPA/Hibernate
- H2 Database (for development)

Frontend:
- React

## Project Structure

```
taskly/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── michelegrieco/
│   │   │           └── taskly/
│   │   │               ├── model/
│   │   │               ├── repository/
│   │   │               ├── service/
│   │   │               └── controller/
│   │   ├── resources/
│   │   └── webapp/
│   │       └── frontend/    # React application
│   │           ├── src/
│   │           ├── public/
│   │           └── package.json
│   └── test/
├── pom.xml
└── README.md
```

## API Endpoints

Coming soon!