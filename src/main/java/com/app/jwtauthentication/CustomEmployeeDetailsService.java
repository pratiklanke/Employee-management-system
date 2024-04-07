package com.app.jwtauthentication;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.app.entities.Employee;
import com.app.repository.EmployeeRepo;

@Service
@Transactional
public class CustomEmployeeDetailsService implements UserDetailsService {
	
	@Autowired
	private EmployeeRepo empdao;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Employee user = empdao.findByEmail(email)
				.orElseThrow(() -> new UsernameNotFoundException("Email not found!!!!"));
		return new CustomEmployeeDetails(user);
	}

}
