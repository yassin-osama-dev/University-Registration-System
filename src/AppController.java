import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
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
    }

    @FXML
    private void openStudentView(ActionEvent event) throws IOException {
        switchScene(event, "Student.fxml");
    }

    @FXML
    private void openProfessorView() {
        showAlert(Alert.AlertType.INFORMATION, "Professor", "Professor screen is not available yet.");
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
        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Enrollment failed", e.getMessage());
        }
    }

    @FXML
    private void backToStudentMenu(ActionEvent event) throws IOException {
        switchScene(event, "Student.fxml");
    }

    public void showCourses() {
        coursesTable.getItems().setAll(registrationSystem.courses);
        coursesTable.refresh();
    }

    private void setupCoursesTable() {
        codeColumn.setCellValueFactory(new PropertyValueFactory<>("courseCode"));
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        creditsColumn.setCellValueFactory(new PropertyValueFactory<>("credits"));
        registeredColumn.setCellValueFactory(cellData ->
                new SimpleIntegerProperty(calculateRegisteredStudents(cellData.getValue())).asObject());
        seatsColumn.setCellValueFactory(cellData ->
                new SimpleIntegerProperty(calculateSeatsLeft(cellData.getValue())).asObject());
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
    }

    @Override
    public RegistrationSystem getRegistrationSystem() {
        return registrationSystem;
    }
}
