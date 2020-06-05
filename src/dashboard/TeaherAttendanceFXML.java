package dashboard;

import database.DataBaseHelper;
import information.Attendance;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.ResultSet;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class TeaherAttendanceFXML implements Initializable {

    @FXML
    private DatePicker teaAtDatePick;

    @FXML
    private TableView<Attendance> tableAttend;

    @FXML
    public TableColumn<Attendance, String> tableName;

    @FXML
    private TableColumn<Attendance, String> tableUSN;

    @FXML
    private TableColumn<Attendance, Integer> tableEng;

    @FXML
    private TableColumn<Attendance, Integer> tableMat;

    @FXML
    private TableColumn<Attendance, Button> tableOption;

    @FXML
    private TableColumn<Attendance, Integer> tableSci;

    private ObservableList<Attendance> list = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        fillTable();
        tableAttend.setEditable(true);
    }

    private void fillTable() {
        DataBaseHelper db = new DataBaseHelper();
        try {
            db.useDataBase("registerportal");
            ResultSet set = db.getStatement().executeQuery("Select name, usn,currEng,totalEng,currMaths," +
                    "totalMaths,currSci,totalSci from studententry,attendance " +
                    "where attendance.link_id = studententry.link_id;");
            while (set.next())
                list.add(new Attendance(set.getString(1), set.getString(2), set.getInt(3)
                        , set.getInt(4), set.getInt(5), set.getInt(6), set.getInt(7),
                        set.getInt(8), new Button("Edit")));

            tableName.setCellValueFactory(new PropertyValueFactory<>("name"));
            tableUSN.setCellValueFactory(new PropertyValueFactory<>("usn"));
            tableEng.setCellValueFactory(new PropertyValueFactory<>("currEng"));
            tableMat.setCellValueFactory(new PropertyValueFactory<>("currMaths"));
            tableSci.setCellValueFactory(new PropertyValueFactory<>("currScience"));
            tableOption.setCellValueFactory(new PropertyValueFactory<>("button"));
            tableAttend.setItems(list);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void pickDate() {
        //TO-DO Later
    }

    public  TableView<Attendance> getTableAttend() {
        return tableAttend;
    }


    public void findID() {
        System.out.println(tableAttend.getSelectionModel().getSelectedItem().getName());
    }
}
