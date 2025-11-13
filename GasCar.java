public class GasCar extends Car{

    private boolean isGascar;
    private boolean isBooked;
    private int fuelCapacity;

    public GasCar(int regNumber, double rentalPrice, CarBrand carBrand, String carType, int fuelCapacity){
        super(regNumber, rentalPrice, carBrand, carType);
        this.fuelCapacity = fuelCapacity;
        this.isGascar = true;
        this.isBooked = false;
    }
    public int getFuelCapacity(){
        return fuelCapacity;
    }
    public boolean getIsGasCar(){
        return isGascar;
    }
}
