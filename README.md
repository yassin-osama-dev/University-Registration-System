# University Registration System

A simple University Registration System built using Java and Object-Oriented Programming (OOP) concepts.

## Features

- Add students
- Add professors
- Display profiles
- Store data in text files
- Manage student courses
- Demonstrates core OOP principles

---

# OOP Concepts Used

## Inheritance
- `Student` and `Professor` inherit from `Person`

## Abstraction
- `Person` is an abstract class
- `displayProfile()` is an abstract method

## Encapsulation
- Private attributes with getters and setters

## Polymorphism
- Method overriding using:
  - `displayProfile()`
  - `toString()`

---

# Classes

## Person.java
Abstract parent class containing:
- ID
- Name
- Email

## Student.java
Extends `Person` and contains:
- Major
- GPA
- List of courses

## Professor.java
Extends `Person` and contains:
- Department

## RegistrationSystem.java
Handles:
- Saving students to `student.txt`
- Saving professors to `proff.txt`

## Main.java
Tests the entire system.

---

# Example Output

## Student Profile

```text
Name: Yassin
ID: 2025001
Email: yassin@gmail.com
Major: Computer Science
gpa: 3.7
```

## Professor Profile

```text
Name: Ahmed Ali
ID: P100
Email: ahmed@university.com
Department: Computer Science
```

---

# Saved Files

## student.txt

```text
2025001,Yassin,yassin@gmail.com,Computer Science,3.7
```

## proff.txt

```text
P100,Ahmed Ali,ahmed@university.com,ComputerScience
```

---

# Technologies Used

- Java
- OOP
- File Handling
- Linked Lists

---

# Future Improvements

- Search functionality
- Delete/Edit functionality
- GUI
- Database integration
- Course registration system

---

# Author

Developed as a university OOP project.