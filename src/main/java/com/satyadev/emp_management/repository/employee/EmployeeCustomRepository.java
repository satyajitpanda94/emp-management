package com.satyadev.emp_management.repository.employee;

import com.satyadev.emp_management.entity.employee.Employee;

import java.util.List;

public interface EmployeeCustomRepository {
    List<Employee> searchEmployees(
            String department,
            Double minSalary
    );
}
