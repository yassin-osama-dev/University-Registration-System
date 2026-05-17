import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.FileNotFoundException;
import java.io.IOException;

public class StudentController implements FxmlController {
    private RegistrationSystem registrationSystem;

    @FXML
    private void initialize() throws FileNotFoundException {
        if (registrationSystem == null) {
            registrationSystem = new RegistrationSystem();
        }
    }

    @FXML
    private void backToLogin(ActionEvent event) throws IOException {
        switchScene(event, "login.fxml");
    }

    @FXML
    private void openRegisterCourse(ActionEvent event) throws IOException {
        switchScene(event, "Register Course.fxml");
    }

    @FXML
    private void openAddNewStudent(ActionEvent event) throws IOException {
        switchScene(event, "Add New Student.fxml");
    }

    @FXML
    private void openShowCourses(ActionEvent event) throws IOException {
        switchScene(event, "Show_Courses.fxml");
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
