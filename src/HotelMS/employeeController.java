package HotelMS;

import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXToggleButton;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

public class employeeController implements Initializable {

    @FXML
    private JFXTextField fnameFieild;

    @FXML
    private JFXTextField lnameField;

    @FXML
    private JFXTextField emailField;

    @FXML
    private JFXTextField passField;

    @FXML
    private JFXTextField ageField;

    @FXML
    private JFXToggleButton adminButton;
    
    @FXML
    private JFXTextField fnameField1;

    @FXML
    private JFXTextField lnameField1;

    @FXML
    private JFXTextField emailField1;

    @FXML
    private JFXTextField passField1;


    @FXML
    private TableView<Employee> employeeTable;

    @FXML
    private TableColumn<Employee, Integer> emp_id;

    @FXML
    private TableColumn<Employee, String> emp_fname;

    @FXML
    private TableColumn<Employee, String> emp_lname;

    @FXML
    private TableColumn<Employee, Integer> emp_age;

    @FXML
    private TableColumn<Employee, String> isAdmin;
    
    @FXML
    private TableColumn<Employee, String> email;

    @FXML
    private TableColumn<Employee, String> password;
    @FXML
    private ChoiceBox choiceBoc;
    
    
    ObservableList<Employee> emp_list = FXCollections.observableArrayList();
    ObservableList<String> choiceBocList = FXCollections.observableArrayList("admin", "employee");
    
     int index = 0;
    
    @Override
    public void initialize (URL url, ResourceBundle rb){
       getEmployee();
       showEmployee();
       choiceBoc.setValue("employee");
       choiceBoc.setItems(choiceBocList);
    }

    @FXML
    void getSelected(MouseEvent event) {
        Employee select = employeeTable.getSelectionModel().getSelectedItem();
     
    }
    
    @FXML
    void addUser(ActionEvent event) {
        String query = "INSERT INTO employee (emp_id, email, password, emp_fname, emp_lname, emp_age, isAdmin) VALUES (NULL,'" + emailField.getText() + "','" + passField.getText() + "','"
                + fnameFieild.getText() + "','" + lnameField.getText() + "'," + ageField.getText()+ ",'" + choiceBoc.getSelectionModel().getSelectedItem() + "')";
      System.out.println(query);
        executeQuery(query);
        employeeTable.getItems().clear();
        getEmployee();
            fnameFieild.clear();
            lnameField.clear();
            emailField.clear();
            passField.clear();
            ageField.clear();
            
        

    }

   

    @FXML
    void deleteUser(ActionEvent event) {
        Employee select = employeeTable.getSelectionModel().getSelectedItem();
        emp_list.remove(select);
       
        try{
                    Connection con = loginSQL.connectdb();
                    Statement stat = con.createStatement();
                    stat.executeUpdate("DELETE FROM employee WHERE emp_id =" + select.getEmp_id());
                    con.close();

                } catch (SQLException e){
                    e.printStackTrace(); 
                }
        employeeTable.getItems().clear();
        getEmployee();
    
    }
    public ObservableList<Employee> getEmployee(){
            ObservableList<Employee> listEmployee = FXCollections.observableArrayList();
       
        try {
            Connection con = loginSQL.connectdb();
            ResultSet rs = con.createStatement().executeQuery("SELECT * FROM employee");
            
            while(rs.next()){
                emp_list.add(new Employee(rs.getInt("emp_id"), rs.getString("emp_fname"), rs.getString("emp_lname"), rs.getInt("emp_age"), rs.getString("isAdmin"),rs.getString("email"), rs.getString("password")));
                
            }
            
             } catch (SQLException ex) {
                Logger.getLogger(LoginMainController.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println(ex.getMessage());
             }
        
       return listEmployee;
    }
      public void showEmployee(){
        
           try{
                   
                emp_id.setCellValueFactory(new PropertyValueFactory("emp_id"));
                emp_fname.setCellValueFactory(new PropertyValueFactory("emp_fname"));
                emp_lname.setCellValueFactory(new PropertyValueFactory("emp_lname"));
                emp_age.setCellValueFactory(new PropertyValueFactory("emp_age"));
                isAdmin.setCellValueFactory(new PropertyValueFactory("isAdmin"));
                email.setCellValueFactory(new PropertyValueFactory("email"));
                password.setCellValueFactory(new PropertyValueFactory("password"));
                
                employeeTable.setItems(emp_list);}
                catch (Exception e){
                    e.printStackTrace();
                
    }
       
    }
      private void executeQuery(String query) {
        Connection conn = loginSQL.connectdb();
        Statement st;
        try{
            st = conn.createStatement();
            st.executeUpdate(query);
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
          @FXML
    void updateUser(ActionEvent event) {
        Employee select = employeeTable.getSelectionModel().getSelectedItem();
        String query = "UPDATE  employee SET emp_fname  = '" + fnameField1.getText() + "', emp_lname = '" + lnameField1.getText() + "', email = '" +
                emailField1.getText() + "', password = '" + passField1.getText() + "' WHERE emp_id = " + select.getEmp_id();
        executeQuery(query);
        
        employeeTable.getItems().clear();
        getEmployee();
        fnameField1.clear();
            lnameField1.clear();
            emailField1.clear();
            passField1.clear();

    }
        @FXML
    void editUser(ActionEvent event) {
        Employee select = employeeTable.getSelectionModel().getSelectedItem();
        if(select == null){
            return;
            
        } else{
            fnameField1.setText(select.getEmp_fname());
            lnameField1.setText(select.getEmp_lname());
            emailField1.setText(select.getEmail());
            passField1.setText(select.getPassword());
        }
    }
}
