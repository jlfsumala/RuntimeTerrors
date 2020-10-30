
package HotelMS;

public class Room {
    private int room_number;
    private String room_type;
    private String room_availability;

        public Room(int room_number, String room_type, String room_availability){
            this.room_number = room_number;
            this.room_type = room_type;
            this.room_availability = room_availability;
        }

    public int getRoom_number() {
        return room_number;
    }

    public void setRoom_id(int room_number) {
        this.room_number = room_number;
    }

    public String getRoom_type() {
        return room_type;
    }

    public void setRoom_type(String room_type) {
        this.room_type = room_type;
    }

    public String getRoom_availability() {
        return room_availability;
    }

    public void setRoom_availability(String room_availability) {
        this.room_availability = room_availability;
    }
         
}
