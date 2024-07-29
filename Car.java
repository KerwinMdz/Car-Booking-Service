public class Car {

    //Instance variables
    private final int regNumber;
    private final double rentalPrice;
    private boolean isElectric;
    private CarBrand carBrand;


    public Car(int regNumber, double rentalPrice, CarBrand carBrand){
        this.regNumber = regNumber;
        this.rentalPrice = rentalPrice;
        this.carBrand = carBrand;
        this.isElectric = (carBrand == CarBrand.TESLA);
    }
    //Constructor
    public Car(int regNumber, double rentalPrice, CarBrand carBrand, boolean isElectric){
        this.regNumber = regNumber;
        this.rentalPrice = rentalPrice;
        this.isElectric = isElectric;
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
    //Setters
    public void setCarBrand(CarBrand carBrand){
        this.carBrand = carBrand;
    }
    public void setisElectric(boolean isElectric){
        this.isElectric = isElectric;
    }
    //toString
    @Override
    public String toString(){
       return "RegNum: " + regNumber + " \n" + "Rental Price: " + rentalPrice + " \n" +
                "Brand: " + carBrand + " \n" + "Electric Car: " + isElectric;
    }
}
