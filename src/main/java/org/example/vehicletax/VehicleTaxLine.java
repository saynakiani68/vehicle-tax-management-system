
package org.example.vehicletax;

public class VehicleTaxLine {

    private final String plateNumber;
    private final String maker;
    private final String model;
    private final double tax;

    public VehicleTaxLine(Vehicle vehicle, double tax) {
        this.plateNumber = vehicle.getPlateNumber();
        this.maker = vehicle.getMaker();
        this.model = vehicle.getModel();
        this.tax = tax;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public String getMaker() {
        return maker;
    }

    public String getModel() {
        return model;
    }

    public double getTax() {
        return tax;
    }
}
