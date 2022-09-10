package com.demo.order_management.ordermanagement.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.demo.order_management.ordermanagement.filter.JwtRequestFilter;



@Configuration
public class WebSecurity extends WebSecurityConfigurerAdapter{
	@Autowired
	private UserDetailsService myUserDetailsService;
	@Autowired
	private JwtRequestFilter jwtRequestFilter;

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(myUserDetailsService);
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder(12);
	}
//	
//	protected void configure(HttpSecurity httpSecurity) throws Exception {
//		 httpSecurity.csrf().disable()
//		 .authorizeHttpRequests().antMatchers("/hospital/v1/api/*").permitAll()
//		// all other requests need to be authenticated
//         .anyRequest().authenticated();
//		// Add a filter to validate the tokens with every request
//		//return httpSecurity.build();
//
//	}

//	@Bean
//	public PasswordEncoder passwordEncoder() {
//		return NoOpPasswordEncoder.getInstance();
//	}

	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		
		httpSecurity.csrf().disable().authorizeRequests()
//				.anyRequest().permitAll()
				.antMatchers("/auth/authenticate").permitAll()
				.antMatchers("/swagger-ui.html").permitAll()
//				.antMatchers("/hospital/v1/api/all/patients").permitAll()
				.antMatchers("/amazon/v1/api/customers/create").permitAll()
				.antMatchers("/amazon/v1/api/customers/**").hasAuthority("Customer")
//				.anyRequest().hasAuthority("Doctor")
				.and().exceptionHandling().and()
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		httpSecurity.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

	}

}
