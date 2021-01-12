package com.paypal.bfs.test.employeeserv.repo;

import com.paypal.bfs.test.employeeserv.entity.EmployeeEntiy;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<EmployeeEntiy,Integer> {
}

