
package HotelMS;



public class Employee {
 
        private int emp_id;
        private String emp_fname;
        private String emp_lname;
        private int emp_age;
        private String isAdmin;
        private String email;
        private String password;
 
        public Employee(int emp_id, String emp_fname, String emp_lname, int emp_age, String isAdmin, String email, String password) {
            this.emp_id = emp_id;
            this.emp_fname = emp_fname;
            this.emp_lname = emp_lname;
            this.emp_age = emp_age;
            this.isAdmin = isAdmin;
            this.email = email;
            this.password = password;
                    

        }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getEmp_id() {
        return emp_id;
    }

    public void setEmp_id(int emp_id) {
        this.emp_id = emp_id;
    }

    public String getEmp_fname() {
        return emp_fname;
    }

    public void setEmp_fname(String emp_fname) {
        this.emp_fname = emp_fname;
    }

    public String getEmp_lname() {
        return emp_lname;
    }

    public void setEmp_lname(String emp_lname) {
        this.emp_lname = emp_lname;
    }

    public int getEmp_age() {
        return emp_age;
    }

    public void setEmp_age(int emp_age) {
        this.emp_age = emp_age;
    }

    public String getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(String isAdmin) {
        this.isAdmin = isAdmin;
    }

}






















/*
public class Employee {
    private int emp_id;
    private String emp_fname;
    private String emp_lname;
    private int emp_age;
    private String isAdmin;

    public Employee (int emp_id, String emp_fname, String emp_lname, int emp_age, String isAdmin){
        this.emp_id = emp_id;
        this.emp_fname = emp_fname;
        this.emp_lname = emp_lname;
        this.emp_id = emp_age;
        this.isAdmin = isAdmin;
    }

    public int getEmp_id() {
        return emp_id;
    }

    public void setEmp_id(int emp_id) {
        this.emp_id = emp_id;
    }

    public String getEmp_fname() {
        return emp_fname;
    }

    public void setEmp_fname(String emp_fname) {
        this.emp_fname = emp_fname;
    }

    public String getEmp_lname() {
        return emp_lname;
    }

    public void setEmp_lname(String emp_lname) {
        this.emp_lname = emp_lname;
    }

    public int getEmp_age() {
        return emp_age;
    }

    public void setEmp_age(int emp_age) {
        this.emp_age = emp_age;
    }

    public String getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(String isAdmin) {
        this.isAdmin = isAdmin;
    }
}
*/