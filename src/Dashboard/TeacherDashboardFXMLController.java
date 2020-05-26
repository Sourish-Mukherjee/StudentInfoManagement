package Dashboard;

import DataBase.DataBaseHelper;
import Information.Student;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class TeacherDashboardFXMLController implements Initializable {

    @FXML
    private TableView<Student> table_teacDashboard;
    @FXML
    private TableColumn<Student, String> table_name;
    @FXML
    private TableColumn<Student, String> table_email;
    @FXML
    private TableColumn<Student, String> table_usn;
    @FXML
    private TableColumn<Student, String> table_branch;
    @FXML
    private TableColumn<Student, Integer> table_year;
    @FXML
    private TableColumn<Student, String> table_phone;

    private ObservableList<Student> list = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        try {
            DataBaseHelper db = new DataBaseHelper();
            db.useDataBase("registerportal");
            ResultSet set = db.getStatement().executeQuery("select name,usn,phone,branch,emailID,year from data, studententry"
                    + " where data.id = studententry.link_id and studententry.name is not null ;");
            while (set.next()) {
                list.add(new Student(set.getString(1), set.getString(2), set.getString(3), set.getString(4), set.getString(5), set.getInt(6)));
            }

        } catch (SQLException e) {
            System.out.println(e);
        }
        table_name.setCellValueFactory(new PropertyValueFactory<>("name"));
        table_email.setCellValueFactory(new PropertyValueFactory<>("email"));
        table_usn.setCellValueFactory(new PropertyValueFactory<>("usn"));
        table_branch.setCellValueFactory(new PropertyValueFactory<>("branch"));
        table_phone.setCellValueFactory(new PropertyValueFactory<>("phone"));
        table_year.setCellValueFactory(new PropertyValueFactory<>("year"));
        table_teacDashboard.setItems(list);
    }

}
