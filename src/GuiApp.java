import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;

public class GuiApp extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(findFxml("Login.fxml"));

        stage.setTitle("University Registration System");
        stage.setScene(new Scene(root));
        stage.show();
    }

    private URL findFxml(String fileName) throws Exception {
        URL resource = GuiApp.class.getResource("/" + fileName);
        if (resource != null) {
            return resource;
        }

        File file = new File("src", fileName);
        if (file.exists()) {
            return file.toURI().toURL();
        }

        throw new IllegalStateException("Could not find " + fileName);
    }
}
