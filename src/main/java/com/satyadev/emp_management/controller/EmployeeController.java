package com.satyadev.emp_management.controller;

import com.satyadev.emp_management.entity.employee.Employee;
import com.satyadev.emp_management.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public ResponseEntity<List<Employee>> getEmployees() {
        return ResponseEntity.ok(
                employeeService.getAllEmployees()
        );
    }

    @PostMapping
    public ResponseEntity<Employee> createEmployee(@Valid @RequestBody Employee employee) {
        Employee saved = employeeService.createEmployee(employee);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(saved);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) {
        return ResponseEntity.ok(
                employeeService.getEmployee(id)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployeeById(@PathVariable Long id) {
        employeeService.deleteEmployeeByID(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Employee> updateEmployeeById(@PathVariable Long id, @RequestBody Employee employee) {
        Employee saved = employeeService.updateEmployeeById(id, employee);
        return ResponseEntity.ok(saved);
    }

    @GetMapping("/department/{department}")
    public ResponseEntity<List<Employee>> getEmployeeByDepartment(@PathVariable String department) {
        return ResponseEntity.ok(employeeService.getEmployeeByDepartment(department));
    }

    @GetMapping("/salary")
    public ResponseEntity<List<Employee>> getEmployeeBySalaryGreaterThan(@RequestParam Double salary) {
        return ResponseEntity.ok(employeeService.getEmployeeBySalaryGreaterThan(salary));
    }

    @PutMapping("/{id}/salary")
    public ResponseEntity<String> updateSalary(
            @PathVariable Long id,
            @RequestParam Double salary
    ) {
        employeeService.updateSalary(id, salary);
        return ResponseEntity.ok("Salary Updated successfully.");
    }

    @GetMapping("/search")
    public ResponseEntity<List<Employee>> findEmployeeByDepartmentAndSalary(
            @RequestParam String department,
            @RequestParam Double salary
    ) {
        return ResponseEntity.ok(employeeService.findEmpByDepartmentAndSalary(department, salary));
    }

    @GetMapping("/customsearch")
    public ResponseEntity<List<Employee>> searchEmployees(
            @RequestParam String department,
            @RequestParam Double minSalary) {

        return ResponseEntity.ok(
                employeeService.searchEmployees(
                        department,
                        minSalary
                )
        );
    }
}
