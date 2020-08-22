package com.af.prototype.serializable;

import org.apache.commons.lang3.SerializationUtils;

import java.io.Serializable;

class Address implements Serializable{
    private String streetName;
    private String postCode;
    private String city;

    public Address(String streetName, String postCode, String city) {
        this.streetName = streetName;
        this.postCode = postCode;
        this.city = city;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return "Address{" +
                "streetName='" + streetName + '\'' +
                ", postCode='" + postCode + '\'' +
                ", city='" + city + '\'' +
                '}';
    }
}

public class ProtoTypeUsingSerializableDemo {
    public static void main(String[] args) {
        Address address1 = new Address("Vrindavan Society", "382350", "Ahmedabad");
        Address address2 = SerializationUtils.roundtrip(address1);
        address2.setStreetName("Sundarvan Society");

        System.out.println(address1);
        System.out.println(address2);
    }
}
