import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
class RegistrationSystem {
    java.io.File student_file = new java.io.File("student.txt");
    java.io.File proff_file = new java.io.File("proff.txt");
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
}