public class ElectricCar extends Car {

    private boolean isElectric;
    private boolean isBooked;
    private int batteryCapacity;

    public ElectricCar(int regNumber, double rentalPrice, CarBrand carBrand, String carType, int batteryCapacity){
        super(regNumber, rentalPrice, carBrand, carType);
        this.batteryCapacity = batteryCapacity;
        this.isElectric = true;
        this.isBooked = false;
    }

    public int getBatteryCapacity(){
        return batteryCapacity;
    }

    public boolean getisElectric(){
        return isElectric;
    }
}
