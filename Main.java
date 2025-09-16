
import java.util.ArrayList;
import java.util.Scanner;
import java.util.UUID;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static BookingServiceDAO bookingServiceDAO = new BookingServiceDAO(new ArrayList<>(), new ArrayList<>());

    private static ArrayList<Car> cars = new ArrayList<>();
    private static ArrayList<User> user = new ArrayList<>();


    public static void main(String[] args) {

        cars.add(new Car(1234, 99.00, CarBrand.BMW));
        cars.add(new Car(4321, 55.00, CarBrand.TOYOTA));
        cars.add(new Car(2134, 199.00, CarBrand.TESLA));


        user.add(new User("Kevin", "Jackson"));
        user.add(new User("Zaya", "James"));

        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1. Book a car");
            System.out.println("2. View all user booked cars");
            System.out.println("3. View all bookings");
            System.out.println("4. View available cars");
            System.out.println("5. View available electric cars");
            System.out.println("6. View all users");
            System.out.println("7. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();

            switch (choice){
                case 1 -> bookCar();
                case 2 -> viewAllUserBookedCars();
                case 3 -> viewAllBookings();
                case 4 -> viewAllAvailableCars();
                case 5 -> viewAllElectricCars();
                case 6 -> viewAllUsers();
                case 7 -> {
                    System.out.println("Thank you for using the car booking service!");
                    return;
                }
                default -> System.out.println("Invalid Input.");
            }
        }
    }
    private static void bookCar(){
        displayALlCars();
        System.out.println("Enter Car Registration No. : ");
        int regNum = scanner.nextInt();

        Car car = findCarByRegNum(regNum);
        if(car != null){
            Main.displayAllUsers();
        }

        System.out.println("Enter User ID: ");
        scanner.nextLine();
        String userID = scanner.nextLine();
        try{
            UUID userUUID = UUID.fromString(userID);
            User user = findUserByID(userUUID);
            if(user != null){
            Bookings booking = new Bookings(userUUID, regNum);
            bookingServiceDAO.addBooking(booking);
            car.setIsBooked(true);
            System.out.println("Car successfully booked with reference no. " + booking.getBookingRefNo());
        } else {
                System.out.println("User not found.");
            }
            } catch (IllegalArgumentException e) {
            System.out.println("Invalid UUID");
        }
    }

    //View All User Booked Cars
    public static void viewAllUserBookedCars(){
        displayAllUsers();
        System.out.print("Enter user ID: ");
        scanner.nextLine();
        String userID = scanner.nextLine();
        UUID uuid = UUID.fromString(userID);

        Bookings booking = findBookingById(uuid);
        if(booking != null){
            User user = findUserByID(booking.getUserBookingID());
            System.out.println("Booking Info:\n" + "ID: " + user.getUserID() + "\n" +
                    "Name: " + user.getFirstName() + " " + user.getLastName() + "\nBooking Ref No. : " + booking.getBookingRefNo());
        } else {
            System.out.println("\nNo bookings found for this user ID.");
        }
        }

        //View All Bookings!
    public static void viewAllBookings(){
        if(bookingServiceDAO.getBookings().isEmpty()){
            System.out.println("No Bookings Available");
            return;
        }
        for(Bookings b : bookingServiceDAO.getBookings()){
            User u = findUserByID(b.getUserBookingID());
            Car c = findCarByRegNum(b.getCarRegNumber());
            System.out.println("\nBooking Reference No. " + b.getBookingRefNo() + "\n" +
                    "User ID: " + u.getUserID() + "\n" +
                    "Name: " + u.getFirstName() + " " + u.getLastName() + "\n" +
                    "Car Reg No. : " + c.getRegNumber() + "\n" +
                    "Brand: " + c.getCarBrand() + "\n" +
                    "Booking time: " + b.getBookingDateTime() + "\n");
        }
    }

        //View Available Cars
    public static void viewAllAvailableCars(){
        if(cars.isEmpty()){
            System.out.println("No cars in system.");
            return;
        }

        boolean found = false;
        for(Car c : cars){
            if(!isCarBooked(c.getRegNumber())){
                found = true;
                System.out.println("Car Brand: " + c.getCarBrand() + "\n" + "Registration Number: " + c.getRegNumber() + "\n" +
                        "Rental Price Per Day: " + c.getRentalPrice() + "\n" + "Electric Vehicle: " + c.getisElectric() + "\n");
            }
        }
        if(!found){
            System.out.println("No available cars right now.");
        }
    }
    //View all electric vehicles
    public static void viewAllElectricCars(){
        boolean found = false;
        for(Car c : cars){
            if(c.getisElectric() && !c.getIsBooked()){
                found = true;
                System.out.println("Car Brand: " + c.getCarBrand() + "\n" + "Registration Number: " + c.getRegNumber() + "\n" +
                        "Rental Price Per Day: " + c.getRentalPrice() + "\n" + "Electric Vehicle: " + c.getisElectric() + "\n");
            }
        }
        if(!found){
            System.out.println("No electric cars found.");
        }
    }
    //View All Users
    public static void viewAllUsers(){
        for(User u : user){
            System.out.println("\nName: " + u.getFirstName() + " " + u.getLastName() + "\n" +
                    "User ID: " + u.getUserID());
        }
    }
    //isCarBooked Method
    private static boolean isCarBooked(int regNum){
        for(Bookings b : bookingServiceDAO.getBookings()){
            if(b.getCarRegNumber() == regNum){
                return true;
            }
        }
        return false;
    }
    //Find Booking By id
    public static Bookings findBookingById(UUID uuid){
        for(Bookings b : BookingServiceDAO.getBookings()){
                if(b.getUserBookingID().equals(uuid)){
                    return b;
                }
        }
        return null;
    }


    public static Car findCarByRegNum(int regNum){
        for(Car c : cars){
            if(c.getRegNumber() == regNum){
                return c;
            }
        }
        return null;
    }
    //Find User By id
    public static User findUserByID(UUID uuid){
        for(User u : user){
            if(u.getUserID().equals(uuid)){
                return u;
            }
        }
        return null;
    }
//
//
//    //Display All Cars
    private static void displayALlCars(){
        for(Car c : cars) {
            if (!c.getIsBooked()) {
                System.out.println("Car Brand: " + c.getCarBrand() + "\n" + "Registration Number: " + c.getRegNumber() + "\n" +
                        "Rental Price Per Day: " + c.getRentalPrice() + "\n" + "Electric Vehicle: " + c.getisElectric() + "\n");
            }
        }
    }
//
//    //Display All Users
    public static void displayAllUsers(){
        for(User u : user){
            System.out.println("Name: " + u.getFirstName() + " " + u.getLastName() + "\n" +
                    "User ID: " + u.getUserID());
        }
    }

        } //end of Main class





