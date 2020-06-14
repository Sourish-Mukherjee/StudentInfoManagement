

package dashboard;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.sql.ResultSet;

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

public class StudentDashboardController implements Initializable {

    @FXML
    private Label stuDashName,
            stuDashUSN,
            stuDashEmail;
    @FXML
    private ImageView stuDashImage;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        String loggedEmail = LoginController.getEmaiL();
        DataBaseHelper db = new DataBaseHelper();
        try {
            int id = new RegisterFXMLController().findID(db, loggedEmail);
            ResultSet set = db.getStatement().executeQuery("Select name,usn from studententry" +
                    " where link_id = " + id + ";");
            if (set.next()) {
                String name = set.getString(1);
                String usn = set.getString(2);
                stuDashName.setText(name);
                stuDashUSN.setText(usn);
            } else
                throw new SQLException("ResultSet = false in initialize method of Student DashBoard");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        stuDashEmail.setText(loggedEmail);
        try {
            stuDashImage.setImage(new Image(new FileInputStream("E:\\Java\\Projects\\StudentInfoManagement\\icon.jpg")));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @FXML
    protected void checkMarks() throws IOException {
        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/fxmlpackage/StudentMarksFXML.fxml")));
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    @FXML
    protected void checkAttend() {
    }

}
