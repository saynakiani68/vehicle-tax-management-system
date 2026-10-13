
package org.example.vehicletax;

public abstract class Car extends Vehicle {

    private double co2Emissions;


    public Car(String plateNumber, String maker,
               String model, double co2Emissions) {
        super(plateNumber, maker, model);
        setCo2Emissions(co2Emissions);
    }


    public double getCo2Emissions() {
        return co2Emissions;
    }


    public void setCo2Emissions(double co2Emissions) {
        if (!Double.isFinite(co2Emissions) || co2Emissions < 0) {
            throw new IllegalArgumentException(
                    "CO2 emissions must be a non-negative number.");
        }
        this.co2Emissions = co2Emissions;
    }

}
