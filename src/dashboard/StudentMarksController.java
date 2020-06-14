package dashboard;

import authentication.LoginController;
import authentication.RegisterFXMLController;
import database.DataBaseHelper;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class StudentMarksController implements Initializable {

    @FXML
    private Label stuMarksIAT1;

    @FXML
    private Label stuMarksIAT2;

    @FXML
    private Label stuMarksIAT3;

    @FXML
    private Label stuMarksTotal;

    @FXML
    private Label stuMarksRank;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        fillMarks();
    }

    private void fillMarks() {
        DataBaseHelper db = new DataBaseHelper();
        try {
            db.useDataBase("registerportal");
            String email = LoginController.getEmaiL();
            int findID = new RegisterFXMLController().findID(db, email);
            ResultSet set = db.getStatement().executeQuery("Select Iat1,Iat2,Iat3,Total from internalmarkstable" +
                    " where link_id =" + findID);
            if (set.next()) {
                stuMarksIAT1.setText("IAT 1 : " + set.getInt(1));
                stuMarksIAT2.setText("IAT 2 : " + set.getInt(2));
                stuMarksIAT3.setText("IAT 3 : " + set.getInt(3));
                stuMarksTotal.setText("Total : " + set.getInt(4));
                ResultSet rest = db.getStatement().executeQuery("Select Count(Total) from internalmarkstable" +
                        " where total >" + set.getInt(4));
                if (rest.next()) {
                    int rank = (rest.getInt(1))+1;
                    stuMarksRank.setText("Rank : " + rank);
                }
                else
                    throw new SQLException();
            } else
                throw new SQLException("ResultSet - False, fillMarks method");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
