package dashboard;

import database.DataBaseHelper;
import information.Attendance;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import javax.xml.crypto.Data;
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
    private TableColumn<Attendance, Integer> tableSci;

    private ObservableList<Attendance> list = FXCollections.observableArrayList();
    private final ObservableList<String> CHOICE = FXCollections.observableArrayList("YES", "NO");

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        fillTable();
    }

    private void fillTable() {
        DataBaseHelper db = new DataBaseHelper();
        try {
            db.useDataBase("registerportal");
            ResultSet set = db.getStatement().executeQuery("Select name, usn,currEng,totalEng,currMaths," +
                    "totalMaths,currSci,totalSci from studententry,attendance " +
                    "where attendance.link_id = studententry.link_id;");
            while (set.next())
                list.add(new Attendance(set.getString(1), set.getString(2), new ComboBox<>(CHOICE)
                        , set.getInt(4), new ComboBox<>(CHOICE), set.getInt(6), new ComboBox<>(CHOICE),
                        set.getInt(8)));

            tableName.setCellValueFactory(new PropertyValueFactory<>("name"));
            tableUSN.setCellValueFactory(new PropertyValueFactory<>("usn"));
            tableEng.setCellValueFactory(new PropertyValueFactory<>("currEng"));
            tableMat.setCellValueFactory(new PropertyValueFactory<>("currMaths"));
            tableSci.setCellValueFactory(new PropertyValueFactory<>("currScience"));
            tableAttend.setItems(list);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @FXML
    protected void pickDate() {
        //TO-DO Later
    }

    @FXML
    protected void doneMethod() {
        try {
            DataBaseHelper db = new DataBaseHelper();
            db.useDataBase("registerportal");
            for (Attendance attend : tableAttend.getItems()) {
                String valueEng = attend.getCurrEng().getSelectionModel().getSelectedItem().toString();
                String valueMath = attend.getCurrMaths().getSelectionModel().getSelectedItem().toString();
                String valueSci = attend.getCurrScience().getSelectionModel().getSelectedItem().toString();
                String usn = attend.getUsn();
                int findID = new TeacherMarksController().findUSNID(db, usn);
                updateAttendance(db, findID, valueEng, valueMath, valueSci);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void updateAttendance(DataBaseHelper db, int id, String eng, String math, String sci) {
        try {
            System.out.println(id);
            ResultSet set = db.getStatement().executeQuery("select totaleng,totalmaths,totalsci from " +
                    "studententry,attendance where studententry.link_id = attendance.link_id;");
            if (set.next()) {
                int totalEng = (eng.equals("YES") ? 1 : 0) + set.getInt(1);
                int totalMat = (math.equals("YES") ? 1 : 0) + set.getInt(2);
                int totalSci = (sci.equals("YES") ? 1 : 0) + set.getInt(3);
                db.getStatement().executeUpdate("update attendance set totaleng = " + totalEng +
                        ",totalmaths = " + totalMat + ",totalsci = " + totalSci + " where link_id =" + id);
            } else
                throw new SQLException("ResultSet - False, updateAttendance");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}
