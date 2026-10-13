# Vehicle Tax Management System

A Java application developed for the Software Architecture and Object-Oriented Design course.

The project models a municipal vehicle tax management system. It allows the municipality to maintain records of people and vehicles, manage vehicle ownership, calculate annual taxes, and generate tax reports.

## Features

- Add, edit, delete, and search for people and vehicles.
- Register vehicles without an owner.
- Assign vehicles to owners and transfer ownership between people.
- Calculate annual vehicle taxes based on vehicle type.
- Generate annual tax listings showing each owner's vehicles, individual taxes, and total amount.
- Validate input data and prevent duplicate identifiers.

## Vehicle Tax Calculation

| Vehicle Type | Annual Tax |
|---|---|
| Motorcycle | 10% of engine displacement |
| Petrol Car | €1.40 × CO₂ emissions |
| Diesel Car | €1.80 × CO₂ emissions |
| Hybrid Car | €1.20 × CO₂ emissions |

Each vehicle calculates its own tax through the `calculateAnnualTax()` method.

## Design

The implementation follows object-oriented design principles, including inheritance, polymorphism, and encapsulation.

- `Person` stores personal information.
- `Vehicle` is an abstract class representing common vehicle properties.
- `Car` is an abstract subclass of `Vehicle`.
- `Motorcycle`, `PetrolCar`, `DieselCar`, and `HybridCar` implement their respective tax calculations.
- `Registry` manages stored people and vehicles.
- `VehicleTaxService` coordinates the main operations.
- `AnnualTaxListing`, `OwnerTaxEntry`, and `VehicleTaxLine` represent the annual tax report.

## Running the Project

1. Clone the repository.
2. Open the project in IntelliJ IDEA.
3. Import the project using `pom.xml`.
4. Run `Main.java`.

The main program demonstrates vehicle registration, ownership transfers, annual tax calculations, report generation, and functional tests.

## Documentation

The accompanying project report contains:

1. Use case descriptions and diagram.
2. Class diagrams.
3. Sequence diagram for vehicle ownership transfer.
4. Sequence diagram for annual tax listing generation.

## Author

Sayna Kiani

Master's in Artificial Intelligence for Connected Industries

Universitat Politècnica de Catalunya (UPC)
