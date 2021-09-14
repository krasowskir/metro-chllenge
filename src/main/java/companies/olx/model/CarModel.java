package companies.olx.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
public class CarModel {

    private Brand brand;
    private String modelName;
    private int version;

    public CarModel() {
        this.version = 1;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
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
