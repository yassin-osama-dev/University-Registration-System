import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {

        // Create registration system
        RegistrationSystem rs = new RegistrationSystem();

        // Create student
        Student s1 = new Student(
                "Yassin",
                "2025001",
                "yassin@gmail.com",
                "Computer Science",
                3.7
        );

        // Add courses to student
        List<String> courses = new LinkedList<>();

        courses.add("OOP");
        courses.add("Data Structures");
        courses.add("Database");

        s1.setCourse(courses);

        // Create professor
        Professor p1 = new Professor(
                "Ahmed Ali",
                "P100",
                "ahmed@university.com",
                "Computer Science"
        );

        // Display profiles
        System.out.println("===== STUDENT PROFILE =====");
        s1.displayProfile();

        System.out.println();

        System.out.println("===== PROFESSOR PROFILE =====");
        p1.displayProfile();

        System.out.println();

        // Save to files
        rs.Add_Student(s1);

        rs.Add_Proff(p1);

        System.out.println("Data saved successfully!");

        // Print courses
        System.out.println();

        System.out.println("===== STUDENT COURSES =====");

        for (String course : s1.getCourse()) {
            System.out.println(course);
        }

        // Test toString()
        System.out.println();

        System.out.println("===== TOSTRING TEST =====");

        System.out.println(s1.toString());

        System.out.println(p1.toString());
    }
}