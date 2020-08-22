package com.af.prototype.copyconstructor;

class Address{
    private String streetAddress;
    private String city;
    private String country;

    public Address(String streetAddress, String city, String country) {
        this.streetAddress = streetAddress;
        this.city = city;
        this.country = country;
    }

    public Address(Address other){
        this(other.streetAddress, other.city, other.country);
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return "Address{" +
                "streetAddress='" + streetAddress + '\'' +
                ", city='" + city + '\'' +
                ", country='" + country + '\'' +
                '}';
    }
}

class Employee {
    private String name;
    private Address address;

    public Employee(String name, Address address) {
        this.name = name;
        this.address = address;
    }

    public Employee(Employee other){
        this.name = other.getName();
        this.address = new Address(other.getAddress());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", address=" + address +
                '}';
    }
}

public class CopyConstructorDemo {
    public static void main(String[] args) {
        Employee ravi = new Employee("Ravi", new Address(
                "Vrindavan Society", "Ahmedabad", "India"
        ));

        Employee aakash = new Employee(ravi);
        aakash.setName("Aakash");

        System.out.println(ravi);
        System.out.println(aakash);
    }
}
