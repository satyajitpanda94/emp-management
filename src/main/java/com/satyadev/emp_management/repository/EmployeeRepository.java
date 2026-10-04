package com.satyadev.emp_management.repository;

import com.satyadev.emp_management.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    List<Employee> findByDepartment(String department);

    @Query("""
            select e
            from Employee e
            where e.salary> :salary
            """)
    List<Employee> findEmployeeWithSalaryGreaterThan(@Param("salary") Double salary);

    @Modifying
    @Query(value = """
            Update employees e
            Set e.salary=:salary
            Where e.id=:id
            """,
            nativeQuery = true
    )
    int updateSalary(@Param("id") Long id, @Param("salary") Double salary);

    @Query(value = """
            select *
            from employees
            where department=:department
            and salary>:salary
            """,
            nativeQuery = true
    )
    List<Employee> findEmployeeByDepartmentAndSalary(
            @Param("department") String department,
            @Param("salary") Double salary
    );
}
