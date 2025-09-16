import java.time.LocalDateTime;
import java.util.UUID;
public class Bookings {
    private static UUID userBookingID;
    private static UUID bookingRefNo;
    private static LocalDateTime bookingDateTime;
    private int carRegNumber;

    public Bookings(UUID userBookingIDs, int carRegNumber){
        this.userBookingID = userBookingIDs;
        this.bookingRefNo = UUID.randomUUID();
        this.bookingDateTime = LocalDateTime.now();
        this.carRegNumber = carRegNumber;
    }
    //Getters
    public static UUID getBookingRefNo() {
        return bookingRefNo;
    }
    public static LocalDateTime getBookingDateTime(){
        return bookingDateTime;
    }
    public static UUID getUserBookingID(){
        return userBookingID;
    }
    public int getCarRegNumber(){
        return carRegNumber;
    }
}
