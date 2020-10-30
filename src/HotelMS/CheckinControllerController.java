package HotelMS;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.stage.Stage;
import javafx.util.Callback;

public class CheckinControllerController implements Initializable {

    @FXML
    private TextField GuestNameField;

    @FXML
    private TextField PhoneField;

    @FXML
    private TextField EmailField;

    @FXML
    private TextField AddressField;

    @FXML
    private TextField CardNumberField;

    @FXML
    private TextField CVCCodeField;

    @FXML
    private DatePicker CheckinDatePicker;

    @FXML
    private ChoiceBox chooseRoomType;

    @FXML
    private ChoiceBox chooseAvailableRoom;

    @FXML
    private DatePicker CheckoutDatePicker;

    @FXML
    private Button ClearButton;

    @FXML
    private Button EnterButton;
    
    ObservableList<guest> guest_list = FXCollections.observableArrayList();
    ObservableList<Room> room_list = FXCollections.observableArrayList();
    ObservableList<Integer> room_avail = FXCollections.observableArrayList();
    ObservableList<String> chooseType  = FXCollections.observableArrayList("Economy", "VIP");
    
    @Override
    public void initialize (URL url, ResourceBundle rb){
           chooseRoomType.setItems(chooseType);
           CheckinDatePicker.setValue(LocalDate.now());
           pickerDate();
        
    }

    @FXML
    void clearValues(ActionEvent event) {
        GuestNameField.setText("");
        PhoneField.setText("");
        EmailField.setText("");
        AddressField.setText("");
        CardNumberField.setText("");
        CVCCodeField.setText("");
        CheckoutDatePicker.setValue(null);
        chooseRoomType.valueProperty().set(null);
        chooseAvailableRoom.valueProperty().set(null);
        room_avail.clear();
        
    }

      @FXML
    void searchRoom(ActionEvent event) {
        fillChoiceBox();
        chooseAvailableRoom.setItems(room_avail);
    }
    
    @FXML
    void finishEntering(ActionEvent event) {
        
            
       
        String query = "INSERT INTO guest (guest_no, guest_name, guest_cp, guest_address, room_number, guest_cardno, guest_cvc, CheckinDate, CheckoutDate) VALUES (NULL,'" + GuestNameField.getText() + "','" + PhoneField.getText() + "','"
                + AddressField.getText() + "','" + chooseAvailableRoom.getSelectionModel().getSelectedItem() + "'," + CardNumberField.getText()+ ",'" + CVCCodeField.getText() + "','" + CheckinDatePicker.getValue() + "','" + CheckoutDatePicker.getValue() + "')";
      System.out.println(query);
        String query1 ="UPDATE rooms SET room_availability = 'occupied', guest_checkin='" + CheckinDatePicker.getValue() + "', guest_checkout='" + CheckoutDatePicker.getValue() + "' WHERE room_number='" + chooseAvailableRoom.getSelectionModel().getSelectedItem()+ "'";
        executeQuery(query);
        executeQuery(query1);
         room_avail.clear();
         Stage stage = (Stage) EnterButton.getScene().getWindow();
        stage.close();
        
           
            
        

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
    
    public ObservableList<guest> getGuest(){
            ObservableList<guest> listGuest = FXCollections.observableArrayList();
       
        try {
            Connection con = loginSQL.connectdb();
            ResultSet rs = con.createStatement().executeQuery("SELECT * FROM employee");
            
            while(rs.next()){
                guest_list.add(new guest(rs.getInt("guest_no"), rs.getString("guest_name"), rs.getInt("guest_cp"), rs.getString("guest_address"), rs.getInt("room_number"),rs.getInt("guest_cardno"), rs.getInt("guest_cvc"),rs.getDate("CheckinDate"),rs.getDate("CheckoutDate")));
                
            }
            
             } catch (SQLException ex) {
                Logger.getLogger(LoginMainController.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println(ex.getMessage());
             }
        
       return listGuest;
    }
    public void fillChoiceBox(){
    try {
            Connection con = loginSQL.connectdb();
            ResultSet rs = con.createStatement().executeQuery("SELECT * FROM rooms WHERE room_type = '" + chooseRoomType.getSelectionModel().getSelectedItem()+"'"); //+ "' and room_availability = 'available';");
            
            while(rs.next()){
                
                room_list.add(new Room (rs.getInt("room_number"), rs.getString("room_type"), rs.getString("room_availability")));
                System.out.println(room_list);
                if(rs.getString("room_availability").equals("available")){
                    int roomNum = rs.getInt("room_number");
                room_avail.add(roomNum);
                }
                
            }
                try{
                    //int roomNum = rs.getInt("room_number");
                    //room_avail.add(roomNum);
                }
                catch (Exception e){
                    e.printStackTrace();
                }
             } catch (SQLException ex) {
                Logger.getLogger(LoginMainController.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println(ex.getMessage());
             }
    }
    public void pickerDate(){
        final Callback<DatePicker, DateCell> dayCellFactory = 
            new Callback<DatePicker, DateCell>() {
                
                @Override
                public DateCell call(final DatePicker datePicker) {
                  
                    return new DateCell() {
                        
                        @Override
                        public void updateItem(LocalDate item, boolean empty) {
                            super.updateItem(item, empty);
                            if (item.isBefore(
                                    CheckinDatePicker.getValue().plusDays(1))
                                ) {
                                    setDisable(true);
                                    setStyle("-fx-background-color: #ffc0cb;");
                            }
                            long p = ChronoUnit.DAYS.between(
                                    CheckinDatePicker.getValue(), item
                                    
                            );
                            
                            setTooltip(new Tooltip(
                                "You're about to stay for " + p + " days")
                            );
                    }
                };
            }
        };            
                            CheckoutDatePicker.setDayCellFactory(dayCellFactory);
                            CheckoutDatePicker.setValue(CheckinDatePicker.getValue().plusDays(1));
    } 
        
}
