package com.ml.epic.ta.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import com.ml.epic.ta.service.impl.UserServiceImpl;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	
	@Autowired
    private AwsAuthenticationProvider authProvider;
	
	@Autowired
	private UserServiceImpl userServiceImpl;
 
    @Override
    protected void configure(
      AuthenticationManagerBuilder auth) throws Exception {
  
        auth.authenticationProvider(authProvider);
    }
 
    @Override
    protected void configure(HttpSecurity http) throws Exception {
       /* http.authorizeRequests().anyRequest().authenticated()
            .and()
            .httpBasic();*/
    	http.authorizeRequests()
		.antMatchers("/",  "/css/**", "/images/**", "/js/**", "/fonts/**")
		.permitAll().anyRequest().permitAll().and().formLogin().loginPage("/login").permitAll();
    }
    
    
    @Override
    public void configure(WebSecurity web) throws Exception {
	web.ignoring().antMatchers("/resources/**"); // #excluding resources

    }
}
