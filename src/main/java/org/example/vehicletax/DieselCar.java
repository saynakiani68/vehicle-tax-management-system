
package org.example.vehicletax;

public class DieselCar extends Car {

    public DieselCar(String plateNumber, String maker,
                     String model, double co2Emissions) {
        super(plateNumber, maker, model, co2Emissions);
    }

    @Override
    public double calculateAnnualTax() {
        return 1.8 * getCo2Emissions();
    }
}
