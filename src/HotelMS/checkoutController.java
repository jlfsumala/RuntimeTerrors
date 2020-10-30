package HotelMS;

import java.sql.Connection;
import java.sql.Statement;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class checkoutController {

    @FXML
    private TextField CheckoutID;

    @FXML
    private TextField CheckoutName;
    
    @FXML
    private Button EnterCheckout;

    @FXML
    void finishEntering(ActionEvent event) {
        if(CheckoutID.getText()!=null || CheckoutName.getText() != null ){
        String query1 ="UPDATE rooms SET room_availability = 'available' WHERE room_number='" + CheckoutID.getText() + "'";
        String query  ="DELETE FROM guest WHERE guest_name ='"  + CheckoutName.getText()+ "'"; 
        executeQuery(query1);
        executeQuery(query);
        CheckoutID.clear();
        CheckoutName.clear();
        Stage stage = (Stage) EnterCheckout.getScene().getWindow();
        stage.close();
        }
    }
    @FXML
    void ClearCheckout(ActionEvent event) {
         CheckoutID.clear();
         CheckoutName.clear();

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
