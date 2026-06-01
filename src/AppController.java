import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.input.MouseButton;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.FileNotFoundException;
import java.io.IOException;

public class AppController implements FxmlController {
    @FXML
    private TextField nameField;

    @FXML
    private TextField idField;

    @FXML
    private TextField emailField;

    @FXML
    private TextField majorField;

    @FXML
    private TextField gpaField;

    @FXML
    private TextField departmentField;

    @FXML
    private TextField studentIdField;

    @FXML
    private TextField courseCodeField;

    @FXML
    private TableView<Courses> coursesTable;

    @FXML
    private TableColumn<Courses, String> codeColumn;

    @FXML
    private TableColumn<Courses, String> titleColumn;

    @FXML
    private TableColumn<Courses, Integer> creditsColumn;

    @FXML
    private TableColumn<Courses, Integer> registeredColumn;

    @FXML
    private TableColumn<Courses, Integer> seatsColumn;

    @FXML
    private TableView<Student> studentsTable;

    @FXML
    private TableColumn<Student, String> studentIdColumn;

    @FXML
    private TableColumn<Student, String> studentNameColumn;

    @FXML
    private TableColumn<Student, String> studentEmailColumn;

    @FXML
    private TableColumn<Student, String> studentMajorColumn;

    @FXML
    private TableColumn<Student, Double> studentGpaColumn;

    @FXML
    private TableColumn<Student, Integer> studentCreditsColumn;

    @FXML
    private TableColumn<Student, String> studentCoursesColumn;

    @FXML
    private TextField professorIdField;

    @FXML
    private TextField professorCourseCodeField;

    @FXML
    private TableView<Professor> professorsTable;

    @FXML
    private TableColumn<Professor, String> professorIdColumn;

    @FXML
    private TableColumn<Professor, String> professorNameColumn;

    @FXML
    private TableColumn<Professor, String> professorEmailColumn;

    @FXML
    private TableColumn<Professor, String> professorDepartmentColumn;

    @FXML
    private TableColumn<Professor, String> professorCoursesColumn;

    private RegistrationSystem registrationSystem;

    @FXML
    private void initialize() throws FileNotFoundException {
        if (registrationSystem == null) {
            registrationSystem = new RegistrationSystem();
        }

        if (coursesTable != null) {
            setupCoursesTable();
            showCourses();
        }

        if (studentsTable != null) {
            setupStudentsTable();
            showStudents();
        }

        if (professorsTable != null) {
            setupProfessorsTable();
            showProfessors();
        }
    }

    @FXML
    private void openStudentView(ActionEvent event) throws IOException {
        switchScene(event, "Student.fxml");
    }

    @FXML
    private void openProfessorView(ActionEvent event) throws IOException {
        switchScene(event, "Show_Professors.fxml");
    }

    @FXML
    private void backToLogin(ActionEvent event) throws IOException {
        switchScene(event, "Login.fxml");
    }

    @FXML
    private void openRegisterCourse(ActionEvent event) throws IOException {
        switchScene(event, "RegisterCourse.fxml");
    }

    @FXML
    private void openAddNewStudent(ActionEvent event) throws IOException {
        switchScene(event, "AddNewStudent.fxml");
    }

    @FXML
    private void openShowCourses(ActionEvent event) throws IOException {
        switchScene(event, "Show_Course.fxml");
    }

    @FXML
    private void openShowStudents(ActionEvent event) throws IOException {
        switchScene(event, "Show_Students.fxml");
    }

    @FXML
    private void openAssignProfessorCourses(ActionEvent event) throws IOException {
        switchScene(event, "Assign_Professor_Courses.fxml");
    }

    @FXML
    private void openAddProfessor(ActionEvent event) throws IOException {
        switchScene(event, "Add_Professor.fxml");
    }

    @FXML
    private void openDropStudent(ActionEvent event) throws IOException {
        switchScene(event, "Drop_student.fxml");
    }

    @FXML
    private void addStudent() {
        String name = nameField.getText().trim();
        String id = idField.getText().trim();
        String email = emailField.getText().trim();
        String major = majorField.getText().trim();
        String gpaText = gpaField.getText().trim();

        if (name.isEmpty() || id.isEmpty() || email.isEmpty() || major.isEmpty() || gpaText.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Missing input", "Enter all student details.");
            return;
        }

        if (registrationSystem.findStudentById(id) != null) {
            showAlert(Alert.AlertType.ERROR, "Duplicate student", "A student with ID " + id + " already exists.");
            return;
        }

        double gpa;
        try {
            gpa = Double.parseDouble(gpaText);
        } catch (NumberFormatException e) {
            showAlert(Alert.AlertType.ERROR, "Invalid GPA", "GPA must be a number.");
            return;
        }

        if (gpa < 0.0 || gpa > 4.0) {
            showAlert(Alert.AlertType.ERROR, "Invalid GPA", "GPA must be between 0.0 and 4.0.");
            return;
        }

        try {
            Student student = new Student(name, id, email, major, gpa);
            registrationSystem.Add_Student(student);
            showAlert(Alert.AlertType.INFORMATION, "Student added", name + " has been added to the system.");
            clearStudentFields();
        } catch (IOException e) {
            showAlert(Alert.AlertType.ERROR, "Save failed", "Could not save the student: " + e.getMessage());
        }
    }

    @FXML
    private void enrollStudentInCourse() {
        String studentId = studentIdField.getText().trim();
        String courseCode = courseCodeField.getText().trim();

        if (studentId.isEmpty() || courseCode.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Missing input", "Enter both student ID and course code.");
            return;
        }

        Student student = registrationSystem.findStudentById(studentId);
        if (student == null) {
            showAlert(Alert.AlertType.ERROR, "Student not found", "No student exists with ID: " + studentId);
            return;
        }

        Courses course = registrationSystem.findCourseByCode(courseCode);
        if (course == null) {
            showAlert(Alert.AlertType.ERROR, "Course not found", "No course exists with code: " + courseCode);
            return;
        }

        try {
            registrationSystem.enrollStudent(student, course);
            showAlert(Alert.AlertType.INFORMATION, "Enrollment complete",
                    student.getName() + " has been enrolled in " + course.getTitle() + ".");
            studentIdField.clear();
            courseCodeField.clear();
            if (coursesTable != null) {
                showCourses();
            }
        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Enrollment failed", e.getMessage());
        }
    }

    @FXML
    private void addProfessor() {
        String name = nameField.getText().trim();
        String id = idField.getText().trim();
        String email = emailField.getText().trim();
        String department = departmentField.getText().trim();

        if (name.isEmpty() || id.isEmpty() || email.isEmpty() || department.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Missing input", "Enter all professor details.");
            return;
        }

        if (!email.contains("@") || !email.contains(".")) {
            showAlert(Alert.AlertType.ERROR, "Invalid email", "Enter a valid professor email address.");
            return;
        }

        if (registrationSystem.findProfessorById(id) != null) {
            showAlert(Alert.AlertType.ERROR, "Duplicate professor", "A professor with ID " + id + " already exists.");
            return;
        }

        try {
            Professor professor = new Professor(name, id, email, department);
            registrationSystem.Add_Proff(professor);
            showAlert(Alert.AlertType.INFORMATION, "Professor added", name + " has been added to the system.");
            clearProfessorFields();
        } catch (IOException e) {
            showAlert(Alert.AlertType.ERROR, "Save failed", "Could not save the professor: " + e.getMessage());
        }
    }

    @FXML
    private void assignProfessorToCourse() {
        String professorId = professorIdField.getText().trim();
        String courseCode = professorCourseCodeField.getText().trim();

        if (professorId.isEmpty() || courseCode.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Missing input", "Enter both professor ID and course code.");
            return;
        }

        Professor professor = registrationSystem.findProfessorById(professorId);
        if (professor == null) {
            showAlert(Alert.AlertType.ERROR, "Professor not found", "No professor exists with ID: " + professorId);
            return;
        }

        Courses course = registrationSystem.findCourseByCode(courseCode);
        if (course == null) {
            showAlert(Alert.AlertType.ERROR, "Course not found", "No course exists with code: " + courseCode);
            return;
        }

        for (Courses assignedCourse : professor.getTeachingCourses()) {
            if (assignedCourse.getCourseCode().equals(course.getCourseCode())) {
                showAlert(Alert.AlertType.ERROR, "Already assigned",
                        professor.getName() + " is already assigned to " + course.getTitle() + ".");
                return;
            }
        }

        registrationSystem.assignProfessorToCourse(professor, course);
        showAlert(Alert.AlertType.INFORMATION, "Assignment complete",
                professor.getName() + " has been assigned to " + course.getTitle() + ".");
        professorIdField.clear();
        professorCourseCodeField.clear();
        if (professorsTable != null) {
            showProfessors();
        }
    }

    @FXML
    private void dropStudentByProfessor() {
        String professorId = professorIdField.getText().trim();
        String studentId = studentIdField.getText().trim();
        String courseCode = courseCodeField.getText().trim();

        if (professorId.isEmpty() || studentId.isEmpty() || courseCode.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Missing input", "Enter professor ID, student ID, and course code.");
            return;
        }

        Professor professor = registrationSystem.findProfessorById(professorId);
        if (professor == null) {
            showAlert(Alert.AlertType.ERROR, "Professor not found", "No professor exists with ID: " + professorId);
            return;
        }

        Student student = registrationSystem.findStudentById(studentId);
        if (student == null) {
            showAlert(Alert.AlertType.ERROR, "Student not found", "No student exists with ID: " + studentId);
            return;
        }

        Courses course = registrationSystem.findCourseByCode(courseCode);
        if (course == null) {
            showAlert(Alert.AlertType.ERROR, "Course not found", "No course exists with code: " + courseCode);
            return;
        }

        try {
            registrationSystem.dropStudentByProfessor(professor, student, course);
            showAlert(Alert.AlertType.INFORMATION, "Student dropped",
                    student.getName() + " has been dropped from " + course.getTitle() + ".");
            professorIdField.clear();
            studentIdField.clear();
            courseCodeField.clear();
            if (studentsTable != null) {
                showStudents();
            }
            if (coursesTable != null) {
                showCourses();
            }
        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Drop failed", e.getMessage());
        }
    }

    @FXML
    private void backToStudentMenu(ActionEvent event) throws IOException {
        switchScene(event, "Student.fxml");
    }

    @FXML
    private void backToProfessorMenu(ActionEvent event) throws IOException {
        switchScene(event, "Show_Professors.fxml");
    }

    public void showCourses() {
        coursesTable.getItems().setAll(registrationSystem.courses);
        coursesTable.refresh();
    }

    public void showStudents() {
        studentsTable.getItems().setAll(registrationSystem.students);
        studentsTable.refresh();
    }

    public void showProfessors() {
        professorsTable.getItems().setAll(registrationSystem.professors);
        professorsTable.refresh();
    }

    private void setupCoursesTable() {
        codeColumn.setCellValueFactory(new PropertyValueFactory<>("courseCode"));
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        creditsColumn.setCellValueFactory(new PropertyValueFactory<>("credits"));
        registeredColumn.setCellValueFactory(cellData ->
                new SimpleIntegerProperty(calculateRegisteredStudents(cellData.getValue())).asObject());
        seatsColumn.setCellValueFactory(cellData ->
                new SimpleIntegerProperty(cellData.getValue().getSeatsLeft()).asObject());
    }

    private void setupStudentsTable() {
        if (studentIdColumn != null) {
            studentIdColumn.setCellValueFactory(new PropertyValueFactory<>("ID"));
        }
        if (studentNameColumn != null) {
            studentNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        }
        if (studentEmailColumn != null) {
            studentEmailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        }
        if (studentMajorColumn != null) {
            studentMajorColumn.setCellValueFactory(new PropertyValueFactory<>("major"));
        }
        if (studentGpaColumn != null) {
            studentGpaColumn.setCellValueFactory(new PropertyValueFactory<>("gpa"));
        }
        if (studentCreditsColumn != null) {
            studentCreditsColumn.setCellValueFactory(cellData ->
                    new SimpleIntegerProperty(cellData.getValue().CalculateCredithours()).asObject());
        }
        if (studentCoursesColumn != null) {
            studentCoursesColumn.setCellValueFactory(cellData ->
                    new SimpleStringProperty(formatRegisteredCourses(cellData.getValue())));
        }


    }

    private void displayStudentProfile(Student student) {
        showAlert(Alert.AlertType.INFORMATION, "Student Profile", student.displayProfile());
    }

    private void setupProfessorsTable() {
        if (professorIdColumn != null) {
            professorIdColumn.setCellValueFactory(new PropertyValueFactory<>("ID"));
        }
        if (professorNameColumn != null) {
            professorNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        }
        if (professorEmailColumn != null) {
            professorEmailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        }
        if (professorDepartmentColumn != null) {
            professorDepartmentColumn.setCellValueFactory(new PropertyValueFactory<>("department"));
        }
        if (professorCoursesColumn != null) {
            professorCoursesColumn.setCellValueFactory(cellData ->
                    new SimpleStringProperty(formatTeachingCourses(cellData.getValue())));
        }


    }

    private void displayProfessorProfile(Professor professor) {
        showAlert(Alert.AlertType.INFORMATION, "Professor Profile", professor.displayProfile());
    }

    private String formatTeachingCourses(Professor professor) {
        if (professor.getTeachingCourses().isEmpty()) {
            return "None";
        }

        StringBuilder courses = new StringBuilder();
        for (Courses course : professor.getTeachingCourses()) {
            if (courses.length() > 0) {
                courses.append(", ");
            }
            courses.append(course.getCourseCode()).append(" - ").append(course.getTitle());
        }
        return courses.toString();
    }

    private String formatRegisteredCourses(Student student) {
        if (student.getRegisteredCourses().isEmpty()) {
            return "None";
        }

        StringBuilder courses = new StringBuilder();
        for (Courses course : student.getRegisteredCourses()) {
            if (courses.length() > 0) {
                courses.append(", ");
            }
            courses.append(course.getCourseCode()).append(" - ").append(course.getTitle());
        }
        return courses.toString();
    }

    private int calculateSeatsLeft(Courses course) {
        return 30 - calculateRegisteredStudents(course);
    }

    private int calculateRegisteredStudents(Courses course) {
        int registeredStudents = 0;

        for (Student student : registrationSystem.students) {
            for (Courses registeredCourse : student.getRegisteredCourses()) {
                if (registeredCourse.getCourseCode().equals(course.getCourseCode())) {
                    registeredStudents++;
                    break;
                }
            }
        }

        return registeredStudents;
    }

    private void clearStudentFields() {
        nameField.clear();
        idField.clear();
        emailField.clear();
        majorField.clear();
        gpaField.clear();
    }

    private void clearProfessorFields() {
        nameField.clear();
        idField.clear();
        emailField.clear();
        departmentField.clear();
    }

    private void showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @Override
    public void setRegistrationSystem(RegistrationSystem registrationSystem) {
        this.registrationSystem = registrationSystem;
        if (coursesTable != null) {
            showCourses();
        }
        if (studentsTable != null) {
            showStudents();
        }
        if (professorsTable != null) {
            showProfessors();
        }
    }


    @Override
    public RegistrationSystem getRegistrationSystem() {
        return registrationSystem;
    }
}
