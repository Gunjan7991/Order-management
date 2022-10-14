package com.demo.order_management.ordermanagement.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class WebSecurity extends WebSecurityConfigurerAdapter{
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.httpBasic().and().authorizeRequests()
		.antMatchers("/amazon/v1/api/customers")
		.hasRole ("admin").and().csrf().disable().formLogin().disable();
		
	}
	
	
//	@Override
//	protected void configure(AuthenticationManagerBuilder auth)throws Exception{
//		auth.inMemoryAuthentication()
//			.withUser("username")
//			.password("password")
//			.roles("USER"); 
//	}
//	@Bean
//	public PasswordEncoder getPasswordEncoder() {
//		return NoOpPasswordEncoder.getInstance();
//	}
}
