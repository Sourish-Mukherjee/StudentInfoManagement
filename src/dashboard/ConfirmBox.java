package dashboard;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ConfirmBox implements Initializable {

    @FXML
    private ComboBox confrimEng;

    @FXML
    private ComboBox confrimMaths;

    @FXML
    private ComboBox confrimScience;

    @FXML
    private Button confirmDone;

    private ObservableList<String> list = FXCollections.observableArrayList(new String[]{"YES", "NO"});

    public void display() {
        Stage stage = new Stage();
        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/fxmlpackage/ConfirmBox.fxml"))));
        } catch (IOException e) {
            System.out.print(e.getMessage());
        }
        stage.setTitle("Student Attendance");
        stage.setMinHeight(100);
        stage.show();

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        confrimEng.setItems(list);
        confrimMaths.setItems(list);
        confrimScience.setItems(list);
        confrimEng.setValue("Mark");
        confrimMaths.setValue("Mark");
        confrimScience.setValue("Mark");
    }

    @FXML
    protected void updateAttendance() {
        Stage stage = new Stage();
        stage = (Stage) confirmDone.getScene().getWindow();
        TeaherAttendanceFXML ta = new TeaherAttendanceFXML();
        new TeaherAttendanceFXML().findID();
        //System.out.println(new TeaherAttendanceFXML().getTableAttend().getSelectionModel().getSelectedItem().getName());
        stage.close();
    }
}
