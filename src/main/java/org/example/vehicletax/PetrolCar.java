
package org.example.vehicletax;

public class PetrolCar extends Car {

    public PetrolCar(String plateNumber, String maker,
                     String model, double co2Emissions) {
        super(plateNumber, maker, model, co2Emissions);
    }

    @Override
    public double calculateAnnualTax() {
        return 1.4 * getCo2Emissions();
    }
}
