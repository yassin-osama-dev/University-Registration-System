import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.FileNotFoundException;
import java.io.IOException;

public class ShowCoursesController implements FxmlController {
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
        codeColumn.setCellValueFactory(new PropertyValueFactory<>("courseCode"));
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        creditsColumn.setCellValueFactory(new PropertyValueFactory<>("credits"));
        registeredColumn.setCellValueFactory(cellData ->
                new SimpleIntegerProperty(calculateRegisteredStudents(cellData.getValue())).asObject());
        seatsColumn.setCellValueFactory(cellData ->
                new SimpleIntegerProperty(calculateSeatsLeft(cellData.getValue())).asObject());

        if (registrationSystem == null) {
            registrationSystem = new RegistrationSystem();
        }
        showCourses();
    }

    public void showCourses() {
        coursesTable.getItems().setAll(registrationSystem.courses);
        coursesTable.refresh();
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

    @FXML
    private void backToStudentMenu(ActionEvent event) throws IOException {
        switchScene(event, "Student.fxml");
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
