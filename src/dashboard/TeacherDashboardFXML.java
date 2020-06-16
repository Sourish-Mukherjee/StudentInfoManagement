package dashboard;

import authentication.LoginController;
import authentication.RegisterFXMLController;
import database.DataBaseHelper;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.ResultSet;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class TeacherDashboardFXML implements Initializable {
    @FXML
    private Label teaDashEmail;

    @FXML
    private Label teaDashName;

    @FXML
    private ImageView imageView;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            imageView.setImage(new Image(new FileInputStream("E:\\Java\\Projects\\StudentInfoManagement\\" +
                    "resources\\images\\teacher_dashboard_icon.png")));
            String email = LoginController.getEmaiL();
            teaDashEmail.setText(email);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    @FXML
    protected void openTeacherStudentInfo() throws IOException {
        Stage stage = new Stage();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/fxmlPackage/TeacherStudentInfoFXML.fxml"))));
        stage.show();
        stage.setTitle("Student-Information Panel");
    }

    @FXML
    protected void openTeacherMarks() throws IOException {
        Stage stage = new Stage();
        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/fxmlpackage/TeacherMarksFXML.fxml")));
        scene.getStylesheets().add(getClass().getResource("/cssPackage/teacherMarks.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
        stage.setTitle("Student-Marks Portal");
    }

    @FXML
    protected void openTeacherAttend() throws IOException {
        Stage stage = new Stage();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/fxmlpackage/TeacherAttendanceFXML.fxml"))));
        stage.setTitle("Attendance-Panel");
        stage.show();
    }
}
