import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

class RegistrationSystem {
    ArrayList<Courses> courses = new ArrayList<>();
    ArrayList<Student> students = new ArrayList<>();
    ArrayList<Professor> professors = new ArrayList<>();
    java.io.File student_file = new java.io.File("student.txt");
    java.io.File proff_file = new java.io.File("proff.txt");
    java.io.File course_file = new java.io.File("Courses.txt");

    RegistrationSystem() throws FileNotFoundException {
        loadData();
    }

    public void Add_Student(Student student) throws IOException {
        PrintWriter out = new PrintWriter(new FileWriter(student_file, true));
        String student_info = student.toString();
        out.println(student_info);
        out.close();
        students.add(student);
    }
    public void Add_Proff(Professor proffessor) throws IOException {
        PrintWriter out = new PrintWriter(new FileWriter(proff_file, true));
        String Proff_info = proffessor.toString();
        out.println(Proff_info);
        out.close();
        professors.add(proffessor);
    }

    public void enrollStudent(Student student, Courses course) throws Exception {
        if (!student.registerCourse(course, "Fall 2026")) {
            throw new Exception("Could not enroll " + student.getName() + " in " + course.getTitle());
        }
    }

    public void dropStudent(Student student, Courses course) throws Exception {
        if (!student.DropSubject(course)) {
            throw new Exception("Could not drop " + student.getName() + " from " + course.getTitle());
        }
    }

    public void assignProfessorToCourse(Professor professor, Courses course) {
        professor.assignCourses(course);
    }

    public Student findStudentById(String id) {
        for (Student student : students) {
            if (student.getID().equals(id)) {
                return student;
            }
        }
        return null;
    }

    public Professor findProfessorById(String id) {
        for (Professor professor : professors) {
            if (professor.getID().equals(id)) {
                return professor;
            }
        }
        return null;
    }

    public Courses findCourseByCode(String code) {
        for (Courses course : courses) {
            if (course.getCourseCode().equals(code)) {
                return course;
            }
        }
        return null;
    }

    public void loadData() throws FileNotFoundException {
        Subject();
        loadStudents();
        loadProfessors();
    }

    public void Subject() throws FileNotFoundException {
        courses.clear();
        Scanner scanner = new Scanner(course_file);
        while (scanner.hasNext()) {
            String line = scanner.nextLine().trim();
            if (line.isEmpty()) {
                continue;
            }
            String[] parts = line.split(",", -1);
            String course_code = parts[0];
            String title = parts[1];
            int credits = Integer.parseInt(parts[2]);
            Courses course = new Courses(course_code, title, credits);

            // read one or more prerequisites if they exist
            for (int i = 3; i < parts.length; i++) {
                if (!parts[i].isEmpty()) {
                    course.addPrerequisite(parts[i]);
                }
            }

            courses.add(course);
        }
        scanner.close();
    }

    private void loadStudents() throws FileNotFoundException {
        students.clear();
        Scanner scanner = new Scanner(student_file);
        while (scanner.hasNext()) {
            String line = scanner.nextLine().trim();
            if (line.isEmpty()) {
                continue;
            }
            String[] parts = line.split(",", -1);
            if (parts.length < 5) {
                continue;
            }
            String id = parts[0];
            String name = parts[1];
            String email = parts[2];
            String major = parts[3];
            double gpa = Double.parseDouble(parts[4]);
            students.add(new Student(name, id, email, major, gpa));
        }
        scanner.close();
    }

    private void loadProfessors() throws FileNotFoundException {
        professors.clear();
        Scanner scanner = new Scanner(proff_file);
        while (scanner.hasNext()) {
            String line = scanner.nextLine().trim();
            if (line.isEmpty()) {
                continue;
            }
            String[] parts = line.split(",", -1);
            if (parts.length < 3) {
                continue;
            }
            String id = parts[0];
            String name = parts[1];
            String email = parts[2];
            String department = "";
            if (parts.length > 3) {
                department = parts[3];
            }
            professors.add(new Professor(name, id, email, department));
        }
        scanner.close();
    }



}
