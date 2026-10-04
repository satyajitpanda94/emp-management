package com.satyadev.emp_management.repository;

import com.satyadev.emp_management.entity.Employee;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmployeeCustomRepositoryImpl implements EmployeeCustomRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Employee> searchEmployees(
            String department,
            Double minSalary) {
        String jpql = """
                SELECT e
                FROM Employee e
                WHERE e.department = :department
                AND e.salary >= :minSalary
                """;
        return entityManager
                .createQuery(jpql, Employee.class)
                .setParameter("department", department)
                .setParameter("minSalary", minSalary)
                .getResultList();
    }
}
