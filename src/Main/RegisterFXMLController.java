package Main;

import DataBase.DataBaseHelper;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class RegisterFXMLController {

    @FXML
    private TextField email_register_textfield;
    @FXML
    private PasswordField register_password, register_repassword;
    @FXML
    private Label register_status_label;

    @FXML
    public void registerAccount(ActionEvent event) {
        if (!register_password.getText().equals(register_repassword.getText())) {
            register_status_label.setText("Password Does Not Match!");
        } else {
            try {
                DataBaseHelper db = new DataBaseHelper();
                createDataTable(db);
                if (!checkIfEmailExists(db, email_register_textfield.getText())) {
                    db.getStatement().executeUpdate("Insert into Data (EmailID, Password) values ('" + email_register_textfield.getText()
                            + "', '" + register_password.getText() + "');");
                    register_status_label.setText("Email ID has been Registered!");
                } else {
                    register_status_label.setText("Email ID already exists!");
                }
            } catch (SQLException ex) {
                register_status_label.setText("Could Not Register EmailID!");
                System.out.println(ex.getMessage());
            }
        }
    }

    protected void createDataTable(DataBaseHelper db) throws SQLException {
        db.createDatabase("RegisterPortal");
        db.useDataBase("RegisterPortal");
        db.createTable("Data", "ID INT NOT NULL AUTO_INCREMENT ,EmailID VARCHAR(40), Password VARCHAR(20),PRIMARY KEY(ID)");
    }

    protected boolean checkIfEmailExists(DataBaseHelper db, String emailID) throws SQLException {
        ResultSet res = (db.getStatement().executeQuery("SELECT  COUNT(EmailID) from Data where "
                + "EmailID = '" + emailID + "';"));
        res.next();
        int n = res.getInt(1);
        return n != 0;
    }

}
