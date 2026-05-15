# University Registration System (OOP Project)

## Overview

This project is a simple University Registration System implemented in Java. It demonstrates core Object-Oriented Programming (OOP) principles such as:

* Inheritance
* Aggregation
* Encapsulation
* Basic file handling (persistence)

The system allows:

* Adding students and professors
* Loading courses from a file
* Registering students into courses
* Assigning courses to professors
* Tracking enrollments

---

## OOP Design

### 1. Inheritance

The system uses inheritance through the `Person` class:

* `Student` extends `Person`
* `Professor` extends `Person`

This allows shared attributes like:

* ID
* Name
* Email

---

### 2. Aggregation

Aggregation is used in multiple places:

* `Student` → holds `ArrayList<Enrollment>`
* `Professor` → holds `ArrayList<Courses>`

These objects can exist independently from their owners.

---

### 3. Composition (Partial)

* `Enrollment` contains a `Courses` object

This represents a strong dependency relationship inside the enrollment structure.

---

## Key Features

* Student course registration with credit limit check
* Course capacity limit (30 students)
* File-based storage for students and professors
* Course loading from external text file
* Professor course assignment

---

## Limitations

* No course drop functionality
* No grade processing logic
* Enrollment is not persisted to file
* No bidirectional mapping between courses and students

---

## Conclusion

This project demonstrates strong foundational OOP principles and is suitable for a university-level coursework assignment. It models a simplified academic registration system with persistent storage and object relationships.
