package com.paypal.bfs.test.employeeserv.impl;

import com.paypal.bfs.test.employeeserv.EmployeeAdvice;
import com.paypal.bfs.test.employeeserv.api.model.Address;
import com.paypal.bfs.test.employeeserv.api.model.Employee;
import com.paypal.bfs.test.employeeserv.exception.EmployeeNotFoundException;
import com.paypal.bfs.test.employeeserv.exception.EmployeeValidationFailedException;
import com.paypal.bfs.test.employeeserv.service.EmployeeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Objects;

import static com.paypal.bfs.test.employeeserv.util.EmployeeMocks.mockEmployee;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = EmployeeAdvice.class)
@RunWith(SpringRunner.class)
public class EmployeeResourceImplTest {


    @Autowired
    private EmployeeResourceImpl employeeResource;

    @MockBean
    EmployeeService service;

    @Test
    public void employeeShouldBeReturnedFromService() {
        when(service.getEmployee(anyInt())).thenReturn(mockEmployee());
        ResponseEntity<Employee> employee = employeeResource.employeeGetById(1);
        assertEquals(employee.getStatusCode(), HttpStatus.OK);
        assertNotNull(employee.getBody());

    }

    @Test(expected = EmployeeNotFoundException.class)
    public void employeeNotFoundExceptionShouldBeThrownFromService() throws Exception {
        when(service.getEmployee(anyInt())).thenThrow(new EmployeeNotFoundException(1));
        ResponseEntity<Employee> employee = employeeResource.employeeGetById(1);

    }

    @Test
    public void employeeShouldBeSaved() {
        when(service.addEmployee(any(Employee.class))).thenReturn(mockEmployee());
        ResponseEntity<Employee> employee = employeeResource.createEmployee(mockEmployee());
        assertEquals(employee.getStatusCode(), HttpStatus.OK);
        assertNotNull(employee.getBody());

    }

    @Test
    public void employeeShouldBeSavedWhenAddressLine2isEmpty() {
        when(service.addEmployee(any(Employee.class))).thenReturn(mockEmployee());

        Employee emp = mockEmployee();
        emp.getAddress().setLine2("");
        ResponseEntity<Employee> employee = employeeResource.createEmployee(emp);
        assertEquals(employee.getStatusCode(), HttpStatus.OK);
        assertNotNull(employee.getBody());

    }

    @Test(expected = EmployeeValidationFailedException.class)
    public void employeeValidationFailedExceptionShouldBeThrownForEmptyObject() {

        ResponseEntity<Employee> employee = employeeResource.createEmployee(new Employee());

    }

    @Test(expected = EmployeeValidationFailedException.class)
    public void employeeValidationFailedExceptionShouldBeThrownForEmptyLastName() {

        Employee emp = mockEmployee();
        emp.setLastName("");

        ResponseEntity<Employee> employee = employeeResource.createEmployee(new Employee());

    }

    @Test(expected = EmployeeValidationFailedException.class)
    public void employeeValidationFailedExceptionShouldBeThrownForEmptyFirstName() {

        Employee emp = mockEmployee();
        emp.setFirstName("");

        ResponseEntity<Employee> employee = employeeResource.createEmployee(new Employee());
    }

    @Test(expected = EmployeeValidationFailedException.class)
    public void employeeValidationFailedExceptionShouldBeThrownForEmptyAddressLine1() {

        Employee emp = mockEmployee();
        emp.getAddress().setLine1("");

        ResponseEntity<Employee> employee = employeeResource.createEmployee(new Employee());
    }

    @Test(expected = EmployeeValidationFailedException.class)
    public void employeeValidationFailedExceptionShouldBeThrownForEmptyAddressCity() {

        Employee emp = mockEmployee();
        emp.getAddress().setCity("");

        ResponseEntity<Employee> employee = employeeResource.createEmployee(new Employee());
    }

    @Test(expected = EmployeeValidationFailedException.class)
    public void employeeValidationFailedExceptionShouldBeThrownForEmptyAddressCountry() {

        Employee emp = mockEmployee();
        emp.getAddress().setCountry("");

        ResponseEntity<Employee> employee = employeeResource.createEmployee(new Employee());
    }

    @Test(expected = EmployeeValidationFailedException.class)
    public void employeeValidationFailedExceptionShouldBeThrownForEmptyAddressZip() {

        Employee emp = mockEmployee();
        emp.getAddress().setZipCode("");

        ResponseEntity<Employee> employee = employeeResource.createEmployee(new Employee());
    }

    @Test(expected = EmployeeValidationFailedException.class)
    public void employeeValidationFailedExceptionShouldBeThrownForEmptyAddressState() {

        Employee emp = mockEmployee();
        emp.getAddress().setState("");

        ResponseEntity<Employee> employee = employeeResource.createEmployee(new Employee());
    }

    @Test(expected = EmployeeValidationFailedException.class)
    public void employeeValidationFailedExceptionShouldBeThrownForEmptyAddress() {

        Employee emp = mockEmployee();
        emp.setAddress(new Address());

        ResponseEntity<Employee> employee = employeeResource.createEmployee(new Employee());

    }


    @Test(expected = EmployeeValidationFailedException.class)
    public void employeeValidationFailedExceptionShouldBeThrownForEmptyDate() {

        Employee emp = mockEmployee();
        emp.setDateOfBirth("");
        ResponseEntity<Employee> employee = employeeResource.createEmployee(new Employee());
    }

    @Test(expected = EmployeeValidationFailedException.class)
    public void employeeValidationFailedExceptionShouldBeThrownForInvalidMonth() {

        Employee emp = mockEmployee();
        emp.setDateOfBirth("2016-13-01");
        ResponseEntity<Employee> employee = employeeResource.createEmployee(new Employee());
    }

    @Test(expected = EmployeeValidationFailedException.class)
    public void employeeValidationFailedExceptionShouldBeThrownForInvalidYear() {

        Employee emp = mockEmployee();
        emp.setDateOfBirth("0016-12-01");
        ResponseEntity<Employee> employee = employeeResource.createEmployee(new Employee());
    }

    @Test(expected = EmployeeValidationFailedException.class)
    public void employeeValidationFailedExceptionShouldBeThrownForInvalidDate() {

        Employee emp = mockEmployee();
        emp.setDateOfBirth("2016-12-40");
        ResponseEntity<Employee> employee = employeeResource.createEmployee(new Employee());
    }




}

