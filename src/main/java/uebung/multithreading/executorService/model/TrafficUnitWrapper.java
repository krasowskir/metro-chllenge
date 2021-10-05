package uebung.multithreading.executorService.model;

public class TrafficUnitWrapper {
    private double speed;
    private Vehicle vehicle;
    private TrafficUnit trafficUnit;

    public TrafficUnitWrapper(TrafficUnit trafficUnit) {
        this.trafficUnit = trafficUnit;
    }

    public TrafficUnit getTrafficUnit() {
        return trafficUnit;
    }

    public double getSpeed() {
        return speed;
    }

    public TrafficUnitWrapper calcSpeed(double timeSec){
        double speed = this.vehicle.getSpeedMph(timeSec);
        this.speed = Math.round(speed * this.trafficUnit.getTraction());
        return this;
    }
}


