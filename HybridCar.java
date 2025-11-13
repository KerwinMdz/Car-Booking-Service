public class HybridCar extends Car{

    private boolean isElectric;
    private boolean isBooked;
    private int batteryCapacity;
    private int fuelCapacity;

    public HybridCar(int regNumber, double rentalPrice, CarBrand carBrand, String carType, int batteryCapacity, int fuelCapacity){
        super(regNumber, rentalPrice, carBrand, carType);
        this.fuelCapacity = fuelCapacity;
        this.batteryCapacity = batteryCapacity;
        this.isElectric = true;
        this.isBooked = false;
    }

    public int getBatteryCapacity(){
        return batteryCapacity;
    }

    public int getFuelCapacity(){
        return fuelCapacity;
    }
}
