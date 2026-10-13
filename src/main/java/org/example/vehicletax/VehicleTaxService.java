
package org.example.vehicletax;

import java.util.List;

public class VehicleTaxService {

    private final Registry registry = new Registry();

    // PERSON MANAGEMENT

    public void addPerson(Person person) {
        registry.savePerson(person);
    }

    public Person findPerson(String licenseNumber) {
        return registry.findPerson(licenseNumber);
    }

    public List<Person> searchPersons(String criteria) {
        return registry.searchPersons(criteria);
    }

    public void editPerson(String licenseNumber,
                           String name,
                           String surname,
                           String address) {

        Person person = registry.findPerson(licenseNumber);

        if (person == null) {
            throw new IllegalArgumentException("Person not found.");
        }

        // Validate all fields before making any changes
        validateText(name, "Name");
        validateText(surname, "Surname");
        validateText(address, "Address");

        person.setName(name);
        person.setSurname(surname);
        person.setAddress(address);
    }

    public boolean deletePerson(String licenseNumber) {

        Person person = registry.findPerson(licenseNumber);

        if (person == null) {
            return false;
        }

        if (!registry.vehiclesOf(person).isEmpty()) {
            throw new IllegalStateException(
                    "Transfer owned vehicles before deleting this person.");
        }

        registry.deletePerson(person);
        return true;
    }

    // VEHICLE MANAGEMENT

    public void addVehicle(Vehicle vehicle) {

        if (vehicle == null) {
            throw new IllegalArgumentException(
                    "Vehicle cannot be null.");
        }

        if (vehicle.getOwner() != null) {
            throw new IllegalArgumentException(
                    "Register the vehicle without an owner first.");
        }

        registry.saveVehicle(vehicle);
    }

    public Vehicle findVehicle(String plateNumber) {
        return registry.findVehicle(plateNumber);
    }

    public List<Vehicle> searchVehicles(String criteria) {
        return registry.searchVehicles(criteria);
    }

    public void editVehicle(String plateNumber,
                            String maker,
                            String model) {

        Vehicle vehicle = registry.findVehicle(plateNumber);

        if (vehicle == null) {
            throw new IllegalArgumentException("Vehicle not found.");
        }

        // Validate before updating
        validateText(maker, "Maker");
        validateText(model, "Model");

        vehicle.setMaker(maker);
        vehicle.setModel(model);

        registry.saveVehicle(vehicle);
    }

    public boolean deleteVehicle(String plateNumber) {

        Vehicle vehicle = registry.findVehicle(plateNumber);

        if (vehicle == null) {
            return false;
        }

        vehicle.setOwner(null);
        registry.deleteVehicle(vehicle);

        return true;
    }

    // OWNERSHIP TRANSFER

    public double transferVehicle(String plateNumber,
                                  String licenseNumber) {

        Vehicle vehicle = registry.findVehicle(plateNumber);
        Person newOwner = registry.findPerson(licenseNumber);

        if (vehicle == null || newOwner == null) {
            throw new IllegalArgumentException(
                    "Vehicle or person not found.");
        }

        if (vehicle.getOwner() == newOwner) {
            throw new IllegalStateException(
                    "This person already owns the vehicle.");
        }

        double annualTax = calculateVehicleTax(plateNumber);

        Person previousOwner = vehicle.getOwner();

        try {
            vehicle.changeOwner(newOwner);

            // Save the updated vehicle in the registry
            registry.saveVehicle(vehicle);

        } catch (RuntimeException e) {
            vehicle.setOwner(previousOwner);
            throw e;
        }

        return annualTax;
    }

    // TAX CALCULATION

    public double calculateVehicleTax(String plateNumber) {

        Vehicle vehicle = registry.findVehicle(plateNumber);

        if (vehicle == null) {
            throw new IllegalArgumentException("Vehicle not found.");
        }

        return Math.round(
                vehicle.calculateAnnualTax() * 100.0
        ) / 100.0;
    }

    public double calculateOwnerTotalTax(String licenseNumber) {

        Person owner = registry.findPerson(licenseNumber);

        if (owner == null) {
            throw new IllegalArgumentException("Person not found.");
        }

        double total = 0.0;

        for (Vehicle vehicle : registry.vehiclesOf(owner)) {
            total += calculateVehicleTax(vehicle.getPlateNumber());
        }

        return total;
    }

    // ANNUAL TAX LISTING

    public AnnualTaxListing generateAnnualTaxListing() {

        AnnualTaxListing listing = new AnnualTaxListing();

        for (Person owner : registry.getOwners()) {

            OwnerTaxEntry entry = listing.addOwnerEntry(owner);

            for (Vehicle vehicle : registry.vehiclesOf(owner)) {

                double tax = calculateVehicleTax(
                        vehicle.getPlateNumber()
                );

                entry.addVehicleLine(vehicle, tax);
            }
        }

        return listing;
    }


    public void editVehiclePlate(String oldPlate, String newPlate) {
        registry.updateVehiclePlate(oldPlate, newPlate);
    }


    // EDIT TAX-RELATED VEHICLE DATA

    public void editVehicleTaxData(String plateNumber,
                                   double value) {

        Vehicle vehicle = registry.findVehicle(plateNumber);

        if (vehicle == null) {
            throw new IllegalArgumentException("Vehicle not found.");
        }

        if (vehicle instanceof Car car) {
            car.setCo2Emissions(value);

        } else if (vehicle instanceof Motorcycle motorcycle) {
            motorcycle.setEngineDisplacement(value);
        }

        registry.saveVehicle(vehicle);
    }

    // REGISTRY ACCESS

    public Registry getRegistry() {
        return registry;
    }

    // VALIDATION

    private static void validateText(String value,
                                     String fieldName) {

        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException(
                    fieldName + " cannot be empty.");
        }
    }
}
