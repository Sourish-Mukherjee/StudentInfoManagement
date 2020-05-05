package Main;

import DataBase.DataBaseHelper;
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
        if (emaiLTextbox.getText().equals("somu18cs@cmrit.ac.in") && passtextBox.getText().equals("kolkata")) {
            try {
                textBox.setText("Logged In!");
                DataBaseHelper dataBaseHelper = new DataBaseHelper();
                dataBaseHelper.createDatabase("RegisterPortal");
                dataBaseHelper.useDataBase("RegisterPortal");
                dataBaseHelper.createTable("Data", "EmailID VARCHAR(40), Password VARCHAR(20)");
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
