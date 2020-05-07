package Main;

import DataBase.DataBaseHelper;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController {

    @FXML
    private Label textBox;
    @FXML
    private PasswordField passtextBox;
    @FXML
    private TextField emaiLTextbox;

    @FXML
    public void generateNumber(ActionEvent event) {
        try {
            textBox.setText("Logged In!");
            DataBaseHelper dataBaseHelper = new DataBaseHelper();
            dataBaseHelper.useDataBase("RegisterPortal");
            ResultSet res = (dataBaseHelper.getStatement().executeQuery("SELECT  COUNT(EmailID) from Data where "
                    + "EmailID = '" + emaiLTextbox.getText() + "';"));
            res.next();
            int n = res.getInt(1);
            if (n == 0) {
                textBox.setText("No EmailID found in Database!");
            } else {
                res = dataBaseHelper.getStatement().executeQuery("SELECT Password from Data where "
                        + "EmailID = '" + emaiLTextbox.getText() + "';");
                res.next();
                if (res.getString(1).equals(passtextBox.getText())) {
                    textBox.setText("LoggedIn!");
                } else {
                    textBox.setText("Password Wrong!");
                }
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

    }

    @FXML
    public void createAccount(ActionEvent event) {
        if (!emaiLTextbox.getText().isEmpty() && !passtextBox.getText().isEmpty()) {
            try {
                DataBaseHelper db = new DataBaseHelper();
                db.createDatabase("RegisterPortal");
                db.useDataBase("RegisterPortal");
                db.createTable("Data", "ID INT NOT NULL AUTO_INCREMENT ,EmailID VARCHAR(40), Password VARCHAR(20),PRIMARY KEY(ID)");
                db.getStatement().executeUpdate("Insert into Data (EmailID, Password) values ('" + emaiLTextbox.getText() + "', '" + passtextBox.getText() + "');");
                textBox.setText("Registered!");
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }

        }
    }
}
