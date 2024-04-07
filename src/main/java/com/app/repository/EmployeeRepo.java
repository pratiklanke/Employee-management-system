package com.app.repository;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.entities.Employee;

@Repository
@Transactional
public interface EmployeeRepo extends JpaRepository<Employee, Integer> {
	
	Optional<Employee> findByEmail(String email);

}
