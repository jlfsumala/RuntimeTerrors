/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package HotelMS;

import java.sql.*;
import javax.swing.*;

    public class loginSQL {
        Connection conn = null;
       
            public static Connection connectdb() {
                try{
                   
                    Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/hotelDBase?useTimezon=true&serverTimezone=UTC", "root", "");
                    return conn;
                }
                catch(Exception e){
                JOptionPane.showMessageDialog(null, e);
                return null;
                }
            }
    }