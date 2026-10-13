
package org.example.vehicletax;

import java.util.ArrayList;
import java.util.List;

public class OwnerTaxEntry {

    private final String licenseNumber;
    private final String name;
    private final String surname;
    private final String address;

    private final List<VehicleTaxLine> vehicleLines =
            new ArrayList<>();

    private double totalTax = 0.0;

    public OwnerTaxEntry(Person owner) {
        this.licenseNumber = owner.getLicenseNumber();
        this.name = owner.getName();
        this.surname = owner.getSurname();
        this.address = owner.getAddress();
    }

    public void addVehicleLine(Vehicle vehicle, double tax) {
        vehicleLines.add(new VehicleTaxLine(vehicle, tax));
        totalTax += tax;
    }

    public String getLicenseNumber() {
        return licenseNumber;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getAddress() {
        return address;
    }

    public List<VehicleTaxLine> getVehicleLines() {
        return List.copyOf(vehicleLines);
    }

    public double getTotalTax() {
        return totalTax;
    }
}
