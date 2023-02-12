package com.fissionlabs.coe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fissionlabs.coe.entity.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

	
}
