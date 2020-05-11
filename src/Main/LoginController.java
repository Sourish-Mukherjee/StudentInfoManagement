package Main;

import DataBase.DataBaseHelper;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginController {

    @FXML
    private Label textBox;
    @FXML
    private PasswordField passtextBox;
    @FXML
    private TextField emaiLTextbox;
    @FXML
    private Button register_button;

    @FXML
    public void generateNumber(ActionEvent event) {
        try {
            DataBaseHelper db = new DataBaseHelper();
            RegisterFXMLController rg = new RegisterFXMLController();
            rg.createDataTable(db);
            boolean check = rg.checkIfEmailExists(db, emaiLTextbox.getText());
            if (!check) {
                textBox.setText("No EmailID found in Database!");
            } else {
                ResultSet res = db.getStatement().executeQuery("SELECT Password from Data where "
                        + "EmailID = '" + emaiLTextbox.getText() + "';");
                res.next();
                if (res.getString(1).equals(passtextBox.getText())) {
                    textBox.setText("LoggedIn Succesfully!");
                } else {
                    textBox.setText("Password Wrong!");
                }
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

    }

    @FXML
    public void createAccount(ActionEvent event) throws IOException {
        Stage stage = (Stage) register_button.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/fxmlPackage/RegisterFXML.fxml"))));
    }
}
