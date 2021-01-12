package com.paypal.bfs.test.employeeserv.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import com.paypal.bfs.test.employeeserv.entity.converter.AddressObjectConverter;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Date;


@Entity
public class EmployeeEntiy {


    private @Id
    @GeneratedValue
    Integer id;

    private String firstName;
    private String lastName;
    private String dateOfBirth;
    @Convert(converter = AddressObjectConverter.class)
    private Address address;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

}
