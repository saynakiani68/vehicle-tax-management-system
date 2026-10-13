
package org.example.vehicletax;

public class Motorcycle extends Vehicle {

    private double engineDisplacement;


    public Motorcycle(String plateNumber, String maker,
                      String model, double engineDisplacement) {
        super(plateNumber, maker, model);
        setEngineDisplacement(engineDisplacement);
    }


    public double getEngineDisplacement() {
        return engineDisplacement;
    }


    public void setEngineDisplacement(double engineDisplacement) {
        if (!Double.isFinite(engineDisplacement)
                || engineDisplacement < 0) {
            throw new IllegalArgumentException(
                    "Engine displacement must be a non-negative number.");
        }
        this.engineDisplacement = engineDisplacement;
    }


    @Override
    public double calculateAnnualTax() {
        return 0.10 * engineDisplacement;
    }
}
