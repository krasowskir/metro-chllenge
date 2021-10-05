package uebung.multithreading.executorService.model;

//public class Vehicle {
//    private int weightPounds;
//    private Engine engine;
//
//    public Vehicle(int weightPounds, Engine engine) {
//        this.weightPounds = weightPounds;
//        this.engine = engine;
//    }
//
//    public double getSpeedMph(double timeSec){
//        return this.engine.getSpeedMph(timeSec, weightPounds);
//    }
//}
public interface Vehicle {
    void setSpeedModel(SpeedModel speedModel);
    double getSpeedMph(double timeSec);
    int getWeightPounds();
    int getHorsePower();

    enum VehicleType {
        CAR("Car"),
        TRUCK("Truck"),
        CAB_CREW("CabCrew");

        private String type;
        VehicleType(String type){
            this.type = type;
        }
        public String getType(){return this.type;}
    }
}