# University Registration System

A Java-based university registration system built using Object-Oriented Programming principles and JavaFX. The system allows students and professors to manage courses, enrollments, and academic workflows through a graphical user interface.

---

## Features

### Student Features
- Student course registration
- Course dropping functionality
- Credit-hour limit validation
- Prerequisite course validation
- Seat-capacity management
- View registered courses
- Student profile management

### Professor Features
- Professor course assignment
- View teaching courses
- Professor profile management

### System Features
- Login system for students and professors
- Persistent file-based storage
- Dynamic course loading from text files
- JavaFX graphical user interface
- Scene switching and controller-based frontend handling

---

## Object-Oriented Programming Concepts Used

The project was designed using core OOP principles:

| Concept | Implementation |
|---|---|
| Inheritance | `Student` and `Professor` inherit from `Person` |
| Abstraction | `Person` implemented as an abstract class |
| Composition | `Student` contains `Enrollment` objects |
| Aggregation | `Professor` is associated with independent `Courses` objects |
| Encapsulation | Private fields with getters/setters |

---

## Project Structure

```text
UniversityRegistrationSystem/
│
├── Main.java
├── RegistrationSystem.java
├── Person.java
├── Student.java
├── Professor.java
├── Courses.java
├── Enrollment.java
│
├── GUI/
│   ├── Login.fxml
│   ├── Student.fxml
│   ├── Professor.fxml
│   └── Controllers...
│
├── student.txt
├── proff.txt
├── Courses.txt
└── ...