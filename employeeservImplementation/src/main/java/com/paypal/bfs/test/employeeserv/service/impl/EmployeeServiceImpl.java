package com.paypal.bfs.test.employeeserv.service.impl;

import com.paypal.bfs.test.employeeserv.api.model.Employee;
import com.paypal.bfs.test.employeeserv.entity.EmployeeEntiy;
import com.paypal.bfs.test.employeeserv.exception.EmployeeNotFoundException;
import com.paypal.bfs.test.employeeserv.repo.EmployeeRepository;
import com.paypal.bfs.test.employeeserv.service.EmployeeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    private final ModelMapper modelMapper;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository, ModelMapper modelMapper) {
        this.employeeRepository = employeeRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Employee addEmployee(Employee employee) {
        EmployeeEntiy emp = employeeRepository.save(convertToEntity(employee));

        return convertToDto(emp);

    }

    @Override
    public Employee getEmployee(Integer employeeId) {
        EmployeeEntiy employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new EmployeeNotFoundException(employeeId));
        return convertToDto(employee);
    }

    private Employee convertToDto(EmployeeEntiy employeeEntiy) {
        return modelMapper.map(employeeEntiy, Employee.class);
    }

    private EmployeeEntiy convertToEntity(Employee postDto) {
        return modelMapper.map(postDto, EmployeeEntiy.class);
    }
}
