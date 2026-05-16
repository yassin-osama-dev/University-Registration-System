import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

class RegistrationSystem {
    ArrayList<Courses> courses = new ArrayList<>();
    java.io.File student_file = new java.io.File("student.txt");
    java.io.File proff_file = new java.io.File("proff.txt");
    java.io.File course_file = new java.io.File("Courses.txt");
    Scanner scanner=new Scanner(course_file);

    RegistrationSystem() throws FileNotFoundException {
        Subject();
    }

    public void Add_Student(Student student) throws IOException {
        PrintWriter out = new PrintWriter(new FileWriter(student_file, true));
        String student_info = student.toString();
        out.println(student_info);
        out.close();
    }
    public void Add_Proff(Professor proffessor) throws IOException {
        PrintWriter out = new PrintWriter(new FileWriter(proff_file, true));
        String Proff_info = proffessor.toString();
        out.println(Proff_info);
        out.close();
    }
    public void Subject() throws FileNotFoundException {
        courses.clear();
        Scanner scanner=new Scanner(course_file);
        while(scanner.hasNext())
        {
            String line = scanner.nextLine();
            String[] parts = line.split(",");
            String course_code=parts[0];
            String title=parts[1];
            int credits=Integer.parseInt(parts[2]);
            courses.add(new Courses(course_code,title,credits));
        }
    }

    public void enrollStudent(Student student, Courses course) throws Exception {
    if (!course.add())
        throw new Exception(course.getTitle() + " is full!");
    }
}