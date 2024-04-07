package com.app.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.app.dto.ApiResponse;
import com.app.dto.EmployeeDto;
import com.app.empexception.EmployeeException;

@Service
public interface EmployeeService {
	
	List<EmployeeDto> getAllEmp();
	
	List<EmployeeDto> getAllEmp(int pageNumber,int pageSize);
	
	EmployeeDto getOneEmp(Integer id)throws EmployeeException;
	
	EmployeeDto add(EmployeeDto emp)throws EmployeeException;
	
	EmployeeDto update(Integer id ,EmployeeDto emp)throws EmployeeException;
	
	ApiResponse delete(Integer id)throws EmployeeException;

}
