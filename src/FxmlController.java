import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public interface FxmlController {
    void setRegistrationSystem(RegistrationSystem registrationSystem);

    RegistrationSystem getRegistrationSystem();

    default void switchScene(ActionEvent event, String fxmlFile) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
        Parent root = loader.load();

        Object controller = loader.getController();
        if (controller instanceof FxmlController fxmlController) {
            fxmlController.setRegistrationSystem(getRegistrationSystem());
        }

        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        currentStage.close();

        Stage newStage = new Stage();
        newStage.setScene(new Scene(root));
        newStage.show();
    }
}
