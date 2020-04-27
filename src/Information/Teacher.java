package Information;

public class Teacher {

    public Teacher(String name, String branch, String role) {
        this.name = name;
        this.branch = branch;
        this.role = role;
    }

    private String name, branch, role;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

}
