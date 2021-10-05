package uebung.multithreading.executorService.model;

enum TireCondition {
    NEW(1.0), WORN(0.2);
    private double traction;

    TireCondition(double traction) {
        this.traction = traction;
    }

    public double getTraction() {
        return this.traction;
    }
}
