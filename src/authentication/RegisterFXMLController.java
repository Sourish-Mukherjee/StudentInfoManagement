package authentication;

import dashboard.StudentEntryFXMLController;
import dataBase.DataBaseHelper;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class RegisterFXMLController {

    @FXML
    private TextField email_register_textfield;
    @FXML
    private PasswordField register_password,
            register_repassword;
    @FXML
    private Label register_status_label;
    @FXML
    private CheckBox fac_checkBox,
            stu_checkBox;

    @FXML
    public void registerAccount(ActionEvent event) throws IOException {
        if (!register_password.getText().equals(register_repassword.getText())) {
            register_status_label.setText("Password Does Not Match!");
        } else {
            try {
                DataBaseHelper db = new DataBaseHelper();
                createDataTable(db);
                if (!checkIfEmailExists(db, email_register_textfield.getText())) {
                    String type = fac_checkBox.isSelected() ? "Faculty" : "Student";
                    db.getStatement().executeUpdate("Insert into Data (EmailID, Password, Type) values ('" + email_register_textfield.getText()
                            + "', '" + register_password.getText() + "' , '" + type + "')");
                    register_status_label.setText("Email ID has been Registered!");
                    db.getStatement().executeUpdate("Insert into StudentEntry(LINK_ID) Values (" + findID(db, email_register_textfield.getText()) + ")");
                    Stage stage = (Stage) register_password.getScene().getWindow();
                    stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/fxmlPackage/LoginFXML.fxml"))));
                } else {
                    register_status_label.setText("Email ID already exists!");
                }
            } catch (SQLException ex) {
                register_status_label.setText("Could Not Register EmailID!");
                System.out.println(ex.getMessage());
            }
        }
    }

    public int findID(DataBaseHelper db, String emailID) throws SQLException {
        StudentEntryFXMLController se = new StudentEntryFXMLController();
        se.createStudentEntryTable(db);
        ResultSet set = db.getStatement().executeQuery("SELECT ID FROM data where EmailID = '" + emailID + "'");
        if (set.next()) {
            return set.getInt(1);
        } else {
            throw new SQLException("ResultSet returning false in findID method!");
        }
    }

    public void createDataTable(DataBaseHelper db) throws SQLException {
        db.createDatabase("RegisterPortal");
        db.useDataBase("RegisterPortal");
        db.createTable("Data", "ID INT NOT NULL AUTO_INCREMENT ,EmailID VARCHAR(40), Password VARCHAR(20),Type VARCHAR(7),PRIMARY KEY(ID)");
    }

    public boolean checkIfEmailExists(DataBaseHelper db, String emailID) throws SQLException {
        ResultSet res = (db.getStatement().executeQuery("SELECT  COUNT(EmailID) from Data where "
                + "EmailID = '" + emailID + "';"));
        if (res.next()) {
            int n = res.getInt(1);
            return n != 0;
        } else {
            throw new SQLException("ResultSet returning false in checkIfEmailExists Method!");
        }
    }

}
