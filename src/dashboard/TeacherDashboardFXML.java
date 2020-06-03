package dashboard;

import authentication.LoginController;
import authentication.RegisterFXMLController;
import database.DataBaseHelper;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.ResultSet;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class TeacherDashboardFXML implements Initializable {


    @FXML
    private Button teaDashAttendance;

    @FXML
    private Button teaDashInfo;

    @FXML
    private Button teaDashMarks;

    @FXML
    private Label teaDashEmail;

    @FXML
    private Label teaDashName;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            setDetailsOnScreen();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    private void setDetailsOnScreen() throws SQLException {
        String email = LoginController.getEmaiL();
        DataBaseHelper db = new DataBaseHelper();
        int id = new RegisterFXMLController().findID(db, email);
        ResultSet set = db.getStatement().executeQuery("Select name from studententry where link_id = " + id);
        if (set.next()) {
            String name = set.getString(1);
            teaDashName.setText(name);
            teaDashEmail.setText(email);
        } else {
            throw new SQLException("ResultSet false in setDetailsOnScreen TeacherDashboard");
        }
    }

    @FXML
    protected void openTeacherStudentInfo() throws IOException {
        Stage stage = new Stage();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/fxmlPackage/TeacherStudentInfoFXML.fxml"))));
        stage.show();
        stage.setTitle("Student-Information Panel");
        stage = (Stage) teaDashName.getScene().getWindow();
        stage.close();
    }
    @FXML
    protected void openTeacherMarks() throws IOException {
        Stage stage = new Stage();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/fxmlPackage/TeacherMarksFXML.fxml"))));
        stage.show();
        stage.setTitle("Student-Marks Portal");
        stage = (Stage)teaDashName.getScene().getWindow();
        stage.close();
    }


}
