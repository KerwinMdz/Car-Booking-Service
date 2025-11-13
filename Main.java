
import java.util.ArrayList;
import java.util.Scanner;
import java.util.UUID;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static BookingServiceDAO bookingServiceDAO = new BookingServiceDAO(new ArrayList<>(), new ArrayList<>());

    private static ArrayList<Car> cars = new ArrayList<>();
//    private static ArrayList<ElectricCar> electricCars = new ArrayList<>();
//    private static ArrayList<GasCar> gasCars = new ArrayList<>();
//    private static ArrayList<HybridCar> hybridCars = new ArrayList<>();
    //private static ArrayList<User> user = new ArrayList<>();


    public static void main(String[] args) {

//        cars.add(new Car(1234, 99.00, CarBrand.BMW));
//        cars.add(new Car(4321, 55.00, CarBrand.TOYOTA));
//        electricCars.add(new ElectricCar(2134, 199.00, CarBrand.TESLA, 99));


//        user.add(new User("Kevin", "Jackson"));
//        user.add(new User("Zaya", "James"));

        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1. Add a user");
            System.out.println("2. Book a car");
            System.out.println("3. View all user booked cars");
            System.out.println("4. View all bookings");
            System.out.println("5. View available cars");
            System.out.println("6. View available electric cars");
            System.out.println("7. View all users");
            System.out.println("8. Add a car");
            System.out.println("9. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();

            switch (choice){
                case 1 -> addUser();
                case 2 -> bookCar();
                case 3 -> viewAllUserBookedCars();
                case 4 -> viewAllBookings();
                case 5 -> viewAllAvailableCars();
                case 6 -> viewAllElectricCars();
                case 7 -> viewAllUsers();
                case 8 -> addCar();
                case 9 -> {
                    System.out.println("Thank you for using the car booking service!");
                    return;
                }
                default -> System.out.println("Invalid Input.");
            }
        }
    }
    //Add a car
    private static void addCar(){
        System.out.print("Enter car registration number: ");
        int regNum = scanner.nextInt();
        //Test
        while (checkRegNo(regNum)){
            System.out.println("Registration Number Already Existed!");
            System.out.print("Enter car registration number: ");
            regNum = scanner.nextInt();
        }
        System.out.print("Enter car rental price: ");
        double rentalPrice = scanner.nextDouble();
        System.out.print("Enter car brand: ");
        String carBrand = scanner.next();
        System.out.print("Enter Car Type: ");
        String carType = scanner.next();

        try{
            CarBrand brand = CarBrand.valueOf(carBrand.toUpperCase());
        if(carType.equalsIgnoreCase("Electric")){
            System.out.print("Enter Battery Capacity: ");
            int batteryCapacity = scanner.nextInt();
            Car electricCar = new ElectricCar(regNum, rentalPrice, brand, carType, batteryCapacity);
            cars.add(electricCar);
            System.out.println("Successfully added: " + electricCar.getCarBrand());
        } else if (carType.equalsIgnoreCase("Gas")) {
            System.out.print("Enter Fuel Capacity: ");
            int fuelCapacity = scanner.nextInt();
            GasCar gasCar = new GasCar(regNum, rentalPrice, brand, carType, fuelCapacity);
            cars.add(gasCar);
            System.out.println("Successfully added: " + gasCar.getCarBrand());
        } else if (carType.equalsIgnoreCase("Hybrid")) {
            System.out.print("Enter Battery Capacity: ");
            int batteryCapacity = scanner.nextInt();
            System.out.print("Enter Fuel Capacity: ");
            int fuelCapacity = scanner.nextInt();
            HybridCar hybridCar = new HybridCar(regNum, rentalPrice, brand, carType, batteryCapacity,  fuelCapacity);
            cars.add(hybridCar);
            System.out.println("Successfully added: " + hybridCar.getCarBrand());
        } else
            System.out.print("Sorry Unable to Process!");
        } catch (Exception e) {
            System.out.println("Invalid brand!");
        }
    }

    //Add a user
    private static void addUser(){
        System.out.print("Enter first name: ");
        String firstName = scanner.next();
        System.out.print("Enter last name: ");
        String lastName = scanner.next();

        User user = new User(firstName, lastName);
        bookingServiceDAO.addUser(user);
        System.out.println("\nUser added successfully! User ID: " + user.getUserID());
    }

    private static void bookCar(){
        if(BookingServiceDAO.getAllUsers().isEmpty() && cars.isEmpty()){
            System.out.println("Add a user and car before you booking");
        } else {
            displayALlCars();
            System.out.println("Enter Car Registration No. : ");
            int regNum = scanner.nextInt();

            Car car = findCarByRegNum(regNum);
            if (car != null) {
                Main.displayAllUsers();
            }

            System.out.println("Enter User ID: ");
            scanner.nextLine();
            String userID = scanner.nextLine();
            try {
                UUID userUUID = UUID.fromString(userID);
                User user = findUserByID(userUUID);
                if (user != null) {
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
                    "Car Type: " + c.getCarType() + "\n" +
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
                if(c instanceof ElectricCar electricCar && c.getCarType().equalsIgnoreCase("Electric")){
                System.out.println("Car Brand: " + c.getCarBrand() + "\n" + "Registration Number: " + c.getRegNumber() + "\n" +
                        "Rental Price Per Day: " + c.getRentalPrice() + "\n" + "Car Type: " + c.getCarType() + "\n" + "Battery Capacity: " + electricCar.getBatteryCapacity() + " kWh\n");
            } else if (c instanceof HybridCar hybridCar && c.getCarType().equalsIgnoreCase("Hybrid")) {
                    System.out.println("Car Brand: " + c.getCarBrand() + "\n" + "Registration Number: " + c.getRegNumber() + "\n" +
                            "Rental Price Per Day: " + c.getRentalPrice() + "\n" + "Car Type: " + c.getCarType() + "\n" + "Battery Capacity: " + hybridCar.getBatteryCapacity() + " kWh" + "\n" +
                            "Fuel Capacity: " + hybridCar.getFuelCapacity() + " gallons\n");
                } else if (c instanceof GasCar gasCar && c.getCarType().equalsIgnoreCase("Gas")) {
                    System.out.println("Car Brand: " + c.getCarBrand() + "\n" + "Registration Number: " + c.getRegNumber() + "\n" +
                            "Rental Price Per Day: " + c.getRentalPrice() + "\n" + "Car Type: " + c.getCarType() + "\n" + "Fuel Capacity: " + gasCar.getFuelCapacity() + " gallons\n");
                }
            }
        }
        if(!found){
            System.out.println("No available cars right now.");
        }
    }
    //View all electric vehicles
    public static void viewAllElectricCars(){
        boolean found = false;
        for(Car c : cars) {
            if (c instanceof ElectricCar electricCar){
                if (electricCar.getisElectric() && !electricCar.getIsBooked()) {
                    found = true;
                    System.out.println("Car Brand: " + electricCar.getCarBrand() + "\n" + "Registration Number: " + electricCar.getRegNumber() + "\n" +
                            "Rental Price Per Day: " + electricCar.getRentalPrice() + "\n" + "Battery Capacity: " + electricCar.getBatteryCapacity() + "\n");
                }
        }

        }
        if(!found){
            System.out.println("No electric cars found.");
        }
    }
    //View All Users
    public static void viewAllUsers(){
        if (bookingServiceDAO.getAllUsers().isEmpty()){
                System.out.println("\nNo users found.");
            } else {
                for (User u : bookingServiceDAO.getAllUsers()) {
                    System.out.println("\nName: " + u.getFirstName() + " " + u.getLastName() + "\n" +
                            "User ID: " + u.getUserID());
                }
            }
        //-----Optimized, much cleaner way for future reference-----
        /**
         List<User> users = bookingServiceDAO.getAllUsers();
         if (users.isEmpty()){
         System.out.println("No users found.");
         } else {
         for (User u : users) {
         System.out.println("\nName: " + u.getFirstName() + " " + u.getLastName() + "\n" +
         "User ID: " + u.getUserID());
         }
         }
         **/
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
        for(User u : bookingServiceDAO.getAllUsers()){
            if(u.getUserID().equals(uuid)){
                return u;
            }
        }
        return null;
    }
//
//
//    //Display All Cars
    private static void displayALlCars() {
        for (Car c : cars) {
            if (c instanceof ElectricCar electricCar) {
                if (!electricCar.getIsBooked()) {
                    System.out.println("Car Brand: " + electricCar.getCarBrand() + "\n" + "Registration Number: " + electricCar.getRegNumber() + "\n" +
                            "Rental Price Per Day: " + electricCar.getRentalPrice() + "\n" + "Car Type: " + electricCar.getCarType() + "\n" + "Battery Capacity: " + electricCar.getBatteryCapacity());
                }
                } else if (c instanceof GasCar gasCar) {
                if(!gasCar.getIsBooked()) {
                    System.out.println("Car Brand: " + gasCar.getCarBrand() + "\n" + "Registration Number: " + gasCar.getRegNumber() + "\n" +
                            "Rental Price Per Day: " + gasCar.getRentalPrice() + "\n" + "Car Type: " + gasCar.getCarType() + "\n" + "Fuel Capacity: " + gasCar.getFuelCapacity());
                }
            } else if (c instanceof HybridCar hybridCar){
                if (!hybridCar.getIsBooked()) {
                    System.out.println("Car Brand: " + hybridCar.getCarBrand() + "\n" + "Registration Number: " + hybridCar.getRegNumber() + "\n" +
                            "Rental Price Per Day: " + hybridCar.getRentalPrice() + "\n" + "Car Type: " + hybridCar.getCarType() + "\n" + "Fuel Capacity: " + hybridCar.getFuelCapacity() + "\n" + "Battery Capacity: " + hybridCar.getBatteryCapacity());
                }
            }
        }
        }

        //Display All Users
        public static void displayAllUsers(){
            for (User u : bookingServiceDAO.getAllUsers()) {
                System.out.println("Name: " + u.getFirstName() + " " + u.getLastName() + "\n" +
                        "User ID: " + u.getUserID());
            }
        }
        //RegNo Validation
    public static boolean checkRegNo(int regNumber){
        for (Car car : cars){
            if(car.getRegNumber() == regNumber){
                return true;
            }
        }
        return false;
    }

        } //end of Main class





