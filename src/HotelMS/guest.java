
package HotelMS;

import java.sql.Date;


public class guest {
    private int guest_no;
    private String guest_name;
    private double guest_cp;
    private String guest_address;
    private int room_number;
    protected double guest_cardno;
    protected int guest_cvc;
    private Date CheckinDate;
    private Date CheckoutDate;
    
    
    public guest(int guest_no, String guest_name, double guest_cp, String guest_address, int room_number, double guest_cardno, int guest_cvc, Date CheckinDate, Date CheckoutDate){
        this.guest_no = guest_no;
        this.guest_name = guest_name;
        this.guest_cp = guest_cp;
        this.guest_address = guest_address;
        this.room_number = room_number;
        this.guest_cardno = guest_cardno;
        this.guest_cvc = guest_cvc;
        this.CheckinDate = CheckinDate;
        this.CheckoutDate = CheckoutDate;
    }

    public Date getCheckinDate() {
        return CheckinDate;
    }

    public void setCheckinDate(Date CheckinDate) {
        this.CheckinDate = CheckinDate;
    }

    public Date getCheckoutDate() {
        return CheckoutDate;
    }

    public void setCheckoutDate(Date CheckoutDate) {
        this.CheckoutDate = CheckoutDate;
    }

    public int getRoom_number() {
        return room_number;
    }

    public void setRoom_number(int room_number) {
        this.room_number = room_number;
    }

    public int getGuest_no() {
        return guest_no;
    }

    public void setGuest_no(int guest_no) {
        this.guest_no = guest_no;
    }

    public String getGuest_name() {
        return guest_name;
    }

    public void setGuest_name(String guest_name) {
        this.guest_name = guest_name;
    }

    public double getGuest_cp() {
        return guest_cp;
    }

    public void setGuest_cp(int guest_cp) {
        this.guest_cp = guest_cp;
    }

    public String getGuest_address() {
        return guest_address;
    }

    public void setGuest_address(String guest_address) {
        this.guest_address = guest_address;
    }

    public double getGuest_cardno() {
        return guest_cardno;
    }

    public void setGuest_cardno(int guest_cardno) {
        this.guest_cardno = guest_cardno;
    }

    public int getGuest_cvc() {
        return guest_cvc;
    }

    public void setGuest_cvc(int guest_cvc) {
        this.guest_cvc = guest_cvc;
    }
    
}
