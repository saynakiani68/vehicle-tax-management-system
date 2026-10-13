
package org.example.vehicletax;

public class Person {

    private String licenseNumber;
    private String name;
    private String surname;
    private String address;


    public Person(String licenseNumber, String name,
                  String surname, String address) {

        if (licenseNumber == null || licenseNumber.isBlank()) {
            throw new IllegalArgumentException(
                    "License number cannot be empty.");
        }

        this.licenseNumber = licenseNumber;
        setName(name);
        setSurname(surname);
        setAddress(address);
    }


    public String getLicenseNumber() {
        return licenseNumber;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getAddress() {
        return address;
    }


    public void setName(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Name cannot be empty.");
        }
        this.name = name;
    }

    public void setSurname(String surname) {
        if (surname == null || surname.isBlank()) {
            throw new IllegalArgumentException("Surname cannot be empty.");
        }
        this.surname = surname;
    }

    public void setAddress(String address) {
        if (address == null || address.isBlank()) {
            throw new IllegalArgumentException("Address cannot be empty.");
        }
        this.address = address;
    }

}
