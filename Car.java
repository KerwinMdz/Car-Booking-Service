public class Car {

    //Instance variables
    public int regNumber;
    private double rentalPrice;
    private boolean isElectric;
    private CarBrand carBrand;
    public boolean isBooked;

    //Constructor
    public Car(int regNumber, double rentalPrice, CarBrand carBrand){
        this.regNumber = regNumber;
        this.rentalPrice = rentalPrice;
        this.carBrand = carBrand;
        if(carBrand == CarBrand.TESLA) {
            this.isElectric = true;
        }
        this.isBooked = false;
    }
    //Getters
    public int getRegNumber(){
        return regNumber;
    }
    public double getRentalPrice(){
        return rentalPrice;
    }
    public boolean getisElectric(){
        return isElectric;
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
