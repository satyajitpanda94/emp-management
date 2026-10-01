package com.satyadev.emp_management.service;

import com.satyadev.emp_management.entity.Employee;
import com.satyadev.emp_management.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }
}
