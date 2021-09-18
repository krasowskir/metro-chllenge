package companies.olx.model;

public class CarModel {

    private Brand brand;
    private String modelName;
    private int version;

    public CarModel() {
        this.version = 1;
    }

//    public void setBrand(Brand brand) {
//        this.brand = brand;
//    }


    public void setBrand(companies.olx.model.Brand brand) {
        this.brand = brand;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public Brand getBrand() {
        return brand;
    }

    public String getModelName() {
        return modelName;
    }

    public int getVersion() {
        return version;
    }

    @Override
    public String toString() {
        return "CarModel{" +
                "brand=" + brand +
                ", modelName='" + modelName + '\'' +
                ", version=" + version +
                '}';
    }

    public String toJson(){
        return "CarModel{" +
                "brand=" + brand +
                ", modelName='" + modelName + '\'' +
                ", version=" + version +
                '}';
    }

    public void incrementVersion(){
        this.version = this.version +1;
    }
}
