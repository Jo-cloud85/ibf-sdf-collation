package main.java.sdfassessment.task01;

public class Person {
    private String firstName;
    private String lastName;
    private String address;
    private Integer years;
    
    public Person(String firstName, String lastName, String address, Integer years) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.years = years;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getAddress() {
        return address;
    }

    public Integer getYears() {
        return years;
    }
}
