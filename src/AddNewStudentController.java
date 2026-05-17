import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

import java.io.FileNotFoundException;
import java.io.IOException;

public class AddNewStudentController implements FxmlController {
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

    private RegistrationSystem registrationSystem;

    @FXML
    private void initialize() throws FileNotFoundException {
        if (registrationSystem == null) {
            registrationSystem = new RegistrationSystem();
        }
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
            clearFields();
        } catch (IOException e) {
            showAlert(Alert.AlertType.ERROR, "Save failed", "Could not save the student: " + e.getMessage());
        }
    }

    @FXML
    private void backToStudentMenu(ActionEvent event) throws IOException {
        switchScene(event, "Student.fxml");
    }

    private void clearFields() {
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
    }

    @Override
    public RegistrationSystem getRegistrationSystem() {
        return registrationSystem;
    }
}
