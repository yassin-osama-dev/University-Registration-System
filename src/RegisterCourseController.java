import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

import java.io.FileNotFoundException;
import java.io.IOException;

public class RegisterCourseController implements FxmlController {
    @FXML
    private TextField studentIdField;

    @FXML
    private TextField courseCodeField;

    private RegistrationSystem registrationSystem;

    @FXML
    private void initialize() throws FileNotFoundException {
        if (registrationSystem == null) {
            registrationSystem = new RegistrationSystem();
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
    }

    @Override
    public RegistrationSystem getRegistrationSystem() {
        return registrationSystem;
    }
}
