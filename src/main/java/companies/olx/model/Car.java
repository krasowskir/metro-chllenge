package companies.olx.model;

public class Car {

    private CarModel carModel;
    private String manufacturingYear;
    private String engineSerialNumber;

    public Car(CarModel carModel, String manufacturingYear, String engineSerialNumber) {
        this.carModel = carModel;
        this.manufacturingYear = manufacturingYear;
        this.engineSerialNumber = engineSerialNumber;
    }

    public Car() {
    }

    public CarModel getCarModel() {
        return carModel;
    }

    public void setCarModel(CarModel carModel) {
        this.carModel = carModel;
    }

    public String getManufacturingYear() {
        return manufacturingYear;
    }

    public void setManufacturingYear(String manufacturingYear) {
        this.manufacturingYear = manufacturingYear;
    }

    public String getEngineSerialNumber() {
        return engineSerialNumber;
    }

    public void setEngineSerialNumber(String engineSerialNumber) {
        this.engineSerialNumber = engineSerialNumber;
    }
}
