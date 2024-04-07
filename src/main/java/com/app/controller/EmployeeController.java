package com.app.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.EmployeeDto;
import com.app.empexception.EmployeeException;
import com.app.service.EmployeeService;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

	@Autowired
	private EmployeeService empServ;

	@PostMapping
	public ResponseEntity<?> addNewEmployee(@RequestBody @Valid EmployeeDto dto) throws EmployeeException {
		System.out.println("in add emp " + dto);
		return ResponseEntity.status(HttpStatus.CREATED).body(empServ.add(dto));
	}

	@GetMapping
	public ResponseEntity<?> getAllEmpDetails() {
		return ResponseEntity.ok(empServ.getAllEmp());
	}

	@GetMapping("{Id}")
	public ResponseEntity<?> getEmpDetails(@PathVariable Integer Id) throws EmployeeException {
		System.out.println("Emp get 1");
		return ResponseEntity.ok(empServ.getOneEmp(Id));
	}

	@PutMapping("/{Id}")
	public ResponseEntity<?> updateEmployee(@PathVariable Integer Id, @RequestBody EmployeeDto dto)
			throws EmployeeException {
		return ResponseEntity.ok(empServ.update(Id, dto));
	}

	@DeleteMapping("/{Id}")
	public ResponseEntity<?> deleteEmployee(@PathVariable Integer empId) throws EmployeeException {
		System.out.println("in update emp " + empId);
		return ResponseEntity.ok(empServ.delete(empId));
	}

}
