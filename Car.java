abstract public class Car {

    //Instance variables
    public int regNumber;
    private double rentalPrice;
    //private boolean isElectric;
    private CarBrand carBrand;
    public boolean isBooked;
    private String carType;

    //Constructor
    public Car(int regNumber, double rentalPrice, CarBrand carBrand, String carType){
        this.regNumber = regNumber;
        this.rentalPrice = rentalPrice;
        this.carBrand = carBrand;
//        if(carBrand == CarBrand.TESLA) {
//            this.isElectric = true;
//        }
        this.isBooked = false;
        this.carType = carType;
    }
    //Getters
    public int getRegNumber(){
        return regNumber;
    }
    public double getRentalPrice(){
        return rentalPrice;
    }

    public String getCarType(){
        return carType;
    }
    public CarBrand getCarBrand(){
        return carBrand;
    }
    public boolean getIsBooked(){
        return isBooked;
    }
    public void setIsBooked(boolean isBooked){
        this.isBooked = isBooked;
    }
}
