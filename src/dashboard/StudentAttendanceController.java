package dashboard;

import authentication.LoginController;
import authentication.RegisterFXMLController;
import database.DataBaseHelper;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class StudentAttendanceController implements Initializable {

    @FXML
    private DatePicker datePicker;

    @FXML
    private Label mathAttend;

    @FXML
    private Label engAttend;

    @FXML
    private Label sciAttend;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        engAttend.setText("----");
        mathAttend.setText("----");
        sciAttend.setText("----");
    }

    @FXML
    protected void pickDate() {
        DataBaseHelper db = new DataBaseHelper();
        LocalDate ld = datePicker.getValue();
        try {
            db.useDataBase("registerportal");
            String email = LoginController.getEmaiL();
            int findID = new RegisterFXMLController().findID(db, email);
            ResultSet set = db.getStatement().executeQuery("Select currEng,currMaths,currSci from attendance" +
                    " where link_id =" + findID + " and Date = '" + ld.toString() + "'");
            if (set.next()) {
                engAttend.setText(set.getInt(1) == 1 ? "Present" : "Absent");
                mathAttend.setText(set.getInt(2) == 1 ? "Present" : "Absent");
                sciAttend.setText(set.getInt(3) == 1 ? "Present" : "Absent");
            } else {
                engAttend.setText("Not Marked");
                mathAttend.setText("Not Marked");
                sciAttend.setText("Not Marked");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
