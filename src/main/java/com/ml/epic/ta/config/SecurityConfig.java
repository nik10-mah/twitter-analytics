package com.ml.epic.ta.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import com.ml.epic.ta.auth.CustomizeAuthenticationSuccessHandler;
import com.ml.epic.ta.auth.TasAuthenticationFailureHandler;

// TODO: Auto-generated Javadoc
/**
 * The Class SecurityConfig : used to configure the security of App. Any wrong
 * access will redirect to login page..
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private AwsAuthenticationProvider authProvider;

	@Autowired
	private CustomizeAuthenticationSuccessHandler customizeAuthenticationSuccessHandler;

	@Autowired
	private TasAuthenticationFailureHandler tasAuthenticationFailureHandler;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.security.config.annotation.web.configuration.
	 * WebSecurityConfigurerAdapter#configure(org.springframework.security.config.
	 * annotation.authentication.builders.AuthenticationManagerBuilder)
	 */
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authProvider);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.security.config.annotation.web.configuration.
	 * WebSecurityConfigurerAdapter#configure(org.springframework.security.config.
	 * annotation.web.builders.HttpSecurity)
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// allow css, images , js, fonts and default urls
		http.authorizeRequests()
				.antMatchers("/", "/css/**", "/images/**", "/js/**", "/fonts/**", "/login", "/confirmSignup/execute")
				.permitAll().anyRequest().authenticated().and().formLogin()
				.successHandler(customizeAuthenticationSuccessHandler).failureHandler(tasAuthenticationFailureHandler)
				.loginPage("/login").permitAll().and().logout().logoutUrl("/logout").logoutSuccessUrl("/");
		/*
		 * .logoutRequestMatcher(new
		 * AntPathRequestMatcher("/logout")).logoutSuccessUrl("/login")
		 */ /* .invalidateHttpSession(true).permitAll() */;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.security.config.annotation.web.configuration.
	 * WebSecurityConfigurerAdapter#configure(org.springframework.security.config.
	 * annotation.web.builders.WebSecurity)
	 */
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/resources/**"); // #excluding resources
	}
}
