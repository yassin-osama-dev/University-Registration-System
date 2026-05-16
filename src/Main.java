import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        try {

            // -----------------------------
            // Load system (loads courses from file)
            // -----------------------------
            RegistrationSystem system = new RegistrationSystem();

            // -----------------------------
            // Create student and professor
            // -----------------------------
            Student s1 = new Student("Yassin", "S1001", "yassin@gmail.com", "CS", 3.5);
            Professor p1 = new Professor("Dr Ahmed", "P2001", "ahmed@uni.com", "CS");

            // -----------------------------
            // Save to files
            // -----------------------------
            system.Add_Student(s1);
            system.Add_Proff(p1);

            // -----------------------------
            // Show available courses FROM FILE
            // -----------------------------
            System.out.println("=== Available Courses ===");
            for (Courses c : system.courses) {
                System.out.println(c.getCourseCode() + " - " + c.getTitle() +
                        " (" + c.getCredits() + " credits)");
            }

            // -----------------------------
            // Pick courses from system (NOT new ones)
            // -----------------------------
            if (system.courses.size() >= 2) {

                Courses c1 = system.courses.get(0);
                Courses c2 = system.courses.get(1);

                // Professor assigns courses
                p1.assignCourses(c1);
                p1.assignCourses(c2);

                // Student registers
                System.out.println("\n=== Student Registration ===");
                s1.registerCourse(c1, "Fall 2026");
                s1.registerCourse(c2, "Fall 2026");

                // duplicate test
                s1.registerCourse(c1, "Fall 2026");
            } else {
                System.out.println("Not enough courses in file.");
            }

            // -----------------------------
            // Display results
            // -----------------------------
            System.out.println("\n=== Student Profile ===");
            s1.displayProfile();

            System.out.println("\n=== Student Courses ===");
            s1.viewCourse();

            System.out.println("\n=== Professor Courses ===");
            p1.viewTeachingCourses();

            System.out.println("\nTotal Credits: " + s1.CalculateCredithours());

            // -----------------------------
            // System-level test (still incomplete method)
            // -----------------------------
            System.out.println("\n=== System enroll test ===");
            if (system.courses.size() > 0) {
                system.enrollStudent(s1, system.courses.get(0));
            }

        } catch (IOException e) {
            System.out.println("File error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
