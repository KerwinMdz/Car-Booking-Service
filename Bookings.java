import java.time.LocalDateTime;
import java.util.UUID;
public class Bookings {
    private final UUID bookingRefNo;
    private final LocalDateTime bookingDateTime;

    public Bookings(){
        this.bookingRefNo = UUID.randomUUID();
        this.bookingDateTime = LocalDateTime.now();
    }
    //Getters
    public UUID getBookingRefNo() {
        return bookingRefNo;
    }
    public LocalDateTime getBookingDateTime(){
        return bookingDateTime;
    }
    @Override
    public String toString(){
        return "Booking Reference no. " + bookingRefNo + " \n" +
                "Booking Date & Time: " + bookingDateTime;

    }
}
