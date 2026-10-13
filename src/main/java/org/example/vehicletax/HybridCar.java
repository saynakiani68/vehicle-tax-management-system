
package org.example.vehicletax;

public class HybridCar extends Car {

    public HybridCar(String plateNumber, String maker,
                     String model, double co2Emissions) {
        super(plateNumber, maker, model, co2Emissions);
    }

    @Override
    public double calculateAnnualTax() {
        return 1.2 * getCo2Emissions();
    }
}
