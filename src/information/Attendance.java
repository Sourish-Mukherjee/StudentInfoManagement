package information;

import dashboard.ConfirmBox;
import dashboard.TeaherAttendanceFXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;

public class Attendance {

    private String name;
    private String usn;
    private int currEng;
    private int totalEng;
    private int currMaths;
    private int totalMaths;
    private int currScience;
    private int totalScience;
    private Button button;
    protected static int row;
    public Attendance(String name, String usn, int currEng, int totalEng, int currMaths,
                      int totalMaths, int currScience, int totalScience, Button button) {
        this.name = name;
        this.usn = usn;
        this.currEng = currEng;
        this.totalEng = totalEng;
        this.currMaths = currMaths;
        this.totalMaths = totalMaths;
        this.currScience = currScience;
        this.totalScience = totalScience;
        this.button = button;
        button.setOnAction(e -> {
            new ConfirmBox().display();
            //System.out.println(TeaherAttendanceFXML.getTableAttend().getSelectionModel().getSelectedItem().getName());
        });
    }

    public String getUsn() {
        return usn;
    }
    public String getName() {
        return name;
    }

    public int getCurrEng() {
        return currEng;
    }

    public int getTotalEng() {
        return totalEng;
    }

    public int getCurrMaths() {
        return currMaths;
    }

    public int getTotalMaths() {
        return totalMaths;
    }

    public int getCurrScience() {
        return currScience;
    }

    public int getTotalScience() {
        return totalScience;
    }

    public Button getButton() {
        return button;
    }
}
