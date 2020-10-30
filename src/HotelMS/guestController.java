package HotelMS;

import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class guestController implements Initializable {

    @FXML
    private TableView<guest> guestTable;
    
    @FXML
    private TableColumn<guest, Integer> guest_no;

    @FXML
    private TableColumn<guest, String> guest_name;

    @FXML
    private TableColumn<guest, Integer> guest_cp;

    @FXML
    private TableColumn<guest, String> guest_address;

    @FXML
    private TableColumn<Room, Integer> room_number;
    
    @FXML
    private TableColumn<guest, Date> CheckinDate;

    @FXML
    private TableColumn<guest, Date> CheckoutDate;

 
    
    
    ObservableList<guest> guest_list = FXCollections.observableArrayList();

    
    @Override
    public void initialize (URL url, ResourceBundle rb){
        getGuest();
        showGuest();
    }
    @FXML
    void deleteGuest(ActionEvent event) {
           guest select = guestTable.getSelectionModel().getSelectedItem();
           guest_list.remove(select);
           String sql = "DELETE FROM guest WHERE guest_no =" +select.getGuest_no();
           String query1 ="UPDATE rooms SET room_availability = 'available' WHERE room_number='" + select.getRoom_number() + "'";
           executeQuery(sql);
           executeQuery(query1);
    }
    @FXML
    void refreshTable(ActionEvent event) {
        guestTable.getItems().clear();
        getGuest();
        showGuest();
    }
     public ObservableList<guest> getGuest(){
            ObservableList<guest> listGuest = FXCollections.observableArrayList();
       
        try {
            Connection con = loginSQL.connectdb();
            ResultSet rs = con.createStatement().executeQuery("SELECT * FROM guest");
            
            while(rs.next()){
                guest_list.add(new guest(rs.getInt("guest_no"), rs.getString("guest_name"), rs.getDouble("guest_cp"), rs.getString("guest_address"), rs.getInt("room_number"),rs.getDouble("guest_cardno"), rs.getInt("guest_cvc"), rs.getDate("CheckinDate"),rs.getDate("CheckoutDate")));
                
            }
            
             } catch (SQLException ex) {
                Logger.getLogger(LoginMainController.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println(ex.getMessage());
             }
        
       return listGuest;
    }
        public void showGuest(){
        
           try{
                   
                guest_no.setCellValueFactory(new PropertyValueFactory("guest_no"));
                guest_name.setCellValueFactory(new PropertyValueFactory("guest_name"));
                guest_cp.setCellValueFactory(new PropertyValueFactory("guest_cp"));
                guest_address.setCellValueFactory(new PropertyValueFactory("guest_address"));
                room_number.setCellValueFactory(new PropertyValueFactory("room_number"));
                CheckinDate.setCellValueFactory(new PropertyValueFactory("CheckinDate"));
                CheckoutDate.setCellValueFactory(new PropertyValueFactory("CheckoutDate"));
                
                
                guestTable.setItems(guest_list);}
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
}
