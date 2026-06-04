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

    RegistrationSystem() throws IOException {
        ensureDataFiles();
        loadData();
    }

    private void ensureDataFiles() throws IOException {
        if (!student_file.exists()) {
            student_file.createNewFile();
        }
        if (!proff_file.exists()) {
            proff_file.createNewFile();
        }
        if (!course_file.exists()) {
            course_file.createNewFile();
        }
    }

    public void Add_Student(Student student) throws IOException {
        students.add(student);
        saveStudents();
    }

    public void saveStudents() throws IOException {
        PrintWriter out = new PrintWriter(new FileWriter(student_file, false));
        for (Student student : students) {
            out.println(student.toString());
        }
        out.close();
    }

    public void Add_Proff(Professor proffessor) throws IOException {
        PrintWriter out = new PrintWriter(new FileWriter(proff_file, true));
        String Proff_info = proffessor.toString();
        out.println(Proff_info);
        out.close();
        professors.add(proffessor);
    }

    public void enrollStudent(Student student, Courses course) throws Exception {
        if (!student.registerCourse(course)) {
            throw new Exception("Could not enroll " + student.getName() + " in " + course.getTitle());
        }
        saveCourses();
        saveStudents();
    }

    public void dropStudent(Student student, Courses course) throws Exception {
        if (!student.DropSubject(course)) {
            throw new Exception("Could not drop " + student.getName() + " from " + course.getTitle());
        }
        saveCourses();
        saveStudents();
    }

    public void dropStudentByProfessor(Professor professor, Student student, Courses course) throws Exception {
        if (!isProfessorAssignedToCourse(professor, course)) {
            throw new Exception(professor.getName() + " is not assigned to " + course.getTitle());
        }

        if (!isStudentRegisteredInCourse(student, course)) {
            throw new Exception(student.getName() + " is not registered in " + course.getTitle());
        }

        dropStudent(student, course);
    }

    public void assignProfessorToCourse(Professor professor, Courses course) {
        professor.assignCourses(course);
    }

    public boolean isProfessorAssignedToCourse(Professor professor, Courses course) {
        for (Courses teachingCourse : professor.getTeachingCourses()) {
            if (teachingCourse.getCourseCode().equals(course.getCourseCode())) {
                return true;
            }
        }
        return false;
    }

    public boolean isStudentRegisteredInCourse(Student student, Courses course) {
        for (Courses registeredCourse : student.getRegisteredCourses()) {
            if (registeredCourse.getCourseCode().equals(course.getCourseCode())) {
                return true;
            }
        }
        return false;
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

    public void loadData() throws IOException {
        loadCourses();
        loadStudents();
        loadProfessors();
    }

    public void loadCourses() throws IOException {
        courses.clear();
        Scanner scanner = new Scanner(course_file);
        while (scanner.hasNext()) {
            String line = scanner.nextLine().trim();
            if (line.isEmpty()) {
                continue;
            }
            String[] parts = line.split(",", -1);
            if (parts.length < 4) {
                continue;
            }
            String course_code = parts[0];
            String title = parts[1];
            int credits;
            int seats;
            try {
                credits = Integer.parseInt(parts[2]);
                seats = Integer.parseInt(parts[3]);
            } catch (NumberFormatException e) {
                continue;
            }
            Courses course = new Courses(course_code, title, credits);
            course.setSeats(seats);

            // read one or more prerequisites if they exist
            for (int i = 4; i < parts.length; i++) {
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
            Student student = new Student(name, id, email, major, gpa);
            for (int i = 5; i < parts.length; i++) {
                String courseCode = parts[i].trim();
                if (!courseCode.isEmpty()) {
                    Courses course = findCourseByCode(courseCode);
                    if (course != null) {
                        student.addEnrollment(course);
                    }
                }
            }
            students.add(student);
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

    public void saveCourses() throws IOException {
        PrintWriter out = new PrintWriter(new FileWriter(course_file, false));
        for (Courses course : courses) {
            StringBuilder line = new StringBuilder();
            line.append(course.getCourseCode()).append(",");
            line.append(course.getTitle()).append(",");
            line.append(course.getCredits()).append(",");
            line.append(course.getSeats());
            
            ArrayList<String> prerequisites = course.getPrerequisites();
            for (String prerequisite : prerequisites) {
                line.append(",").append(prerequisite);
            }
            
            out.println(line.toString());
        }
        out.close();
    }
}
