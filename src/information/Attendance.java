package information;

import javafx.scene.control.ComboBox;

/*
    Author: Sourish Mukherjee
    Link: https://github.com/Sourish-Mukherjee/StudentInfoManagement
 */

// UserDefined Variable , Type - Attendance ----> Storesa attendance record of each student

public class Attendance {

    private String name;
    private String usn;
    private ComboBox currEng;
    private int totalEng;
    private ComboBox currMaths;
    private int totalMaths;
    private ComboBox currScience;
    private int totalScience;

    public Attendance(String name, String usn, ComboBox currEng, int totalEng, ComboBox currMaths,
                      int totalMaths, ComboBox currScience, int totalScience) {
        this.name = name;
        this.usn = usn;
        this.currEng = currEng;
        this.totalEng = totalEng;
        this.currMaths = currMaths;
        this.totalMaths = totalMaths;
        this.currScience = currScience;
        this.totalScience = totalScience;
    }

    public String getUsn() {
        return usn;
    }

    public String getName() {
        return name;
    }

    public ComboBox getCurrEng() {
        return currEng;
    }

    public int getTotalEng() {
        return totalEng;
    }

    public ComboBox getCurrMaths() {
        return currMaths;
    }

    public int getTotalMaths() {
        return totalMaths;
    }

    public ComboBox getCurrScience() {
        return currScience;
    }

    public int getTotalScience() {
        return totalScience;
    }

}
