import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;

import java.io.IOException;

public class LoginController implements FxmlController {
    private RegistrationSystem registrationSystem;

    @FXML
    public void initialize() throws IOException {
        if (registrationSystem == null) {
            registrationSystem = new RegistrationSystem();
        }
    }

    @FXML
    private void openStudentView(ActionEvent event) throws IOException {
        switchScene(event, "Student.fxml");
    }

    @FXML
    private void openProfessorView() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Professor");
        alert.setHeaderText(null);
        alert.setContentText("Professor screen is not available yet.");
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
