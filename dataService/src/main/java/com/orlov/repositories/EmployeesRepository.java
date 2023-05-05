package com.orlov.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeesRepository extends JpaRepository<Employees, Long> {
   Employees findEmployeeById(Long id);
    List<Employees> findAllEmployeeByDepartment(int department);
}
