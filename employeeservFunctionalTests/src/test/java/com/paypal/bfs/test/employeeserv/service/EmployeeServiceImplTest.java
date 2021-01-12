package com.paypal.bfs.test.employeeserv.service;

import com.paypal.bfs.test.employeeserv.EmployeeAdvice;
import com.paypal.bfs.test.employeeserv.api.model.Employee;
import com.paypal.bfs.test.employeeserv.entity.EmployeeEntiy;
import com.paypal.bfs.test.employeeserv.exception.EmployeeNotFoundException;
import com.paypal.bfs.test.employeeserv.exception.EmployeeValidationFailedException;
import com.paypal.bfs.test.employeeserv.repo.EmployeeRepository;
import com.paypal.bfs.test.employeeserv.util.EmployeeMocks;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = EmployeeAdvice.class)
@RunWith(SpringRunner.class)
public class EmployeeServiceImplTest {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    EmployeeService service;

    @MockBean
    private EmployeeRepository employeeRepository;

    @Test
    public void whenConvertEmployeeDtoToEmployeeEntity_thenCorrect() {
        Employee emp= EmployeeMocks.mockEmployee();

        EmployeeEntiy empEntity = modelMapper.map(emp, EmployeeEntiy.class);
        assertEquals(empEntity.getFirstName(), emp.getFirstName());
        assertEquals(empEntity.getLastName(), emp.getLastName());
        assertEquals(empEntity.getDateOfBirth(), emp.getDateOfBirth());
        assertEquals(empEntity.getAddress().getCity(), emp.getAddress().getCity());
        assertEquals(empEntity.getAddress().getLine1(), emp.getAddress().getLine1());
        assertEquals(empEntity.getAddress().getLine2(), emp.getAddress().getLine2());
        assertEquals(empEntity.getAddress().getState(), emp.getAddress().getState());
        assertEquals(empEntity.getAddress().getZipCode(), emp.getAddress().getZipCode());
        assertEquals(empEntity.getAddress().getCountry(), emp.getAddress().getCountry());


    }

    @Test
    public void whenConvertEmployeeEntityToEmployeeDto_thenCorrect() {
        EmployeeEntiy emp= EmployeeMocks.mockEmployeeEntity();

        Employee empEntity = modelMapper.map(emp, Employee.class);
        assertEquals(empEntity.getFirstName(), emp.getFirstName());
        assertEquals(empEntity.getLastName(), emp.getLastName());
        assertEquals(empEntity.getDateOfBirth(), emp.getDateOfBirth());
        assertEquals(empEntity.getAddress().getCity(), emp.getAddress().getCity());
        assertEquals(empEntity.getAddress().getLine1(), emp.getAddress().getLine1());
        assertEquals(empEntity.getAddress().getLine2(), emp.getAddress().getLine2());
        assertEquals(empEntity.getAddress().getState(), emp.getAddress().getState());
        assertEquals(empEntity.getAddress().getZipCode(), emp.getAddress().getZipCode());
        assertEquals(empEntity.getAddress().getCountry(), emp.getAddress().getCountry());
    }

    @Test
    public void whenGetEmployeeByIdShouldReturnEntity() {

        when(employeeRepository.findById(any(Integer.class)))
                .thenReturn(Optional.of(EmployeeMocks.mockEmployeeEntity()));
        Employee employee = service.getEmployee(1);
        assertNotNull(employee);
    }

    @Test(expected = EmployeeNotFoundException.class)
    public void whenGetEmployeeByIdShouldThrowEmployeeNotFoundException() {

        when(employeeRepository.findById(any(Integer.class)))
                .thenReturn(Optional.empty());
        Employee employee = service.getEmployee(1);

    }

    @Test
    public void whenSaveEmployeeShouldReturnEntity() {

        when(employeeRepository.save(any(EmployeeEntiy.class)))
                .thenReturn(EmployeeMocks.mockEmployeeEntity());
        Employee employee = service.addEmployee(EmployeeMocks.mockEmployee());
        assertNotNull(employee);
    }
}
