package com.demo.order_management.ordermanagement.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;


import com.demo.order_management.ordermanagement.dao.CustomerDao;
import com.demo.order_management.ordermanagement.model.Customer;

@Component
public class MyUserDetailsService implements UserDetailsService {
	@Autowired
	CustomerDao customerDao;
	

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		try {
			Customer customer = customerDao.findByEmail(username);
			List<GrantedAuthority> grantedAuths = new ArrayList<>();
			if (customer != null) {
				if (customer.getEmail().equals(username)) {
					grantedAuths.add(new SimpleGrantedAuthority("Patient"));
					return new User(username, customer.getPassword(), grantedAuths);
				} else {
					throw new UsernameNotFoundException("patient not found with username: " + username);
				}

			}  else {
				throw new UsernameNotFoundException("User not found with username: " + username);
			}
		} catch (Exception e) {
			System.out.println(e);
			throw new UsernameNotFoundException("Something went haywire!!");
		}
		
	}

}
