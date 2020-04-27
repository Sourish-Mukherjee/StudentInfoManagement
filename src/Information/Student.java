package Information;

public class Student {

    private String name, usn, sec, branch;
    private int year, sem;

    public Student() {
        this("", "", "", "", 0, 0);
    }

    public Student(String name, String usn, String branch, String sec, int year, int sem) {
        this.name = name;
        this.usn = usn;
        this.sec = sec;
        this.year = year;
        this.sem = sem;
        this.branch = branch;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsn() {
        return usn;
    }

    public void setUsn(String usn) {
        this.usn = usn;
    }

    public String getSec() {
        return sec;
    }

    public void setSec(String sec) {
        this.sec = sec;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getSem() {
        return sem;
    }

    public void setSem(int sem) {
        this.sem = sem;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    @Override
    public String toString() {
        return ("Name : " + this.getName() + " USN : " + this.getUsn()
                + " Year: " + this.getYear() + System.lineSeparator() + "Branch : " + this.getBranch()
                + " Semester : " + this.getSem() + " Section : " + this.getSec());
    }

}
