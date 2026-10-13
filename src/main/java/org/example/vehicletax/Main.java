
package org.example.vehicletax;

public class Main {

    public static void main(String[] args) {

        VehicleTaxService service = new VehicleTaxService();

        // REGISTER PERSONS
        service.addPerson(new Person(
                "L001", "John", "Smith", "Barcelona"));

        service.addPerson(new Person(
                "L002", "Anna", "Brown", "Madrid"));

        // REGISTER VEHICLES
        service.addVehicle(new PetrolCar(
                "1234ABC", "Toyota", "Yaris", 120));

        service.addVehicle(new DieselCar(
                "5678DEF", "Volkswagen", "Golf", 150));

        service.addVehicle(new HybridCar(
                "9012GHI", "Toyota", "Prius", 90));

        service.addVehicle(new Motorcycle(
                "3456JKL", "Honda", "CB500", 500));

        // ASSIGN OWNERSHIP
        service.transferVehicle("1234ABC", "L001");
        service.transferVehicle("5678DEF", "L001");
        service.transferVehicle("3456JKL", "L002");

        // ANNUAL LISTING BEFORE TRANSFER
        System.out.println("BEFORE TRANSFER");
        printListing(service.generateAnnualTaxListing());

        // TRANSFER OWNERSHIP
        double tax = service.transferVehicle(
                "5678DEF", "L002");

        System.out.printf(
                "\nTransfer successful. Annual tax: %.2f EUR%n",
                tax
        );

        // ANNUAL LISTING AFTER TRANSFER
        System.out.println("\nAFTER TRANSFER");
        printListing(service.generateAnnualTaxListing());

        // EDIT VEHICLE TAX DATA
        System.out.println("\n=== VEHICLE DATA UPDATE ===");

        service.editVehicleTaxData("1234ABC", 130);

        System.out.printf(
                "Updated petrol tax: %.2f EUR%n",
                service.calculateVehicleTax("1234ABC")
        );

        // UPDATED ANNUAL LISTING
        System.out.println("\nAFTER VEHICLE UPDATE");
        printListing(service.generateAnnualTaxListing());

        // FINAL FUNCTIONAL TESTS
        System.out.println("\n=== FINAL FUNCTIONAL TESTS ===");

        // Search persons
        System.out.println("Search person: "
                + service.searchPersons("John").size());

        // Search vehicles
        System.out.println("Search vehicle: "
                + service.searchVehicles("Toyota").size());

        // Edit person
        service.editPerson(
                "L001", "John", "Smith", "Valencia");

        System.out.println("Updated address: "
                + service.findPerson("L001").getAddress());

        // Add and delete a person
        service.addPerson(new Person(
                "L003", "David", "Wilson", "Seville"));

        System.out.println("Delete person: "
                + service.deletePerson("L003"));

        // Delete an ownerless vehicle
        System.out.println("Delete vehicle: "
                + service.deleteVehicle("9012GHI"));

        // Reject negative CO2
        try {
            service.editVehicleTaxData("1234ABC", -50);
            System.out.println("Negative CO2 accepted: ERROR");
        } catch (IllegalArgumentException e) {
            System.out.println("Negative CO2 rejected: PASS");
        }

        // Reject deletion of a person who owns vehicles
        try {
            service.deletePerson("L001");
            System.out.println("Owner deletion accepted: ERROR");
        } catch (IllegalStateException e) {
            System.out.println("Owner deletion rejected: PASS");
        }

        // REJECT EMPTY SEARCH CRITERIA
        try {
            service.searchPersons("");
            System.out.println("Empty person search: ERROR");
        } catch (IllegalArgumentException e) {
            System.out.println("Empty person search: PASS");
        }

        try {
            service.searchVehicles("");
            System.out.println("Empty vehicle search: ERROR");
        } catch (IllegalArgumentException e) {
            System.out.println("Empty vehicle search: PASS");
        }

        // VEHICLE PLATE UPDATE TEST
        System.out.println("\n=== PLATE UPDATE TEST ===");

        service.editVehiclePlate("1234ABC", "9999XYZ");

        System.out.println("New plate found: "
                + (service.findVehicle("9999XYZ") != null));

        System.out.println("Old plate removed: "
                + (service.findVehicle("1234ABC") == null));

        // REJECT DUPLICATE PLATE
        try {
            service.editVehiclePlate("9999XYZ", "5678DEF");
            System.out.println("Duplicate plate: ERROR");
        } catch (IllegalArgumentException e) {
            System.out.println("Duplicate plate: PASS");
        }

        // VERIFY OWNERSHIP AND TAX AFTER PLATE UPDATE
        Vehicle updatedVehicle = service.findVehicle("9999XYZ");

        System.out.println("Owner preserved: "
                + (updatedVehicle.getOwner()
                == service.findPerson("L001")));

        System.out.printf(
                "Tax after plate update: %.2f EUR%n",
                service.calculateVehicleTax("9999XYZ")
        );
    }

    // PRINT ANNUAL TAX LISTING
    private static void printListing(AnnualTaxListing listing) {

        System.out.println("\n=== ANNUAL TAX LISTING ===");

        System.out.println("Generated on: "
                + listing.getGeneratedOn());

        for (OwnerTaxEntry owner : listing.getOwnerEntries()) {

            System.out.println("\nOwner: "
                    + owner.getName() + " "
                    + owner.getSurname());

            System.out.println("License: "
                    + owner.getLicenseNumber());

            System.out.println("Address: "
                    + owner.getAddress());

            for (VehicleTaxLine vehicle : owner.getVehicleLines()) {

                System.out.printf(
                        "Vehicle: %s | %s %s | Tax: %.2f EUR%n",
                        vehicle.getPlateNumber(),
                        vehicle.getMaker(),
                        vehicle.getModel(),
                        vehicle.getTax()
                );
            }

            System.out.printf(
                    "Total annual tax: %.2f EUR%n",
                    owner.getTotalTax()
            );
        }
    }
}
