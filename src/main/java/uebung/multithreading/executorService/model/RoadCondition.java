package uebung.multithreading.executorService.model;

enum RoadCondition {
    DRY(1.0),
    WET(0.2) {
        public double getTraction() {
            return temperature > 60 ? 0.4 : 0.2;
        }
    },
    SNOW(0.04);
    public static int temperature;
    private double traction;

    RoadCondition(double traction) {
        this.traction = traction;
    }

    public double getTraction() {
        return this.traction;
    }
}
