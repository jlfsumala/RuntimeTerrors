package HotelMS;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class LoginMainController implements Initializable  {

    @FXML
    private Label emp_loggedin;
    @FXML
    private TableView<Room> roomTable;
    
    @FXML
    private TableColumn<Room, Integer> room_number;

    @FXML
    private TableColumn<Room, String> room_type;

    @FXML
    private TableColumn<Room, String> room_availability;

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
    private JFXTextField userField;

    @FXML
    private JFXPasswordField passField;

    @FXML
    private JFXButton signinField;

    @FXML
    private Text promptText;

    @FXML
    private Label label;
    
    @FXML
    public Button closeButton;
    
    
    ObservableList<Employee> emp_list = FXCollections.observableArrayList();
    ObservableList<Room> room_list = FXCollections.observableArrayList();
    ObservableList<String> room_avail = FXCollections.observableArrayList();
                
    
    @Override
    public void initialize(URL location, ResourceBundle rb){
        
                try {
            Connection con = loginSQL.connectdb();
            ResultSet rs = con.createStatement().executeQuery("SELECT * FROM rooms");
            
            while(rs.next()){
                room_list.add(new Room (rs.getInt("room_number"), rs.getString("room_type"), rs.getString("room_availability")));
                
            }
                try{
                   
                room_number.setCellValueFactory(new PropertyValueFactory("room_number"));
                room_type.setCellValueFactory(new PropertyValueFactory("room_type"));
                room_availability.setCellValueFactory(new PropertyValueFactory("room_availability"));
                roomTable.setItems(room_list);}
                catch (Exception e){
                    e.printStackTrace();
                }
             } catch (SQLException ex) {
                Logger.getLogger(LoginMainController.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println(ex.getMessage());
             }
                
        
             try {
            Connection con = loginSQL.connectdb();
            ResultSet rs = con.createStatement().executeQuery("SELECT * FROM employee");
            
            while(rs.next()){
                emp_list.add(new Employee(rs.getInt("emp_id"), rs.getString("emp_fname"), rs.getString("emp_lname"), rs.getInt("emp_age"), rs.getString("isAdmin"), rs.getString("email"), rs.getString("password")));
                
            }
                try{
                   
                emp_id.setCellValueFactory(new PropertyValueFactory("emp_id"));
                emp_fname.setCellValueFactory(new PropertyValueFactory("emp_fname"));
                emp_lname.setCellValueFactory(new PropertyValueFactory("emp_lname"));
                emp_age.setCellValueFactory(new PropertyValueFactory("emp_age"));
                isAdmin.setCellValueFactory(new PropertyValueFactory("isAdmin"));
                employeeTable.setItems(emp_list);}
                catch (Exception e){
                    e.printStackTrace();
                }
                
             } catch (SQLException ex) {
                Logger.getLogger(LoginMainController.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println(ex.getMessage());
             }
            }
             
             
    
    @FXML
    public void handleCloseButtonAction(ActionEvent event) {
    Stage stage = (Stage) closeButton.getScene().getWindow();
    stage.close();
    }
    
    
    Stage dialogStage = new Stage();
    Scene scene;
 
        
Connection connection = null;
PreparedStatement preparedStatement = null;
ResultSet resultSet = null;
    
    
    
    @FXML
    void signin(ActionEvent event)throws Exception{
        String email = userField.getText();
        String password = passField.getText();
        connection = loginSQL.connectdb();

        String sql = "SELECT * FROM employee WHERE email = ? and password = ?";
        
        
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);
            resultSet = preparedStatement.executeQuery();
            //emp_loggedin.setText(resultSet.getString("emp_fname"));
            
            if(!resultSet.next()){
        
                label.setText("Login Failed");
            }              
            else{
                String isAdmin = resultSet.getString("isAdmin");
                if (isAdmin.equals("admin")){
                FXMLLoader loader = new FXMLLoader(getClass().getResource("Main.fxml"));
                Stage stage = new Stage();
                Parent root = loader.load();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.setTitle("Admin");
                stage.show();
                ((Stage)(((Button)event.getSource()).getScene().getWindow())).close(); 
                
            }
                else{
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("EmpUI.fxml"));
                Stage stage = new Stage();
                Parent root = loader.load();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.setTitle("Employee");
                stage.show();
                ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
                }
            }
        } catch (Exception e) {
            Logger.getLogger(LoginMainController.class.getName()).log(Level.SEVERE, null, e);
        }
           
    }

    @FXML
       void guest(ActionEvent event) throws Exception {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("guest.fxml"));

            Stage stage = new Stage();
            Parent root = loader.load();
            Scene scene = new Scene(root);
            stage.setScene(scene);
                stage.setTitle("Guest");
                stage.show();
        }
        
    @FXML
    void checkIn(ActionEvent event) throws Exception {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("checkin.fxml"));
            
            Stage stage = new Stage();
            Parent root = loader.load();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Check In");
            stage.show();
    }

    @FXML
    void checkOut(ActionEvent event) throws Exception {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("checkout.fxml"));
            
            Stage stage = new Stage();
            Parent root = loader.load();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Check out");
            stage.show();
    }

    @FXML
    void employee(ActionEvent event) throws Exception {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("employee.fxml"));
            
            Stage stage = new Stage();
            Parent root = loader.load();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("App");
            stage.show();

            
    }
     @FXML
    void refreshTable(ActionEvent event) {
               
            employeeTable.getItems().clear();
            roomTable.getItems().clear();
            
        
        
        try {
            Connection con = loginSQL.connectdb();
            ResultSet rs = con.createStatement().executeQuery("SELECT * FROM rooms");
            
            while(rs.next()){
                room_list.add(new Room (rs.getInt("room_number"), rs.getString("room_type"), rs.getString("room_availability")));
                
            }
                try{
                   
                room_number.setCellValueFactory(new PropertyValueFactory("room_number"));
                room_type.setCellValueFactory(new PropertyValueFactory("room_type"));
                room_availability.setCellValueFactory(new PropertyValueFactory("room_availability"));
                roomTable.setItems(room_list);}
                catch (Exception e){
                    e.printStackTrace();
                }
             } catch (SQLException ex) {
                Logger.getLogger(LoginMainController.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println(ex.getMessage());
             }
                try {
            Connection con = loginSQL.connectdb();
            ResultSet rs = con.createStatement().executeQuery("SELECT * FROM employee");
            
            while(rs.next()){
                emp_list.add(new Employee(rs.getInt("emp_id"), rs.getString("emp_fname"), rs.getString("emp_lname"), rs.getInt("emp_age"), rs.getString("isAdmin"), rs.getString("email"), rs.getString("password")));
                
            }
                try{
                   
                emp_id.setCellValueFactory(new PropertyValueFactory("emp_id"));
                emp_fname.setCellValueFactory(new PropertyValueFactory("emp_fname"));
                emp_lname.setCellValueFactory(new PropertyValueFactory("emp_lname"));
                emp_age.setCellValueFactory(new PropertyValueFactory("emp_age"));
                isAdmin.setCellValueFactory(new PropertyValueFactory("isAdmin"));
                employeeTable.setItems(emp_list);}
                catch (Exception e){
                    e.printStackTrace();
                }
                
             } catch (SQLException ex) {
                Logger.getLogger(LoginMainController.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println(ex.getMessage());
             }
            
    }
    @FXML
    void logOut(ActionEvent event) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Login.fxml"));

            Stage stage = new Stage();
            Parent root = loader.load();
            Scene scene = new Scene(root);
            stage.setScene(scene);
                stage.setTitle("Log In");
                stage.show();
        ((Stage)(((Button)event.getSource()).getScene().getWindow())).close(); 
        
    }
}
