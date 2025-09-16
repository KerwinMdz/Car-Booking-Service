import java.util.ArrayList;

public class BookingServiceDAO {
    private final ArrayList<User> users;
    private final ArrayList<Bookings> bookings;

    public BookingServiceDAO(ArrayList<User> user, ArrayList<Bookings> booking){
        this.users = user;
        this.bookings = booking;
    }
    public ArrayList<User> getAllUsers(){
        return users;
    }

    public ArrayList<Bookings> getBookings() {
        return bookings;
    }

    public void addUser(User user){
        users.add(user);
    }

    public void addBooking(Bookings booking){
        bookings.add(booking);
    }
}

