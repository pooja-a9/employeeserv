package com.paypal.bfs.test.employeeserv.impl;

import com.paypal.bfs.test.employeeserv.api.EmployeeResource;
import com.paypal.bfs.test.employeeserv.api.model.Employee;
import com.paypal.bfs.test.employeeserv.entity.EmployeeEntiy;
import com.paypal.bfs.test.employeeserv.exception.EmployeeValidationFailedException;
import com.paypal.bfs.test.employeeserv.exception.EmployeeNotFoundException;
import com.paypal.bfs.test.employeeserv.repo.EmployeeRepository;
import com.paypal.bfs.test.employeeserv.service.EmployeeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;


import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Implementation class for employee resource.
 */
@RestController
public class EmployeeResourceImpl implements EmployeeResource {

    final
    EmployeeService employeeService;

    @Autowired
    public EmployeeResourceImpl(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }


    @Override
    public ResponseEntity<Employee> employeeGetById(Integer id) {

        Employee employee = employeeService.getEmployee(id);

        return new ResponseEntity<>(employee, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Employee> createEmployee(Employee employee) {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        Set<ConstraintViolation<Employee>> violations = validator.validate(employee);


        if (violations.size() > 0) {
            List<String> errors = violations.stream().map(c -> c.getPropertyPath().toString() + " " + c.getMessage())
                    .collect(Collectors.toList());
            throw new EmployeeValidationFailedException(errors);
        } else if (!employee.getDateOfBirth().matches("([12]\\d{3}-(0[1-9]|1[0-2])-(0[1-9]|[12]\\d|3[01]))")) { //Validate Date of Birth
            throw new EmployeeValidationFailedException("dataOfBirth should match yyyy-MM-dd");
        }


        Employee savedEmployee = employeeService.addEmployee(employee);

        return new ResponseEntity<>(savedEmployee, HttpStatus.OK);

    }


}
