package com.satyadev.emp_management.repository;

import com.satyadev.emp_management.entity.Employee;

import java.util.List;

public interface EmployeeCustomRepository {
    List<Employee> searchEmployees(
            String department,
            Double minSalary
    );
}
