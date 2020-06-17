package dashboard;

import database.DataBaseHelper;
import information.InternalMarks;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.sql.ResultSet;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

/*
    Author: Sourish Mukherjee
    Link: https://github.com/Sourish-Mukherjee/StudentInfoManagement
 */

// This class is the backend part of Marks Panel Dashboard Screen

public class TeacherMarksController implements Initializable {

    @FXML
    private TableView<InternalMarks> teaStuMarksTable;

    @FXML
    private TableColumn<InternalMarks, String> tableName;

    @FXML
    private TableColumn<InternalMarks, String> tableUsn;

    @FXML
    private TableColumn<InternalMarks, Integer> tableIat1;

    @FXML
    private TableColumn<InternalMarks, Integer> tableIat2;

    @FXML
    private TableColumn<InternalMarks, Integer> tableIat3;

    @FXML
    private TableColumn<InternalMarks, Integer> tableTotal;

    @FXML
    private TableColumn<InternalMarks, Float> tableAvg;

    @FXML
    private TextField updateIAT1;

    @FXML
    private TextField updateIAT2;

    @FXML
    private TextField updateIAT3;

    @FXML
    private Label updateTotal;

    @FXML
    private Label updateAvg;

    private final ObservableList<InternalMarks> list = FXCollections.observableArrayList();

    //Initializes the Screen as soon as it loads with the fillTable function
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        fillTable();
    }

    // Fills up the table present in the current window of Marks Panel window
    private void fillTable() {
        DataBaseHelper db = new DataBaseHelper();
        try {
            db.useDataBase("RegisterPortal");
            ResultSet set = db.getStatement().executeQuery("select name,usn,iat1,iat2,iat3,total,average " +
                    "from studententry,internalmarkstable where " +
                    "studententry.link_id = internalmarkstable.link_id;");
            while (set.next())
                list.add(new InternalMarks(set.getString(1), set.getString(2),
                        set.getInt(3), set.getInt(4), set.getInt(5),
                        set.getInt(6), set.getFloat(7)));
            tableName.setCellValueFactory(new PropertyValueFactory<>("name"));
            tableUsn.setCellValueFactory(new PropertyValueFactory<>("usn"));
            tableIat1.setCellValueFactory(new PropertyValueFactory<>("iat1"));
            tableIat2.setCellValueFactory(new PropertyValueFactory<>("iat2"));
            tableIat3.setCellValueFactory(new PropertyValueFactory<>("iat3"));
            tableTotal.setCellValueFactory(new PropertyValueFactory<>("total"));
            tableAvg.setCellValueFactory(new PropertyValueFactory<>("avg"));
            teaStuMarksTable.setItems(list);
            teaStuMarksTable.setOnMouseClicked((MouseEvent event) -> {
                if (event.getClickCount() > 1) {
                    onEdit();
                }
            });
        } catch (
                SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    // After Double Clicking on any of the row this function gets called for editing the data
    private void onEdit() {
        if (teaStuMarksTable.getSelectionModel().getSelectedItem() != null) {
            InternalMarks internalMarks = teaStuMarksTable.getSelectionModel().getSelectedItem();
            updateIAT1.setText(String.valueOf(internalMarks.getIat1()));
            updateIAT2.setText(String.valueOf(internalMarks.getIat2()));
            updateIAT3.setText(String.valueOf(internalMarks.getIat3()));
            updateTotal.setText(String.valueOf("Old Total = " + internalMarks.getTotal()));
            updateAvg.setText(String.valueOf("Old Average =" + internalMarks.getAvg()));
        }
    }

    // After Clicking the Update button, necessary changes will be saved into the Database.
    @FXML
    protected void updateTable() {
        try {
            DataBaseHelper db = new DataBaseHelper();
            db.useDataBase("registerportal");
            String oldUSN = teaStuMarksTable.getSelectionModel().getSelectedItem().getUsn();
            String newIAT1 = updateIAT1.getText();
            String newIAT2 = updateIAT2.getText();
            String newIAT3 = updateIAT3.getText();
            int newTotal = Integer.parseInt(newIAT1) + Integer.parseInt(newIAT2) + Integer.parseInt(newIAT3);
            float newAvg = (float) newTotal / 3;
            if (findUSNID(db, oldUSN) != -1) {
                db.getStatement().executeUpdate("update internalmarkstable set iat1 =" + newIAT1 +
                        ", iat2 =" + newIAT2 + ", iat3 =" + newIAT3 + "," +
                        " total =" + newTotal + ", average =" + newAvg + " where link_id = " + findUSNID(db, oldUSN));
            }
            list.clear();
            ResultSet set = db.getStatement().executeQuery("select name,usn,iat1,iat2,iat3,total,average " +
                    "from studententry,internalmarkstable where " +
                    "studententry.link_id = internalmarkstable.link_id;");
            while (set.next())
                list.add(new InternalMarks(set.getString(1), set.getString(2),
                        set.getInt(3), set.getInt(4), set.getInt(5),
                        set.getInt(6), set.getFloat(7)));
            teaStuMarksTable.setItems(list);
            teaStuMarksTable.refresh();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //This function is used to the Link_ID foreign key value which has the desired usn from StudentEntry table
    protected int findUSNID(DataBaseHelper db, String usn) throws SQLException {
        ResultSet set = db.getStatement().executeQuery("Select Link_ID from studententry where usn ='" + usn + "';");
        if (set.next())
            return set.getInt(1);
        return -1;
    }

}
