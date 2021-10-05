package uebung.multithreading.executorService.model;

enum VehicleType {
    CAR("Car"), TRUCK("Truck"), CAB_CREW("CabCrew");
    private String type;

    VehicleType(String type) {
        this.type = type;
    }

    public String getType() {
        return this.type;
    }
}

