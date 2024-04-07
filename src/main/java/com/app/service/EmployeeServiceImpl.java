package com.app.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.app.dto.ApiResponse;
import com.app.dto.EmployeeDto;
import com.app.empexception.EmployeeException;
import com.app.entities.Employee;
import com.app.repository.EmployeeRepo;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private ModelMapper mapper;

	@Autowired
	private EmployeeRepo empdao;
	
	private BCryptPasswordEncoder encoder;
	

	@Override
	public List<EmployeeDto> getAllEmp() {
		List<Employee> empList = empdao.findAll();
		empList.forEach(e -> System.out.println(e));
		return empList.stream().map(e -> mapper.map(e, EmployeeDto.class)).collect(Collectors.toList());
	}

	@Override
	public List<EmployeeDto> getAllEmp(int pageNumber, int pageSize) {
		Pageable pageable = PageRequest.of(pageNumber, pageSize);
		List<Employee> empList = empdao.findAll(pageable).getContent();
		return empList.stream().map(e -> mapper.map(e, EmployeeDto.class)).collect(Collectors.toList());
	}

	@Override
	public EmployeeDto getOneEmp(Integer id) throws EmployeeException {
		Employee emp = empdao.findById(id).orElseThrow(() -> new EmployeeException("Employee Not Exist By This ID..!"));
		System.out.println(emp);
		return mapper.map(emp, EmployeeDto.class);
	}


	@Override
	public EmployeeDto add(EmployeeDto emp) throws EmployeeException {
		Employee e = mapper.map(emp, Employee.class);
		e.setPassword(encoder.encode(e.getPassword()));
		empdao.save(e);
		return mapper.map(e, EmployeeDto.class);
	}

	@Override
	public EmployeeDto update(Integer id, EmployeeDto emp) throws EmployeeException {
		Employee e = empdao.findById(id).orElseThrow(() -> new EmployeeException("Id not exist"));
		e.setPassword(encoder.encode(e.getPassword()));
		empdao.save(e);
		return mapper.map(e, EmployeeDto.class);
	}

	@Override
	public ApiResponse delete(Integer id) throws EmployeeException {
		if (empdao.existsById(id)) {
			empdao.deleteById(id);
			return new ApiResponse("Employee Deleted with id = " + id);
		}
		return new ApiResponse("Employee Not found with id = " + id);
	}

}
