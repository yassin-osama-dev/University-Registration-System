import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public interface FxmlController {
    void setRegistrationSystem(RegistrationSystem registrationSystem);

    RegistrationSystem getRegistrationSystem();

     default void switchScene(ActionEvent event, String fxmlFile) throws IOException {
        FXMLLoader loader = new FXMLLoader(findFxml(fxmlFile));
        Parent root = loader.load();

        Object controller = loader.getController();
        if (controller instanceof FxmlController fxmlController) {
            fxmlController.setRegistrationSystem(getRegistrationSystem());
        }

        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        currentStage.setScene(new Scene(root));
        currentStage.show();
    }

    private static URL findFxml(String fxmlFile) throws IOException {
        URL resource = FxmlController.class.getResource(fxmlFile);
        if (resource != null) {
            return resource;
        }

        File file = new File("src", fxmlFile);
        if (file.exists()) {
            return file.toURI().toURL();
        }

        throw new IOException("Could not find " + fxmlFile);
    }
}
