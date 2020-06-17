package information;

/*
    Author: Sourish Mukherjee
    Link: https://github.com/Sourish-Mukherjee/StudentInfoManagement
 */

// UserDefined Variable , Type - Student ----> Stores information of student

public class Student {

    private String name, 
            usn, 
            phone, 
            branch, 
            email;
    private int year;

    public Student(String name, String usn, String phone, String branch, String email, int year) {
        this.name = name;
        this.usn = usn;
        this.phone = phone;
        this.branch = branch;
        this.email = email;
        this.year = year;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

}
