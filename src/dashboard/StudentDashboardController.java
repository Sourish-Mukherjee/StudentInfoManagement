

package dashboard;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.sql.ResultSet;

import authentication.LoginController;
import authentication.RegisterFXMLController;
import database.DataBaseHelper;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

public class StudentDashboardController implements Initializable {

    @FXML
    private Label stuDashName,
            stuDashUSN,
            stuDashEmail;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        String loggedEmail = LoginController.getEmaiL();
        DataBaseHelper db = new DataBaseHelper();
        try {
            int id = new RegisterFXMLController().findID(db, loggedEmail);
            ResultSet set = db.getStatement().executeQuery("Select name,usn from studententry" +
                    " where link_id = " + id+";");
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
    }

}
