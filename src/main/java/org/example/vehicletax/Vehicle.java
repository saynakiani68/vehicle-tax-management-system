
package org.example.vehicletax;

public abstract class Vehicle {

    private String plateNumber;
    private String maker;
    private String model;
    private Person owner;


    public Vehicle(String plateNumber, String maker, String model) {
        if (plateNumber == null || plateNumber.isBlank()
                || maker == null || maker.isBlank()
                || model == null || model.isBlank()) {
            throw new IllegalArgumentException(
                    "Vehicle information cannot be empty.");
        }

        this.plateNumber = plateNumber;
        this.owner = null;
        setMaker(maker);
        setModel(model);
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

    public Person getOwner() {
        return owner;
    }


    public void setMaker(String maker) {
        if (maker == null || maker.isBlank()) {
            throw new IllegalArgumentException("Maker cannot be empty.");
        }
        this.maker = maker;
    }

    public void setModel(String model) {
        if (model == null || model.isBlank()) {
            throw new IllegalArgumentException("Model cannot be empty.");
        }
        this.model = model;
    }


    public void setOwner(Person owner) {
        this.owner = owner;
    }


    public void changeOwner(Person newOwner) {
        setOwner(newOwner);
    }


    public void setPlateNumber(String plateNumber) {
        if (plateNumber == null || plateNumber.isBlank()) {
            throw new IllegalArgumentException(
                    "Plate number cannot be empty.");
        }

        this.plateNumber = plateNumber;
    }



    public abstract double calculateAnnualTax();
}
