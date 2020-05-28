package dashboard;

import authentication.RegisterFXMLController;
import dataBase.DataBaseHelper;
import information.Student;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

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
    @FXML
    private TextField table_editName;

    @FXML
    private TextField table_editEmail;

    @FXML
    private TextField table_editUSN;

    @FXML
    private TextField table_editBranch;

    @FXML
    private TextField table_editYear;

    @FXML
    private TextField table_editPhone;

    private final ObservableList<Student> list = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        DataBaseHelper db = new DataBaseHelper();
        try {
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
        table_teacDashboard.setOnMouseClicked((MouseEvent event) -> {
            if (event.getClickCount() > 1) {
                onEdit();
            }
        });
    }

    private void onEdit() {
        if (table_teacDashboard.getSelectionModel().getSelectedItem() != null) {
            Student stu = table_teacDashboard.getSelectionModel().getSelectedItem();
            table_editName.setText(stu.getName());
            table_editEmail.setText(stu.getEmail());
            table_editUSN.setText(stu.getUsn());
            table_editBranch.setText(stu.getBranch());
            table_editYear.setText(String.valueOf(stu.getYear()));
            table_editPhone.setText(stu.getPhone());
        }
    }

    @FXML
    private void updateRegisterTable() throws SQLException {
        DataBaseHelper db = new DataBaseHelper();
        db.useDataBase("RegisterPortal");
        String oldEmail = table_teacDashboard.getSelectionModel().getSelectedItem().getEmail();
        String newName = table_editName.getText();
        String newEmail = table_editEmail.getText();
        String newUSN = table_editUSN.getText();
        String newBranch = table_editBranch.getText();
        String newYear = table_editYear.getText();
        String newPhone = table_editPhone.getText();
        int id = new RegisterFXMLController().findID(db, oldEmail);
        db.getStatement().executeUpdate("Update data set EmailID ='" + newEmail + "' where ID =" + id);
        db.getStatement().executeUpdate("Update studententry set Name = '" + newName + "',"
                + "USN = '" + newUSN + "', Branch ='" + newBranch + "', Year ='" + newYear + "', Phone ='" + newPhone + "' WHERE LINK_ID =" + id);
        list.clear();
        ResultSet set = db.getStatement().executeQuery("select name,usn,phone,branch,emailID,year from data, studententry"
                + " where data.id = studententry.link_id and studententry.name is not null ;");
        while (set.next()) {
            list.add(new Student(set.getString(1), set.getString(2), set.getString(3), set.getString(4), set.getString(5), set.getInt(6)));
        }
        table_teacDashboard.setItems(list);
        table_teacDashboard.refresh();
    }

}
