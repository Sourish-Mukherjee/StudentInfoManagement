package authentication;

import database.DataBaseHelper;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class LoginController implements Initializable {

    @FXML
    private Label textBox;
    @FXML
    private PasswordField passtextBox;
    @FXML
    private TextField emaiLTextbox;
    @FXML
    private Button register_button;
    @FXML
    private ComboBox<String> comboBox_Login;
    @FXML
    private ImageView imageView;
    private static String emailTyped = "";

    @FXML
    protected void generateNumber() throws IOException {
        emailTyped = emaiLTextbox.getText();
        try {
            DataBaseHelper db = new DataBaseHelper();
            RegisterFXMLController rg = new RegisterFXMLController();
            rg.createDataTable(db);
            boolean check = rg.checkIfEmailExists(db, emaiLTextbox.getText());
            if (!check) {
                textBox.setText("No EmailID found in Database!");
            } else {
                if (checkType(db, emaiLTextbox.getText(), comboBox_Login.getSelectionModel().getSelectedItem().toString())) {
                    ResultSet res = db.getStatement().executeQuery("SELECT Password from Data where "
                            + "EmailID = '" + emaiLTextbox.getText() + "';");
                    res.next();
                    if (res.getString(1).equals(passtextBox.getText())) {
                        textBox.setText("LoggedIn Succesfully!");
                        if (comboBox_Login.getSelectionModel().getSelectedItem().toString().equals("Student")) {
                            if (findStudentEntryExists(db)) {
                                Stage stage = new Stage();
                                stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/fxmlpackage/StudentDashboardFXML.fxml"))));
                                stage.setTitle("Student Dashboard");
                                stage.show();
                                stage = (Stage) register_button.getScene().getWindow();
                                stage.close();
                            } else {
                                Stage stage = new Stage();
                                stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/fxmlpackage/StudentEntryFXML.fxml"))));
                                stage.setTitle("Student Info Entry!");
                                stage.show();
                                stage = (Stage) register_button.getScene().getWindow();
                                stage.close();
                            }
                        } else {
                            Stage stage = new Stage();
                            Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/fxmlpackage/TeacherDashboardFXML.fxml")));
                            stage.setScene(scene);
                            scene.getStylesheets().add(getClass().getResource("/cssPackage/teacherdashboardfxml.css").toExternalForm());
                            stage.setTitle("Teacher Dashboard");
                            stage.show();
                            stage = (Stage) register_button.getScene().getWindow();
                            stage.close();
                            textBox.setText("Teacher Screen!");
                        }

                    } else {
                        textBox.setText("Password Wrong!");
                    }
                } else {
                    textBox.setText("Wrong Selection of Choice!");
                }

            }
        } catch (SQLException e) {
            System.out.println(e);
        }

    }

    private boolean findStudentEntryExists(DataBaseHelper db) throws SQLException {
        int n = new RegisterFXMLController().findID(db, emaiLTextbox.getText());
        ResultSet set = db.getStatement().executeQuery("Select Count(LINK_ID) from StudentEntry where LINK_ID=" + n + " AND USN IS NOT NULL");
        if (set.next()) {
            return set.getInt(1) == 1;
        } else {
            throw new SQLException("Result Set returns false in findStudentEntryExists method");
        }
    }

    @FXML
    public void createAccount(ActionEvent event) throws IOException {
        Stage stage = (Stage) register_button.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/fxmlpackage/RegisterFXML.fxml"))));
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            imageView.setImage(new Image(new FileInputStream("E:\\Java\\Projects\\StudentInfoManagement\\" +
                    "resources\\images\\login_Page_Icon.png")));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        ObservableList<String> options
                = FXCollections.observableArrayList(
                "Faculty",
                "Student"
        );
        comboBox_Login.setItems(options);
        comboBox_Login.setValue("Select");
    }

    private boolean checkType(DataBaseHelper db, String emailID, String type) throws SQLException {
        ResultSet set = db.getStatement().executeQuery("SELECT COUNT(Type) FROM DATA WHERE EmailID ='" + emailID + "'"
                + "AND Type = '" + type + "';");
        if (set.next()) {
            int n = set.getInt(1);
            return n != 0;
        } else {
            throw new SQLException("Result Set returning false in checkType method");
        }

    }

    public static String getEmaiL() {
        return emailTyped;
    }

}
