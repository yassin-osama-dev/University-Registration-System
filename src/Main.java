public class Main {

    public static void main(String[] args) {

        try {

            // Create registration system
            RegistrationSystem system = new RegistrationSystem();

            // Create students
            Student s1 = new Student(
                    "Yassin Osama",
                    "2025001",
                    "yassin@gmail.com",
                    "Computer Science",
                    3.7
            );

            Student s2 = new Student(
                    "Ahmed Ali",
                    "2025002",
                    "ahmed@gmail.com",
                    "Software Engineering",
                    3.4
            );

            // Add students to file
            system.Add_Student(s1);
            system.Add_Student(s2);

            System.out.println("Students added successfully!");
            System.out.println();

            // Display student profiles
            System.out.println("=== STUDENT 1 ===");
            s1.displayProfile();

            System.out.println();

            System.out.println("=== STUDENT 2 ===");
            s2.displayProfile();

            System.out.println();

            // Display loaded courses
            System.out.println("=== COURSES ===");

            for (Courses c : system.courses) {

                System.out.println("Course Code: " + c.courseCode);
                System.out.println("Title: " + c.title);
                System.out.println("Credits: " + c.credits);

                System.out.println("-------------------");
            }

        }

        catch (Exception e) {

            System.out.println("Error: " + e.getMessage());

        }
    }
}