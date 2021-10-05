package uebung.multithreading.executorService.model;

import java.util.concurrent.RecursiveTask;

public class AverageSpeed extends RecursiveTask<Double> {
    private double timeSec;
    private DateLocation dateLocation;
    private int threshold, trafficUnitsNumber;

    public AverageSpeed(DateLocation dateLocation, double timeSec, int trafficUnitsNumber, int threshold) {
        this.timeSec = timeSec;
        this.dateLocation = dateLocation;
        this.threshold = threshold;
        this.trafficUnitsNumber = trafficUnitsNumber;
    }

    @Override
    protected Double compute() {
        if (trafficUnitsNumber < threshold) {
            double speed = FactoryTraffic.getTrafficUnitStream(dateLocation, trafficUnitsNumber)
                    .map(TrafficUnitWrapper::new)
                    .map(tuw -> tuw.setSpeedModel(FactorySpeedModel.generateSpeedModel(tuw.getTrafficUnit())))
                    .map(tuw -> tuw.calcSpeed(timeSec))
                    .mapToDouble(TrafficUnitWrapper::getSpeed)
                    .average()
                    .getAsDouble();
            System.out.println("speed=(" + trafficUnitsNumber + ")=" + speed);
            return (double) Math.round(speed);
        } else {
            int tun = trafficUnitsNumber / 2;
            System.out.println("tun = " + tun);
            AverageSpeed avs1 = new AverageSpeed(dateLocation, timeSec, tun, threshold);
            AverageSpeed avs2 = new AverageSpeed(dateLocation, timeSec, tun, threshold);

//            dasselbe
//            avs1.fork();
//            double res1 = avs1.join();
//            avs2.fork();
//            double res2 = avs2.join();

            double res1 = avs1.invoke();
            double res2 = avs2.invoke();

            return (double) Math.round((res1 + res2) / 2);
        }
    }

    private class TrafficUnitWrapper {
        private double speed;
        private Vehicle vehicle;
        private TrafficUnit trafficUnit;

        public TrafficUnitWrapper(TrafficUnit trafficUnit) {
            this.trafficUnit = trafficUnit;
            this.vehicle = FactoryVehicle.build(trafficUnit);
        }

        public TrafficUnitWrapper setSpeedModel(SpeedModel speedModel) {
            this.vehicle.setSpeedModel(speedModel);
            return this;
        }

        public double getSpeed() {
            return speed;
        }

        TrafficUnit getTrafficUnit() {
            return this.trafficUnit;
        }

        public TrafficUnitWrapper calcSpeed(double timeSec) {
            double speed = this.vehicle.getSpeedMph(timeSec);
            this.speed = Math.round(speed * this.trafficUnit.getTraction());
            return this;
        }
    }
}
