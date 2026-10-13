
package org.example.vehicletax;

import java.util.ArrayList;
import java.util.List;

public class Registry {

    private final List<Person> persons = new ArrayList<>();
    private final List<Vehicle> vehicles = new ArrayList<>();

    // FIND PERSON
    public Person findPerson(String licenseNumber) {

        for (Person person : persons) {
            if (person.getLicenseNumber().equals(licenseNumber)) {
                return person;
            }
        }

        return null;
    }

    // FIND VEHICLE
    public Vehicle findVehicle(String plateNumber) {

        for (Vehicle vehicle : vehicles) {
            if (vehicle.getPlateNumber().equals(plateNumber)) {
                return vehicle;
            }
        }

        return null;
    }

    // SEARCH PERSONS
    public List<Person> searchPersons(String criteria) {

        if (criteria == null || criteria.isBlank()) {
            throw new IllegalArgumentException(
                    "Search criteria cannot be empty.");
        }

        List<Person> results = new ArrayList<>();

        for (Person person : persons) {

            if (person.getLicenseNumber().equalsIgnoreCase(criteria)
                    || person.getName().equalsIgnoreCase(criteria)
                    || person.getSurname().equalsIgnoreCase(criteria)) {

                results.add(person);
            }
        }

        return results;
    }

    // SEARCH VEHICLES
    public List<Vehicle> searchVehicles(String criteria) {

        if (criteria == null || criteria.isBlank()) {
            throw new IllegalArgumentException(
                    "Search criteria cannot be empty.");
        }

        List<Vehicle> results = new ArrayList<>();

        for (Vehicle vehicle : vehicles) {

            if (vehicle.getPlateNumber().equalsIgnoreCase(criteria)
                    || vehicle.getMaker().equalsIgnoreCase(criteria)
                    || vehicle.getModel().equalsIgnoreCase(criteria)) {

                results.add(vehicle);
            }
        }

        return results;
    }

    // SAVE PERSON
    public void savePerson(Person person) {

        if (person == null
                || findPerson(person.getLicenseNumber()) != null) {

            throw new IllegalArgumentException(
                    "Invalid or duplicate person.");
        }

        persons.add(person);
    }

    // SAVE VEHICLE
    public void saveVehicle(Vehicle vehicle) {

        if (vehicle == null) {
            throw new IllegalArgumentException(
                    "Vehicle cannot be null.");
        }

        Vehicle existing = findVehicle(vehicle.getPlateNumber());

        if (existing != null && existing != vehicle) {
            throw new IllegalArgumentException(
                    "A different vehicle with this plate already exists.");
        }

        if (existing == null) {
            vehicles.add(vehicle);
        }
    }

    // DELETE PERSON
    public void deletePerson(Person person) {
        persons.remove(person);
    }


    public void updateVehiclePlate(String oldPlate, String newPlate) {

        if (newPlate == null || newPlate.isBlank()) {
            throw new IllegalArgumentException(
                    "New plate number cannot be empty.");
        }

        Vehicle vehicle = findVehicle(oldPlate);

        if (vehicle == null) {
            throw new IllegalArgumentException(
                    "Vehicle not found.");
        }

        Vehicle duplicate = findVehicle(newPlate);

        if (duplicate != null && duplicate != vehicle) {
            throw new IllegalArgumentException(
                    "Plate number already exists.");
        }

        vehicle.setPlateNumber(newPlate);
    }


    // DELETE VEHICLE
    public void deleteVehicle(Vehicle vehicle) {
        vehicles.remove(vehicle);
    }

    // GET CURRENT OWNERS
    public List<Person> getOwners() {

        List<Person> owners = new ArrayList<>();

        for (Person person : persons) {

            if (!vehiclesOf(person).isEmpty()) {
                owners.add(person);
            }
        }

        return owners;
    }

    // GET VEHICLES OF AN OWNER
    public List<Vehicle> vehiclesOf(Person owner) {

        List<Vehicle> results = new ArrayList<>();

        for (Vehicle vehicle : vehicles) {

            if (vehicle.getOwner() == owner) {
                results.add(vehicle);
            }
        }

        return results;
    }
}
