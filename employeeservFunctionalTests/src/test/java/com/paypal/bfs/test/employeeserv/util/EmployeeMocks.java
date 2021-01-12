package com.paypal.bfs.test.employeeserv.util;

import com.paypal.bfs.test.employeeserv.api.model.Address;
import com.paypal.bfs.test.employeeserv.api.model.Employee;
import com.paypal.bfs.test.employeeserv.entity.EmployeeEntiy;

public class EmployeeMocks {

    public static Employee mockEmployee() {

        Employee entity = new Employee();
        entity.setFirstName("Test");
        entity.setLastName("User");
        entity.setDateOfBirth("2020-06-10");

        Address address = new Address();
        address.setCity("st louis Park");
        address.setCountry("USA");
        address.setLine1("Test Line1");
        address.setLine1("Test Line2");
        address.setState("MN");
        address.setLine1("Test Line1");
        address.setZipCode("55426");

        entity.setAddress(address);

        return entity;
    }

    public static EmployeeEntiy mockEmployeeEntity() {

        EmployeeEntiy entity = new EmployeeEntiy();
        entity.setFirstName("Test");
        entity.setLastName("User");
        entity.setDateOfBirth("2020-06-10");

        com.paypal.bfs.test.employeeserv.entity.Address address = new com.paypal.bfs.test.employeeserv.entity.Address();
        address.setCity("st louis Park");
        address.setCountry("USA");
        address.setLine1("Test Line1");
        address.setLine1("Test Line2");
        address.setState("MN");
        address.setLine1("Test Line1");
        address.setZipCode("55426");

        entity.setAddress(address);

        return entity;
    }
}
