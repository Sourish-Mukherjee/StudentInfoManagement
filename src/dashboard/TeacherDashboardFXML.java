package dashboard;

import authentication.LoginController;
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
import java.net.URL;
import java.util.ResourceBundle;

/*
    Author: Sourish Mukherjee
    Link: https://github.com/Sourish-Mukherjee/StudentInfoManagement
 */

// This class is the backend part of Teacher Dashboard Screen

public class TeacherDashboardFXML implements Initializable {
    @FXML
    private Label teaDashEmail;

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

    // Opens The Information Screen panel after clicking the information button
    @FXML
    protected void openTeacherStudentInfo() throws IOException {
        Stage stage = new Stage();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/fxmlPackage/TeacherStudentInfoFXML.fxml"))));
        stage.show();
        stage.setTitle("Student-Information Panel");
    }

    // Opens The Marks Screen panel after clicking the Marks button
    @FXML
    protected void openTeacherMarks() throws IOException {
        Stage stage = new Stage();
        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/fxmlpackage/TeacherMarksFXML.fxml")));
        scene.getStylesheets().add(getClass().getResource("/cssPackage/teacherMarks.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
        stage.setTitle("Student-Marks Portal");
    }

    // Opens The Attendance Screen panel after clicking the Attendance button
    @FXML
    protected void openTeacherAttend() throws IOException {
        Stage stage = new Stage();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/fxmlpackage/TeacherAttendanceFXML.fxml"))));
        stage.setTitle("Attendance-Panel");
        stage.show();
    }
}
