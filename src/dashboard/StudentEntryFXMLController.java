package dashboard;

import authentication.LoginController;
import authentication.RegisterFXMLController;
import database.DataBaseHelper;

import java.sql.SQLException;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class StudentEntryFXMLController {

    @FXML
    private TextField name_stuEntry,
            usn_stuEntry,
            pho_stuEntry,
            branch_stuEntry,
            year_stuEntry;
    @FXML
    private Label label_stuEntry;

    @FXML
    protected void onSave() throws SQLException {
        DataBaseHelper db = new DataBaseHelper();
        createTableForStudentDetails(db);
        enterDataInStudentDetails(db);
    }

    public void createStudentEntryTable(DataBaseHelper db) throws SQLException {
        if (db == null) {
            System.out.println("Error In Table Creation Of Student Entry");
        } else {
            createTableForStudentDetails(db);
        }
    }

    private void createTableForStudentDetails(DataBaseHelper db) throws SQLException {
        db.useDataBase("RegisterPortal");
        db.createTable("StudentEntry", "SL INT AUTO_INCREMENT PRIMARY KEY,"
                + "NAME VARCHAR(20), USN VARCHAR(20), BRANCH VARCHAR(20), PHONE VARCHAR(20)"
                + ",YEAR VARCHAR(5), LINK_ID INT, FOREIGN KEY(LINK_ID) REFERENCES data(ID)");
        db.createTable("InternalMarksTable ", "Marks_ID INT PRIMARY KEY AUTO_INCREMENT ," +
                "IAT1 INT DEFAULT 0, IAT2 INT DEFAULT 0 , IAT3 INT DEFAULT 0, TOTAL " +
                "INT DEFAULT 0 , AVERAGE DECIMAL(4,2) DEFAULT 0, LINK_ID INT," +
                " FOREIGN KEY(LINK_ID) REFERENCES studententry(LINK_ID)");
        db.createTable("Attendance", "ID INT PRIMARY KEY AUTO_INCREMENT, CurrEng INT Default 0," +
                " TotalEng INT Default 0, CurrMaths INT Default 0, TotalMaths INT Default 0, CurrSci INT Default 0," +
                " TotalSci INT Default 0, LINK_ID INT, Date DATE, FOREIGN KEY(LINK_ID) REFERENCES STUDENTENTRY(LINK_ID)");

    }

    private boolean enterDataInStudentDetails(DataBaseHelper db) {
        try {
            db.useDataBase("RegisterPortal");
        } catch (SQLException ex) {
            System.out.println("Could Not Use StudentDetails DataBase");
            return false;
        }
        try {
            db.getStatement().executeUpdate("UPDATE StudentEntry SET NAME = '" + name_stuEntry.getText() + "',USN ='"
                    + usn_stuEntry.getText() + "', BRANCH ='" + branch_stuEntry.getText() + "',PHONE='" + pho_stuEntry.getText()
                    + "', YEAR='" + year_stuEntry.getText() + "' WHERE LINK_ID=" + new RegisterFXMLController().findID(db, LoginController.getEmaiL()));
            db.getStatement().executeUpdate("Insert into InternalMarksTable(Link_ID) values(" + new RegisterFXMLController().findID(db, LoginController.getEmaiL()) + ");");
            db.getStatement().executeUpdate("Insert into Attendance(Link_ID) values(" + new RegisterFXMLController().findID(db, LoginController.getEmaiL()) + ");");
        } catch (SQLException ex) {
            System.out.println(ex);
            label_stuEntry.setText("Could Not Store Data!");
            return false;
        }
        label_stuEntry.setText("Saved Data!");
        return true;
    }

}
