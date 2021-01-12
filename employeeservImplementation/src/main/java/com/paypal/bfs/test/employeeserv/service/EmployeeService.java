package com.paypal.bfs.test.employeeserv.service;

import com.paypal.bfs.test.employeeserv.api.model.Employee;

public interface EmployeeService {

    Employee addEmployee(Employee employee);

    Employee getEmployee(Integer employeeId);
}
