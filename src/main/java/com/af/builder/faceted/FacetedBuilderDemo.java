package com.af.builder.faceted;

class Person{
    //Address Details
    String streetName, postCode, city;

    //Employment information
    String companyName, position;
    int annualIncome;

    @Override
    public String toString() {
        return "Person{" +
                "streetName='" + streetName + '\'' +
                ", postCode='" + postCode + '\'' +
                ", city='" + city + '\'' +
                ", companyName='" + companyName + '\'' +
                ", position='" + position + '\'' +
                ", annualIncome=" + annualIncome +
                '}';
    }
}

class PersonBuilder{
    protected Person person = new Person();

    public PersonAddressBuilder lives(){
        return new PersonAddressBuilder(person);
    }

    public PersonEmploymentBuilder works(){
        return new PersonEmploymentBuilder(person);
    }

    public Person build(){
        return person;
    }
}

class PersonAddressBuilder extends PersonBuilder{
    public PersonAddressBuilder(Person person) {
        this.person = person;
    }

    public PersonAddressBuilder at(String streetName){
        this.person.streetName = streetName;
        return this;
    }

    public PersonAddressBuilder withPostCode(String postCode){
        this.person.postCode = postCode;
        return this;
    }

    public PersonAddressBuilder in(String city){
        this.person.city = city;
        return this;
    }
}

class PersonEmploymentBuilder extends PersonBuilder{
    public PersonEmploymentBuilder(Person person) {
        this.person = person;
    }

    public PersonEmploymentBuilder at(String companyName){
        this.person.companyName = companyName;
        return this;
    }

    public PersonEmploymentBuilder withPosition(String position){
        this.person.position = position;
        return this;
    }

    public PersonEmploymentBuilder havingIncome(int annualIncome){
        this.person.annualIncome = annualIncome;
        return this;
    }
}

public class FacetedBuilderDemo {
    public static void main(String[] args) {
        Person person = new PersonBuilder()
                            .lives()
                                .at("Nandanvan Society")
                                .withPostCode("382350")
                                .in("Ahmedabad")
                            .works()
                                .at("eClinicalWorks Pvt. Ltd")
                                .withPosition("Technical Mentor")
                                .havingIncome(999999999)
                            .build();

        System.out.println(person);
    }
}
