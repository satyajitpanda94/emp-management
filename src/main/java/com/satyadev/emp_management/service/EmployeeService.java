package com.satyadev.emp_management.service;

import com.satyadev.emp_management.entity.Employee;
import com.satyadev.emp_management.exception.EmployeeNotFoundException;
import com.satyadev.emp_management.repository.EmployeeRepository;
import jakarta.transaction.Transactional;
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

    public Employee createEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    public Employee getEmployee(Long id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException("Employee with id : " + id + " not found."));
    }

    public void deleteEmployeeByID(Long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException("Employee not found by id : " + id));

        employeeRepository.delete(employee);
    }

    public Employee updateEmployeeById(Long id, Employee employee) {
        Employee existingEmp = employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException("Employee not found by id : " + id));
        existingEmp.setName(employee.getName());
        existingEmp.setDepartment(employee.getDepartment());
        existingEmp.setEmail(employee.getEmail());
        existingEmp.setSalary(employee.getSalary());

        return employeeRepository.save(existingEmp);
    }

    public List<Employee> getEmployeeByDepartment(String department) {
        return employeeRepository.findByDepartment(department);
    }

    public List<Employee> getEmployeeBySalaryGreaterThan(Double salary) {
        return employeeRepository.findEmployeeWithSalaryGreaterThan(salary);
    }

    @Transactional
    public void updateSalary(Long id, Double salary) {
        int updatedRow=employeeRepository.updateSalary(id,salary);

        if(updatedRow==0){
            throw new EmployeeNotFoundException("Employee not found with id : "+id);
        }
    }
}
